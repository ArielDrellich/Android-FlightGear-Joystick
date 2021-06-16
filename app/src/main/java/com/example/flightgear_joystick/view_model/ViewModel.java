package com.example.flightgear_joystick.view_model;

import android.widget.SeekBar;

import com.example.flightgear_joystick.model.FGPlayer;
import com.google.android.material.snackbar.Snackbar;

public class ViewModel {
    private FGPlayer model;
    boolean portOpen;

    public ViewModel() {
        model = new FGPlayer();
        portOpen = false;
    }

    public void startFlight(String host, int port) throws Exception {
            model.openSocket(host, port);
            portOpen = true;
    }

    public void setAileron(int a) {
        if (portOpen)
            model.sendDataToFG("aileron", (double)a/1000);
    }

    public void setElevator(int e) {
        if (portOpen)
            model.sendDataToFG("elevator", (double)e/1000);
    }

    public void setThrottle(int t, SeekBar seekBar) {
//        System.out.println("throttle value: " + (double)t/1000);
        if (portOpen)
            model.sendDataToFG("throttle", (double)t/1000);
        else
            seekBar.setProgress(0);
    }

    public void setRudder(int r) {
        if (portOpen)
            model.sendDataToFG("rudder", (double)r/1000);
    }
}
