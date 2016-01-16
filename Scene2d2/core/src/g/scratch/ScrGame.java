package g.scratch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by dadeeo on 1/16/2016.
 */
public class ScrGame implements Screen {
    StgGame stgGame;


    public ScrGame(){
        stgGame = new StgGame();
        // the next line is needed to set the inputprocessor to the stage.
        Gdx.input.setInputProcessor(stgGame);



    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // stgGame.act(Gdx.graphics.getDeltaTime());
        stgGame.act(delta);
        stgGame.draw();
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
}
