package com.example.flightgear_joystick.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.flightgear_joystick.R;

public class Joystick extends View {
    private int boundCircleX;
    private int boundCircleY;
    private int boundCircleRadius;

//    public Joystick(Context context) {
//        super(context);
//        this.boundCircleX = 10;
//        this.boundCircleY = 10;
//        this.boundCircleRadius = 10;
//    }

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.boundCircleX = 10;
        this.boundCircleY = 10;
        this.boundCircleRadius = 10;
    }

    public Joystick(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);
        int xPosition = (int)event.getX();
        int yPosition = (int)event.getY();
        int action = event.getAction();
        if ( action == MotionEvent.ACTION_MOVE ) {


        }
        else if (action == MotionEvent.ACTION_UP) { //when client off the phone - the circle return to center

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawCircle();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));
        canvas.drawCircle(this.boundCircleX, this.boundCircleY, (float)this.boundCircleRadius, paint);


//        (getResources().getDrawable(R.drawable.stick)).draw(canvas);
    }
}
