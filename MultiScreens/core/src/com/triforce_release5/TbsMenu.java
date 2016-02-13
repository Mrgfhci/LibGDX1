package com.triforce_release5;
// this was originally a "skin" class, but now, I am making it a TextButtonStyle. Why?
// TextButton constructors can take either a skin, or tbs. The tbs does more - it contains a skin.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 */
public class TbsMenu extends TextButton.TextButtonStyle {
    Skin skin = new Skin(); // a generic skin for the tbs.
    public TbsMenu(){
    TextureAtlas taNewGame;
        BitmapFont font = new BitmapFont();
        skin.add("default", font);
        taNewGame = new TextureAtlas(Gdx.files.internal("MenuButton.pack"));
        skin.addRegions(taNewGame);
        this.over = skin.getDrawable("MenuButtonDown");
        this.up = skin.getDrawable("MenuButtonUp");
        this.down = skin.getDrawable("MenuButtonDown");
        this.font = skin.getFont("default");

    }

    public TextButton.TextButtonStyle getTbs() {
        return this;
    }
}