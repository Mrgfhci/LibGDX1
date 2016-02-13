package com.triforce_release5;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Matthew on 11/23/2015.
 */
public class HUD {
    public Stage stage;
    private Viewport vpHud; //Hud's viewport.

    private Integer nHighScore, nCurrentHighScore; //Score, coin, and time variables.
    private Integer nCoins;
    private Integer nTimer;
    float fTimer;

    Label lblCurrScore;
    Label lblHighScore;
    Label lblCoins;
    Label lblTimer;
    //Labels to display.
    Label lblCurrScoreWords;
    Label lblHighScoreWords;
    Label lblCoinsWords;
    Label lblTimerWords;

    Preferences Score; //For saving variables.

    public HUD(SpriteBatch spriteBatch) { //Thanks Cam & Brent Aureli (https://www.youtube.com/watch?v=7idwNW5a8Qs).
        Score = Gdx.app.getPreferences("High Score");
        nHighScore = Score.getInteger("High Score", 0);
        nCurrentHighScore = 0;

        nCoins = 0;
        nTimer = 0;
        fTimer = 0f;

        vpHud = new FitViewport(200, 400, new OrthographicCamera());
        stage = new Stage(vpHud, spriteBatch);

        Table tHudTable = new Table();
        tHudTable.top(); //Sets the table to the top of the screen.
        tHudTable.setFillParent(true); //Can easily be added to the stage.

        lblCurrScore = new Label(String.format("%03d", nCurrentHighScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //3 decimal spaces.
        lblHighScore = new Label(String.format("%03d", nHighScore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lblCoins = new Label(String.format("%03d", nCoins), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //White text.
        lblTimer = new Label(String.format("%03d", nTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lblCurrScore.setFontScale((float) 0.7); //Scales the fonts; makes it smaller to fit the screen.
        lblHighScore.setFontScale((float) 0.7);
        lblCoins.setFontScale((float) 0.7);
        lblTimer.setFontScale((float) 0.7);

        lblCurrScoreWords = new Label("C. SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lblHighScoreWords = new Label("H. SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lblCoinsWords = new Label("COINS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lblTimerWords = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lblCurrScoreWords.setFontScale((float) 0.7);
        lblHighScoreWords.setFontScale((float) 0.7);
        lblCoinsWords.setFontScale((float) 0.7);
        lblTimerWords.setFontScale((float) 0.7);

        tHudTable.add(lblCurrScoreWords).expandX().padTop(10); //Expands labels, fills the extra space.
        tHudTable.add(lblHighScoreWords).expandX().padTop(10); //Padding gives extra space around the cell.
        tHudTable.add(lblCoinsWords).expandX().padTop(10);
        tHudTable.add(lblTimerWords).expandX().padTop(10);
        tHudTable.row(); //Sets a new cell (row).
        tHudTable.add(lblCurrScore).expandX();
        tHudTable.add(lblHighScore).expandX();
        tHudTable.add(lblCoins).expandX();
        tHudTable.add(lblTimer).expandX();

        stage.addActor(tHudTable);
    }

    public void update() {
        lblCurrScore.setText(Integer.toString(nCurrentHighScore));
        fTimer += Gdx.graphics.getDeltaTime();
        if(fTimer > 1f){
            nTimer++;
            fTimer = 0f;
        }
        lblTimer.setText(Integer.toString(nTimer) + "s");
        if (Gdx.input.justTouched()) {
            nCurrentHighScore += 1;
            SoundFiles.FlapSound.play(); //When you click the screen, it plays the "hitmarker" sound.
        }
        if (nCurrentHighScore > nHighScore) { //If your current score is higher than the saved high score, that becomes the new high score and gets saved.
            Score.putInteger("High Score", nCurrentHighScore);
            nHighScore = Score.getInteger("HighScore", nCurrentHighScore);
            lblHighScore.setText(Integer.toString(nCurrentHighScore));
            Score.flush();
        }
        Score.flush(); //Saves it after the program is closed.
        //Score.clear(); //This will clear the high score.
    }

    public void reset(){
        nCurrentHighScore = 0;
        nTimer = 0;
        lblCurrScore.setText(Integer.toString(nCurrentHighScore));
        lblHighScore.setText(Integer.toString(nHighScore));
        lblTimer.setText(Integer.toString(nTimer));
    }
}


