package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;

public class SplashScreen implements Screen {

    public BriskyBird game;


    private OrthographicCamera cam;
    private Viewport viewport;
    private Texture splashBGImage;
    private BitmapFont font;

    private float Screen_Width,Screen_Height;

    private String str;
    private float w, h , pixelsToScreen_Width_Ratio,pixelsToScreen_Height_Ratio;
    private float widthofLine;
    private GlyphLayout glyphLayout;

    private static final float TIMEPERCHAR = 0.1f;
    private float ctimeperchar = 0;
    private int numchars = 0;




    long startTime= TimeUtils.millis();



    public SplashScreen(final BriskyBird game){
        this.game = game;

        game.handler.showAds(false);
        splashBGImage = game.myassetManager.manager.get(VersionPBAssetManager.splashBGImage);

        Screen_Width = GameInfo.GAME_WIDTH;
        Screen_Height = GameInfo.GAME_HEIGHT;

        pixelsToScreen_Width_Ratio = (float) Gdx.graphics.getWidth() / Screen_Width ;
        pixelsToScreen_Height_Ratio = (float) Gdx.graphics.getHeight() / Screen_Height ;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Screen_Width/2 , Screen_Height/2 );
        cam.position.set(Screen_Width/2 , Screen_Height/2,0);

        float aspectRaio = (float)Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth() ;

        viewport = new StretchViewport(Screen_Width  , Screen_Height,cam);

        viewport.apply();



        font = game.myassetManager.manager.get(VersionPBAssetManager.splashFont,BitmapFont.class);

        str = GameInfo.str;
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, str);
        w = glyphLayout.width;
        h = glyphLayout.height;

        widthofLine = cam.viewportWidth / 2 - w / 2;

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);

        if ( TimeUtils.timeSinceMillis(startTime)  >  GameInfo.splashScreen_Milliseconds ){

            game.setScreen(new BBMainMenuScreen(game));
            dispose();
            //Gdx.app.log("LOG","massageByVibrations : 3 seconds over ... set final Screen");
        }


        game.getBatch().begin();
        game.getBatch().draw(splashBGImage,0,0,cam.viewportWidth,cam.viewportHeight);


        str=GameInfo.str;
        if (numchars < str.length()) { // if num of chars are lesser than string length , if all chars are not parsed
            ctimeperchar += delta; // character time per char to be added with delta
            if (ctimeperchar >= TIMEPERCHAR) { // if c time ie greater than time // for 1 char
                ctimeperchar = 0; // make ctimeper char again 0
                numchars++; // go to next character , to be printed
            }
        }
        str = str.substring(0, numchars); // get string to be printed


        font.draw(game.getBatch(), str, cam.viewportWidth / 2 - w / 2, cam.viewportHeight / 2 + h / 2);
        game.getBatch().end();

        if(str.equalsIgnoreCase(GameInfo.str)) {
            game.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
            game.getShapeRenderer().setColor(GameInfo.SplashScreenUnderLineColor);
            game.getShapeRenderer().rectLine( (cam.viewportWidth / 2 - w / 2 + GameInfo.LineOffsetX) * pixelsToScreen_Width_Ratio,
                    (cam.viewportHeight/ 2 - h) * pixelsToScreen_Height_Ratio,
                    (widthofLine + GameInfo.LineOffsetX + GameInfo.LineAdjustmentOffsetX) * pixelsToScreen_Width_Ratio,
                    (cam.viewportHeight/ 2 - h) * pixelsToScreen_Height_Ratio,
                    GameInfo.Rectangle_Width);

            game.getShapeRenderer().end();
            update();
        }


    }

    public void update(){
        if(widthofLine <  w  + GameInfo.LineAdjustmentOffsetX)
            widthofLine += 10;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        cam.position.set(Screen_Width/2 , Screen_Height/2,0);

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
        game.myassetManager.unloadSplashImage();

    }
}
