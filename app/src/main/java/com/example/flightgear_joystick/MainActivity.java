package com.example.flightgear_joystick;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.flightgear_joystick.view.Joystick;
import com.example.flightgear_joystick.view_model.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final ViewModel viewModel;
    private Joystick joystick;
    private EditText ipBox;
    private EditText portBox;
    private Snackbar portErrorMessage;
    private Snackbar portSuccessMessage;
    private final int messageDuration = 3000;

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
        joystick = (Joystick) findViewById(R.id.joystick);
        View content = findViewById(android.R.id.content);
        portErrorMessage = Snackbar.make(content, "Error establishing connection.", messageDuration);
        portSuccessMessage = Snackbar.make(content, "Connection Successful!", messageDuration);

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
            int offset = joystick.getStickRange();
            double xPosition = (double) (joystick.getJoystickPosition().x - offset) / offset ;
            double yPosition = - (double) (joystick.getJoystickPosition().y - offset) / offset;
            // If releasing, return joystick to center
            if (event.getAction() == MotionEvent.ACTION_UP) {
                xPosition = (double) (joystick.getJoystickStartPosition().x - offset) / offset;
                yPosition = - (double) (joystick.getJoystickStartPosition().y - offset) / offset;
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

        /* Sets the joystick positions as soon as the layout is finished. The purpose is to avoid any sort of "magic numbers". */
        joystick.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // get the layout of the joystick.
                Rect joystickBasePosition = new Rect();
                Joystick joystick = (Joystick) findViewById(R.id.joystick);
                joystick.getDrawingRect(joystickBasePosition);
                // send the range to joystick.
                joystick.setPositions(joystickBasePosition.right - joystickBasePosition.left);
                //remove now useless listener.
                joystick.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            viewModel.stopSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}