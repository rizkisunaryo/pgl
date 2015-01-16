package com.colouredtulips;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.colouredtulips.object.CustomSprite;
import com.colouredtulips.object.SkeletonAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rizkisunaryo on 1/15/15.
 */
public class Global {
    public static float worldVirtualWidth;
    public static float worldVirtualHeight=Constants.WORLD_VIRTUAL_HEIGHT;
    public static List<CustomSprite> customSpriteList = new ArrayList<CustomSprite>();
    public static List<SkeletonAnimation> skeletonAnimationList = new ArrayList<SkeletonAnimation>();
}
