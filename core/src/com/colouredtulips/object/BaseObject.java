package com.colouredtulips.object;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class BaseObject {
    private float oriX;
    private float oriY;
    private float accelSpeed=0;

    public float getAccelSpeed() {
        return accelSpeed;
    }

    public void setAccelSpeed(float accelSpeed) {
        this.accelSpeed = accelSpeed;
    }

    public float getOriX() {
        return oriX;
    }

    public void setOriX(float oriX) {
        this.oriX = oriX;
    }

    public float getOriY() {
        return oriY;
    }

    public void setOriY(float oriY) {
        this.oriY = oriY;
    }

    public void setOriPos(float x, float y) {
        oriX=x; oriY=y;
    }
}
