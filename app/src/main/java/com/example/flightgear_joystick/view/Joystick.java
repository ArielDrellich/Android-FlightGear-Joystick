package com.example.flightgear_joystick.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.flightgear_joystick.R;

public class Joystick extends View {
    private final int startX = 215;
    private final int startY = 210;
    private int stickXPos;
    private int stickYPos;
    private int boundCircleRadius;

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.stickXPos = startX;
        this.stickYPos = startY;
        this.boundCircleRadius = 110;
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
            //event.getActionMasked();
            this.stickXPos = xPosition;
            this.stickYPos = yPosition;
        }
        else if (action == MotionEvent.ACTION_UP) { //when client off the phone - the circle return to center
            this.stickXPos = startX;
            this.stickYPos = startY;
        }

        // tells the screen to update
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawCircle();

//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setAntiAlias(true);
//        paint.setColor(Color.parseColor("#ff0000"));
//        canvas.drawCircle(this.boundCircleX, this.boundCircleY, (float)this.boundCircleRadius, paint);

//        Drawable stick = getResources().getDrawable(R.drawable.stick);
        Drawable stick = getResources().getDrawable(R.drawable.stick_v2);
        stick.setBounds(stickXPos, stickYPos, 300 + stickXPos, 300 + stickYPos);
        stick.draw(canvas);
    }
}
