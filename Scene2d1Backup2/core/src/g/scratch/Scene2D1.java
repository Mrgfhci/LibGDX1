package g.scratch;
// If I click the original spot of the jet, it starts to move right across the screen. Clicking the original spot stops and starts it again.
// time to fix it up so that it will only change if the jet is clicked.
// Update - the plane moves and stops if it is clicked - not the original spot.
// I have backed this up as a copy. I am now going to get it split up into different files with better names.

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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

public class Scene2D1 implements ApplicationListener {
    /* the first inner Actor class:

    public class ActPlane extends Actor {
        Texture texture = new Texture(Gdx.files.internal("jet.jpg"));
        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,0,0);
        }
    }*/
    // this next class definition will help it move.


    //private Stage stage;
    private StgPlane stgPlane;

	@Override
	public void create () {
        // the next line is Mike's code, the next line is the one that works.
        //stage = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
        stgPlane = new StgPlane();
        //Gdx.input.setInputProcessor(stgPlane);


	}

    @Override
    public void resize(int width, int height) {

    }

    @Override
	public void render () {
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
}
