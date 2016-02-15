package g.scratch;

import com.badlogic.gdx.Game;

/**
 * Created by dadeeo on 2/15/2016.
 */
public class GamPlane extends Game {
    ScrPlane scrPlane;


    @Override
    public void create() {
        //ScrLaz scrLaz = new ScrLaz(this);
        scrPlane = new ScrPlane(this);
        setScreen(scrPlane);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }




}
