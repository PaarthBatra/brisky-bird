package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
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


public class BBMainMenuScreen implements Screen {

    public BriskyBird game;

    private Skin skin,skinVPB,skinFreezing;
    private Stage stage;
    private Texture backGroundImage,SignInImage,birdImage;
    private OrthographicCamera cam;
    private Viewport viewport;
    private BitmapFont font;

    private float Screen_Width,Screen_Height;
    private float pixelsToScreen_Width_Ratio,pixelsToScreen_Height_Ratio;

    private GlyphLayout glyphLayout_2;
    float w, h;

    private Label label , versionLabel;

    private TextButton classicButton,easyButton,sagaButton,highScoresButton,howToPlayButton,signInGooglePlayGamesButton;
    boolean SignedIn,directionX,directionY;

    private float birdPosX,birdPosY;

    private Music music;

    public BBMainMenuScreen(final BriskyBird game) {
        this.game = game;


        music = game.myassetManager.manager.get(VersionPBAssetManager.MenuMusicFile);
        music.setLooping(true);
        music.setVolume(0.03f);
        music.play();

        game.handler.showAds(true);
        directionX = true;
        directionY = true;

        Screen_Width = GameInfo.GAME_WIDTH;
        Screen_Height = GameInfo.GAME_HEIGHT;

        pixelsToScreen_Width_Ratio = (float) Gdx.graphics.getWidth() / Screen_Width ;
        pixelsToScreen_Height_Ratio = (float) Gdx.graphics.getHeight() / Screen_Height ;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Screen_Width/2 , Screen_Height/2 );
        cam.position.set(Screen_Width/2 , Screen_Height/2,0);

        float aspectRaio = (float)Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth() ;
        //viewport = new FillViewport(Screen_Width  , Screen_Height,cam);
        //viewport = new FitViewport(Screen_Width  , Screen_Height,cam);
        viewport = new StretchViewport(Screen_Width  , Screen_Height,cam);
        //viewport = new ExtendViewport(Screen_Width  , Screen_Height,cam);
        //viewport = new ScreenViewport(cam);
        viewport.apply();

        stage = new Stage(viewport,game.getBatch());
        Gdx.input.setInputProcessor(stage);

        backGroundImage = game.myassetManager.manager.get(VersionPBAssetManager.MenuBackground);
        SignInImage = game.myassetManager.manager.get(VersionPBAssetManager.SignInButtonImage);
        birdImage = game.myassetManager.manager.get(VersionPBAssetManager.birdYellowFlapUp);
        font = game.myassetManager.manager.get(VersionPBAssetManager.MenuFont,BitmapFont.class);
        skin = game.myassetManager.manager.get(VersionPBAssetManager.shade_skin);
        skinVPB = game.myassetManager.manager.get(VersionPBAssetManager.vpb_skin);
        skinFreezing = game.myassetManager.manager.get(VersionPBAssetManager.freezing_skin);

        glyphLayout_2 = new GlyphLayout();
        glyphLayout_2.setText(font, GameInfo.MenuHeading);
        w = glyphLayout_2.width;
        h = glyphLayout_2.height;

        Table rootTable = new Table();
        rootTable.setFillParent(true);


        label = new Label("",skin);
        versionLabel = new Label(GameInfo.appVersion,skin,GameInfo.skin_vpbLabelStyle);

        classicButton = new TextButton(GameInfo.classicButtonText,skinVPB);
        easyButton = new TextButton(GameInfo.easyButtonText,skinVPB);
        sagaButton = new TextButton(GameInfo.sagaButtonText,skinVPB);
        highScoresButton = new TextButton(GameInfo.highScoreButtonText, skinFreezing);
        howToPlayButton = new TextButton(GameInfo.howToPlayButtonText, skinFreezing);

        //signInGooglePlayGamesButton = new TextButton("SignIn",skinVPB);


        stage.addActor(rootTable);
        rootTable.top();

        rootTable.add(versionLabel).top().right().padRight(GameInfo.versionNumberRightPadding);
        rootTable.row();

        rootTable.add(label).padBottom(GameInfo.rootTableInvisibleLabelPadBottom);

        //rootTable.row();
        //rootTable.add(signInGooglePlayGamesButton).width(100).height(20).padRight(-300).padTop(10);

        rootTable.row();
        rootTable.add(classicButton).width(250).height(60).padBottom(20).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();
        rootTable.add(easyButton).width(250).height(60).padBottom(140).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();
        //rootTable.add(sagaButton).width(250).height(60).padBottom(80).padLeft(GameInfo.allButtonsLeftPadding);
        //rootTable.row();

        rootTable.add(highScoresButton).width(250).height(60).padBottom(30).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();
        rootTable.add(howToPlayButton).width(250).height(60).padBottom(20).padLeft(GameInfo.allButtonsLeftPadding);
        rootTable.row();



        //listeners
        highScoresButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HighScoreScreen(game));
                //dispose();
            }
        });

        howToPlayButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HowToPlayScreen(game));
                //dispose();
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

        //rootTable.debug();

        if(game.ply.isSignedIn()) {
            //System.out.println("C:MenuState : F:MenuState Constructor : Already SignedIn Google PlayServices");
            SignedIn = true;

        }
        else {
            SignedIn = false;
            //ply.onStartMethod();
            //ply.signIn();
            //System.out.println("C:MenuState : F:MenuState Constructor : SignedIn Google PlayServices");
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
        font.draw(game.getBatch(), GameInfo.MenuHeading, Screen_Width/2 - w/2,
                Screen_Height);
        if (!SignedIn)
            game.getBatch().draw(SignInImage,  cam.viewportWidth - 80, cam.viewportHeight - 100 , 60 , 60);

        update(delta,directionX,directionY);

        game.getBatch().draw(birdImage, birdPosX,  birdPosY);
        game.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    public void update(float delta, boolean X, boolean Y){
        if (X)
            birdPosX += 1;
        else
            birdPosX -= 1;

        if (Y)
            birdPosY += 1;
        else
            birdPosY -= 1;

        if (birdPosX >= cam.viewportWidth){
            birdPosX -= 1;
            directionX = false;
        }


        if (birdPosY >= cam.viewportHeight){
            birdPosY -= 1;
            directionY = false;
        }

        if (birdPosX <= 0){
            birdPosX += 1;
            directionX = true;
        }

        if (birdPosY <= 0){
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

        //game.myassetManager.unloadSplashFont();
        //game.myassetManager.unloadSkin();
        //game.myassetManager.unloadMenuFont();
        //game.myassetManager.unloadImages();

        music.stop();
        //game.myassetManager.unloadMenuMusic();
        //Gdx.input.setInputProcessor(null);
        //System.out.println("Menu State Disposed");

    }

    public void handleInput(float delta){
        if (Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            Rectangle textureBoundsSignIn = new Rectangle(cam.viewportWidth - 80, cam.viewportHeight - 100 , 60 , 60);
            //(SignInImage,  cam.viewportWidth - 80, cam.viewportHeight - 100 , 60 , 60)
            if (textureBoundsSignIn.contains(tmp.x, tmp.y)) {

                //System.out.println("Clicked on Sign In");
                CallSignIn();
            }
        }
        }

    public void CallSignIn(){
        game.ply.signIn();
        game.ply.onStartMethod();
        SignedIn = true;
    }
}
