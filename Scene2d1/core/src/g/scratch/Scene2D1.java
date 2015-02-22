package g.scratch;
// If I click the original spot of the jet, it starts to move right across the screen. Clicking the original spot stops and starts it again.
// time to fix it up so that it will only change if the jet is clicked.

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

    public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("jet.jpg"));
        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,0,0);
        }
    }*/
    // this next class definition will help it move.
    public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("jet.jpg"));
        float actorX = 0, actorY = 0;
        public boolean started = false;

        public MyActor() {
            setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
            addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //((MyActor) event.getTarget()).started = true;
                    ((MyActor)event.getTarget()).started = !((MyActor)event.getTarget()).started;


                    return true;
                }
            });
        }
        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,actorX,actorY);
        }

        @Override
        public void act(float delta){
            if(started){
                actorX+=5;
                // by resetting the bounds here, then the hit detection sticks with the jet.
                setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
            }
        }


    }

    private Stage stage;

	@Override
	public void create () {
        // the next line is Mike's code, the next line is the one that works.
        //stage = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        MyActor myActor = new MyActor();
        myActor.setTouchable(Touchable.enabled);
        stage.addActor(myActor);
	}

    @Override
    public void resize(int width, int height) {

    }

    @Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
