package com.versionpb.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.versionpb.game.Screens.LoadingBarScreen;
import com.versionpb.game.helpers.VersionPBAssetManager;



public class BriskyBird extends Game {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    public VersionPBAssetManager myassetManager = new VersionPBAssetManager();


    public AdHandler handler;
    public OrthographicCamera cam;



    private boolean pauseState;
    long startTime;

    public PlayServices ply;
    //public static boolean tapone = false;
    //public static boolean taptwo = false;
    public static boolean running = false;




    public BriskyBird(AdHandler handler,PlayServices ply){
        this.handler = handler;
        this.ply = ply;
    }

    public BriskyBird(){
    }




    @Override
    public  void pause() {
        super.pause();
        running = false;
        pauseState = true;
        //System.out.println("Game is Paused");

        dispose();

    }

    @Override
    public void resume() {
        super.resume();
        pauseState = false;

        setScreen(new LoadingBarScreen(this));

        handler.showAds(true);
    }

    @Override
	public void create () {
    	startTime = TimeUtils.millis();
		batch = new SpriteBatch();


        Gdx.gl.glClearColor(1, 0, 0, 1);


        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera();



        setScreen(new com.versionpb.game.Screens.LoadingBarScreen(this));




	}

    @Override
    public void dispose(){

    }


    @Override
    public void render () {



        super.render();


        

	}

    public Batch getBatch(){
        return this.batch;
    }
    public ShapeRenderer getShapeRenderer(){
        return this.shapeRenderer;
    }
    public OrthographicCamera getCam(){
        return this.cam;
    }

}
