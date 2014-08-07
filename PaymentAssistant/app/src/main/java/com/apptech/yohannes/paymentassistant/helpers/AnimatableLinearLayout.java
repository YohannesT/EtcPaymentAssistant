package com.apptech.yohannes.paymentassistant.helpers;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.LinearLayout;

/**
 * Created by Yohannes on 7/29/2014.
 */
    public class AnimatableLinearLayout extends LinearLayout {

    public AnimatableLinearLayout(Context context) {
        super(context);
    }

    public AnimatableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatableLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public float getXFraction() {
        if(getWidth() == 0) return getX();
        return getX() / getWidth(); // TODO: guard divide-by-zero
    }

    public void setXFraction(float xFraction) {
        // TODO: cache width
        final int width = getWidth();
        setX((width > 0) ? (xFraction * width) : -9999);
    }

    public float getRotateX()
    {
        return getRotationX();
    }

    public void setRotateX(float x)
    {
         setRotationX(x);
    }

    public float getRotateY()
    {
        return getRotationY();
    }

    public void setRotateY(float y)
    {
        setRotationY(y);
    }

    public float getRotateZ()
    {
        return getRotation();
    }

    public void setRotateZ(float z)
    {
        setRotation(z);
    }
    }

