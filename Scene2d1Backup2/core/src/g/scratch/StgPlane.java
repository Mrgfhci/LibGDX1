package g.scratch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by dadeeo on 2/15/2016.
 */
public class StgPlane  extends Stage {


    public StgPlane() {
        super(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(this);
        ActPlane ActPlane = new ActPlane();
        ActPlane.setTouchable(Touchable.enabled);
        addActor(ActPlane);

    }


}



