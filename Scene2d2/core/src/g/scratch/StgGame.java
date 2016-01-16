package g.scratch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by dadeeo on 1/16/2016.
 */
public class StgGame extends Stage{

    public StgGame(){
        ActPlane actPlane = new ActPlane();
        actPlane.setTouchable(Touchable.enabled);
        addActor(actPlane);
    }


}
