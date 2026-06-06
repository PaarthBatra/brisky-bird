package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class BBMainMenuScreen implements Screen {

    public BriskyBird game;

    private Skin skin, skinVPB, skinFreezing;
    private Stage stage;
    private Texture backGroundImage, SignInImage, birdImage;
    private OrthographicCamera cam;
    private Viewport viewport;
    private BitmapFont font;

    private float Screen_Width, Screen_Height;
    private float pixelsToScreen_Width_Ratio, pixelsToScreen_Height_Ratio;

    private GlyphLayout glyphLayout_2;
    float w, h;

    private Label label, versionLabel;

    private TextButton classicButton, easyButton, chooseBirdButton, sagaButton, highScoresButton, howToPlayButton,
            signInGooglePlayGamesButton;
    boolean SignedIn, directionX, directionY;

    private float birdPosX, birdPosY;

    // Animated Frame Bird Members
    private Animation frameBirdAnimation;
    private float stateTime;
    private float frameBirdX, frameBirdY;
    private boolean frameBirdDirX, frameBirdDirY;

    // Red Animated Bird Members
    private Animation redBirdAnimation;
    private float redBirdX, redBirdY;
    private boolean redBirdDirX, redBirdDirY;

    private Music music;
    private Texture muteTexture, unmuteTexture;
    private boolean isMuted;
    private Preferences prefs;

    public BBMainMenuScreen(final BriskyBird game) {
        this.game = game;

        music = game.myassetManager.manager.get(VersionPBAssetManager.MenuMusicFile);
        music.setLooping(true);
        prefs = Gdx.app.getPreferences(GameInfo.PREFERENCES);
        isMuted = prefs.getBoolean("isMuted", false);
        music.setVolume(isMuted ? 0f : 0.03f);
        music.play();

        muteTexture = game.myassetManager.manager.get(VersionPBAssetManager.muteImage);
        muteTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        unmuteTexture = game.myassetManager.manager.get(VersionPBAssetManager.unmuteImage);
        unmuteTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        game.handler.showAds(true);
        directionX = true;
        directionY = true;

        // Initialize Animated Bird
        Array<TextureRegion> birdFrames = new Array<TextureRegion>();
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame1, Texture.class)));
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame2, Texture.class)));
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame3, Texture.class)));
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame4, Texture.class)));
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame5, Texture.class)));
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame6, Texture.class)));
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame7, Texture.class)));
        birdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame8, Texture.class)));

        frameBirdAnimation = new Animation(0.1f, birdFrames, Animation.PlayMode.LOOP);
        stateTime = 0f;
        frameBirdX = 0; // Start at different position
        frameBirdY = 200;
        frameBirdDirX = true;
        frameBirdDirY = true;

        // Initialize Red Animated Bird
        Array<TextureRegion> redBirdFrames = new Array<TextureRegion>();
        redBirdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdRedFrame1, Texture.class)));
        redBirdFrames.add(
                new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdRedFrame2, Texture.class)));

        redBirdAnimation = new Animation(0.1f, redBirdFrames, Animation.PlayMode.LOOP);
        redBirdX = 300; // Start at different position
        redBirdY = 400;
        redBirdDirX = false; // Move in opposite direction initially
        redBirdDirY = true;

        Screen_Width = GameInfo.GAME_WIDTH;
        Screen_Height = GameInfo.GAME_HEIGHT;

        pixelsToScreen_Width_Ratio = (float) Gdx.graphics.getWidth() / Screen_Width;
        pixelsToScreen_Height_Ratio = (float) Gdx.graphics.getHeight() / Screen_Height;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Screen_Width / 2, Screen_Height / 2);
        cam.position.set(Screen_Width / 2, Screen_Height / 2, 0);

        float aspectRaio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        // viewport = new FillViewport(Screen_Width , Screen_Height,cam);
        // viewport = new FitViewport(Screen_Width , Screen_Height,cam);
        viewport = new StretchViewport(Screen_Width, Screen_Height, cam);
        // viewport = new ExtendViewport(Screen_Width , Screen_Height,cam);
        // viewport = new ScreenViewport(cam);
        viewport.apply();

        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        backGroundImage = game.myassetManager.manager.get(VersionPBAssetManager.MenuBackground);
        SignInImage = game.myassetManager.manager.get(VersionPBAssetManager.SignInButtonImage);
        birdImage = game.myassetManager.manager.get(VersionPBAssetManager.birdYellowFlapUp);
        font = game.myassetManager.manager.get(VersionPBAssetManager.MenuFont, BitmapFont.class);
        skin = game.myassetManager.manager.get(VersionPBAssetManager.shade_skin);
        skinVPB = game.myassetManager.manager.get(VersionPBAssetManager.vpb_skin);
        skinFreezing = game.myassetManager.manager.get(VersionPBAssetManager.freezing_skin);

        glyphLayout_2 = new GlyphLayout();
        glyphLayout_2.setText(font, GameInfo.MenuHeading);
        w = glyphLayout_2.width;
        h = glyphLayout_2.height;

        Table rootTable = new Table();
        rootTable.setFillParent(true);

        label = new Label("", skin);
        versionLabel = new Label(GameInfo.appVersion, skin, GameInfo.skin_vpbLabelStyle);

        classicButton = new TextButton(GameInfo.classicButtonText, skinVPB);
        easyButton = new TextButton(GameInfo.easyButtonText, skinVPB);
        chooseBirdButton = new TextButton("Choose Bird", skinVPB);
        sagaButton = new TextButton(GameInfo.sagaButtonText, skinVPB);
        highScoresButton = new TextButton(GameInfo.highScoreButtonText, skinFreezing);
        howToPlayButton = new TextButton(GameInfo.howToPlayButtonText, skinFreezing);

        // signInGooglePlayGamesButton = new TextButton("SignIn",skinVPB);

        stage.addActor(rootTable);
        rootTable.top();

        rootTable.add(versionLabel).top().right().padRight(GameInfo.versionNumberRightPadding);
        rootTable.row();

        rootTable.add(label).padBottom(GameInfo.rootTableInvisibleLabelPadBottom);

        // rootTable.row();
        // rootTable.add(signInGooglePlayGamesButton).width(100).height(20).padRight(-300).padTop(10);

        rootTable.row();
        rootTable.add(classicButton).width(250).height(60).padBottom(20).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();
        rootTable.add(easyButton).width(250).height(60).padBottom(20).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();
        rootTable.add(chooseBirdButton).width(250).height(60).padBottom(20).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();
        // rootTable.add(sagaButton).width(250).height(60).padBottom(80).padLeft(GameInfo.allButtonsLeftPadding);
        // rootTable.row();

        rootTable.add(highScoresButton).width(250).height(60).padBottom(30).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();
        rootTable.add(howToPlayButton).width(250).height(60).padBottom(20).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();

        // listeners
        highScoresButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HighScoreScreen(game));
                // dispose();
            }
        });

        howToPlayButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HowToPlayScreen(game));
                // dispose();
            }
        });

        classicButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingBarClassicLevelScreen(game));
                dispose();
            }
        });

        easyButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingBarEasyLevelScreen(game));
                dispose();
            }
        });

        chooseBirdButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ChooseBirdScreen(game));
            }
        });

        // rootTable.debug();

        if (game.ply.isSignedIn()) {
            // System.out.println("C:MenuState : F:MenuState Constructor : Already SignedIn
            // Google PlayServices");
            SignedIn = true;

        } else {
            SignedIn = false;
            // ply.onStartMethod();
            // ply.signIn();
            // System.out.println("C:MenuState : F:MenuState Constructor : SignedIn Google
            // PlayServices");
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(backGroundImage, 0, 0, Screen_Width, Screen_Height);
        font.draw(game.getBatch(), GameInfo.MenuHeading, Screen_Width / 2 - w / 2,
                Screen_Height);
        if (!SignedIn)
            game.getBatch().draw(SignInImage, cam.viewportWidth - 80, cam.viewportHeight - 100, 60, 60);

        update(delta, directionX, directionY);
        updateFrameBird(delta); // Update position for animated bird

        game.getBatch().draw(birdImage, birdPosX, birdPosY);

        // Draw Animated Bird
        stateTime += delta;
        TextureRegion currentFrame = (TextureRegion) frameBirdAnimation.getKeyFrame(stateTime, true);
        game.getBatch().draw(currentFrame, frameBirdX, frameBirdY, GameInfo.ANIMATED_BIRD_WIDTH,
                GameInfo.ANIMATED_BIRD_HEIGHT);

        // Draw Red Animated Bird
        updateRedBird(delta);
        TextureRegion currentRedFrame = (TextureRegion) redBirdAnimation.getKeyFrame(stateTime, true);
        game.getBatch().draw(currentRedFrame, redBirdX, redBirdY, GameInfo.RED_BIRD_WIDTH, GameInfo.RED_BIRD_HEIGHT);

        game.getBatch().end();

        // Draw mute / unmute buttons (bottom-center)
        float btnSize = 35f;
        float muteBtnX = cam.viewportWidth / 2f - btnSize - 4;
        float unmuteBtnX = cam.viewportWidth / 2f + 4;
        float btnY = 6f;
        game.getBatch().begin();
        game.getBatch().draw(muteTexture,   Math.round(muteBtnX),   Math.round(btnY), btnSize, btnSize);
        game.getBatch().draw(unmuteTexture, Math.round(unmuteBtnX), Math.round(btnY), btnSize, btnSize);
        game.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    public void updateFrameBird(float delta) {
        if (frameBirdDirX)
            frameBirdX += GameInfo.ANIMATED_BIRD_SPEED;
        else
            frameBirdX -= GameInfo.ANIMATED_BIRD_SPEED;

        if (frameBirdDirY)
            frameBirdY += GameInfo.ANIMATED_BIRD_SPEED;
        else
            frameBirdY -= GameInfo.ANIMATED_BIRD_SPEED;

        if (frameBirdX >= cam.viewportWidth - GameInfo.ANIMATED_BIRD_WIDTH) { // Adjust boundary using width
            frameBirdX -= GameInfo.ANIMATED_BIRD_SPEED;
            frameBirdDirX = false;
        }

        if (frameBirdY >= cam.viewportHeight - GameInfo.ANIMATED_BIRD_HEIGHT) { // Adjust boundary using height
            frameBirdY -= GameInfo.ANIMATED_BIRD_SPEED;
            frameBirdDirY = false;
        }

        if (frameBirdX <= 0) {
            frameBirdX += GameInfo.ANIMATED_BIRD_SPEED;
            frameBirdDirX = true;
        }

        if (frameBirdY <= 0) {
            frameBirdY += GameInfo.ANIMATED_BIRD_SPEED;
            frameBirdDirY = true;
        }
    }

    public void updateRedBird(float delta) {
        if (redBirdDirX)
            redBirdX += GameInfo.RED_BIRD_SPEED;
        else
            redBirdX -= GameInfo.RED_BIRD_SPEED;

        if (redBirdDirY)
            redBirdY += GameInfo.RED_BIRD_SPEED;
        else
            redBirdY -= GameInfo.RED_BIRD_SPEED;

        if (redBirdX >= cam.viewportWidth - GameInfo.RED_BIRD_WIDTH) {
            redBirdX -= GameInfo.RED_BIRD_SPEED;
            redBirdDirX = false;
        }

        if (redBirdY >= cam.viewportHeight - GameInfo.RED_BIRD_HEIGHT) {
            redBirdY -= GameInfo.RED_BIRD_SPEED;
            redBirdDirY = false;
        }

        if (redBirdX <= 0) {
            redBirdX += GameInfo.RED_BIRD_SPEED;
            redBirdDirX = true;
        }

        if (redBirdY <= 0) {
            redBirdY += GameInfo.RED_BIRD_SPEED;
            redBirdDirY = true;
        }
    }

    public void update(float delta, boolean X, boolean Y) {
        if (X)
            birdPosX += 1;
        else
            birdPosX -= 1;

        if (Y)
            birdPosY += 1;
        else
            birdPosY -= 1;

        if (birdPosX >= cam.viewportWidth) {
            birdPosX -= 1;
            directionX = false;
        }

        if (birdPosY >= cam.viewportHeight) {
            birdPosY -= 1;
            directionY = false;
        }

        if (birdPosX <= 0) {
            birdPosX += 1;
            directionX = true;
        }

        if (birdPosY <= 0) {
            birdPosY += 1;
            directionY = true;
        }

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

        // game.myassetManager.unloadSplashFont();
        // game.myassetManager.unloadSkin();
        // game.myassetManager.unloadMenuFont();
        // game.myassetManager.unloadImages();

        music.stop();
        // game.myassetManager.unloadMenuMusic();
        // Gdx.input.setInputProcessor(null);
        // System.out.println("Menu State Disposed");

    }

    public void handleInput(float delta) {
        // Mute / Unmute button handling
        if (Gdx.input.justTouched()) {
            Vector3 muteTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(muteTouch);
            float btnSize = 35f;
            float muteBtnX = cam.viewportWidth / 2f - btnSize - 4;
            float unmuteBtnX = cam.viewportWidth / 2f + 4;
            float btnY = 6f;
            Rectangle muteBounds   = new Rectangle(muteBtnX,   btnY, btnSize, btnSize);
            Rectangle unmuteBounds = new Rectangle(unmuteBtnX, btnY, btnSize, btnSize);
            if (muteBounds.contains(muteTouch.x, muteTouch.y)) {
                isMuted = true;
                music.setVolume(0f);
                prefs.putBoolean("isMuted", true);
                prefs.flush();
                return;
            } else if (unmuteBounds.contains(muteTouch.x, muteTouch.y)) {
                isMuted = false;
                music.setVolume(0.03f);
                prefs.putBoolean("isMuted", false);
                prefs.flush();
                return;
            }
        }
        // Sign-in button handling
        if (Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            Rectangle textureBoundsSignIn = new Rectangle(cam.viewportWidth - 80, cam.viewportHeight - 100, 60, 60);
            // (SignInImage, cam.viewportWidth - 80, cam.viewportHeight - 100 , 60 , 60)
            if (textureBoundsSignIn.contains(tmp.x, tmp.y)) {

                // System.out.println("Clicked on Sign In");
                CallSignIn();
            }
        }
    }

    public void CallSignIn() {
        game.ply.signIn();
        game.ply.onStartMethod();
        SignedIn = true;
    }
}
