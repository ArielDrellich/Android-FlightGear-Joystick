package com.example.flightgear_joystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flightgear_joystick.view.Joystick;
import com.example.flightgear_joystick.view_model.ViewModel;

public class MainActivity extends AppCompatActivity {
    private Joystick joystick;
    private ViewModel viewModel;
    EditText ipBox;
    EditText portBox;
    Button flyButton;

    public MainActivity() {
        //this.joystick = new Joystick();
        this.viewModel = new ViewModel();
//        ipBox = (EditText) findViewById(R.id.input_ip);
//        portBox = (EditText) findViewById(R.id.input_port);
//        flyButton = (Button) findViewById(R.id.flyButton);
//        flyButton.setOnClickListener(v -> {
//            String IP = ipBox.getText().toString();
//            int port = Integer.parseInt(portBox.getText().toString());
//            viewModel.getModel().sendDataToFG(IP, port);
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipBox = (EditText) findViewById(R.id.input_ip);
        portBox = (EditText) findViewById(R.id.input_port);
        flyButton = (Button) findViewById(R.id.flyButton);
        flyButton.setOnClickListener(v -> {
            String IP = ipBox.getText().toString();
            int port = Integer.parseInt(portBox.getText().toString());
//            System.out.println( "port: " + port + " IP: " + IP);
            viewModel.getModel().sendDataToFG(IP, port);
        });
    }




    /*this.joystick.onChange=(a,e)->{
        this.viewModel.setAileron(a);
        this.viewModel.setElevator(e);
    }*/

}