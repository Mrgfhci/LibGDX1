package g.funwithtext;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FunWithText extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    BitmapFont font;
    String sMessage;
    BitmapFont.TextBounds bounds;  // get bounds of message for centering purposes.
    // bounds is a lot like "getRect". for fonts.
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        font = new BitmapFont(Gdx.files.internal("ariel.fnt"));
        sMessage = "Howdy";
        bounds = font.getBounds(sMessage);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        // use the font to draw the String
        font.draw(batch,
                sMessage, Gdx.graphics.getWidth()/2 - bounds.width/2, Gdx.graphics.getHeight()/2);
		batch.draw(img, 0, 0);
		batch.end();
	}
}
