package gdx.input;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MyGdxGame implements ApplicationListener {
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        texture = new Texture("badlogic.jpg");

       // texture = new Texture(Gdx.files.internal("baddlogic.jpg"));
        sprite = new Sprite(texture);
        sprite.setPosition(w / 2 - sprite.getWidth() / 2, h / 2 - sprite.getHeight() / 2);

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                sprite.translateX(-1f);
            else
                sprite.translateX(-10.0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                sprite.translateX(1f);
            else
                sprite.translateX(10.0f);
        }
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}
