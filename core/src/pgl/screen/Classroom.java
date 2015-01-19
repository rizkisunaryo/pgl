package pgl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private final int GLOBE_NUMBER=16;
    private final float TEACHER_CENTER_Y = 350;
    private final float MAIN_GIRL_HEIGHT_TOLERANCE =180;
    private final float MAIN_GIRL_MIN_X=430;
    private final float MAIN_GIRL_MAX_X=1200;

    private ShapeRenderer shapeRenderer;

    private CustomSprite globeArray[] = new CustomSprite[GLOBE_NUMBER];
    private int globeCounter=0;
    private boolean isGlobeTimerOn=false;
    private SkeletonAnimation teacher;
    private float teacherCVar;
    private SkeletonAnimation student2;
    private CustomSprite emptyTable;
    private SkeletonAnimation student3;
    private SkeletonAnimation student4;
    private CustomSprite historyBook;
    private SkeletonAnimation mainGirl;
    private CustomSprite historyBookFront;

    private float historyBookTimer =0;
    private boolean isHistoryBookTimerOn =false;

    public Classroom() {
        super();

        shapeRenderer = new ShapeRenderer();

        midBg = new CustomSprite("classroom_midbg.png", 650, 341, 1.5f, Constants.MAX_ACCELERATION_SPEED);

        bg = new CustomSprite("classroom_bg.png", Constants.STANDARD_BG_X, 0, 1.2f, Constants.MAX_ACCELERATION_SPEED/3*2);
        for (int i1= GLOBE_NUMBER -1; i1>=0; i1--) {
            globeArray[i1]=new CustomSprite("classroom_globe_"+i1+".png", 910, 520, 0f, Constants.MAX_ACCELERATION_SPEED/3*2);
            globeArray[i1].setOriScale(1.5f);
        }
        globeArray[0].setScale(globeArray[0].getOriScale());

        teacher=new SkeletonAnimation("teacher", 0.75f, 325, 117, "breath", Constants.MAX_ACCELERATION_SPEED/3);
        teacherCVar = teacher.getCurX() - (teacher.getCurY() + TEACHER_CENTER_Y);
        student2=new SkeletonAnimation("student2", 0.7f, 760, 147, "breath", Constants.MAX_ACCELERATION_SPEED/3);
        emptyTable = new CustomSprite("classroom_empty_table.png", 442, 35, 1, Constants.MAX_ACCELERATION_SPEED/3);
        student4=new SkeletonAnimation("student4", 1.4f, 1090, 180, "breathing", Constants.MAX_ACCELERATION_SPEED/3);
        student3=new SkeletonAnimation("student3", 0.7f, 1090, 35, "breath_bookclose", Constants.MAX_ACCELERATION_SPEED/3);
        historyBook = new CustomSprite("classroom_book1.png", 565, 300, 1, Constants.MAX_ACCELERATION_SPEED/3);
        historyBook.setOriPos(historyBook.getX(),historyBook.getY());
        mainGirl=new SkeletonAnimation("main_school_girl", 0.7f, 884, 10, "breath", Constants.MAX_ACCELERATION_SPEED/3);
        mainGirl.setHeightTolerance(MAIN_GIRL_HEIGHT_TOLERANCE);
        historyBookFront = new CustomSprite("classroom_book1.png", 565, 300, 1, Constants.MAX_ACCELERATION_SPEED/3);
        historyBookFront.setOriPos(historyBookFront.getX(), historyBookFront.getY());
        historyBookFront.getSprite().setScale(0);
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
        for (int i1= GLOBE_NUMBER -1; i1>=0; i1--)
            globeArray[i1].getSprite().draw(batch);

        renderer.draw(batch, teacher.getSkeleton());
        renderer.draw(batch, student2.getSkeleton());
        emptyTable.getSprite().draw(batch);
        renderer.draw(batch, student4.getSkeleton());
        renderer.draw(batch, student3.getSkeleton());
        historyBook.getSprite().draw(batch);
        renderer.draw(batch, mainGirl.getSkeleton());
        historyBookFront.getSprite().draw(batch);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.2f);
        shapeRenderer.rect(0, 0, Global.worldVirtualWidth*2, Global.worldVirtualHeight*2);
        shapeRenderer.end();

        moveByAcceleration();

        if (isGlobeTimerOn) {
            globeArray[globeCounter].setScale(0);
            if (globeCounter>= GLOBE_NUMBER -1) {
                globeCounter= -1;
                isGlobeTimerOn=false;
            }
            globeCounter++;
            globeArray[globeCounter].setScale(globeArray[globeCounter].getOriScale());
        }
        if (isHistoryBookTimerOn) {
            historyBookTimer +=Gdx.graphics.getDeltaTime();
            if (historyBookTimer >0.17) {
                historyBookFront.getSprite().setScale(0);
                historyBook.getSprite().setScale(1);
                isHistoryBookTimerOn =false;
                historyBookTimer =0;
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
        else if (teacher.contains(stageVector.x,stageVector.y)) {
            teacher.setTouched(true);
        }
        else if (globeArray[0].getSprite().getBoundingRectangle().contains(stageVector.x,stageVector.y)) {
            globeArray[0].setTouched(true);
        }

        return true;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        if (mainGirl.isTouched()) {
            float x=stageVector.x>MAIN_GIRL_MAX_X? MAIN_GIRL_MAX_X :
                    stageVector.x<MAIN_GIRL_MIN_X? MAIN_GIRL_MIN_X : stageVector.x;

            if (!mainGirl.isDragged()) {
                mainGirl.setAnimation(0,"walking",true);
                mainGirl.setDragged(true);
            }

            if (stageVector.x<MAIN_GIRL_MIN_X || stageVector.x<prevDraggedX)
                mainGirl.getSkeleton().setFlipX(false);
            else mainGirl.getSkeleton().setFlipX(true);

//            System.out.println(x+":"+mainGirl.getY());

            mainGirl.setCurPos(x,mainGirl.getCurY());
            mainGirl.setPosition(x,mainGirl.getY());
        }
        else if (historyBookFront.isTouched()) {
            historyBookFront.setCurPos(stageVector.x,stageVector.y);
            historyBookFront.setPosition(stageVector.x, stageVector.y);
        }
        else if (teacher.isTouched()) {
            if (!teacher.isDragged()) {
                teacher.setAnimation(0,"walk",true);
                teacher.setDragged(true);
            }

            float teacherY = stageVector.y;
            float teacherTopY = 220 + TEACHER_CENTER_Y;
            float teacherBottomY = 93 + TEACHER_CENTER_Y;
            if (teacherY>teacherTopY) {
                teacherY = teacherTopY;
            }
            else if (teacherY<teacherBottomY) {
                teacherY = teacherBottomY;
            }

            float teacherX = teacherCVar + teacherY;
            teacher.setCurPos(teacherX, teacherY-TEACHER_CENTER_Y);
            teacher.setPosition(teacherX, teacherY-TEACHER_CENTER_Y);
        }

        prevDraggedX = stageVector.x;
        prevDraggedY = stageVector.y;

        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        stageVector = camera.unproject(new Vector3(screenX,screenY,0));

        if (mainGirl.isTouched()) {
            mainGirl.setDragged(false);
            mainGirl.setAnimation(0,"breath",true);
        }
        else if (historyBookFront.isTouched()) {
            if (mainGirl.contains(stageVector.x, stageVector.y)) {
                dispose();
                Main.main.setScreen(new Palace());
                return true;
            }
            else {
                historyBookFront.setCurPos(historyBookFront.getOriX(), historyBookFront.getOriY());
                isHistoryBookTimerOn =true;
            }
        }
        else if (teacher.isTouched()) {
            teacher.setDragged(false);
            teacher.setAnimation(0,"breath",true);
        }
        else if (globeArray[0].isTouched() &&
                globeArray[0].getSprite().getBoundingRectangle().contains(stageVector.x,stageVector.y)) {
            isGlobeTimerOn=true;
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