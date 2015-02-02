package taurockdeepdark;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


/**
 * Created by Matthew Brock on 30/10/2014.
 */
//http://obviam.net/index.php/getting-started-in-android-game-development-with-libgdx-create-a-working-prototype-in-a-day-tutorial-part-1/
public class Main extends Game {
    OrthographicCamera camera;
    Buttons buttons;





    @Override
    public void create() {//did this change

        buttons = new Buttons();
        buttons.create();

        System.out.println("Width" + Gdx.graphics.getWidth());
        System.out.println("Height" + Gdx.graphics.getHeight());
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        buttons.render();
    }

    @Override
    public void pause() {
        super.pause();


    }

    @Override
    public void resume() {
        super.resume();

    }

    @Override
    public void dispose() {
        super.dispose();

    }


}
