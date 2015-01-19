package pgl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.colouredtulips.BaseScreen;
import com.colouredtulips.Constants;
import com.colouredtulips.Global;
import com.colouredtulips.Main;
import com.colouredtulips.object.CustomSprite;
import com.colouredtulips.object.SkeletonAnimation;

/**
 * Created by rizkisunaryo on 1/14/15.
 */
public class GasingStory extends BaseScreen {
    private SkeletonAnimation hangtuah;
    private SkeletonAnimation pariGasing;

    public GasingStory() {
        super();

        midBg = new CustomSprite("congkak_gasing_story_midbg.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED);

        bg = new CustomSprite("gasing_story_bg.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED/3*2);
        hangtuah=new SkeletonAnimation("hangtuah_facing_left", 0.75f, 260, 190, "breath", Constants.MAX_ACCELERATION_SPEED/3*2);
        hangtuah.getSkeleton().setFlipX(true);
        pariGasing=new SkeletonAnimation("gasing_boy", 0.45f, 1014, 210, "breathing", Constants.MAX_ACCELERATION_SPEED/3*2);

        foreground = new CustomSprite("gasing_story_forground.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED/3);
    }

    @Override
    public void render(float delta) {
        updateAnimations();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        applyAnimations();

        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        midBg.getSprite().draw(batch);

        bg.getSprite().draw(batch);
        renderer.draw(batch,hangtuah.getSkeleton());
        renderer.draw(batch,pariGasing.getSkeleton());

        foreground.getSprite().draw(batch);

        moveByAcceleration();

        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        return true;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        dispose();
        Main.main.setScreen(new PrincessCourt());

//        for (CustomSprite customSprite : Global.customSpriteList) {
//            customSprite.setTouched(false);
//        }
//        for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
//            skeletonAnimation.setTouched(false);
//        }
        return true;
    }
}