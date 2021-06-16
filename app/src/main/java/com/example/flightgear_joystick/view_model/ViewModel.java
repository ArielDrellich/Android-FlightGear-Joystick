package com.example.flightgear_joystick.view_model;

import com.example.flightgear_joystick.model.FGPlayer;

public class ViewModel {
    private FGPlayer model;
//    private String host;
//    private int port;

    public ViewModel() {
        model = new FGPlayer();
    }

    public FGPlayer getModel() {
        return model;
    }

    public void startFlight(String host, int port) {
        try {
//            model = new FGPlayer(host,port);
            model.openSocket(host, port);
        } catch (Exception e) {
            ///write error message on screen
        }
    }

    public void setAileron(int a) {
        model.sendDataToFG("aileron", a);
    }

    public void setElevator(int e) {
        model.sendDataToFG("elevator", e);
    }

    public void setThrottle(int t) {
        model.sendDataToFG("throttle", t);
    }

    public void setRudder(int r) {
        model.sendDataToFG("rudder", r);
    }
}
