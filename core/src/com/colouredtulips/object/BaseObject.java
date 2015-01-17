package com.colouredtulips.object;

import com.badlogic.gdx.Gdx;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class BaseObject {
    private float curX;
    private float curY;
    private float curAccelStartX;
    private float curAccelStartY;
    private float moveToTimer =0;
    private float moveToStartX=0;
    private float moveToStartY=0;
    private float moveToEndX=0;
    private float moveToEndY=0;
//    private float curAcceledX;
//    private float curAcceledY;
    private float accelSpeed=0;

//    public float getCurAccelStartX() {
//        return curAccelStartX;
//    }
//
//    public void setCurAccelStartX(float curAccelStartX) {
//        this.curAccelStartX = curAccelStartX;
//    }
//
//    public float getCurAccelStartY() {
//        return curAccelStartY;
//    }
//
//    public void setCurAccelStartY(float curAccelStartY) {
//        this.curAccelStartY = curAccelStartY;
//    }

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

//    public float getCurAcceledX() {
//        return curAcceledX;
//    }
//
//    public void setCurAcceledX(float curAcceledX) {
//        this.curAcceledX = curAcceledX;
//    }
//
//    public float getCurAcceledY() {
//        return curAcceledY;
//    }
//
//    public void setCurAcceledY(float curAcceledY) {
//        this.curAcceledY = curAcceledY;
//    }
//
//    public void setCurAcceledPos(float x, float y) {
//        this.curAcceledX = x;
//        this.curAcceledY = y;
//    }

    public float getAccelSpeed() {
        return accelSpeed;
    }

    public void setAccelSpeed(float accelSpeed) {
        this.accelSpeed = accelSpeed;
    }

    public float getX(){return 0;}
    public float getY(){return 0;}
    public void setPosition(float x, float y) {}

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
        System.out.println(moveToTimer+":"+percent+":"+moveToStartX+":"+moveToStartY+":"+moveToEndX+":"+moveToEndY+":"+getX()+":"+getY());
    }


}
