package com.versionpb.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.versionpb.game.BriskyBird;

public class CopyPasteScreen implements Screen {

    protected BriskyBird game;
    private Texture img;

    Skin skin;
    Stage stage;


    public CopyPasteScreen(final BriskyBird game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);



        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setDebug(true);
        table.top(); //

        TextButton MainMenuButton = new TextButton("MainMenu", skin);
        MainMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        table.add(MainMenuButton).width(250).height(60).padBottom(60);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

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
        stage.dispose();
        skin.dispose();
    }
}


