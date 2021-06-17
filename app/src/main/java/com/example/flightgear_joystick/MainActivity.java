package com.example.flightgear_joystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.flightgear_joystick.view.Joystick;
import com.example.flightgear_joystick.view_model.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private Joystick joystick;
    private ViewModel viewModel;
    EditText ipBox;
    EditText portBox;
    Button flyButton;
    Snackbar portErrorMessage;
    SeekBar throttleBar;
    SeekBar rudderBar;

    public MainActivity() {
        this.viewModel = new ViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipBox = (EditText) findViewById(R.id.input_ip);
        portBox = (EditText) findViewById(R.id.input_port);
        flyButton = (Button) findViewById(R.id.flyButton);
        throttleBar = (SeekBar) findViewById(R.id.throttleBar);
        rudderBar = (SeekBar) findViewById(R.id.rudderBar);
        joystick = (Joystick) findViewById(R.id.joystick_stick);
        portErrorMessage = Snackbar.make(findViewById(android.R.id.content), "Error opening port", 3000);
        flyButton.setOnClickListener(v -> {
            try {
                // hides the keyboard after clicking Fly button
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                // opens socket
                viewModel.startFlight(ipBox.getText().toString(), Integer.parseInt(portBox.getText().toString()));
            } catch (Exception e) {
                portErrorMessage.show();
                System.out.println("Exception: " + e);
            }
        });

        throttleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.setThrottle(progress, seekBar);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        rudderBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.setRudder(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }




    /*this.joystick.onChange=(a,e)->{
        this.viewModel.setAileron(a);
        this.viewModel.setElevator(e);
    }*/

}