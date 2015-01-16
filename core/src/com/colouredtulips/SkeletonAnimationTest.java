package com.colouredtulips;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

import pgl.screen.Classroom;

public class SkeletonAnimationTest extends Game {
    OrthographicCamera camera;
    SpriteBatch batch;
    SkeletonRenderer renderer;
    TextureAtlas atlas;
    Skeleton skeleton;
    AnimationState state;
    public void create () {
        camera = new OrthographicCamera();
        batch = new SpriteBatch(); // Required to render meshes. SpriteBatch can't render meshes.
        renderer = new SkeletonRenderer();
        atlas = new TextureAtlas(Gdx.files.internal("skeletonAnimations/teacher.atlas"));
        SkeletonJson json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
//        json.setScale(0.5f); // Load the skeleton at 50% the size it was in Spine.
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("skeletonAnimations/teacher.json"));
        skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).
        skeleton.setPosition(250, 20);
        AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between animations.
        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).
//        state.setTimeScale(0.6f); // Slow all animations down to 60% speed.
// Queue animations on tracks 0 and 1.
        state.setAnimation(0, "breath_fulltalk", true);
        state.setAnimation(1, "breath_fulltalk", false);
        state.addAnimation(1, "breath_fulltalk", false, 2); // Keys in higher tracks override the pose from lower tracks.
    }
    public void render () {
        state.update(Gdx.graphics.getDeltaTime()); // Update the animation time.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        state.apply(skeleton); // Poses skeleton using current animations. This sets the bones' local SRT.
        skeleton.updateWorldTransform(); // Uses the bones' local SRT to compute their world SRT.
        camera.update();
        batch.getProjectionMatrix().set(camera.combined);
        batch.begin();
        renderer.draw(batch, skeleton); // Draw the skeleton images.
        batch.end();
    }
    public void resize (int width, int height) {
        camera.setToOrtho(false); // Update camera with new size.
    }
    public void dispose () {
        atlas.dispose();
    }
}
