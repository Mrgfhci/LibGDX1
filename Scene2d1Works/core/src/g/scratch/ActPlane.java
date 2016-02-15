package g.scratch;
// I have successfully pulled the Actor class out of the main code.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by dadeeo on 2/15/2016.
 *
 */
public class ActPlane extends Actor{

        Texture texture = new Texture(Gdx.files.internal("jet.jpg"));
        float actorX = 0, actorY = 0;
        public boolean started = false;

        public ActPlane() {
            setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
            addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ((ActPlane)event.getTarget()).started = !((ActPlane)event.getTarget()).started;
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




