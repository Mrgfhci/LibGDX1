package g.scratch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by dadeeo on 1/16/2016.
 */
public class StgGame extends Stage{
    ActPlane actPlane1;
    ActPlane actPlane2;

    public StgGame(){
        actPlane1 = new ActPlane(0,0);
        actPlane2 = new ActPlane(1000,0);
        //actPlane1.setTouchable(Touchable.enabled);
        addActor(actPlane1);
        addActor(actPlane2); // this is still touchable
    }

    @Override
    public void act(float delta) {
        if (actPlane1.getBounds().overlaps(actPlane2.getBounds())) {
            System.out.println("HIT ***************************");
            //fX += 5;
            // by resetting the bounds here, then the hit detection sticks with the jet.
            //setBounds(fX, fY, texture.getWidth(), texture.getHeight());
            actPlane1.stop();
        }
    }


}
