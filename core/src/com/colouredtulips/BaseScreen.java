package com.colouredtulips;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
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
//    public SkeletonRenderer renderer;
//    public PolygonSpriteBatch batch;

    public OrthographicCamera camera;
    public Viewport viewport;

    public CustomSprite midBg;
    public CustomSprite bg;

    public float xAccel=0, yAccel=0;


    public Stage stage;
    public Group farBgGroup;
    public Group midBgGroup;
    public Group bgGroup;
    public Group foregroundGroup;

    public float farBgAccelSpeed=0;
    public float midBgAccelSpeed =0;
    public float bgAccelSpeed =0;
    public float foregroundAccelSpeed=0;

    public BaseScreen() {
        float ratio = (float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        if (ratio<=1.4)
            Global.worldVirtualWidth=Constants.WORLD_VIRTUAL_WIDTH_1p3;
        else
            Global.worldVirtualWidth=Constants.WORLD_VIRTUAL_WIDTH_1p7;

        Global.batch = new PolygonSpriteBatch();
        Global.renderer = new SkeletonRenderer();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(Global.worldVirtualWidth,Global.worldVirtualHeight,camera);
        viewport.apply();
        camera.position.set(Global.worldVirtualWidth / 2, Global.worldVirtualHeight / 2, 0);

        stage = new Stage(viewport);

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
        camera.position.set(Global.worldVirtualWidth/2,Global.worldVirtualHeight/2,0);
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
        for (Texture texture : Global.textureList)
            texture.dispose();
        Global.textureList = new ArrayList<Texture>();
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
//    public void drawBg() {
//        if (midBg!=null)
//            midBg.getSprite().draw(batch);
//        if (bg!=null)
//            bg.getSprite().draw(batch);
//    }
    public void moveByAcceleration() {
        if (midBgGroup !=null) {
            MoveToAction moveToAction = new MoveToAction();
            moveToAction.setPosition(xAccel * midBgAccelSpeed, yAccel * midBgAccelSpeed);
            moveToAction.setDuration(Constants.WORLD_ACCELEROMETER_INTERVAL);
            midBgGroup.addAction(moveToAction);
        }
        if (bgGroup !=null) {
            MoveToAction moveToAction = new MoveToAction();
            moveToAction.setPosition(xAccel * bgAccelSpeed, yAccel * bgAccelSpeed);
            moveToAction.setDuration(Constants.WORLD_ACCELEROMETER_INTERVAL);
            bgGroup.addAction(moveToAction);
        }
        if (foregroundGroup!=null) {
            MoveToAction moveToAction = new MoveToAction();
            moveToAction.setPosition(xAccel * foregroundAccelSpeed, yAccel * foregroundAccelSpeed);
            moveToAction.setDuration(Constants.WORLD_ACCELEROMETER_INTERVAL);
            foregroundGroup.addAction(moveToAction);
        }
    }
}
