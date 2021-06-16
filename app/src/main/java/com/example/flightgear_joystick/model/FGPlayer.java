package com.example.flightgear_joystick.model;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FGPlayer {
    Socket fg;
    PrintWriter out;
    ExecutorService executor;

    public FGPlayer() {
//        try {
//            fg = new Socket(host, port);
//            out = new PrintWriter(fg.getOutputStream(),true);
            executor = Executors.newSingleThreadExecutor();
//        } catch (Exception e) {
//            System.out.println("Error opening socket: " + e);
//        }
    }

    public void openSocket(String host, int port) throws Exception {
        fg = new Socket(host, port);///////figure out how to close later
        out = new PrintWriter(fg.getOutputStream(),true);
    }

    public void sendDataToFG(String field, double value) {
        switch (field) {
            case "aileron":
                executor.execute(() -> {
                    out.print("set /controls/flight/aileron " + value + "\r\n");
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
