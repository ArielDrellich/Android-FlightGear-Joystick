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

    public FGPlayer() {
            executor = Executors.newSingleThreadExecutor();
    }

    public void openSocket(String host, int port) throws Exception {
        System.out.println("Ip: "+host+" port: "+port);
//        executor.execute(() -> {
//            try {
//                fg = new Socket(host, port);//////////////////////////////////////figure out how to close later
//                out = new PrintWriter(fg.getOutputStream(),true);
//                System.out.println("Socket opened successfully");
//            }
//            catch (Exception e) {
//                System.out.println("Failed to open socket. Exception: " + e);
//            }
//        });
//        Future<?> f = executor.submit(() -> {
//            fg = new Socket(host, port);//////////////////////////////////////figure out how to close later
//            out = new PrintWriter(fg.getOutputStream(),true);
//            System.out.println("Socket opened successfully");
//            return null;
//        });
        executor.submit(() -> {
            fg = new Socket(host, port);//////////////////////////////////////figure out how to close later
            out = new PrintWriter(fg.getOutputStream(),true);
            System.out.println("Socket opened successfully");
            return null;
        }).get();

//        f.get();
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
