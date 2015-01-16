package com.colouredtulips.object;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class BaseObject extends Actor{
    private boolean isTouched;
    private boolean isMoved;
    private boolean isDragged;

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean isTouched) {
        this.isTouched = isTouched;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean isMoved) {
        this.isMoved = isMoved;
    }

    public boolean isDragged() {
        return isDragged;
    }

    public void setDragged(boolean isDragged) {
        this.isDragged = isDragged;
    }
    //    private float oriX;
//    private float oriY;
//    private float accelSpeed=0;
//
//    public float getAccelSpeed() {
//        return accelSpeed;
//    }
//
//    public void setAccelSpeed(float accelSpeed) {
//        this.accelSpeed = accelSpeed;
//    }
//
//    public float getOriX() {
//        return oriX;
//    }
//
//    public void setOriX(float oriX) {
//        this.oriX = oriX;
//    }
//
//    public float getOriY() {
//        return oriY;
//    }
//
//    public void setOriY(float oriY) {
//        this.oriY = oriY;
//    }
//
//    public void setOriPos(float x, float y) {
//        oriX=x; oriY=y;
//    }
}
