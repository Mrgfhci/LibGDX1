package com.triforce_release5.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.triforce_release5.TbsMenu;
import com.triforce_release5.TbText;
import com.triforce_release5.TriForceRelease5;

/**
 * Created by Matthew on 11/10/2015.
 */
public class ScrMenuMain implements Screen, InputProcessor {

    TriForceRelease5 triForceRelease5;

    TbsMenu tbsMenu;
    Stage stage;
    TextButton tbPlay, tbInstruct;

    public ScrMenuMain(TriForceRelease5 triForceRelease4){ //References the main class.
        this.triForceRelease5=triForceRelease4;
    }

    public void create(){
        stage = new Stage();
        tbsMenu = new TbsMenu();
        //Skin skin = skMenu.getSkin();
        //TbPlay = new TbText("Play", skin); // Use the initialized skin
        tbPlay = new TbText("Play", tbsMenu); // Use the initialized skin
        tbPlay.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 * 2);
        //TbInstruct = new TbText("Instructions", skin);

        tbInstruct = new TbText("Instructions", tbsMenu);
        tbInstruct.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3);
        stage.addActor(tbPlay);
        stage.addActor(tbInstruct);
        Gdx.input.setInputProcessor(stage);
}
    @Override
    public void show() { //This is called when you set the screen to this class.
        create();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1); //Red background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if(tbPlay.isPressed()){ //When you click the Play Button, it sets the screen to the Play Screen, and updates.
            triForceRelease5.currentState = TriForceRelease5.GameState.PLAY;
            triForceRelease5.updateState();
        }else if(tbInstruct.isPressed()){
            triForceRelease5.currentState = TriForceRelease5.GameState.INSTRUCT;
            triForceRelease5.updateState();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() { //This is called when the other screen is displayed.

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
