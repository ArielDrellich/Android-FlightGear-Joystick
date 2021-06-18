package com.example.flightgear_joystick.model;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FGPlayer {
    Socket fg;
    PrintWriter out;
    ExecutorService executor;
    boolean socketOpen;

    public FGPlayer() {
        // Single thread ThreadPool used for sending data through the socket.
        executor = Executors.newSingleThreadExecutor();
        socketOpen = false;
    }

    /* In charge of opening a socket with the provided information. */
    public void openSocket(String host, int port) throws Exception {
        // Adds task to ThreadPool, then waits for it to finish before continuing so other processes (such as the seekbars and joystick) don't try to use a printwriter that isn't initialized.
        executor.submit(() -> {
            // for the case where we switch ports before closing the application.
            if (socketOpen) {
                fg.close();
                socketOpen = false;
            }
            fg = new Socket(host, port);
            socketOpen = true;
            // used for communicating with FG
            out = new PrintWriter(fg.getOutputStream(),true);
            return null;
        }).get();
    }

    /* Receives field of which parameter to update the FG and then sends the value to FG with the appropriate command. */
    public void sendDataToFG(String field, double value) {
        switch (field) {
            case "aileron":
                // lambda expression to add task to ThreadPool
                executor.execute(() -> {
                    out.print("set /controls/flight/aileron " + value + "\r\n");
                    // just in case ;)
                    out.flush();
                });
                break;

            case "elevator":
                executor.execute(() -> {
                    out.print("set /controls/flight/elevator " + value + "\r\n");
                    out.flush();
                });
                break;

            case "rudder":
                executor.execute(() -> {
                    out.print("set /controls/flight/rudder " + value + "\r\n");
                    out.flush();
                });
                break;

            case "throttle":
                executor.execute(() -> {
                    out.print("set /controls/engines/current-engine/throttle " + value + "\r\n");
                    out.flush();
                });
                break;
        }
    }
}
