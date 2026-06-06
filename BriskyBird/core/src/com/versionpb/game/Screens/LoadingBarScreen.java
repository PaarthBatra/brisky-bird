package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;

public class LoadingBarScreen implements Screen {

    public BriskyBird game;
    private Skin skin;
    private Stage stage;
    private OrthographicCamera cam;
    private Viewport viewport;

    ProgressBar progressBar;
    Label label,labelLoading;
    float progress;

    //just to capture starting time
    long startTime= TimeUtils.millis();


    public LoadingBarScreen(final BriskyBird game){
        this.game = game;

        game.handler.showAds(false);

        cam = new OrthographicCamera();
        viewport = new FitViewport(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT, cam);
        stage = new Stage(viewport,game.getBatch());
        Gdx.input.setInputProcessor(stage);
        //Gdx.app.log("LOG","very first " + TimeUtils.millis() + " Time since then : " + TimeUtils.timeSinceMillis(startTime));
        game.myassetManager.loadSplashSkin();
        //Gdx.app.log("LOG","Loading " + TimeUtils.millis() + " Time since then : " + TimeUtils.timeSinceMillis(startTime));

        try{
            skin = game.myassetManager.manager.get(VersionPBAssetManager.splash_skin);
        }
        catch (Exception e) {
            skin = new Skin(Gdx.files.internal(VersionPBAssetManager.splash_skin));
        }

        Table splashScreenTable = new Table();
        splashScreenTable.setFillParent(true);
        stage.addActor(splashScreenTable);


        label = new Label(progress + " %",skin,GameInfo.loadingLabelStyleName);
        //labelLoading = new Label(String.format("%1$-13s", "Loading"),skin,"title-plain");
        labelLoading = new Label(GameInfo.loadingLabelText,skin,GameInfo.loadingLabelStyleName);

        progressBar = new ProgressBar(0, 100, 1, false, skin);
        progressBar.setValue(0);
        progressBar.setAnimateDuration(.3f);

        splashScreenTable.add(label).padTop(GameInfo.labelTopPadding);
        splashScreenTable.row();
        splashScreenTable.add(progressBar).width(GameInfo.GAME_WIDTH - GameInfo.progressBarXOffset);
        splashScreenTable.row();
        splashScreenTable.row();
        splashScreenTable.row();
        splashScreenTable.add(labelLoading).padTop(GameInfo.labelLoadingTopPadding);



        game.myassetManager.loadSplashImage();
        game.myassetManager.loadSplashFont();
        game.myassetManager.loadSkin();
        game.myassetManager.loadMenuFont();
        game.myassetManager.loadImages();
        game.myassetManager.loadMenuMusic();
        game.myassetManager.loadhighScoreScreenFonts();
        game.myassetManager.loadhighScoreScreenImages();
        game.myassetManager.loadHowToPlayScreenFonts();
        game.myassetManager.loadHowToPlayScreenImages();
        // Pre-load fontChillar so it's available on ChooseBirdScreen
        game.myassetManager.manager.load(VersionPBAssetManager.fontChillar, BitmapFont.class);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(game.myassetManager.manager.update()){
            //Gdx.app.log("LOG","after finishLoading " + TimeUtils.millis() + " Time since then : " + TimeUtils.timeSinceMillis(startTime) );
            game.setScreen(new SplashScreen(game));

        }









        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        progress = game.myassetManager.manager.getProgress();
        progressBar.setValue(progress*100);
        label.setText(  String.format("%.2f",progress*100) + " %");


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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
}
