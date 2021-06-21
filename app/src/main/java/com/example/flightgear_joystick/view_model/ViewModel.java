package com.example.flightgear_joystick.view_model;

import android.widget.SeekBar;

import com.example.flightgear_joystick.model.FGPlayer;

import java.io.IOException;

public class ViewModel {
    private FGPlayer model;
    boolean portOpen;

    /* Constructor */
    public ViewModel() {
        model = new FGPlayer();
        portOpen = false;
    }

    /* Sends received ip and port to FGPlayer to open socket, and if succeeds sets local variable
     * to true to let the following functions know they can interact with the socket. */
    public void startFlight(String host, int port) throws Exception {
            model.openSocket(host, port);
            portOpen = true;
    }

    /* If the port is open, sends aileron position to the socket via the model. */
    public void setAileron(double a) {
        if (portOpen)
            model.sendDataToFG("aileron", a);
    }

    /* If the port is open, sends elevator position to the socket via the model. */
    public void setElevator(double e) {
        if (portOpen)
            model.sendDataToFG("elevator", e);
    }

    /* If the port is open, calculates throttle position and sends to the socket via the model,
     * otherwise keeps seekbar position at 0. */
    public void setThrottle(int t, SeekBar seekBar) {
        if (portOpen)
            model.sendDataToFG("throttle", (double)t/1000);
        else
            seekBar.setProgress(0);
    }

    /* If the port is open, calculates rudder position to the socket via the model. */
    public void setRudder(int r) {
        if (portOpen)
            model.sendDataToFG("rudder", (double)(r-1000)/1000);
    }
    /* Closes the socket. */
    public void stopSocket() throws IOException {
        model.stopSocket();
    }
}
