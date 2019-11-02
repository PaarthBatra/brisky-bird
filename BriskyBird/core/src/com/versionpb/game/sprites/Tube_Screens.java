package com.versionpb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.VersionPBAssetManager;

import java.util.Random;


/**
 * Created by USER on 11/12/2017.
 */
public class Tube_Screens {
    public static final int TUBE_WIDTH = 40;  // pixel width of the tube from the image

    private static final  int FLUCTUATION = 140; // so it can move randomaly between 0 and 140
    private static  int TUBE_GAP = 100;  //  this will be the gap between 2 tubes
    private static final  int LOWEST_OPENING = 130 ; // where from the bottom of the screen should can we have top tube
    private Texture TopTube;
    private Texture BottomTube;
    private Vector2 posTopTube,posBottomTube;  // Position of top and bottom tubes on x axis
    private Random rand; // to get random top and bottom positions on y xis
    public Rectangle boundsTop,boundsBot;

    private Rectangle boundsSpace;

    public Texture getTopTube() {
        return TopTube;
    }

    public Texture getBottomTube() {
        return BottomTube;
    }

    public void setTubeGap(int Value){
        TUBE_GAP = Value;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public Tube_Screens(float x, BriskyBird game){
        TopTube = game.myassetManager.manager.get(VersionPBAssetManager.tubeDown);
        BottomTube = game.myassetManager.manager.get(VersionPBAssetManager.tubeUp);
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x,posTopTube.y - TUBE_GAP - BottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x,posTopTube.y,TopTube.getWidth() - 5,TopTube.getHeight() + 5);  //Set position of invisible rectangle for top tube
        boundsBot = new Rectangle(posBottomTube.x,posBottomTube.y,BottomTube.getWidth() - 5,BottomTube.getHeight() -5); //Set position of invisible rectangle for bottom tube
        boundsSpace = new Rectangle(posTopTube.x,posTopTube.y - TUBE_GAP ,TopTube.getWidth() ,TopTube.getHeight() );


    }

    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x,posTopTube.y - TUBE_GAP - BottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x,posTopTube.y);
        boundsBot.setPosition(posBottomTube.x,posBottomTube.y);
        boundsSpace.setPosition(posTopTube.x,posTopTube.y - TUBE_GAP);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsBot)  || player.overlaps(boundsTop) ;

    }


    public  void dispose(){
        //TopTube.dispose();
        //BottomTube.dispose();
    }
}
