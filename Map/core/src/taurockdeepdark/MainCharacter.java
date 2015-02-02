package taurockdeepdark;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by Matthew Brock on 27/10/2014.
 */

//https://github.com/libgdx/libgdx/wiki/2D-Animation
public class MainCharacter implements ApplicationListener {
    Map[] armMaps;
    OrthographicCamera camera;
    float fCharacterVelocityX = 0, fCharacterVelocityY = 0, fCharacterX, fCharacterY;
    int nSHeight, nSWidth, nCharacterRotation = 1, nCharacterWidth, nCharacterHeight, nLayerCount, nCurrentMap = 0;
    Animation[] araWalking;
    Texture tTemp;
    SpriteBatch sbSpriteBatch;
    float stateTime;
    float fOldX, fOldY, tileWidth, tileHeight;
    boolean bCollidedX, bCollidedY, bJustSet;


    public void setMaps(Map[] armMaps_) {
        armMaps = armMaps_;
    }

    public void setCamera(OrthographicCamera camera_) {
        camera = camera_;
    }


    @Override
    public void create() {
        nSHeight = Gdx.graphics.getHeight(); //use to make scaling
        nSWidth = Gdx.graphics.getWidth();
        camera.setToOrtho(false, nSWidth, nSHeight);
        camera.update();
        nCharacterWidth = nSWidth * 110 / 1794;
        nCharacterHeight = nSHeight * 120 / 1080;
        araWalking = new Animation[8];//array of animations
        sbSpriteBatch = new SpriteBatch();//use to draw multiple sprites at once apparently better
        for (int i = 0; i < 8; i++) {
            int k = 1;
            tTemp = new Texture(Gdx.files.internal("BadLuck" + i + ".png"));
            if (i > 3) {
                k = 3;
            }
            araWalking[i] = build(tTemp, 1, k);//Populating an array of animations using my method BuildAnimation
        }
        stateTime = 0f;
        tileWidth = armMaps[nCurrentMap].nMapScale * (armMaps[nCurrentMap].arclCollisionLayer[0].getTileWidth());//Grabbing the tile width for the tiledMap
        tileHeight = armMaps[nCurrentMap].nMapScale * (armMaps[nCurrentMap].arclCollisionLayer[0].getTileHeight());
        fCharacterX = 42*tileWidth-(tileWidth/2);
        fCharacterY = 7*tileHeight-(tileHeight/2);

    }

    public Animation build(Texture tTexture, int nRows, int nCols) {
        TextureRegion[] trTextureRegion;
        Animation aAnimation;
        int nCount1 = 0;
        TextureRegion[][] tmp = TextureRegion.split(tTexture, tTexture.getWidth() / nCols, tTexture.getHeight() / nRows);//Making an array that holds the region of each image and the image in that region
        trTextureRegion = new TextureRegion[nCols * nRows];//Making a 1d array with a length that is the same as the number of regions
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                trTextureRegion[nCount1++] = tmp[i][j];//Filling the 1d array with the regions from the 2d array
            }
        }
        aAnimation = new Animation(0.10f, trTextureRegion);//Making animation with the array that is the texture region and setting the frame rate
        return aAnimation;
    }


    @Override
    public void resize(int width, int height) {

    }

    public void setCharacterRotation(int nRotation) {
        nCharacterRotation = nRotation;
    }

    public void setCharacterVelocity(int VelocityX, int VelocityY) {
        fCharacterVelocityX = VelocityX;
        fCharacterVelocityY = VelocityY;
    }

    public boolean getTileID(float fX, float fY, int nWidth, String sID) {// this is slightly complicated but its basically grabbing the tile that the character is standing on and getting the ID
        boolean bCollided = false;
        for (nLayerCount = 0; nLayerCount < armMaps[nCurrentMap].tiledMap.getLayers().getCount() - 1; nLayerCount++) {

            bCollided = armMaps[nCurrentMap].arclCollisionLayer[nLayerCount].getCell((int) ((fX + nWidth / 4) / tileWidth), (int) (fY / tileHeight))
                    .getTile().getProperties().containsKey(sID);

            bCollided |= armMaps[nCurrentMap].arclCollisionLayer[nLayerCount].getCell((int) ((fX + 3 * nWidth / 4) / tileWidth), (int) (fY / tileHeight))
                    .getTile().getProperties().containsKey(sID);

            bCollided |= armMaps[nCurrentMap].arclCollisionLayer[nLayerCount].getCell((int) ((fX + nWidth / 2) / tileWidth), (int) (fY / tileHeight))
                    .getTile().getProperties().containsKey(sID);
        }
        return bCollided;
    }

    @Override
    public void render() {
        camera.position.y = fCharacterY;
        camera.position.x = fCharacterX;
        sbSpriteBatch.setProjectionMatrix(camera.combined);
        camera.update();




        fOldX = fCharacterX;//This is used for resetting the players position if they hit a wall
        fOldY = fCharacterY;

        fCharacterX += fCharacterVelocityX;//Move character
        bCollidedX = getTileID(fCharacterX, fCharacterY, nCharacterWidth, "Block");//Did it touched a tile with the block ID

        if (bCollidedX) {//If it touched a tile with the block ID reset the position
            fCharacterX = fOldX;
        }

        fCharacterY += fCharacterVelocityY;//This is the same as the previous bit but for the Y direction
        bCollidedY = getTileID(fCharacterX, fCharacterY, nCharacterWidth, "Block");

        if (bCollidedY) {
            fCharacterY = fOldY;
        }


        if (getTileID(fCharacterX, fCharacterY, nCharacterWidth, "MoveUp")) {//This checks if the character is standing on a door
            if (nCurrentMap < armMaps.length - 1 && !bJustSet) {
                nCurrentMap++;//If the character was standing on a door change the map
                bJustSet = true;//So it only changes the map one time and not every time render is called
            }
        }

        if (getTileID(fCharacterX, fCharacterY, nCharacterWidth, "Walkable")) {//If the character has moved off the door the map can be changed again
            bJustSet = false;
        }


        if (getTileID(fCharacterX, fCharacterY, nCharacterWidth, "MoveDown")) {//Same as the previous bit but for the door the decreases the map index
            if (nCurrentMap > 0 && !bJustSet) {
                nCurrentMap--;
                bJustSet = true;
            }
        }

        if (getTileID(fCharacterX, fCharacterY, nCharacterWidth, "Walkable")) {
            bJustSet = false;
        }


        stateTime += Gdx.graphics.getDeltaTime();//Getting a time to select a frame from the animation
        sbSpriteBatch.begin();
        sbSpriteBatch.draw(araWalking[nCharacterRotation].getKeyFrame(stateTime, true), fCharacterX, fCharacterY, nCharacterWidth, nCharacterHeight);//Drawing the animation from the array of animations based on the character rotation
        sbSpriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

