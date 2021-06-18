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
    private final int startX = 200;
    private final int startY = 215;
    private final int baseDiam = 700;
    private final int stickDiam = 300;
    private final double circleBoundary = 200;
    private int stickXPos;
    private int stickYPos;

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.stickXPos = startX;
        this.stickYPos = startY;
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
        // relative touch location.
        int xPosition = (int) event.getX() - (stickDiam/2);
        int yPosition = (int) event.getY() - (stickDiam/2);
        int action = event.getAction();
        if ( action == MotionEvent.ACTION_MOVE ) {
            // if within the joystick base radius.
            if (Math.sqrt(Math.pow(xPosition - startX, 2) + Math.pow(yPosition - startY, 2) ) < circleBoundary) {
                stickXPos = xPosition;
                stickYPos = yPosition;
            } else {
                // find closest point on circle to touch position.
                double xDist = xPosition - startX;
                double yDist = yPosition - startY;
                int dist = (int) Math.sqrt(xDist*xDist + yDist*yDist);
                stickXPos = (int) ((double) startX + xDist / dist * (double) circleBoundary);
                stickYPos = (int) ((double) startY + yDist / dist * (double) circleBoundary);
            }
        }
        // when releasing, returns stick to center.
        else if (action == MotionEvent.ACTION_UP) {
            this.stickXPos = startX;
            this.stickYPos = startY;
        }

        // tells the screen to update.
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /* draws the joystick base sprite. */
        Drawable base = getResources().getDrawable(R.drawable.base2);
        base.setBounds(0, 0, baseDiam, baseDiam);
        base.draw(canvas);

        /* draws the joystick stick sprite. */
        Drawable stick = getResources().getDrawable(R.drawable.stick_v2);
        // based on prior calculations.
        stick.setBounds(stickXPos, stickYPos, stickDiam + stickXPos, stickDiam + stickYPos);
        stick.draw(canvas);
    }

}
