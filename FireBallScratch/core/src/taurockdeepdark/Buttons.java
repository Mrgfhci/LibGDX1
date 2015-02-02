package taurockdeepdark;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

/**
 * Created by Matthew Brock on 30/10/2014.
 */
//http://stackoverflow.com/questions/21488311/libgdx-how-to-create-a-button
public class Buttons implements ApplicationListener {
    Stage stage;
    BitmapFont font;
    TextButton tbFireButton;
    TextButton.TextButtonStyle tbsFireButton;
    Skin skFireButton;
    TextureAtlas taFireButton;
    Texture tFireBall;
    int nSHeight, nSWidth;
    ArrayList<FireBall> arlFireBalls;
    OrthographicCamera camera;




    public void makeFireBall() {//This makes a new fireball
        arlFireBalls.add(new FireBall(tFireBall, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 90, camera));
    }

    @Override
    public void create() {
        tFireBall = new Texture(Gdx.files.internal("FireBall.png"));

        nSHeight = Gdx.graphics.getHeight();
        nSWidth = Gdx.graphics.getWidth();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        arlFireBalls = new ArrayList<FireBall>();
        skFireButton = new Skin(); //setting up the button
        taFireButton = new TextureAtlas(Gdx.files.internal("FireButton.pack"));
        skFireButton.addRegions(taFireButton);
        tbsFireButton = new TextButton.TextButtonStyle();
        tbsFireButton.font = font;
        tbsFireButton.up = skFireButton.getDrawable("FireButton");
        tbsFireButton.down = skFireButton.getDrawable("pressedFireButton");
        tbsFireButton.checked = skFireButton.getDrawable("FireButton");
        tbFireButton = new TextButton("", tbsFireButton);
        tbFireButton.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbFireButton.setPosition(nSWidth - (nSWidth * 200 / 1794), nSHeight * 200 / 1080);
        tbFireButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                makeFireBall();//Make a new fireball
                return true;
            }
        });
        stage.addActor(tbFireButton);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.08f, 0.08f, 0.08f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        System.out.println(arlFireBalls.size());
        for (FireBall arlFireBall : arlFireBalls) {//This renders all the fireballs
            arlFireBall.render();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
