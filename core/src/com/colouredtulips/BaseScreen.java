package com.colouredtulips;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colouredtulips.object.CustomSprite;
import com.colouredtulips.object.SkeletonAnimation;
import com.esotericsoftware.spine.SkeletonRenderer;

import java.util.ArrayList;

/**
 * Created by rizkisunaryo on 1/14/15.
 */
public class BaseScreen implements Screen,InputProcessor,ApplicationListener {
    public PolygonSpriteBatch batch;
    public SkeletonRenderer renderer;

    public OrthographicCamera camera;
    Viewport viewport;
    public Vector3 stageVector;

    public CustomSprite midBg;
    public CustomSprite bg;
    public CustomSprite foreground;

    private float xAccel=0, yAccel=0;
    private float prevXAccel=0, prevYAccel=0;

    public float prevDraggedX=0, prevDraggedY=0;

    public BaseScreen() {
        float ratio = (float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        if (ratio<=1.4)
            Global.worldVirtualWidth=Constants.WORLD_VIRTUAL_WIDTH_1p3;
        else
            Global.worldVirtualWidth=Constants.WORLD_VIRTUAL_WIDTH_1p7;
        Global.widthPer = Global.worldVirtualWidth/100.0f;

        batch = new PolygonSpriteBatch();
        renderer = new SkeletonRenderer();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Global.worldVirtualWidth,Global.worldVirtualHeight,camera);
        viewport.apply();
        camera.position.set(Global.worldVirtualWidth / 2, Global.worldVirtualHeight / 2, 0);

        stageVector = new Vector3();

        Gdx.input.setInputProcessor(this);
    }

    // Screen methods
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.position.set(Global.worldVirtualWidth/2,Constants.WORLD_VIRTUAL_HEIGHT/2,0);
    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (CustomSprite customSprite : Global.customSpriteList) {
            customSprite.getSprite().getTexture().dispose();
//            System.out.println("Rizki Sunaryo");
        }
        batch.dispose();
        Global.customSpriteList=new ArrayList<CustomSprite>();
        Global.skeletonAnimationList=new ArrayList<SkeletonAnimation>();
    }

    // InputProcessor methods
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void updateAnimations() {
        for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
            skeletonAnimation.getState().update(Gdx.graphics.getDeltaTime());
        }
    }
    public void applyAnimations() {
        for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
            skeletonAnimation.applyAnimation();
        }
    }
    public void moveByAcceleration() {
        if (xAccel!= -Gdx.input.getAccelerometerY()
                || yAccel!= Gdx.input.getAccelerometerX()) {
            prevXAccel=xAccel; prevYAccel=yAccel;

            float maxXAccel = Constants.MAX_ACCELERATED_X/Constants.MAX_ACCELERATION_SPEED;
            float minXAccel = Constants.MIN_ACCELERATED_X/Constants.MAX_ACCELERATION_SPEED;
            float maxYAccel = Constants.MAX_ACCELERATED_Y/Constants.MAX_ACCELERATION_SPEED;
            float minYAccel = Constants.MIN_ACCELERATED_Y/Constants.MAX_ACCELERATION_SPEED;

            xAccel= -Gdx.input.getAccelerometerY()>maxXAccel? maxXAccel :
                    -Gdx.input.getAccelerometerY()<minXAccel? minXAccel : -Gdx.input.getAccelerometerY();
            yAccel= Gdx.input.getAccelerometerX()>maxYAccel? maxYAccel :
                    Gdx.input.getAccelerometerX()<minYAccel? minYAccel : Gdx.input.getAccelerometerX();

            if (Math.abs(xAccel-prevXAccel)<=0.35)
                xAccel=prevXAccel;
            if (Math.abs(yAccel-prevYAccel)<=0.35)
                yAccel=prevYAccel;

//            System.out.println(prevXAccel+":"+xAccel+":"+prevYAccel+":"+yAccel);

//            System.out.println(yAccel+":"+maxYAccel+":"+minYAccel+":"+Gdx.input.getAccelerometerX());

            for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
                skeletonAnimation.setMoveToTimer(0);
            }

            for (CustomSprite customSprite : Global.customSpriteList) {
                customSprite.setMoveToTimer(0);
            }

        }

        for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
            if (skeletonAnimation.getAccelSpeed()!=0 && !skeletonAnimation.isTouched())
                skeletonAnimation.moveTo(skeletonAnimation.getCurX()+ xAccel*skeletonAnimation.getAccelSpeed(),
                        skeletonAnimation.getCurY()+ yAccel *skeletonAnimation.getAccelSpeed(),
                        Constants.ACCELERATION_INTERVAL);
        }

        for (CustomSprite customSprite : Global.customSpriteList) {
            if (customSprite.getAccelSpeed()!=0 && !customSprite.isTouched())
                customSprite.moveTo(customSprite.getCurX()+ xAccel*customSprite.getAccelSpeed(),
                        customSprite.getCurY()+ yAccel *customSprite.getAccelSpeed(),
                        Constants.ACCELERATION_INTERVAL);
        }
    }
}
