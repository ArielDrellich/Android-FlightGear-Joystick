package com.example.flightgear_joystick;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.flightgear_joystick.view.Joystick;
import com.example.flightgear_joystick.view_model.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private final ViewModel viewModel;
    private Joystick joystick;
    private EditText ipBox;
    private EditText portBox;
    private Snackbar portErrorMessage;
    private Snackbar portSuccessMessage;

    public MainActivity() {
        this.viewModel = new ViewModel();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializers based on id.
        ipBox = (EditText) findViewById(R.id.input_ip);
        portBox = (EditText) findViewById(R.id.input_port);
        Button flyButton = (Button) findViewById(R.id.flyButton);
        SeekBar throttleBar = (SeekBar) findViewById(R.id.throttleBar);
        SeekBar rudderBar = (SeekBar) findViewById(R.id.rudderBar);
        joystick = (Joystick) findViewById(R.id.joystick_stick);
        View content = findViewById(android.R.id.content);
        portErrorMessage = Snackbar.make(content, "Error establishing connection.", 3000);
        portSuccessMessage = Snackbar.make(content, "Connection Successful!", 3000);

        flyButton.setOnClickListener(v -> {
            try {
                // hides the keyboard after clicking "Fly" button.
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                // opens socket based on information from text boxes.
                viewModel.startFlight(ipBox.getText().toString(), Integer.parseInt(portBox.getText().toString()));
                portSuccessMessage.show();
            } catch (Exception e) {
                // display error message on screen.
                portErrorMessage.show();
            }
        });

        joystick.setOnTouchListener((v, event) -> {
            double xPosition = (double) (joystick.getJoystickPosition().x - 200) / 200 ;
            double yPosition = - (double) (joystick.getJoystickPosition().y - 205) / 205;
            System.out.println("x is: " + joystick.getJoystickPosition().x);
            System.out.println("y is: " + joystick.getJoystickPosition().y);
            // If releasing, return joystick to center
            if (event.getAction() == MotionEvent.ACTION_UP) {
                xPosition = (double) (joystick.getJoystickStartPosition().x - 200) / 200;
                yPosition = - (double) (joystick.getJoystickStartPosition().y - 200) / 200;
            }
            viewModel.setAileron(xPosition);
            viewModel.setElevator(yPosition);
            return false;
        });


        // sets listener for moving the throttle seek bar.
        throttleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // calls function in ViewModel built for this
                viewModel.setThrottle(progress, seekBar);
            }

            // necessary overrides that aren't used
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // sets listener for moving the rudder seek bar.
        rudderBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // calls function in ViewModel built for this
                viewModel.setRudder(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}