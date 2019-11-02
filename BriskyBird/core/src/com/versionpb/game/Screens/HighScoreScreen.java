package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;


public class HighScoreScreen implements Screen,GestureDetector.GestureListener {

    protected BriskyBird game;

    Skin skin;
    Stage stage;
    private Texture background;
    private Music music;
    private BitmapFont font;
    public boolean tapone,taptwo = false;
    private float fontHEIGHT,fontWIDTH;
    private Texture backButton;




    public HighScoreScreen(final BriskyBird game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        game.getCam().setToOrtho(false, GameInfo.WIDTH , GameInfo.HEIGHT );
        background = game.myassetManager.manager.get(VersionPBAssetManager.highScoreScreenBackGroundImage);



        //music = game.myassetManager.manager.get(VersionPBAssetManager.MenuMusicFile);
        //music.setLooping(true);
        //music.setVolume(0.03f);
        //music.play();

        font = game.myassetManager.manager.get(VersionPBAssetManager.fontChillar_highScoreInfo,BitmapFont.class);
        font.setColor(Color.BLACK);
        font.getData().setScale(0.7f, 0.7f);   // make font size big


        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, GameInfo.EasyModeHighScores);
        fontHEIGHT = layout.height;
        fontWIDTH = layout.width;

        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);

        backButton = game.myassetManager.manager.get(VersionPBAssetManager.backButtonImage);



    }




    @Override
    public void show() {

    }

    public void handleInput(){
        if (Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCam().unproject(tmp);

            Rectangle TB_Easy_LB = new Rectangle(70, game.getCam().viewportHeight  - fontHEIGHT - 300, fontWIDTH, fontHEIGHT);
            Rectangle TB_Classic_LB = new Rectangle(70, game.getCam().viewportHeight  - fontHEIGHT - 400, fontWIDTH, fontHEIGHT);
            Rectangle Rect_backButton = new Rectangle(80 - backButton.getWidth(), backButton.getHeight()/2, backButton.getWidth(), backButton.getHeight());
            if (TB_Easy_LB.contains(tmp.x, tmp.y)) {
                //System.out.println("C:HighScoreState : F:handleInput : Easy LeaderBoards Touched at x : y as " +  tmp.x + " : " + tmp.y);
                //System.out.println("C:HighScoreState : F:handleInput : Easy LeaderBoards Position of text x : y as 70 : " + (game.getCam().viewportHeight - fontHEIGHT - 300));
                //System.out.println(" Font Height : " + fontHEIGHT);
                //System.out.println(" Font Width : " + fontWIDTH);
                if (game.ply.isSignedIn()) {
                    //dispose();
                    game.ply.showScore(GameInfo.leaderboard_Easy);
                }
                else
                {
                    //System.out.println("C:HighScoreState : F:handleInput : Not Signed In Google PLay Services . Nothing Happens");
                }
            }
            else if (TB_Classic_LB.contains(tmp.x, tmp.y)) {
                //System.out.println("C:HighScoreState : F:handleInput : Classic LeaderBoards Touched at x : y as " +  tmp.x + " : " + tmp.y);
                //System.out.println("C:HighScoreState : F:handleInput : Easy LeaderBoards Position of text x : y as 70 : " + (game.getCam().viewportHeight - fontHEIGHT - 400));
                //System.out.println(" Font Height : " + fontHEIGHT);
                //System.out.println(" Font Width : " + fontWIDTH);

                if (game.ply.isSignedIn()) {
                    //dispose();
                    game.ply.showScore(GameInfo.leaderboard_Classic);}
                else
                {
                    //System.out.println("C:HighScoreState : F:handleInput : Not Signed In Google PLay Services . Nothing Happens");
                }
            }
            else if (Rect_backButton.contains(tmp.x, tmp.y)) {
                //System.out.println("C:HighScoreState : F:handleInput : back Button Touched at x : y as " +  tmp.x + " : " + tmp.y);
                dispose();
                game.setScreen(new LoadingBarScreen(game));

            }
            else{
                //System.out.println("C:HighScoreState : F:handleInput : Touched at No Wheres Area Touched at x : y as " +  tmp.x + " : " + tmp.y);

            }

            if(taptwo){
                //System.out.println("C:HighScoreState : F:handleInput : Tap two Detected");
            }
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

        font.setColor(Color.BLACK);
        font.getData().setScale(1.0f, 1.0f);
        font.draw(game.getBatch(),GameInfo.HIGHSCORES , 50, game.getCam().viewportHeight - fontHEIGHT );

        font.setColor(Color.GREEN);
        font.getData().setScale(0.7f, 0.7f);
        font.draw(game.getBatch(),GameInfo.EasyModeHighScores , 70, game.getCam().viewportHeight  - 300);

        font.setColor(Color.GREEN);
        font.draw(game.getBatch(),GameInfo.ClassicModeHighScores , 70 , game.getCam().viewportHeight  - 400);

        game.getBatch().draw(backButton,80 - backButton.getWidth(), backButton.getHeight()/2 );

        font.setColor(Color.BROWN);
        font.getData().setScale(0.3f, 0.3f);
        font.draw(game.getBatch(),GameInfo.PlayServicesSignInMsg , 30, game.getCam().viewportHeight  - 500);


        game.getBatch().end();
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
        //skin.dispose();
        //music.dispose();
        //background.dispose();
        //font.dispose();
        //backButton.dispose();

       //System.out.println("C:HighScoreState : F:dispose :HighScore State Disposed");
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        //System.out.println("tap Detected");
        if(tapone)
            taptwo = true;
        tapone = true;

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


