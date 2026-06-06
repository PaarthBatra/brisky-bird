package com.versionpb.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by USER on 11/15/2017.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;  // how long one frame should stay on screen
    private  float currentFrameTime;
    private  int frameCount;
    private int frame;
    //Tesxture region contains all frames on images
    //frameCount are total frames in our region
    //cycletime is how long it ill take to complete one full cycle of animation
    public Animation(TextureRegion region , int frameCount, float cycletime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;

        for (int i = 0;i < frameCount ; i ++){
            frames.add(new TextureRegion(region , i * frameWidth , 0,frameWidth,region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycletime / frameCount;
        frame = 0;
    }

    public Animation(Array<TextureRegion> framesList, float cycletime) {
        this.frames = new Array<TextureRegion>(framesList);
        this.frameCount = framesList.size;
        this.maxFrameTime = cycletime / frameCount;
        this.frame = 0;
    }

    public  void update(float dt){
            currentFrameTime += dt;
            if(currentFrameTime > maxFrameTime){
                frame++;
                currentFrameTime = 0;
            }
        if (frame >= frameCount)
            frame = 0;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

}
