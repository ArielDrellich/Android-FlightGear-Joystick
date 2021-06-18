package com.example.flightgear_joystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragEvent;
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
        // initializers based on id.
        ipBox = (EditText) findViewById(R.id.input_ip);
        portBox = (EditText) findViewById(R.id.input_port);
        flyButton = (Button) findViewById(R.id.flyButton);
        throttleBar = (SeekBar) findViewById(R.id.throttleBar);
        rudderBar = (SeekBar) findViewById(R.id.rudderBar);
        joystick = (Joystick) findViewById(R.id.joystick_stick);
        portErrorMessage = Snackbar.make(findViewById(android.R.id.content), "Error opening port", 3000);

        flyButton.setOnClickListener(v -> {
            try {
                // hides the keyboard after clicking "Fly" button.
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                // opens socket based on information from text boxes.
                viewModel.startFlight(ipBox.getText().toString(), Integer.parseInt(portBox.getText().toString()));
            } catch (Exception e) {
                // display error message on screen.
                portErrorMessage.show();
            }
        });
        //System.out.println("Enter To Listiner");
//        joystick.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                System.out.println("Enter To Joy Listiner");
//                double xPosition = ((double) event.getX() - 200) / 200 ;
//                double yPosition = ((double) event.getY() - 200) / 200;
//                viewModel.setAileron(xPosition);
//                viewModel.setElevator(yPosition);
//                return false;
//            }
//        });

        joystick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("Enter To Joy Listiner");
                double xPosition = ((double) event.getX() - 200) / 200 ;
                double yPosition = (200 - (double) event.getY()) / 200;
                viewModel.setAileron(xPosition);
                viewModel.setElevator(yPosition);
                return false;
            }
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




    /*this.joystick.onChange=(a,e)->{
        this.viewModel.setAileron(a);
        this.viewModel.setElevator(e);
    }*/

}