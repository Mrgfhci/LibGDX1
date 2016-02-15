package g.scratch;
// If I click the original spot of the jet, it starts to move right across the screen. Clicking the original spot stops and starts it again.
// time to fix it up so that it will only change if the jet is clicked.
// Update - the plane moves and stops if it is clicked - not the original spot.
// I have backed this up as a copy. I am now going to get it split up into different files with better names.

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class ScrPlane implements Screen {

    private StgPlane stgPlane;
    private GamPlane gamPlane;

    public ScrPlane(GamPlane _gamPlane) {
        gamPlane = _gamPlane;
        stgPlane = new StgPlane();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
	public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stgPlane.act(Gdx.graphics.getDeltaTime());
        stgPlane.draw();
	}

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stgPlane.dispose();

    }
    @Override
    public void hide() {

    }
}
