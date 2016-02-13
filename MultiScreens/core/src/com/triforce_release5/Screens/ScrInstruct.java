package com.triforce_release5.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.triforce_release5.TbsMenu;
import com.triforce_release5.TbText;
import com.triforce_release5.TriForceRelease5;

/**
 * Created by Matthew on 12/9/2015.
 */
public class ScrInstruct implements Screen, InputProcessor {

    TriForceRelease5 triForceRelease5;

    Stage stage;

    TbsMenu tbsMenu;
    TextButton TbBack;

    public ScrInstruct(TriForceRelease5 triForceRelease5){ //References the main class.
        this.triForceRelease5=triForceRelease5;
    }

    public void create(){
        stage = new Stage();
        tbsMenu = new TbsMenu();
       // Skin skin = tbsMenu.getSkin();//calls skin
        TbBack = new TbText("Back", tbsMenu);
        TbBack.setPosition(0, 0);
        stage.addActor(TbBack);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        create();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1); //Green background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if(TbBack.isPressed()){
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
    public void hide() {

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
