package com.triforce_release5.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.triforce_release5.HUD;
import com.triforce_release5.TbsMenu;
import com.triforce_release5.TbText;
import com.triforce_release5.TriForceRelease5;

import java.util.Iterator;

/**
 * Created by Matthew on 12/9/2015.
 */
public class EasyState implements Screen, InputProcessor{
    TriForceRelease5 triForceRelease5;

    private TextureAtlas txAtlasBird; //This is for the BirdAnimation of the character.
    private Animation BirdAnimation;
    private float fElapsedTime = 0f, fBirdY = 180, fBirdX = 150, fOldX, fOldY;
    private Integer nGravity = 1, nClick = 50;
    private Sprite sprBird;
    boolean bHit;

    SpriteBatch sbBatch;
    Stage stage;

    TbsMenu tbsMenu;
    TextButton TbBack;

    Texture txTopTube, txBottTube; //Tube Stuff.
    Sprite sprTopTube, sprBottTube;
    OrthographicCamera camera;
    Array<Sprite> arsprTopTube, arsprBottTube;
    long lMoveTime, lMoveTime2;
    int nSpawnTime;
    float fTopTubeY;

    public EasyState(TriForceRelease5 triForceRelease5){ //References the main class.
        this.triForceRelease5=triForceRelease5;
        triForceRelease5.hud = new HUD(triForceRelease5.sbBatch);
    }

    public void create(){
        sbBatch = new SpriteBatch();
        stage = new Stage();
        tbsMenu = new TbsMenu();
        //Skin skin = tbsMenu.getSkin();//calls skin
        TbBack = new TbText("Back", tbsMenu);
        TbBack.setPosition(0, 0);
        txAtlasBird = new TextureAtlas(Gdx.files.internal("Bird.pack"));
        BirdAnimation = new Animation(1f/6f, txAtlasBird.getRegions());
        bHit = false;
// the png's are named incorrectly - september errors.
        txTopTube = new Texture(Gdx.files.internal("TopTube.png")); //Tube Stuff.
        txBottTube = new Texture(Gdx.files.internal("BottomTube.png"));
        nSpawnTime = 150;
        sprTopTube = new Sprite(txTopTube);
        sprBottTube = new Sprite(txBottTube);
        //Creating sprite and camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        arsprTopTube = new Array<Sprite>();
        arsprBottTube = new Array<Sprite>();
        spawnTopTube();
        spawnBottTube();

        stage.addActor(TbBack);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        create();

    }

    private void spawnTopTube() {
        Sprite sprTopTube = new Sprite(txTopTube);
        sprTopTube.setX(750);
        sprTopTube.setY(MathUtils.random(400 - 200) + 250);
        arsprTopTube.add(sprTopTube);
        fTopTubeY = sprTopTube.getY() - 200;
        lMoveTime = TimeUtils.nanoTime();
    }

    private void spawnBottTube() {
        Sprite sprBottTube = new Sprite(txBottTube);
        sprBottTube.setX(750);
        sprBottTube.setY(fTopTubeY - 300);
        arsprBottTube.add(sprBottTube);
        lMoveTime2 = TimeUtils.nanoTime();
    }

    private void updateBird(){
        sprBird = new Sprite(BirdAnimation.getKeyFrame(fElapsedTime, true));
        if(bHit == false) {
            fBirdY -= nGravity;
            sprBird.setX(fBirdX);
            sprBird.setY(fBirdY);
        }else{
            sprBird.setX(fOldX);
            sprBird.setY(fOldY -= nGravity);
            if(sprBird.getY() == 0){
                sprBird.setY(fOldY++);
                nGravity=0;
            }
        }
        sprBird.draw(sbBatch);
        fOldX = sprBird.getX();
        fOldY = sprBird.getY();
        if(Gdx.input.justTouched()){
            fBirdY += nClick;
        }
    }

    private void ResetBird(){
        bHit = false;
        fBirdX = 150;
        fBirdY = 180;
        nGravity = 1;
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        triForceRelease5.hud.stage.draw(); //Draw the hud.
        fElapsedTime += Gdx.graphics.getDeltaTime();
        sbBatch.setProjectionMatrix(camera.combined);
        sbBatch.begin();
        updateBird();
        for (Sprite sprTopTube : arsprTopTube) {
            sbBatch.draw(sprTopTube, sprTopTube.getX(), sprTopTube.getY());
            if(sprBird.getBoundingRectangle().overlaps(sprTopTube.getBoundingRectangle())) {
                System.out.println("Hit Top");
                bHit = true;
            }
        }
        for (Sprite sprBottTube : arsprBottTube) {
            sbBatch.draw(sprBottTube, sprBottTube.getX(), sprBottTube.getY());
            if(sprBird.getBoundingRectangle().overlaps(sprBottTube.getBoundingRectangle())) {
                System.out.println("Hit Bott");
                bHit = true;
            }
        }
        sbBatch.end();
        if(bHit == false) {
            if (TimeUtils.nanoTime() - lMoveTime > 100000000 * nSpawnTime) spawnTopTube();
            Iterator<Sprite> iter = arsprTopTube.iterator();
            while (iter.hasNext()) {
                Sprite sprTopTube = iter.next();
                sprTopTube.setX(sprTopTube.getX() - (200) * Gdx.graphics.getDeltaTime());
            }
        }else{
        }
        if(bHit == false) {
            if (TimeUtils.nanoTime() - lMoveTime2 > 100000000 * nSpawnTime) spawnBottTube();
            Iterator<Sprite> iters = arsprBottTube.iterator();
            while (iters.hasNext()) {
                Sprite sprBottTube = iters.next();
                sprBottTube.setX(sprBottTube.getX() - (200) * Gdx.graphics.getDeltaTime());
            }
        }else{
        }

        sbBatch.begin();
        sbBatch.end();
        triForceRelease5.hud.update();
        if (TbBack.isPressed()) {
            triForceRelease5.currentState = TriForceRelease5.GameState.PLAY;
            triForceRelease5.updateState();
            triForceRelease5.hud.reset();
            ResetBird();
        }
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
    public void hide() {

    }

    @Override
    public void dispose() {
        sbBatch.dispose();
        txAtlasBird.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
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
}
