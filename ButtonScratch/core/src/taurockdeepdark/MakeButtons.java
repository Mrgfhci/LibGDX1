package taurockdeepdark;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Matthew Brock on 30/10/2014.
 */
//http://stackoverflow.com/questions/21488311/libgdx-how-to-create-a-button
public class MakeButtons extends Game {
    Stage stage;
    BitmapFont font;

    private TextButton tbUpButton;
    private TextButton.TextButtonStyle tbsUpButton;
    private Skin skUpButton;
    private TextureAtlas taUpButton;
    static int nSHeight, nSWidth;

    @Override
    public void create() {
        nSHeight = Gdx.graphics.getHeight();
        nSWidth = Gdx.graphics.getWidth();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skUpButton = new Skin();
        taUpButton = new TextureAtlas(Gdx.files.internal("UpButton.pack"));//Importing the .pack into a texture atlas that holds multiple images and can be referenced within a TextButtonStyle
        skUpButton.addRegions(taUpButton);//Applying a texture atlas into a skin
        tbsUpButton = new TextButton.TextButtonStyle();//TextButtonStyle Holds all the images that will be applied to the TextButton
        tbsUpButton.font = font;
        tbsUpButton.up = skUpButton.getDrawable("ArrowUp");//Setting positions and the image to use when the button is in those positions
        tbsUpButton.down = skUpButton.getDrawable("PressedArrowUp");
        //tbsUpButton.checked = skUpButton.getDrawable("ArrowUp");
        tbUpButton = new TextButton("", tbsUpButton);//Applying the TextButtonStyle to the TextButton giving it all of its positions and images as well as any text but I didn't use
        tbUpButton.setSize(nSWidth * 200 / 1794, nSHeight * 200 / 1080);
        tbUpButton.setPosition(nSWidth * 200 / 1794, nSHeight * 400 / 1080);
        tbUpButton.addListener(new InputListener() {//http://gamedev.stackexchange.com/questions/60123/registering-inputlistener-in-libgdx
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
              System.out.println("Press");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Release");
            }
        });
        stage.addActor(tbUpButton);


    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);//setting background
        Gdx.gl.glClearColor(1, 1, 1, 1);
        super.render();
        stage.draw();

    }

}
