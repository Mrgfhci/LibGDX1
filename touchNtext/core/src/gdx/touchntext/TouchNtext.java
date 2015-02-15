// got tis working WITHOUT   gdx-freetype. I'm missing something.
// the program will output the coordinates of where you point.

package gdx.touchntext;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TouchNtext implements ApplicationListener, InputProcessor{
	SpriteBatch batch;
	Texture img;
    private BitmapFont font;
    private String message = "Touch something already!";
    private int w,h;
    private int nX, nY; // touch coordinates
    // inner class for processing input information.
    class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }
    // a map that will associate the touch information with a discrete integer
    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();


	
	@Override
	public void create () {
        // I cannot make this work!!! Giving up
        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!*/
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        // this next line hung me up for 2 hours. Still can't make it work.
        //font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"),false);
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.setScale(5f);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(this);
        // populate the Map.
        for(int i = 0; i < 5; i++){
            touches.put(i, new TouchInfo());
        }
	}
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        message = "";
        for(int i = 0; i < 5; i++){
            if(touches.get(i).touched)
                message += "Finger:" + Integer.toString(i) + "touch at:" +
                        Float.toString(touches.get(i).touchX) +
                        "," +
                        Float.toString(touches.get(i).touchY) +
                        "\n";

        }
        TextBounds tb = font.getBounds(message);
        //float x = w/2 - tb.width/2;
        //float y = h/2 + tb.height/2;
        float x = (float)nX;
        float y = (float)nY;
        //font.drawMultiLine(batch, message, nX, nY);
        font.draw(batch, message, nX, nY);

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

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        nX = screenX;
        nY = screenY;
        if(pointer < 5){
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(pointer < 5){
            touches.get(pointer).touchX = 0;
            touches.get(pointer).touchY = 0;
            touches.get(pointer).touched = false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
