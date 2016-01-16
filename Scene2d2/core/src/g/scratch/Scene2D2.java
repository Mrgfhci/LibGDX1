package g.scratch;
// I am going to build on Scene2d1: First, I am going to clean up the naming and
// create a separate Actor class.
// I will then add a touch screen listener that will move the plane where I touch.
// Then a dPad to move it, followed by collision detection with another actor.

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
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

public class Scene2D2 extends Game{
    /* the first inner Actor class:

    public class Plane extends Actor {
        Texture texture = new Texture(Gdx.files.internal("jet.jpg"));
        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,0,0);
        }
    }*/
    // this next class definition will help it move.
    

    private Stage stage;

	@Override
	public void create () {
        setScreen(new ScrGame());
        // the next line is Mike's code, the next line is the one that works.
        //stage = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
        //stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        //Gdx.input.setInputProcessor(stage);

	}

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}
