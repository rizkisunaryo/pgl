package pgl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.colouredtulips.BaseScreen;
import com.colouredtulips.Constants;
import com.colouredtulips.Global;
import com.colouredtulips.object.CustomSprite;
import com.colouredtulips.object.SkeletonAnimation;


/**
 * Created by rizkisunaryo on 1/14/15.
 */
public class Classroom extends BaseScreen {
    private SkeletonAnimation teacher;
    private SkeletonAnimation student2;
    private CustomSprite emptyTable;
    private SkeletonAnimation student3;
    private SkeletonAnimation student4;
    private CustomSprite historyBook;
    private SkeletonAnimation mainGirl;



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

        midBg = new CustomSprite("classroom_midBg.png",650,341,1.5f);
        bg = new CustomSprite("classroom_bg.png",
                Constants.STANDARD_BG_X,
                Constants.STANDARD_BG_Y,
                Constants.STANDARD_BG_SCALE);

        teacher=new SkeletonAnimation("teacher", 0.75f, 325, 117, "breath");
        student2=new SkeletonAnimation("student2", 0.7f, 760, 147, "breath");
        emptyTable = new CustomSprite("classroom_empty_table.png", 442, 35);
        student4=new SkeletonAnimation("student4", 1.4f, 1090, 180, "breathing");
        student3=new SkeletonAnimation("student3", 0.7f, 1090, 35, "breath_bookclose");
        historyBook = new CustomSprite("classroom_book1.png", 565, 300);
        mainGirl=new SkeletonAnimation("main_school_girl", 0.7f, 884, 10, "walking");

        midBgGroup = new Group();
        midBgGroup.addActor(midBg);
        stage.addActor(midBgGroup);
        midBgAccelSpeed =15f;

        bgGroup = new Group();
        bgGroup.addActor(bg);
        stage.addActor(bgGroup);
        bgAccelSpeed =10f;

        foregroundGroup = new Group();
        foregroundGroup.addActor(teacher);
        foregroundGroup.addActor(student2);
        foregroundGroup.addActor(emptyTable);
        foregroundGroup.addActor(student4);
        foregroundGroup.addActor(student3);
        foregroundGroup.addActor(historyBook);
        foregroundGroup.addActor(mainGirl);
        stage.addActor(foregroundGroup);
        foregroundAccelSpeed =5f;
    }

    @Override
    public void render(float delta) {
        updateAnimations();

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        applyAnimations();

        camera.update();

        Global.batch.begin();
        Global.batch.setProjectionMatrix(camera.combined);

        historyBook.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touched");
                historyBook.setTouched(true);
                return true;
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                System.out.println("moved");
                if (historyBook.isTouched()) {
                    historyBook.setPosition(x,y);
                }
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("released");
            }
        });

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

//        Gdx.input.vibrate(1000);
        xAccel =-Gdx.input.getAccelerometerY();
        yAccel =Gdx.input.getAccelerometerX();
        moveByAcceleration();

        Global.batch.end();

//        System.out.println(mainGirl.getWidth()+":"+mainGirl.getHeight());
    }
}