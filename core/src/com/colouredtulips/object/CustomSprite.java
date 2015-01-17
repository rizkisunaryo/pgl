package com.colouredtulips.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.colouredtulips.Global;
import com.colouredtulips.util.FileUtil;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class CustomSprite extends BaseObject{
    private Sprite sprite;

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public CustomSprite(String fileName, float x, float y, float scale, float accelSpeed) {
        sprite = new Sprite(new Texture(Gdx.files.internal(FileUtil.getTextureFile(fileName))));
        sprite.setPosition(x,y);
        sprite.setScale(scale);
        setCurPos(x, y);
        setAccelSpeed(accelSpeed);

        Global.customSpriteList.add(this);
    }
    public CustomSprite(String fileName, float x, float y, float scale) {
        this(fileName,x,y,scale,0);
    }
    public CustomSprite(String fileName, float x, float y) {
        this(fileName,x,y,1);
    }
    public CustomSprite(String fileName) {
        this(fileName,0,0);
    }

    public float getX() {
        return sprite.getX();
    }
    public float getY() {
        return sprite.getY();
    }
    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }
    public float getWidth() {
        return sprite.getWidth();
    }
    public float getHeight() {
        return sprite.getHeight();
    }
    public void setScale(float scale) {
        sprite.setScale(scale);
    }
}
