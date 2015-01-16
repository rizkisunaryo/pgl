package com.colouredtulips.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.colouredtulips.Global;
import com.colouredtulips.util.FileUtil;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class CustomSprite extends BaseObject{
//    private Texture texture;
    TextureRegion textureRegion;
//    private float x;
//    private float y;

    public CustomSprite(String fileName, float x, float y, float scale) {
//        this.x=x;
//        this.y=y;

        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(FileUtil.getTextureFile(fileName))));
//        setOriPos(x, y);

        setScale(scale);
        setBounds(x, y, textureRegion.getTexture().getWidth(), textureRegion.getTexture().getHeight());

        Global.textureList.add(textureRegion.getTexture());
    }
    public CustomSprite(String fileName, float x, float y) {
        this(fileName,x,y,1);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
    }
}
