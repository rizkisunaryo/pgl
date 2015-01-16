package com.colouredtulips.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.colouredtulips.Global;
import com.colouredtulips.util.FileUtil;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class SkeletonAnimation extends BaseObject{
    private TextureAtlas atlas;
    private Skeleton skeleton;
    private AnimationState state;

    public SkeletonAnimation(String fileName, float scale, float x, float y, String initialAnimationName, float accelSpeed) {
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

        setOriPos(x,y);
        setAccelSpeed(accelSpeed);

        Global.skeletonAnimationList.add(this);
    }
    public SkeletonAnimation(String fileName, float scale, float x, float y, String initialAnimationName) {
        this(fileName,scale,x,y,initialAnimationName,0);
    }
    public SkeletonAnimation(String fileName, float scale, float x, float y) {
        this(fileName,scale,x,y,null);
    }
    public SkeletonAnimation(String fileName, float scale) {
        this(fileName,scale,0,0);
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
