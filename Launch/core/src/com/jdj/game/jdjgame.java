package com.jdj.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class jdjgame extends ApplicationAdapter {
    final float PIXELS_TO_METERS = 100f;//convert pixels to meters since box2d uses meters
    Stage stage;
    TextButton button;
    TextButton.TextButtonStyle textButtonStyle;
    Texture texture;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    SpriteBatch batch;
    Sprite sprite;
    Texture img;
    World world;
    Body body;
    OrthographicCamera camera;
    int nWidth, nHeight;
    @Override
    public void create() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        texture = new Texture(Gdx.files.internal("LiberationMono.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("LiberationMono.fnt"), new TextureRegion(texture), false);

        font.setScale(1f, 1f);//scale to other devices - need to test it
        skin = new Skin();
        buttonAtlas = new TextureAtlas("button.pack");
        img = new Texture("penguin.png");
        sprite = new Sprite(img);
        nWidth = Gdx.graphics.getWidth();
        nHeight = Gdx.graphics.getHeight();
        // Center the sprite in the top/middle of the screen
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);


        world = new World(new Vector2(0, 0f), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) /
                        PIXELS_TO_METERS,
                (sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight()
                / 2 / PIXELS_TO_METERS);


        shape.dispose();
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("Button_Pressed");
        textButtonStyle.down = skin.getDrawable("Button_Released");
        textButtonStyle.checked = skin.getDrawable("Button_Pressed");
        textButtonStyle.fontColor = Color.BLACK;
        button = new TextButton("LAUNCH", textButtonStyle);
        button.setSize(nWidth / 6, nWidth / 6);
        stage.addActor(button);
        camera = new OrthographicCamera(nWidth, nHeight);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //if button is pressed do stuff
                body.setLinearVelocity(5f, 5f);
            }
        });
    }


    @Override
    public void render() {
        camera.update();
        world.step(1 / 60f, 6, 2);
        sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.
                        getWidth() / 2,
                (body.getPosition().y * PIXELS_TO_METERS) - sprite.getHeight() / 2)
        ;
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY());
        batch.end();

        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        world.dispose();
        texture.dispose();
        font.dispose();
    }
}
