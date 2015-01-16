package pgl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
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

        bg = new CustomSprite("classroom_bg.png",650,341,1.5f);
        foreground = new CustomSprite("classroom_foreground.png",
                Constants.STANDARD_BG_X,
                Constants.STANDARD_BG_Y,
                Constants.STANDARD_BG_SCALE);

        teacher=new SkeletonAnimation("teacher", 0.75f, 325, 117, "breath", 5);
        student2=new SkeletonAnimation("student2", 0.7f, 760, 147, "breath", 5);
        emptyTable = new CustomSprite("classroom_empty_table.png", 442, 35);
        student4=new SkeletonAnimation("student4", 1.4f, 1090, 180, "breathing", 5);
        student3=new SkeletonAnimation("student3", 0.7f, 1090, 35, "breath_bookclose", 5);
        historyBook = new CustomSprite("classroom_book1.png", 565, 300);
        mainGirl=new SkeletonAnimation("main_school_girl", 0.7f, 884, 10, "breath", 5);

        bgGroup = new Group();
        bgGroup.addActor(bg);
        stage.addActor(bgGroup);
        bgAccelSpeed=15f;
        foregroundGroup = new Group();
        foregroundGroup.addActor(foreground);
        stage.addActor(foregroundGroup);
        foregroundAccelSpeed=10f;
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

//        foreground.setScale(2);
//        foreground.draw(batch,1);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

//        drawBg();
        renderer.draw(batch, teacher.getSkeleton());
        renderer.draw(batch, student2.getSkeleton());
//        emptyTable.getSprite().draw(batch);
        renderer.draw(batch, student4.getSkeleton());
        renderer.draw(batch, student3.getSkeleton());
//        historyBook.getSprite().draw(batch);
        renderer.draw(batch, mainGirl.getSkeleton());

//        Gdx.input.vibrate(1000);
        xAccel =-Gdx.input.getAccelerometerY();
        yAccel =Gdx.input.getAccelerometerX();
        moveByAcceleration();

        batch.end();
    }
}