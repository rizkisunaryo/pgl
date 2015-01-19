package pgl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.colouredtulips.BaseScreen;
import com.colouredtulips.Constants;
import com.colouredtulips.Global;
import com.colouredtulips.object.CustomSprite;
import com.colouredtulips.object.SkeletonAnimation;

/**
 * Created by rizkisunaryo on 1/14/15.
 */
public class Palace extends BaseScreen {
    SkeletonAnimation leftSoldier;
    SkeletonAnimation rightSoldier;

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
    }
    @Override
    public void dispose() {
        super.dispose();
    }

    public Palace() {
        super();

        midBg = new CustomSprite("palace_palace_midbg.png", Constants.STANDARD_BG_X, 391, 1.2f, Constants.MAX_ACCELERATION_SPEED);

        bg = new CustomSprite("palace_palace_bg.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED/3*2);
        leftSoldier=new SkeletonAnimation("soldier_facing_right", 1.28f, 377, 117, "animation", Constants.MAX_ACCELERATION_SPEED/3*2);
        rightSoldier=new SkeletonAnimation("soldier_facing_right", 1.28f, 910, 117, "animation", Constants.MAX_ACCELERATION_SPEED/3*2);
        rightSoldier.getSkeleton().setFlipX(true);

        foreground = new CustomSprite("palace_palace_forground.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED/3);
    }

    @Override
    public void render(float delta) {
        updateAnimations();

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        applyAnimations();

        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        drawBg();
        midBg.getSprite().draw(batch);
        bg.getSprite().draw(batch);
        renderer.draw(batch,leftSoldier.getSkeleton());
        renderer.draw(batch,rightSoldier.getSkeleton());
        foreground.getSprite().draw(batch);

        moveByAcceleration();

        if (isTimerOn) {
            timer+=Gdx.graphics.getDeltaTime();
            if (timer>0.17) {

            }
        }

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

        for (CustomSprite customSprite : Global.customSpriteList) {
            customSprite.setTouched(false);
        }
        for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
            skeletonAnimation.setTouched(false);
        }
        return true;
    }
}