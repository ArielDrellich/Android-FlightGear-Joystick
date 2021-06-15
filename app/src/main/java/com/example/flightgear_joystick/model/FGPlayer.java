package com.example.flightgear_joystick.model;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketOption;
import java.net.UnknownHostException;

public class FGPlayer {
    Socket fg;

    public void startConnection(String host, int port) {
        try {
            fg = new Socket("localhost", 5400);
//            fg = new Socket(host, port);
        } catch (Exception e) {
            System.out.println("Error opening socket");
            e.printStackTrace();
        }
    }

}
