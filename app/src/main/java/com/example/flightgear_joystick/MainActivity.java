package com.example.flightgear_joystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.flightgear_joystick.view.Joystick;
import com.example.flightgear_joystick.view_model.ViewModel;

public class MainActivity extends AppCompatActivity {
    private Joystick joystick;
    private ViewModel viewModel;

    public MainActivity() {
        //this.joystick = new Joystick();
        this.viewModel = new ViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*this.joystick.onChange=(a,e)->{
        this.viewModel.setAileron(a);
        this.viewModel.setElevator(e);
    }*/

}