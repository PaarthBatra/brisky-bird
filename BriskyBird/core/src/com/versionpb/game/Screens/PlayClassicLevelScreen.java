package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.versionpb.game.sprites.Bird;
import com.versionpb.game.sprites.Tube_Screens;

import java.util.Random;


public class PlayClassicLevelScreen implements Screen,GestureDetector.GestureListener {

    protected BriskyBird game;

    private Bird bird;
    private Texture playBg, playBg_v1, playBg_v2, playBg_v3, playBg_v4, playBg_v5,playBg_v6,playBg_v7,playBg_v8,playBg_v9,playBg_v10,playBg_v11,playBg_v12,playBg_v13;
    private Texture ground;
    private Texture pause, play, mute, unmute;
    private Music music;
    private boolean isMuted;

    private Vector2 groundPos1;
    private Vector2 groundPos2;

    private Array<Tube_Screens> tubes;

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
    private boolean isDying = false;
    private float deathTimer = 0f;
    private float camBaseX;
    private float camBaseY;
    private float shakeDuration = 0f;
    private float shakeIntensity = 0f;

    //private Music music;

    public PlayClassicLevelScreen(final BriskyBird game) {
        this.game = game;


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
        pause.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        play = game.myassetManager.manager.get(VersionPBAssetManager.playImage);
        play.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mute = game.myassetManager.manager.get(VersionPBAssetManager.muteImage);
        mute.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        unmute = game.myassetManager.manager.get(VersionPBAssetManager.unmuteImage);
        unmute.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        die = game.myassetManager.manager.get(VersionPBAssetManager.dieMusicFile);
        point = game.myassetManager.manager.get(VersionPBAssetManager.pointMusicFile);


        font_Score = game.myassetManager.manager.get(VersionPBAssetManager.fontVPB);
        font_TapOnBird = game.myassetManager.manager.get(VersionPBAssetManager.fontChillar);
        font_LevelInfo = game.myassetManager.manager.get(VersionPBAssetManager.fontChillar_LevelInfo);


        //music = game.myassetManager.manager.get(VersionPBAssetManager.MenuMusicFile);

        //music.setLooping(true);
        //music.setVolume(0.03f);
        //music.play();


        Preferences tempPrefs = Gdx.app.getPreferences(GameInfo.PREFERENCES);
        isMuted = tempPrefs.getBoolean("isMuted", false);

        music = game.myassetManager.manager.get(VersionPBAssetManager.MenuMusicFile);
        music.setLooping(true);
        music.setVolume(isMuted ? 0f : 0.03f);
        music.play();

        String selectedBird = tempPrefs.getString("SelectedBird", "frame-1");

        if (selectedBird.equals("birdanimation_black")) {
            bird = new Bird(50, 300, birdFrameImage_black, game);
        } else if (selectedBird.equals("birdanimation_blue")) {
            bird = new Bird(50, 300, birdFrameImage_blue, game);
        } else if (selectedBird.equals("birdanimation_green")) {
            bird = new Bird(50, 300, birdFrameImage_green, game);
        } else if (selectedBird.equals("birdanimation_lightblue")) {
            bird = new Bird(50, 300, birdFrameImage_lightblue, game);
        } else if (selectedBird.equals("birdanimation_pink")) {
            bird = new Bird(50, 300, birdFrameImage_pink, game);
        } else if (selectedBird.equals("birdanimation")) {
            bird = new Bird(50, 300, birdFrameImage, game);
        } else if (selectedBird.equals("Redframe-1")) {
            Array<TextureRegion> frames = new Array<TextureRegion>();
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdRedFrame1, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdRedFrame2, Texture.class)));
            bird = new Bird(50, 300, frames, game);
        } else {
            // Default: "frame-1" (yellow bird)
            Array<TextureRegion> frames = new Array<TextureRegion>();
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame1, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame2, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame3, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame4, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame5, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame6, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame7, Texture.class)));
            frames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame8, Texture.class)));
            bird = new Bird(50, 300, frames, game);
        }
        Score = 0;
        OldScore = 0;
        Scored = false;
        posCamScore = 0;




        prefs = Gdx.app.getPreferences(GameInfo.PREFERENCES);

        RandomR = (0 + (int) (Math.random() * 255));
        RandomG = (0 + (int) (Math.random() * 255));
        RandomB = (0 + (int) (Math.random() * 255));


        groundPos1 = new Vector2(game.getCam().position.x - game.getCam().viewportWidth / 2, GameInfo.CLASSIC_LEVEL_GROUND_OFFSET);
        groundPos2 = new Vector2((game.getCam().position.x - game.getCam().viewportWidth / 2) + ground.getWidth(), GameInfo.CLASSIC_LEVEL_GROUND_OFFSET);
        game.getCam().setToOrtho(false, GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2);



        //setting for Fonts
        font_TapOnBird.setColor(Color.FIREBRICK);
        font_TapOnBird.getData().setScale(0.3f, 0.3f);


        font_LevelInfo.setColor(Color.ORANGE);
        font_LevelInfo.getData().setScale(0.2f, 0.2f);

        tubes = new Array<Tube_Screens>();

        for (int i = 1; i <= GameInfo.CLASSIC_LEVEL_TUBE_COUNT; i++) { // for loop for adding tubes to the array
            tubes.add(new Tube_Screens(i * (GameInfo.CLASSIC_LEVEL_TUBE_SPACING + Tube_Screens.TUBE_WIDTH),game));

        }


        game.handler.showAds(true);
    }

    @Override
    public void show() {

    }

    public void handleInput() {


        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCam().unproject(touchPos);

            float btnW = 22f;
            float btnH = 22f;
            float startX = game.getCam().position.x - 50f;
            float btnY = groundPos1.y + ground.getHeight() - 20;

            Rectangle buttonsArea = new Rectangle(startX, btnY, 100f, btnH);
            if (!buttonsArea.contains(touchPos.x, touchPos.y)) {
                bird.jump(isMuted);
            }
        }
    }

    @Override
    public void render(float delta) {

        //handleInput();
        update(delta);

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





        game.getBatch().draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y, bird.getBounds().getWidth(), bird.getBounds().getHeight());


        game.getBatch().setColor(RandomR, RandomG, RandomB, 1);

        for (Tube_Screens tube : tubes) {
            if (tube.getPosTopTube().x > 320) {
                game.getBatch().draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
                game.getBatch().draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);

            }
        }
        game.getBatch().setColor(c);


        font_Score.draw(game.getBatch(), Integer.toString(Score), game.getCam().position.x - font_Score.getSpaceXadvance(), game.getCam().viewportHeight * 7 / 8);
        font_LevelInfo.draw(game.getBatch(), GameInfo.CLASSIC_LEVEL_LevelInfo, game.getCam().position.x +20  , game.getCam().viewportHeight - 5);
        game.getBatch().draw(ground, groundPos1.x, groundPos1.y);
        game.getBatch().draw(ground, groundPos2.x, groundPos2.y);
        if (!isDying) {
            float btnW = 22f;
            float btnH = 22f;
            float startX = game.getCam().position.x - 50f;
            float btnY = groundPos1.y + ground.getHeight() - 20;

            game.getBatch().draw(pause, Math.round(startX), Math.round(btnY), btnW, btnH);
            game.getBatch().draw(play, Math.round(startX + 26), Math.round(btnY), btnW, btnH);
            game.getBatch().draw(mute, Math.round(startX + 52), Math.round(btnY), btnW, btnH);
            game.getBatch().draw(unmute, Math.round(startX + 78), Math.round(btnY), btnW, btnH);
        }

        if(!BriskyBird.running ){
            font_TapOnBird.draw(game.getBatch(), GameInfo.TAP_ON_BIRD_TO_BEGIN, game.getCam().position.x - 80  , groundPos1.y + ground.getHeight() - 20 );

        }
        game.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


    }

    private void triggerDeath() {
        if (isDying) return;
        isDying = true;

        die.play(isMuted ? 0f : 0.3f);
        Gdx.input.vibrate(500);
        bird.setMOVEMENT(0);

        camBaseX = game.getCam().position.x;
        camBaseY = game.getCam().position.y;
        shakeDuration = 0.3f;
        shakeIntensity = 4f;

        MaxScore = prefs.getInteger(GameInfo.CLASSIC_LEVEL_HighScore);
        if (Score > MaxScore) {
            prefs.putInteger(GameInfo.CLASSIC_LEVEL_HighScore, Score);
            prefs.flush();
            game.ply.submitScore(GameInfo.leaderboard_Classic, Score);
        }
    }

    public void update(float dt) {
        if (isDying) {
            bird.update(dt);
            if (shakeDuration > 0) {
                shakeDuration -= dt;
                float shakeX = ((float) Math.random() - 0.5f) * 2 * shakeIntensity;
                float shakeY = ((float) Math.random() - 0.5f) * 2 * shakeIntensity;
                game.getCam().position.set(camBaseX + shakeX, camBaseY + shakeY, 0);
            } else {
                game.getCam().position.set(camBaseX, camBaseY, 0);
            }
            if (bird.getPosition().y <= ground.getHeight() + GameInfo.CLASSIC_LEVEL_GROUND_OFFSET) {
                deathTimer += dt;
                if (deathTimer >= 0.5f) {
                    dispose();
                    game.setScreen(new GameOverScreen(game, Score, prefs.getInteger(GameInfo.CLASSIC_LEVEL_HighScore)));
                }
            }
            game.getCam().update();
            return;
        }

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCam().unproject(touchPos);

            float btnW = 22f;
            float btnH = 22f;
            float startX = game.getCam().position.x - 50f;
            float btnY = groundPos1.y + ground.getHeight() - 20;

            Rectangle pauseBounds = new Rectangle(startX, btnY, btnW, btnH);
            Rectangle playBounds = new Rectangle(startX + 26, btnY, btnW, btnH);
            Rectangle muteBounds = new Rectangle(startX + 52, btnY, btnW, btnH);
            Rectangle unmuteBounds = new Rectangle(startX + 78, btnY, btnW, btnH);

            if (pauseBounds.contains(touchPos.x, touchPos.y)) {
                BriskyBird.running = false;
            } else if (playBounds.contains(touchPos.x, touchPos.y)) {
                BriskyBird.running = true;
            } else if (muteBounds.contains(touchPos.x, touchPos.y)) {
                isMuted = true;
                music.setVolume(0f);
                prefs.putBoolean("isMuted", true);
                prefs.flush();
            } else if (unmuteBounds.contains(touchPos.x, touchPos.y)) {
                isMuted = false;
                music.setVolume(0.03f);
                prefs.putBoolean("isMuted", false);
                prefs.flush();
            } else if (!BriskyBird.running) {
                Rectangle birdBounds = new Rectangle(bird.getPosition().x, bird.getPosition().y, bird.getBounds().getWidth(), bird.getBounds().getHeight());
                if (birdBounds.contains(touchPos.x, touchPos.y)) {
                    BriskyBird.running = true;
                    bird.jump(isMuted);
                }
            }
        }

        if (BriskyBird.running) {
            updateGround();
            handleInput();
            bird.update(dt);
            game.getCam().position.x = bird.getPosition().x + 80;

            if (game.getCam().position.x > 400) {
                for (Tube_Screens tube : tubes) {
                    if ((game.getCam().position.x - (game.getCam().viewportWidth / 2) + 80 > tube.getPosTopTube().x + (tube.getTopTube().getWidth() / 2)) && !Scored) {
                        posCamScore = game.getCam().position.x;
                        point.play(isMuted ? 0f : 0.3f);
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
                    }

                    if ((game.getCam().position.x > bird.getTexture().getRegionWidth() / 3 + posCamScore + tube.getTopTube().getWidth() + 80) && Scored) {
                        Scored = false;
                    }

                    if (game.getCam().position.x - (game.getCam().viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                        tube.reposition(tube.getPosTopTube().x + (Tube_Screens.TUBE_WIDTH + GameInfo.CLASSIC_LEVEL_TUBE_SPACING) * GameInfo.CLASSIC_LEVEL_TUBE_COUNT);
                    }
                }

                // Check tube collisions
                for (int i = 0; i < GameInfo.CLASSIC_LEVEL_TUBE_COUNT; i++) {
                    if (tubes.get(i).collides(bird.getBounds())) {
                        triggerDeath();
                        return;
                    }
                }
            }

            // Check ground collision
            if (bird.getPosition().y <= ground.getHeight() + GameInfo.CLASSIC_LEVEL_GROUND_OFFSET) {
                triggerDeath();
                return;
            }

            // Check sky collision
            if (bird.getPosition().y >= game.getCam().viewportHeight) {
                triggerDeath();
                return;
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


        game.myassetManager.unloadClassicLevelTextures();
        game.myassetManager.unloadClassicMusic();
        game.myassetManager.unloadClassicLevelFonts();
        bird.setMOVEMENT(100);
        bird.dispose();
        stage.dispose();
        music.stop();


        //font.dispose();


        for (Tube_Screens tube : tubes) {
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

    @Override
    public void pinchStop() {
    }
}




