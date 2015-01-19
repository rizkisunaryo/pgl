package com.colouredtulips.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.colouredtulips.Global;
import com.colouredtulips.util.FileUtil;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonBounds;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class SkeletonAnimation extends BaseObject{
    private TextureAtlas atlas;
    private Skeleton skeleton;
    private AnimationState state;
    private SkeletonJson json;
    private SkeletonData skeletonData;

    private float widthTolerance;
    private float heightTolerance;

    public SkeletonAnimation(String fileName, float scale, float x, float y, String initialAnimationName, float accelSpeed) {
        atlas = new TextureAtlas(Gdx.files.internal(FileUtil.getSkeletonAnimationFile(fileName+".atlas")));
        json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
        json.setScale(scale); // Load the skeleton at 50% the size it was in Spine.

        skeletonData = json.readSkeletonData(Gdx.files.internal(FileUtil.getSkeletonAnimationFile(fileName+".json")));
        skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).
        AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between animations.
        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).

        setPosition(x, y);

        if (initialAnimationName!=null)
            setAnimation(0,initialAnimationName,true);

        setCurPos(x,y);
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

    public SkeletonJson getJson() {
        return json;
    }

    public void setJson(SkeletonJson json) {
        this.json = json;
    }

    public float getWidthTolerance() {
        return widthTolerance;
    }
    public void setWidthTolerance(float widthTolerance) {
        this.widthTolerance = widthTolerance;
    }
    public float getHeightTolerance() {
        return heightTolerance;
    }
    public void setHeightTolerance(float heightTolerance) {
        this.heightTolerance = heightTolerance;
    }
    public void setSizeTolerance(float x, float y) {
        this.widthTolerance=x;
        this.heightTolerance=y;
    }

    public void setAnimation(int trackIndex, String animationName, boolean loop) {
        state.clearTracks();
        skeleton.setToSetupPose();
        state.setAnimation(trackIndex,animationName,loop);
    }
    public void addAnimation(int trackIndex, String animationName, boolean loop, float delay) {
        state.addAnimation(trackIndex,animationName,loop,delay);
    }
    public void applyAnimation() {
        state.apply(skeleton);
        skeleton.updateWorldTransform();
    }
    public boolean contains (float x, float y) {
        float skeletonX=skeleton.getX()-json.getScale()*(skeletonData.getWidth()+widthTolerance)/2;

//        System.out.println(skeletonX
//                +":"+json.getScale()
//                +":"+skeletonData.getWidth()
//                +":"+skeleton.getY()
//                +":"+json.getScale()
//                +":"+skeletonData.getHeight()
//                +":"+x
//                +":"+y);
//
//        boolean bool1 = skeletonX <= x;
//        boolean bool2 = skeletonX + json.getScale()*(skeletonData.getWidth()+widthTolerance) >= x;
//        boolean bool3 = skeleton.getY() <= y;
//        boolean bool4 = skeleton.getY() + json.getScale()*(skeletonData.getHeight()+heightTolerance) >= y;
//
//        System.out.println(bool1+":"+bool2+":"+bool3+":"+bool4);

        return skeletonX <= x
                && skeletonX + json.getScale()*(skeletonData.getWidth()+widthTolerance) >= x
                && skeleton.getY() <= y
                && skeleton.getY() + json.getScale()*(skeletonData.getHeight()+heightTolerance) >= y;
    }
}
