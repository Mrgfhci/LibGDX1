package com.triforce_release5;
// this strips out the screen management from TriForce - using a screen manager.

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.triforce_release5.Screens.EasyState;
import com.triforce_release5.Screens.HardState;
import com.triforce_release5.Screens.ImpossState;
import com.triforce_release5.Screens.ScrInstruct;
import com.triforce_release5.Screens.ScrMenuMain;
import com.triforce_release5.Screens.MediumState;
import com.triforce_release5.Screens.ScrLevelChoice;

public class TriForceRelease5 extends Game {
	ScrMenuMain scrMenuMain; //Instances of the other classes to call.
	ScrLevelChoice scrLevelChoice;
	ScrInstruct scrInstruct;
	EasyState easyState;
	MediumState mediumState;
	HardState hardState;
	ImpossState impossState;

	public HUD hud; //Instance of the hud.

	public SpriteBatch sbBatch;

	public enum GameState{           //http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
		MENU, PLAY , INSTRUCT,       //http://www.kilobolt.com/day-10-gamestates-and-high-score.html
		EASY, MEDIUM, HARD,          //http://ics3ui.sgrondin.ca/ss23/LibGDX.html
		IMPOSSIBLE                   //Different states.
	}

	public GameState currentState; //Current state.

	public void updateState(){ //Updates to different states based on what the current one is.
		if(currentState==GameState.MENU){
			setScreen(scrMenuMain);
		}else if(currentState==GameState.PLAY){
			setScreen(scrLevelChoice);
		}else if(currentState==GameState.INSTRUCT){
			setScreen(scrInstruct);
		}else if(currentState==GameState.EASY){
			setScreen(easyState);
		}else if(currentState==GameState.MEDIUM){
			setScreen(mediumState);
		}else if(currentState==GameState.HARD){
			setScreen(hardState);
		}else if(currentState==GameState.IMPOSSIBLE){
			setScreen(impossState);
		}
	}

	@Override
	public void create () {
		sbBatch = new SpriteBatch();

		scrMenuMain = new ScrMenuMain(this);
		scrLevelChoice = new ScrLevelChoice(this);
		scrInstruct = new ScrInstruct(this);
		easyState = new EasyState(this);
		mediumState = new MediumState(this);
		hardState = new HardState(this);
		impossState = new ImpossState(this);

		SoundFiles.load(); //Loads the music file in the BackgroundMusic class, and then plays it based on the criteria there.

		currentState = GameState.MENU; //Set the current state to the main menu, and update it.
		updateState();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	//If you include this render, it will display whatever is here, and not the render in the other classes.
	//@Override
	//public void render () {
	//Gdx.gl.glClearColor(1, 0, 0, 1);
	//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	//}
}
