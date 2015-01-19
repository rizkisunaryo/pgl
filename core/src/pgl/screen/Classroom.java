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
public class Classroom extends BaseScreen {
    private final static float MAIN_GIRL_HEIGHT_TOLERANCE =180;
    private final static float MAIN_GIRL_MIN_X=430;
    private final static float MAIN_GIRL_MAX_X=1200;

    private SkeletonAnimation teacher;
    private SkeletonAnimation student2;
    private CustomSprite emptyTable;
    private SkeletonAnimation student3;
    private SkeletonAnimation student4;
    private CustomSprite historyBook;
    private SkeletonAnimation mainGirl;
    private CustomSprite historyBookFront;

    private float timer=0;
    private boolean isTimerOn=false;

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
    }
    @Override
    public void dispose() {
        super.dispose();
    }

    public Classroom() {
        super();

        midBg = new CustomSprite("classroom_midbg.png", 650, 341, 1.5f, Constants.MAX_ACCELERATION_SPEED);

        bg = new CustomSprite("classroom_bg.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED/3*2);

        teacher=new SkeletonAnimation("teacher", 0.75f, 325, 117, "breath", Constants.MAX_ACCELERATION_SPEED/3);
        student2=new SkeletonAnimation("student2", 0.7f, 760, 147, "breath", Constants.MAX_ACCELERATION_SPEED/3);
        emptyTable = new CustomSprite("classroom_empty_table.png", 442, 35, 1, Constants.MAX_ACCELERATION_SPEED/3);
        student4=new SkeletonAnimation("student4", 1.4f, 1090, 180, "breathing", Constants.MAX_ACCELERATION_SPEED/3);
        student3=new SkeletonAnimation("student3", 0.7f, 1090, 35, "breath_bookclose", Constants.MAX_ACCELERATION_SPEED/3);
        historyBook = new CustomSprite("classroom_book1.png", 565, 300, 1, Constants.MAX_ACCELERATION_SPEED/3);
        historyBook.setOriPos(historyBook.getX(),historyBook.getY());
        mainGirl=new SkeletonAnimation("main_school_girl", 0.7f, 884, 10, "breath", Constants.MAX_ACCELERATION_SPEED/3);
        mainGirl.setHeightTolerance(MAIN_GIRL_HEIGHT_TOLERANCE);
        historyBookFront = new CustomSprite("classroom_book1.png", 565, 300, 1, Constants.MAX_ACCELERATION_SPEED/3);
        historyBookFront.setOriPos(historyBookFront.getX(),historyBookFront.getY());
        historyBookFront.getSprite().setScale(0);
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
        renderer.draw(batch, teacher.getSkeleton());
        renderer.draw(batch, student2.getSkeleton());
        emptyTable.getSprite().draw(batch);
        renderer.draw(batch, student4.getSkeleton());
        renderer.draw(batch, student3.getSkeleton());
        historyBook.getSprite().draw(batch);
        renderer.draw(batch, mainGirl.getSkeleton());
        historyBookFront.getSprite().draw(batch);

        moveByAcceleration();

        if (isTimerOn) {
            timer+=Gdx.graphics.getDeltaTime();
            if (timer>0.17) {
                historyBookFront.getSprite().setScale(0);
                historyBook.getSprite().setScale(1);
                isTimerOn=false;
                timer=0;
            }
        }

        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        if (mainGirl.contains(stageVector.x,stageVector.y)) {
            mainGirl.setTouched(true);
        }
        else if (historyBook.getSprite().getBoundingRectangle().contains(stageVector.x,stageVector.y)) {
//            historyBook.setTouched(true);
            historyBook.getSprite().setScale(0);
            historyBookFront.getSprite().setScale(1);
            historyBookFront.setTouched(true);
        }

        return true;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        if (mainGirl.isTouched()) {
            float x=stageVector.x>MAIN_GIRL_MAX_X? MAIN_GIRL_MAX_X :
                    stageVector.x<MAIN_GIRL_MIN_X? MAIN_GIRL_MIN_X : stageVector.x;

            if (stageVector.x<MAIN_GIRL_MIN_X || stageVector.x<prevDraggedX) {
                mainGirl.getSkeleton().setFlipX(false);
            }
            else {
                mainGirl.getSkeleton().setFlipX(true);
            }

//            System.out.println(x+":"+mainGirl.getY());

            mainGirl.setCurPos(x,mainGirl.getCurY());
            mainGirl.setPosition(x,mainGirl.getY());
        }
        else if (historyBookFront.isTouched()) {
            historyBookFront.setCurPos(stageVector.x,stageVector.y);
            historyBookFront.setPosition(stageVector.x, stageVector.y);
        }

        prevDraggedX = stageVector.x;
        prevDraggedY = stageVector.y;

        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        if (historyBookFront.isTouched()) {
            if (mainGirl.contains(stageVector.x, stageVector.y)) {
                Main.main.setScreen(new Palace());
            }
            else {
                historyBookFront.setCurPos(historyBookFront.getOriX(), historyBookFront.getOriY());
                isTimerOn=true;
            }
        }

        for (CustomSprite customSprite : Global.customSpriteList) {
            customSprite.setTouched(false);
        }
        for (SkeletonAnimation skeletonAnimation : Global.skeletonAnimationList) {
            skeletonAnimation.setTouched(false);
        }
        return true;
    }
}