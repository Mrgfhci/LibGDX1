package com.triforce_release5.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.triforce_release5.TbsMenu;
import com.triforce_release5.TbText;
import com.triforce_release5.TriForceRelease5;

/**
 * Created by Matthew on 11/10/2015.
 */
public class ScrLevelChoice implements Screen, InputProcessor {

    TriForceRelease5 triForceRelease5;
    TbsMenu tbsMenu;
    Stage stage;
    TextButton TbEasy, TbMedium, TbHard, TbImposs, TbBack;

    SpriteBatch batch;

    public ScrLevelChoice(TriForceRelease5 triForceRelease5){ //References the main class.
        this.triForceRelease5=triForceRelease5;
    }

    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        tbsMenu = new TbsMenu();
        //Skin skin = tbsMenu.getSkin();//calls skin
        tbsMenu = new TbsMenu();
        TbEasy= new TbText("Easy", tbsMenu);
        TbEasy.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3*2);
        TbMedium= new TbText("Medium",tbsMenu);
        TbMedium.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2 );
        TbHard = new TbText("Hard",tbsMenu);
        TbHard.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3);
        TbImposs = new TbText("Impossible", tbsMenu);
        TbImposs.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 5);
        TbBack = new TbText("Back", tbsMenu);
        TbBack.setPosition(0, 0);
        stage.addActor(TbEasy);
        stage.addActor(TbMedium);
        stage.addActor(TbHard);
        stage.addActor(TbImposs);
        stage.addActor(TbBack);
        Gdx.input.setInputProcessor(stage); //Have to change the Input Processor when changing screens, or else you can still use the buttons from the Main Menu on the Play Screen.

    }

    @Override
    public void show() { //This is called when you set the screen to this class.
        create();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1); //Blue background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        batch.begin();
        batch.end();
        if(TbEasy.isPressed()){
            triForceRelease5.currentState = TriForceRelease5.GameState.EASY;
            triForceRelease5.updateState();
        }else if(TbMedium.isPressed()){
            triForceRelease5.currentState = TriForceRelease5.GameState.MEDIUM;
            triForceRelease5.updateState();
        }else if(TbHard.isPressed()){
            triForceRelease5.currentState = TriForceRelease5.GameState.HARD;
            triForceRelease5.updateState();
        }else if(TbImposs.isPressed()){
            triForceRelease5.currentState = TriForceRelease5.GameState.IMPOSSIBLE;
            triForceRelease5.updateState();
        }else if(TbBack.isPressed()){
            triForceRelease5.currentState = TriForceRelease5.GameState.MENU;
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
        batch.dispose();

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
