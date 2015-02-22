
// from http://www.gamefromscratch.com/page/LibGDX-Video-Tutorial-Series.aspx
// handling input.
package g.pollinput;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PollInput extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Sprite sprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        sprite = new Sprite (img);
        sprite.setPosition(Gdx.graphics.getWidth()/2- sprite.getWidth()/2, Gdx.graphics.getHeight()/2 - sprite.getHeight()/2); // centre the sprite
	}

	@Override
	public void render () {
        // polling method.
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            sprite.translateX(-1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            sprite.translateX(1f);
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            // this won't work because the coordinates from the mouse are "ui" cooordinates. I will keep it in for reference purposes:
           // sprite.setPosition(Gdx.input.getX() -sprite.getWidth()/2, Gdx.input.getY() - sprite.getHeight()/2);
            // this will place the lower left coordinate of the image where the mouse is.
            sprite.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) ;
        }
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(sprite, sprite.getX(), sprite.getY());
		batch.end();
	}
    @Override
    public void dispose(){
        img.dispose();
        batch.dispose();
    }
}
