package g.eventinput;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// and event-driven application requires an interface.
// once you put in the "implements ApplicatinoAdapter" you can "ctrl-l" in order to get all the
// methods you need. DAmn - it puts all the methods at the top.
public class EventInput extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT){
            sprite.translateX((-1f));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // touch down and a moved combined. the pointer is a 0-based index.
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    Texture img;
    Sprite sprite;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        sprite = new Sprite(img);
        sprite.setPosition(Gdx.graphics.getWidth()/2- sprite.getWidth()/2, Gdx.graphics.getHeight()/2 - sprite.getHeight()/2); // centre the sprite
        // register your input:
        Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY());
		batch.end();
	}
}
