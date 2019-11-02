package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;
import com.versionpb.game.sprites.Bird;
import com.versionpb.game.sprites.TubeEasy_Screens;

import java.util.Random;


public class PlayEasyLevelScreen implements Screen,GestureDetector.GestureListener {

    protected BriskyBird game;

    private Bird bird;
    private Texture playBg, playBg_v1, playBg_v2, playBg_v3, playBg_v4, playBg_v5,playBg_v6,playBg_v7,playBg_v8,playBg_v9,playBg_v10,playBg_v11,playBg_v12,playBg_v13;
    private Texture ground;
    private Texture pause;

    private Vector2 groundPos1;
    private Vector2 groundPos2;

    private Array<TubeEasy_Screens> tubes;

    private Sound die, point;

    private int Score, MaxScore, OldScore;
    private float posCamScore;
    private boolean Scored;

    private BitmapFont font_Score,font_TapOnBird,font_LevelInfo;

    private Preferences prefs;

    private int RandomR, RandomG, RandomB;
    private Texture birdFrameImage, birdFrameImage_blue , birdFrameImage_black , birdFrameImage_green , birdFrameImage_lightblue, birdFrameImage_pink;

    GestureDetector gestureDetector;

    private Viewport viewport;
    private Stage stage;

    public PlayEasyLevelScreen(final BriskyBird game) {
        this.game = game;


        game.handler.showAds(true);

        gestureDetector = new GestureDetector(this);

        Gdx.input.setInputProcessor(gestureDetector);

        viewport = new StretchViewport(GameInfo.GAME_WIDTH/2 , GameInfo.GAME_HEIGHT/2,game.getCam());
        //viewport = new ExtendViewport(Screen_Width  , Screen_Height,cam);
        //viewport = new ScreenViewport(cam);
        viewport.apply();
        stage = new Stage(viewport,game.getBatch());
        Gdx.input.setInputProcessor(stage);


        //get assets into variables
        birdFrameImage = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage);
        birdFrameImage_blue = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_blue);
        birdFrameImage_black = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_black);
        birdFrameImage_green = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_green);
        birdFrameImage_lightblue = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_lightblue);
        birdFrameImage_pink = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_pink);
        playBg_v1 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v1);
        playBg_v2 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v2);
        playBg_v3 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v3);
        playBg_v4 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v4);
        playBg_v5 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v5);
        playBg_v6 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v6);
        playBg_v7 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v7);
        playBg_v8 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v8);
        playBg_v9 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v9);
        playBg_v10 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v10);
        playBg_v11 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v11);
        playBg_v12 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v12);
        playBg_v13 = game.myassetManager.manager.get(VersionPBAssetManager.playBg_v13);
        ground = game.myassetManager.manager.get(VersionPBAssetManager.ground);
        pause = game.myassetManager.manager.get(VersionPBAssetManager.pauseImage);

        die = game.myassetManager.manager.get(VersionPBAssetManager.dieMusicFile);
        point = game.myassetManager.manager.get(VersionPBAssetManager.pointMusicFile);

        font_Score = game.myassetManager.manager.get(VersionPBAssetManager.fontVPB);
        font_TapOnBird = game.myassetManager.manager.get(VersionPBAssetManager.fontChillar);
        font_LevelInfo = game.myassetManager.manager.get(VersionPBAssetManager.fontChillar_LevelInfo);


        Texture[] list = {birdFrameImage, birdFrameImage_blue, birdFrameImage_black, birdFrameImage_green,
                birdFrameImage_lightblue, birdFrameImage_pink};



        Random r = new Random();

        birdFrameImage = list[r.nextInt(list.length)];

        bird = new Bird(50, 300, birdFrameImage , game);


        Score = 0;
        OldScore = 0;
        Scored = false;
        posCamScore = 0;



        prefs = Gdx.app.getPreferences(GameInfo.PREFERENCES);

        RandomR = (0 + (int) (Math.random() * 255));
        RandomG = (0 + (int) (Math.random() * 255));
        RandomB = (0 + (int) (Math.random() * 255));


        groundPos1 = new Vector2(game.getCam().position.x - game.getCam().viewportWidth / 2, GameInfo.EASY_LEVEL_GROUND_OFFSET);
        groundPos2 = new Vector2((game.getCam().position.x - game.getCam().viewportWidth / 2) + ground.getWidth(), GameInfo.EASY_LEVEL_GROUND_OFFSET);
        game.getCam().setToOrtho(false, GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2);


        font_TapOnBird.setColor(Color.FIREBRICK);
        font_TapOnBird.getData().setScale(0.3f, 0.3f);


        font_LevelInfo.setColor(Color.ORANGE);
        font_LevelInfo.getData().setScale(0.2f, 0.2f);



        tubes = new Array<TubeEasy_Screens>();

        for (int i = 1; i <= GameInfo.EASY_LEVEL_TUBE_COUNT; i++) { // for loop for adding tubes to the array
            tubes.add(new TubeEasy_Screens(i * (GameInfo.EASY_LEVEL_TUBE_SPACING + TubeEasy_Screens.TUBE_WIDTH),game));


        }
    }

    @Override
    public void show() {

    }

    public void handleInput() {


        if (Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void render(float delta) {

        update(delta);

        //handleInput();
        game.getBatch().setProjectionMatrix(game.getCam().combined);
        Color c = new Color(game.getBatch().getColor());
        game.getBatch().begin();



        if (Score == 0)
            playBg = playBg_v1;
        if (OldScore + 2 == Score) {
            Texture[] list = {playBg_v1, playBg_v2, playBg_v3, playBg_v4, playBg_v5,playBg_v6, playBg_v7, playBg_v8, playBg_v9, playBg_v10, playBg_v11, playBg_v12, playBg_v13};
            Random r = new Random();
            playBg = list[r.nextInt(list.length)];

            OldScore = Score;
        }


        game.getBatch().draw(playBg, game.getCam().position.x - (game.getCam().viewportWidth / 2), 0, GameInfo.WIDTH/2, GameInfo.HEIGHT/2);

        game.getBatch().draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);


        game.getBatch().setColor(RandomR, RandomG, RandomB, 1);



        for (TubeEasy_Screens tube : tubes) {

            if (tube.getPosTopTube().x > 320 + 100) {
                game.getBatch().draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
                game.getBatch().draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);

            }
        }
        game.getBatch().setColor(c);


        font_Score.draw(game.getBatch(), Integer.toString(Score), game.getCam().position.x - font_Score.getSpaceWidth(), game.getCam().viewportHeight * 7 / 8);
        font_LevelInfo.draw(game.getBatch(), GameInfo.EASY_LEVEL_LevelInfo, game.getCam().position.x +20  , game.getCam().viewportHeight - 5);
        game.getBatch().draw(ground, groundPos1.x, groundPos1.y);
        game.getBatch().draw(ground, groundPos2.x, groundPos2.y);
        if (BriskyBird.running){
            game.getBatch().draw(pause,game.getCam().position.x - 80 - pause.getWidth(), pause.getHeight()/2);
        }

        if(!BriskyBird.running ){
            font_TapOnBird.draw(game.getBatch(), GameInfo.TAP_ON_BIRD_TO_BEGIN, game.getCam().position.x - 80  , groundPos1.y + ground.getHeight() - 20 );

        }
        game.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    public void update(float dt) {

        if (!BriskyBird.running) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCam().unproject(tmp);
            Rectangle textureBounds = new Rectangle(bird.getPosition().x, bird.getPosition().y, bird.getBounds().getWidth(), bird.getBounds().getHeight());
            if (textureBounds.contains(tmp.x, tmp.y)) {
                //System.out.println("Bird touched , Running true");
                BriskyBird.running = true;
            }
        }
        if (BriskyBird.running) {

            Vector3 pauseB = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCam().unproject(pauseB);

            Rectangle textureBoundsPause = new Rectangle(game.getCam().position.x - 80 - pause.getWidth(), pause.getHeight()/2, pause.getWidth(), pause.getHeight());
            if (textureBoundsPause.contains(pauseB.x, pauseB.y)) {
                //System.out.println("Pause Button touched , Running true");
                BriskyBird.running = false;

            }
            updateGround();
            handleInput();
            bird.update(dt);
            game.getCam().position.x = bird.getPosition().x + 80;

            //100 added in tubes gap horizontally
            if (game.getCam().position.x > 400 + 100) {
                for (TubeEasy_Screens tube : tubes) {
                    if ((game.getCam().position.x - (game.getCam().viewportWidth / 2) + 80 > tube.getPosTopTube().x + (tube.getTopTube().getWidth() / 2)) && !Scored) {
                        posCamScore = game.getCam().position.x;
                        //System.out.println("Inside Score Loop : posCamScore : Cam Position : " + posCamScore + " : " + game.getCam().position.x + " tube.getPosTopTube().x  : " + tube.getPosTopTube().x + " tube.getTopTube().getWidth() " + tube.getTopTube().getWidth() + " cam.viewportWidth / 2  + " + game.getCam().viewportWidth / 2 + " Score " + Score);
                        point.play(0.3f);
                        Score++;
                        Scored = true;
                        RandomB = (0 + (int) (Math.random() * 255));
                        RandomR = (0 + (int) (Math.random() * 255));
                        RandomG = (0 + (int) (Math.random() * 255));
                        if (Score % 2 == 0 && Score < 12) {
                            bird.setMOVEMENT(bird.getMOVEMENT() + 10);
                        }
                        if (Score % 5 == 0 && Score > 12) {
                            bird.setMOVEMENT(bird.getMOVEMENT() + 10);
                        }

                        //System.out.println("Score is  : " + Score);
                        //System.out.println("Movement is : " + bird.getMOVEMENT());
                    }

                    if ((game.getCam().position.x > bird.getTexture().getRegionWidth() / 3 + posCamScore + tube.getTopTube().getWidth() + 80) && Scored) {
                        //System.out.println(" Inside not Score Loop bird.getTexture().getWidth() + posCamScore : Cam Position : " + bird.getTexture().getRegionWidth() / 3 + " : " + posCamScore + ":" + game.getCam().position.x + " tube.getPosTopTube().x  : " + tube.getPosTopTube().x + " tube.getTopTube().getWidth() " + tube.getTopTube().getWidth() + " cam.viewportWidth / 2  + " + game.getCam().viewportWidth / 2 + " Score " + Score);

                        Scored = false;
                    }

                    if (game.getCam().position.x - (game.getCam().viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                        tube.reposition(tube.getPosTopTube().x + (TubeEasy_Screens.TUBE_WIDTH + GameInfo.EASY_LEVEL_TUBE_SPACING) * GameInfo.EASY_LEVEL_TUBE_COUNT);
                    }


                }


                for (int i = 0; i < GameInfo.EASY_LEVEL_TUBE_COUNT; i++) {
                    if (tubes.get(i).collides(bird.getBounds())) {
                        die.play(0.3f);

                        MaxScore = prefs.getInteger(GameInfo.EASY_LEVEL_HighScore);

                        if (Score > MaxScore) {
                            prefs.flush();
                            prefs.putInteger(GameInfo.EASY_LEVEL_HighScore, Score); // Add Maximum Score
                            prefs.flush();
                            //System.out.println("New Max. Score Made , Max Score is  : " + prefs.getInteger(GameInfo.EASY_LEVEL_HighScore));
                            MaxScore = prefs.getInteger(GameInfo.EASY_LEVEL_HighScore);
                            //System.out.println("PlayServices: Setting Score as : " + Score);
                            game.ply.submitScore(GameInfo.leaderboard_Easy,Score);
                            //System.out.println("PlayServices: Submitted Score as : " + Score );
                        }
                        prefs.flush();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            // Thread.currentThread().interrupt();
                        }
                        dispose();

                        game.setScreen(new GameOverScreen(game,Score, prefs.getInteger(GameInfo.EASY_LEVEL_HighScore)));
                    }


                }
            }
            //bird die in case it touches ground
            if (bird.getPosition().y <= ground.getHeight() + GameInfo.EASY_LEVEL_GROUND_OFFSET) {
                die.play(0.3f);

                MaxScore = prefs.getInteger(GameInfo.EASY_LEVEL_HighScore);

                if (Score > MaxScore) {
                    prefs.flush();
                    prefs.putInteger(GameInfo.EASY_LEVEL_HighScore, Score); // Add Maximum Score
                    prefs.flush();
                    //System.out.println("New Max. Score Made , Max Score is  : " + prefs.getInteger(GameInfo.EASY_LEVEL_HighScore));
                    MaxScore = prefs.getInteger(GameInfo.EASY_LEVEL_HighScore);
                    //System.out.println("PlayServices: Setting Score as : " + Score);
                    game.ply.submitScore(GameInfo.leaderboard_Easy,Score);
                    //System.out.println("PlayServices: Submitted Score as : " + Score );
                }
                prefs.flush();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //Thread.currentThread().interrupt();
                }
                dispose();

                game.setScreen(new GameOverScreen(game,Score, prefs.getInteger(GameInfo.EASY_LEVEL_HighScore)));
            }

            //bird die in case it touches sky
            if (bird.getPosition().y >= game.getCam().viewportHeight) {
                die.play(0.3f);

                MaxScore = prefs.getInteger(GameInfo.EASY_LEVEL_HighScore);

                if (Score > MaxScore) {
                    prefs.flush();
                    prefs.putInteger(GameInfo.EASY_LEVEL_HighScore, Score); // Add Maximum Score
                    prefs.flush();
                    //System.out.println("New Max. Score Made , Max Score is  : " + prefs.getInteger(GameInfo.EASY_LEVEL_HighScore));
                    MaxScore = prefs.getInteger(GameInfo.EASY_LEVEL_HighScore);
                    //System.out.println("PlayServices: Setting Score as : " + Score);
                    game.ply.submitScore(GameInfo.leaderboard_Easy,Score);
                    //System.out.println("PlayServices: Submitted Score as : " + Score );

                }
                prefs.flush();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //Thread.currentThread().interrupt();
                }
                dispose();

                //gsm.set(new GameOver(gsm,ply, Score, prefs.getInteger(GameInfo.EASY_LEVEL_HighScore)));
                game.setScreen(new GameOverScreen(game,Score, prefs.getInteger(GameInfo.EASY_LEVEL_HighScore)));
            }

            game.getCam().update();
        }
    }

    private void updateGround() {
        if (game.getCam().position.x - (game.getCam().viewportWidth / 2) > groundPos1.x + ground.getWidth()) {


            groundPos1.add(ground.getWidth() * 2, 0);


        }
        if (game.getCam().position.x - (game.getCam().viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);


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
        //stage.dispose();
        //skin.dispose();

        game.myassetManager.unloadEasyLevelTextures();
        game.myassetManager.unloadEasyMusic();
        game.myassetManager.unloadEasyLevelFonts();

        bird.setMOVEMENT(100);
        bird.dispose();
        stage.dispose();


        for (TubeEasy_Screens tube : tubes) {
            tube.dispose();
        }
        //System.out.println("Play State Disposed");
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
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
}




