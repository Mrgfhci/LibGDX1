package com.jdj.game;
//Source: http://gamedev.stackexchange.com/questions/80182/how-do-i-initialize-and-use-a-texture-array-libgdx
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class jdjgame extends ApplicationAdapter {
    SpriteBatch batch;
    int index;
    Texture[] tile = new Texture[3];
    Stage stage;
    TextButton button;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;

    @Override
    public void create () {
        batch = new SpriteBatch();
        stage = new Stage();
        tile[0] = new Texture(Gdx.files.internal("cave.jpg"));
        tile[1] = new Texture(Gdx.files.internal("Rainforest.jpg"));
        tile[2] = new Texture(Gdx.files.internal("city.jpg"));
        index = 0;
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("button.pack"); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("UP");
        textButtonStyle.down = skin.getDrawable("DOWN");
        textButtonStyle.checked = skin.getDrawable("UP");
        button = new TextButton("", textButtonStyle);
        button.setSize(Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
        button.setPosition(500,0);
        stage.addActor(button);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(index<2){
                    index++;
                }else{
                    index = 0;
                }

            }




        });
    }




    @Override
    public void render () {
        batch.begin();

        batch.draw(tile[index], 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
    }
}