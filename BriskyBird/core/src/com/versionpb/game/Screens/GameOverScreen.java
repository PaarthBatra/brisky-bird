package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;


public class GameOverScreen implements Screen {

    protected BriskyBird game;

    private Texture background;
    private Texture gameOver;
    private Texture medalBox,medal,medal_B,medal_S,medal_G;
    private Texture playButton;
    private int Score , MaxScore;
    private Music music;
    private BitmapFont font;


    public GameOverScreen(final BriskyBird game,int P_Score , int P_Max_Score) {
        this.game = game;

        game.getCam().setToOrtho(false, GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2);


        background = game.myassetManager.manager.get(VersionPBAssetManager.background);
        gameOver = game.myassetManager.manager.get(VersionPBAssetManager.gameOver);
        playButton = game.myassetManager.manager.get(VersionPBAssetManager.playButton);
        medalBox = game.myassetManager.manager.get(VersionPBAssetManager.medalBox);
        medal = game.myassetManager.manager.get(VersionPBAssetManager.medal);
        medal_B = game.myassetManager.manager.get(VersionPBAssetManager.medal_B);
        medal_S = game.myassetManager.manager.get(VersionPBAssetManager.medal_S);
        medal_G = game.myassetManager.manager.get(VersionPBAssetManager.medal_G);
        music = game.myassetManager.manager.get(VersionPBAssetManager.MenuMusicFile);
        font =  game.myassetManager.manager.get(VersionPBAssetManager.fontGameOver);

        music.setLooping(true);
        music.setVolume(0.03f);
        music.play();

        Score = P_Score;
        MaxScore = P_Max_Score;


    }

    public void handleInput() {

        Rectangle bounds = new Rectangle(game.getCam().position.x - playButton.getWidth()/2, game.getCam().position.y - 100, playButton.getWidth(), playButton.getHeight());
        Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        game.getCam().unproject(tmp);

        if (bounds.contains(tmp.x, tmp.y)) {

            if(Gdx.input.justTouched()){

                dispose();
                BriskyBird.running = false;

                game.setScreen(new BBMainMenuScreen(game));
            }


        }


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();

        game.getBatch().setProjectionMatrix(game.getCam().combined);
        //We open the bag , put things inside it and then close it
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, GameInfo.WIDTH, GameInfo.HEIGHT); //image,x coredinate and y cordinate


        game.getBatch().draw(gameOver,game.getCam().position.x - gameOver.getWidth()/2,game.getCam().position.y + gameOver.getHeight());
        game.getBatch().draw(medalBox,game.getCam().position.x - medalBox.getWidth()/2,game.getCam().position.y - medalBox.getHeight());
        game.getBatch().draw(playButton,game.getCam().position.x - playButton.getWidth()/2,game.getCam().position.y - 100);

        font.draw(game.getBatch(), Integer.toString(Score), game.getCam().position.x - medalBox.getWidth()/2 + medalBox.getWidth()*3/4  , game.getCam().position.y - medalBox.getHeight()/4 - font.getCapHeight()/2);
        font.draw(game.getBatch(), Integer.toString(MaxScore), game.getCam().position.x - medalBox.getWidth()/2 + medalBox.getWidth()*3/4  , game.getCam().position.y - medalBox.getHeight()/2 - font.getCapHeight());

        //draw medal
        if (Score > 1 && Score <=10) {
            game.getBatch().draw(medal,game.getCam().position.x - medalBox.getWidth()/2 + medal.getWidth()/4 + 3,game.getCam().position.y - medalBox.getHeight() + medal.getHeight()/2 -3 );


        }
        else if (Score > 10 && Score <= 35){
            game.getBatch().draw(medal_B,game.getCam().position.x - medalBox.getWidth()/2 + medal.getWidth()/4 + 3,game.getCam().position.y - medalBox.getHeight() + medal.getHeight()/2 -3 );
            //System.out.println("Setting Bronze Medal ");
        }
        else if (Score > 35 && Score <= 60){
            game.getBatch().draw(medal_S,game.getCam().position.x - medalBox.getWidth()/2 + medal.getWidth()/4 + 3,game.getCam().position.y - medalBox.getHeight() + medal.getHeight()/2 -3 );
            //System.out.println("Setting Silver Medal ");
        }
        else if (Score > 60){
            game.getBatch().draw(medal_G,game.getCam().position.x - medalBox.getWidth()/2 + medal.getWidth()/4 + 3,game.getCam().position.y - medalBox.getHeight() + medal.getHeight()/2 -3 );
            //System.out.println("Setting Gold Medal ");
        }

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


        //game.myassetManager.unloadGameOverImages();
        //game.myassetManager.unloadMenuMusic();
        //game.myassetManager.unloadGameOverFonts();
        //game.myassetManager.unloadhighScoreScreenFonts();
        //game.myassetManager.unloadhighScoreScreenImages();
        //game.myassetManager.unloadHowToPlayScreenFonts();
        //game.myassetManager.unloadHowToPlayScreenImages();

    }
}


