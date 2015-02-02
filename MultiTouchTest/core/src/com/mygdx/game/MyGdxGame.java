
//https://github.com/jrenner/gdx-smart-font
//Font size generator to fit screen
//use to make menus in the future

package com.mygdx.game;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.SmartFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
public class MyGdxGame extends ApplicationAdapter implements InputProcessor{
    private Stage stage;
    SpriteBatch batch;
    Texture img;
    private BitmapFont font;
    private String message = "Touch something already!";
    private int w,h;
    class TouchInfo {

        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }

    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
    @Override
    public void create () {
        SmartFontGenerator fontGen = new SmartFontGenerator();
        /*https://github.com/jrenner/gdx-smart-font/blob/master/src/org/jrenner/smartfont/example/ExampleMain.java*/
        FileHandle exoFile = Gdx.files.internal("LiberationMono-Regular.ttf");
        BitmapFont fontSmall = fontGen.createFont(exoFile, "exo-small", 24);
        BitmapFont fontMedium = fontGen.createFont(exoFile, "exo-medium", 48);
        BitmapFont fontLarge = fontGen.createFont(exoFile, "exo-large", 64);

        stage = new Stage();

        Label.LabelStyle smallStyle = new Label.LabelStyle();
        smallStyle.font = fontSmall;
        Label.LabelStyle mediumStyle = new Label.LabelStyle();
        mediumStyle.font = fontMedium;
        Label.LabelStyle largeStyle = new Label.LabelStyle();
        largeStyle.font = fontLarge;

        Label small = new Label("Small Font", smallStyle);
        Label medium = new Label("Medium Font", mediumStyle);
        Label large = new Label("Large Font", largeStyle);

        batch = new SpriteBatch();
        font = fontMedium;
        img = new Texture("fireball.png");
        font.setColor(Color.RED);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(this);
        for(int i = 0; i < 10; i++){
            touches.put(i, new TouchInfo());
        }
    }
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
        font.dispose();
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        message = "";
        for(int i = 0; i < 10; i++){
            if(touches.get(i).touched)
                message += "Finger:" + Integer.toString(i) + "touch at:" +
                        Float.toString(touches.get(i).touchX) +
                        "," +
                        Float.toString(touches.get(i).touchY) +
                        "\n";

        }
        TextBounds tb = font.getBounds(message);
        float x = w/2;
        float y = h/2;
        font.drawMultiLine(batch, message, y, x);
        //stage.act(Gdx.graphics.getDeltaTime());
        // stage.draw();
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
        if(pointer < 10){
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer < 10){
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
