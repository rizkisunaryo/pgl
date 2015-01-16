package com.colouredtulips.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.colouredtulips.Global;
import com.colouredtulips.util.FileUtil;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class SkeletonAnimation extends BaseObject{
    private TextureAtlas atlas;
    private Skeleton skeleton;
    private AnimationState state;

    public SkeletonAnimation(String fileName, float scale, float x, float y, String initialAnimationName) {
        atlas = new TextureAtlas(Gdx.files.internal(FileUtil.getSkeletonAnimationFile(fileName+".atlas")));
        SkeletonJson json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
        json.setScale(scale); // Load the skeleton at 50% the size it was in Spine.
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(FileUtil.getSkeletonAnimationFile(fileName+".json")));
        skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).
        AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between animations.
        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).

        setPosition(x,y);

        if (initialAnimationName!=null)
            setAnimation(0,initialAnimationName,true);

//        setOriPos(x,y);
//        setAccelSpeed(accelSpeed);

        setBounds(x, y, skeletonData.getWidth(), skeletonData.getHeight());

//        System.out.println(skeletonData.getWidth()+":"+skeletonData.getHeight());

        Global.skeletonAnimationList.add(this);
    }

    @Override
    public void draw(Batch batch, float alpha){
        Global.renderer.draw(Global.batch, getSkeleton());
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public Skeleton getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(Skeleton skeleton) {
        this.skeleton = skeleton;
    }

    public AnimationState getState() {
        return state;
    }

    public void setState(AnimationState state) {
        this.state = state;
    }

    public void setPosition(float x, float y) {
        skeleton.setPosition(x, y);
    }
    public float getX() {
        return skeleton.getX();
    }
    public float getY() {
        return skeleton.getY();
    }
    public void setAnimation(int trackIndex, String animationName, boolean loop) {
        state.setAnimation(trackIndex,animationName,loop);
    }
    public void addAnimation(int trackIndex, String animationName, boolean loop, float delay) {
        state.addAnimation(trackIndex,animationName,loop,delay);
    }
    public void applyAnimation() {
        state.apply(skeleton);
        skeleton.updateWorldTransform();
    }
}
