package com.colouredtulips;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AccelerometerTest implements ApplicationListener {

    private SpriteBatch batch;

    private BitmapFont font;

    private String message = "Do something already!";

    private float highestX = 0.0f;

    private float highestY = 0.0f;

    private float highestZ = 0.0f;

    @Override

    public void create() {

        batch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"),false);

        font.setColor(Color.RED);

    }

    @Override

    public void dispose() {

        batch.dispose();

        font.dispose();

    }

    @Override

    public void render() {

        int w = Gdx.graphics.getWidth();

        int h = Gdx.graphics.getHeight();

        Gdx.gl.glClearColor(1, 1, 1, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        int deviceAngle = Gdx.input.getRotation();

        Orientation orientation = Gdx.input.getNativeOrientation();

        float accelX = Gdx.input.getAccelerometerX();

        if(accelX > highestX)

            highestX = accelX;

        float accelY = Gdx.input.getAccelerometerY();

        if(accelY > highestY)

            highestY = accelY;

        float accelZ = Gdx.input.getAccelerometerZ();

        if(accelZ > highestZ)

            highestZ = accelZ;

        message = "Device rotated to:" + Integer.toString(deviceAngle) + " degrees\n";

        message += "Device orientation is ";

        switch(orientation){

            case Landscape:

                message += " landscape.\n";

                break;

            case Portrait:

                message += " portrait. \n";

                break;

            default:

                message += " complete crap!\n";

                break;

        }



        message += "Device Resolution: " + Integer.toString(w) + "," + Integer.toString(h) + "\n";

        message += "X axis accel: " + Float.toString(accelX) + " \n";

        message += "Y axis accel: " + Float.toString(accelY) + " \n";

        message += "Z axis accel: " + Float.toString(accelZ) + " \n";

        message += "Highest X value: " + Float.toString(highestX) + " \n";

        message += "Highest Y value: " + Float.toString(highestY) + " \n";

        message += "Highest Z value: " + Float.toString(highestZ) + " \n";

        if(Gdx.input.isPeripheralAvailable(Peripheral.Vibrator)){

            if(accelX > 7){

                Gdx.input.vibrate(1000);

            }

        }

        if(Gdx.input.isPeripheralAvailable(Peripheral.Compass)){

            message += "Azmuth:" + Float.toString(Gdx.input.getAzimuth()) + "\n";

            message += "Pitch:" + Float.toString(Gdx.input.getPitch()) + "\n";

            message += "Roll:" + Float.toString(Gdx.input.getRoll()) + "\n";

        }

        else{

            message += "No compass available\n";

        }

        font.drawMultiLine(batch, message, 0, h);

        batch.end();

    }

    @Override

    public void resize(int width, int height) {

        batch.dispose();

        batch = new SpriteBatch();

        String resolution = Integer.toString(width) + "," + Integer.toString(height);

        Gdx.app.log("MJF", "Resolution changed " + resolution);

    }

    @Override

    public void pause() {

    }

    @Override

    public void resume() {

    }



}