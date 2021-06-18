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
    private int stickDiam;
    private double circleBoundary;

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.stickXPos = startX;
        this.stickYPos = startY;
        this.circleBoundary = 200;
        this.stickDiam = 300;
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
        int xPosition = (int) event.getX() - (stickDiam/2);
        int yPosition = (int) event.getY() - (stickDiam/2);
        int action = event.getAction();
        if ( action == MotionEvent.ACTION_MOVE ) {
            //event.getActionMasked();
            if (Math.sqrt(Math.pow(xPosition - startX, 2) + Math.pow(yPosition - startY, 2) ) < circleBoundary) {
                stickXPos = xPosition;
                stickYPos = yPosition;
            } else {
                double xDist = xPosition - startX;
                double yDist = yPosition - startY;
                int dist = (int) Math.sqrt(xDist*xDist + yDist*yDist);
                stickXPos = (int) ((double) startX + xDist / dist * (double) circleBoundary);
                stickYPos = (int) ((double) startY + yDist / dist * (double) circleBoundary);
            }
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
        stick.setBounds(stickXPos, stickYPos, stickDiam + stickXPos, stickDiam + stickYPos);
        stick.draw(canvas);
    }

}
