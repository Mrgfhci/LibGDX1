package taurockdeepdark;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Matthew Brock on 01/12/2014.
 */
public class FireBall {
    Sprite sFireBall;
    float fVelocityX, fVelocityY, fVelocityYS, fVelocityXS, fSHeight, fSWidth;
    OrthographicCamera camera;
    SpriteBatch sbSpriteBatch;

    FireBall(Texture tFireBall, float fX, float fY, int nRotation, OrthographicCamera camera_) {
        camera = camera_;
        fSHeight = Gdx.graphics.getHeight();
        fSWidth = Gdx.graphics.getWidth();
        fVelocityYS = fSHeight * 30 / 1080;
        fVelocityXS = fSWidth * 30 / 1794;
        sbSpriteBatch = new SpriteBatch();//This is bad use the one that already exists
        sFireBall = new Sprite(tFireBall);
        sFireBall.setOrigin(sFireBall.getWidth()/2,sFireBall.getHeight()/2);
        sFireBall.setRotation(nRotation - 90);
        sFireBall.setPosition(fX, fY);
        if (nRotation == 0) {//Set velocity based on rotation
            fVelocityX = 0;
            fVelocityY = -fVelocityYS;
        } else if (nRotation == 90) {
            fVelocityX = fVelocityXS;
            fVelocityY = 0;
        } else if (nRotation == 180) {
            fVelocityX = 0;
            fVelocityY = fVelocityYS;
        } else if (nRotation == 270) {
            fVelocityX = -fVelocityXS;
            fVelocityY = 0;
        }
    }


    public void render() {//Set matrix move then render

        sFireBall.translate(fVelocityX,fVelocityY);
        sbSpriteBatch.begin();
        sFireBall.draw(sbSpriteBatch);
        sbSpriteBatch.end();
    }

}