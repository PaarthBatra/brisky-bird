package com.versionpb.game.sprites;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.VersionPBAssetManager;
import com.badlogic.gdx.utils.Array;


/**
 * Created by USER on 11/11/2017.
 */
public class Bird {
    private static final int GRAVITY = -15;
    public  static  int MOVEMENT = 100;
    public static int getMOVEMENT() {
		return MOVEMENT;
	}

	public static void setMOVEMENT(int mOVEMENT) {
		MOVEMENT = mOVEMENT;
	}

	private Vector3 position;
    private Vector3 velocity;

    private Rectangle bounds;
    public Texture texture;
    private  Texture bird;
    private TextureRegion bird1,bird2,bird3 ;
    private Sound flap;
    private Animation birdAnimation;

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public  Bird(int x, int y , Texture texture, BriskyBird game ){
        position = new Vector3(x,y,0);  // z axis is 0 because we are not using it
        velocity = new Vector3(0,0,0);


        birdAnimation = new Animation(new TextureRegion(texture),3,0.3f);
        bounds = new Rectangle(x,y,texture.getWidth()/3,texture.getHeight());
        flap = game.myassetManager.manager.get(VersionPBAssetManager.wingMusicFile);
    }

    public Bird(int x, int y, Array<TextureRegion> frames, BriskyBird game) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        birdAnimation = new Animation(frames, 0.3f);
        Texture defaultTex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage, Texture.class);
        float defaultW = defaultTex.getWidth() / 3f;
        float defaultH = defaultTex.getHeight();
        bounds = new Rectangle(x, y, defaultW, defaultH);
        flap = game.myassetManager.manager.get(VersionPBAssetManager.wingMusicFile);
    }

    public void update(float dt){

        birdAnimation.update(dt);
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);// adding GRAVITY to the velocity
        velocity.scl(dt); // we are scaling velocity with delta time
        //position.add(0, velocity.y, 0); // adding velocity values to y axis
        position.add(MOVEMENT * dt , velocity.y,0);

        if (position.y < 0)
            position.y = 0;
        velocity.scl(1/dt); //reversing the velocity scaling was done to basically adding scaled version of velocity to position

        bounds.setPosition(position.x,position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture(){
        return birdAnimation.getFrame();
    }

    public void jump(boolean isMuted){
        velocity.y = 250;
        flap.play(isMuted ? 0f : 0.3f);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public  void  dispose( ){
        //texture.dispose();
        //flap.dispose();
    }
}
