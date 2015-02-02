package taurockdeepdark;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;


/**
 * Created by Matthew Brock on 30/10/2014.
 */
//http://obviam.net/index.php/getting-started-in-android-game-development-with-libgdx-create-a-working-prototype-in-a-day-tutorial-part-1/
public class Main extends Game {
    OrthographicCamera camera;
    MainCharacter mainCharacter;
    Buttons buttons;
    int nNumberOfMaps = 2;
    Map[] armMaps;


    @Override
    public void create() {//did this change
        camera = new OrthographicCamera();
        mainCharacter = new MainCharacter();
        armMaps = new Map[nNumberOfMaps];//Building the array of maps and passing the camera via the constructor
        for (int i = 0; i < nNumberOfMaps; i++) {
            armMaps[i]= new Map(i,camera);
            armMaps[i].create();
       }
        buttons = new Buttons();
        buttons.setMainCharacter(mainCharacter);
        mainCharacter.setMaps(armMaps);
        mainCharacter.setCamera(camera);
        mainCharacter.create();
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
        armMaps[mainCharacter.nCurrentMap].render();
        mainCharacter.render();
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
