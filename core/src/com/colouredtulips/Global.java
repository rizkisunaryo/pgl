package com.colouredtulips;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.colouredtulips.object.CustomSprite;
import com.colouredtulips.object.SkeletonAnimation;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class Global {
    public static float worldVirtualWidth;
    public static float worldVirtualHeight=Constants.WORLD_VIRTUAL_HEIGHT;
    public static List<Texture> textureList = new ArrayList<Texture>();
    public static List<SkeletonAnimation> skeletonAnimationList = new ArrayList<SkeletonAnimation>();

    public static SkeletonRenderer renderer;
}
