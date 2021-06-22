package com.example.flightgear_joystick.model;

import java.io.IOException;

public interface IFGPlayer {

    public void openSocket(String host, int port) throws Exception;

    public void sendDataToFG(String field, double value);

    public void stopSocket() throws IOException;
}
