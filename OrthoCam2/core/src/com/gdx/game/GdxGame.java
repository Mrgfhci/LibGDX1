package com.gdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GdxGame extends ApplicationAdapter {

    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
    float scrollTimer = 0.0f;

    @Override
    public void create () {

       float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1, h / w);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("background.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        sprite = new Sprite(texture, 0, 0, 480, 800);

       sprite.setSize(1f, 1f * sprite.getHeight() / sprite.getWidth() );
       sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
    }

    @Override
    public void render () {
        scrollTimer+=Gdx.graphics.getDeltaTime();
        if(scrollTimer>3f)
            scrollTimer = 0.0f;

        sprite.setU(scrollTimer+1
        );
        sprite.setU2(scrollTimer);
        Gdx.gl.glClearColor( 1f, 1f, 1f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite.draw(batch);
        batch.end();

    }
}