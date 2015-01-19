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
public class Balairong extends BaseScreen {
    private SkeletonAnimation bendahara;
    private SkeletonAnimation penghuluBendahari;
    private SkeletonAnimation tumenggung;
    private SkeletonAnimation hangtuah;
    private SkeletonAnimation sultan;
    private CustomSprite shelter;

    public Balairong() {
        super();

        midBg = new CustomSprite("balairong_midbg.png", -170, 282, 1.2f, Constants.MAX_ACCELERATION_SPEED);

        bg = new CustomSprite("balairong_bg.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED/3*2);
        bendahara=new SkeletonAnimation("Bendahara_Sitting", 0.41f, 169, 180, "breathing", Constants.MAX_ACCELERATION_SPEED/3*2);
        penghuluBendahari=new SkeletonAnimation("Penghulu_Bendahara_Sitting", 0.41f, 533, 200, "breathing", Constants.MAX_ACCELERATION_SPEED/3*2);
        tumenggung=new SkeletonAnimation("Temenggung", 0.49f, 390, 120, "breathing", Constants.MAX_ACCELERATION_SPEED/3*2);
        hangtuah=new SkeletonAnimation("hangtuah_sitting", 0.75f, 715, 120, "breath", Constants.MAX_ACCELERATION_SPEED/3*2);
        sultan=new SkeletonAnimation("Sultan_sitting", 0.45f, 1066, 200, "breath", Constants.MAX_ACCELERATION_SPEED/3*2);
        shelter = new CustomSprite("balairong_shelter.png", 718, 128, 1f, Constants.MAX_ACCELERATION_SPEED/3*2);

        foreground = new CustomSprite("balairong_forground.png", Constants.STANDARD_BG_X, 628, 1.2f, Constants.MAX_ACCELERATION_SPEED/3);
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
        renderer.draw(batch,bendahara.getSkeleton());
        renderer.draw(batch,penghuluBendahari.getSkeleton());
        renderer.draw(batch,tumenggung.getSkeleton());
        renderer.draw(batch,hangtuah.getSkeleton());
        renderer.draw(batch,sultan.getSkeleton());
        shelter.getSprite().draw(batch);

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
        Main.main.setScreen(new CongkakStory());

//        for (CustomSprite customSprite : Global.customSpriteList) {
//            customSprite.setTouched(false);
//        }
//        for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
//            skeletonAnimation.setTouched(false);
//        }
        return true;
    }
}