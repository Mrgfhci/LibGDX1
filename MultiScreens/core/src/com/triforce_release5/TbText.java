package com.triforce_release5;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Abraham on 2015-11-12.
 */
public class TbText extends TextButton {
    String sText;
    // creating it based on a tbs - not a skin
    public TbText(String text, TextButtonStyle tbs) {
        super(text, tbs);
        sText = text;
        this.setSize(600, 200);
        this.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.out.println(sText);
            }
        });
    }
}