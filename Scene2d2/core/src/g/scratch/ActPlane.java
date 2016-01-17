package g.scratch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by dadeeo on 1/16/2016.
 */
public class ActPlane extends Actor {

    Texture txPlane = new Texture(Gdx.files.internal("jet.jpg"));
    float fX = 0, fY = 0, fVx= 0;
    public boolean isStarted = false;
    Rectangle recBounds;
    public ActPlane(float _fX, float _fY) {
        fX = _fX;
        fY = _fY;
        setBounds(fX, fY, txPlane.getWidth(), txPlane.getHeight());
        //this.addListener(new ClickListener() {
            addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //((ActPlane) event.getTarget()).isStarted = true;
                //((ActPlane) event.getTarget()).isStarted = !((ActPlane) event.getTarget()).isStarted;
                if(fVx == 0){
                    fVx = 5;
                }
                else fVx = 0;
                System.out.println("Clicked **************************************");
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        // by resetting the bounds here, then the hit detection sticks with the jet.
        fX += fVx;
        setBounds(fX, fY, txPlane.getWidth(), txPlane.getHeight());
        batch.draw(txPlane, fX, fY);

    }

    @Override
    public void act(float delta) {
        // I put this in the listener.
       // if (isStarted) {
         //  fVx = 5;

        //}
        //else fVx = 0;
    }
    public Rectangle getBounds(){
        recBounds = new Rectangle(fX, fY, txPlane.getWidth(), txPlane.getHeight());
        return recBounds;

    }
    public void stop(){
        // I first make sure that the planes are no longer touching, then I set the velocity to 0
        fX -= fVx;
        fVx = 0;
    }


}

