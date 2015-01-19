package com.colouredtulips.object;

import com.badlogic.gdx.Gdx;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class BaseObject {
    private float curX;
    private float curY;
    private float accelSpeed=0;

    private float oriX;
    private float oriY;

    private float oriScale;

    private boolean isTouched;
    private boolean isDragged;
    private boolean isReleased;

    private float moveToTimer =0;
    private float moveToStartX=0;
    private float moveToStartY=0;
    private float moveToEndX=0;
    private float moveToEndY=0;

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCurY() {
        return curY;
    }

    public void setCurY(float curY) {
        this.curY = curY;
    }

    public void setCurPos(float x, float y) {
        this.curX = x;
        this.curY = y;
    }

    public float getAccelSpeed() {
        return accelSpeed;
    }

    public void setAccelSpeed(float accelSpeed) {
        this.accelSpeed = accelSpeed;
    }

    public float getX(){return 0;}
    public float getY(){return 0;}
    public void setPosition(float x, float y) {}

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean isTouched) {
        this.isTouched = isTouched;
    }

    public boolean isDragged() {
        return isDragged;
    }

    public void setDragged(boolean isDragged) {
        this.isDragged = isDragged;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean isReleased) {
        this.isReleased = isReleased;
    }

    public float getMoveToTimer() {
        return moveToTimer;
    }

    public void setMoveToTimer(float moveToTimer) {
        this.moveToTimer = moveToTimer;
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
        this.oriX = x;
        this.oriY = y;
    }

    public float getOriScale() {
        return oriScale;
    }

    public void setOriScale(float oriScale) {
        this.oriScale = oriScale;
    }

    public void moveTo(float toX, float toY, float t) {
        if (moveToTimer >t) {
            moveToTimer =0;
        }
        if (moveToTimer ==0) {
            moveToStartX = getX();
            moveToStartY = getY();
            moveToEndX = toX;
            moveToEndY = toY;
        }
        moveToTimer += Gdx.graphics.getDeltaTime();
        float percent = moveToTimer / t;

        setPosition(moveToStartX + (moveToEndX - moveToStartX) * percent,
                moveToStartY + (moveToEndY - moveToStartY) * percent);
//        System.out.println(moveToTimer+":"+percent+":"+moveToStartX+":"+moveToStartY+":"+moveToEndX+":"+moveToEndY+":"+getX()+":"+getY());
    }
}
