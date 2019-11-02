package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;


public class HowToPlayScreen implements Screen ,GestureDetector.GestureListener,InputProcessor {

    protected BriskyBird game;


    Stage stage;
    private Texture background;

    private BitmapFont font;

    public boolean tapone,taptwo = false;

    GestureDetector gestureDetector;


    public HowToPlayScreen(final BriskyBird game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());

        gestureDetector = new GestureDetector(this);

        gestureDetector.setLongPressSeconds(0.1f);

        InputMultiplexer im = new InputMultiplexer(stage,gestureDetector);


        Gdx.input.setInputProcessor(im);

        game.getCam().setToOrtho(false, GameInfo.WIDTH , GameInfo.HEIGHT );
        background = game.myassetManager.manager.get(VersionPBAssetManager.backgroundHowToPlayScreen);



        //music = Gdx.audio.newMusic(Gdx.files.internal("musicbg.ogg"));
        //music.setLooping(true);
        //music.setVolume(0.01f);
        //music.play();

        font = game.myassetManager.manager.get(VersionPBAssetManager.fontChillar_hotToPlayInfo,BitmapFont.class);
        font.setColor(Color.BLACK);
        font.getData().setScale(0.5f, 0.5f);   // make font size big


    }

    @Override
    public void show() {

    }

    public void handleInput() {

        if (taptwo) {
            game.setScreen(new LoadingBarScreen(game));
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0.2f, 0.4f, 1); // clear screen with almost bluish colour
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(game.getCam().combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0,GameInfo.WIDTH, GameInfo.HEIGHT);

         font.draw(game.getBatch(), GameInfo.howToPlayMessage, GameInfo.WIDTH / 8, GameInfo.HEIGHT - 100);



        game.getBatch().end();
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

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        //Gdx.app.log("tap","Count is: " + count);
        //System.out.println("tap Detected");
        if(tapone)
            taptwo = true;
        tapone = true;

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        //Gdx.app.log("tap","LOng pressed : " );
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
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


