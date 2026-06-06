package com.versionpb.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.versionpb.game.BriskyBird;
import com.versionpb.game.helpers.GameInfo;
import com.versionpb.game.helpers.VersionPBAssetManager;

public class BirdSelectedSplashScreen implements Screen {

    private final BriskyBird game;
    private final String birdId;
    private String displayName;

    private OrthographicCamera cam;
    private Viewport viewport;
    private Texture backGroundImage;
    private BitmapFont font;
    private GlyphLayout layoutLine1;
    private GlyphLayout layoutLine2;

    private Animation<TextureRegion> birdAnimation;
    private float stateTime = 0f;

    private float Screen_Width = GameInfo.GAME_WIDTH;
    private float Screen_Height = GameInfo.GAME_HEIGHT;

    private long startTime;
    private static final long DISPLAY_DURATION_MS = 2000; // 2 seconds

    public BirdSelectedSplashScreen(final BriskyBird game, String birdId) {
        this.game = game;
        this.birdId = birdId;
        this.startTime = TimeUtils.millis();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Screen_Width / 2, Screen_Height / 2);
        cam.position.set(Screen_Width / 2, Screen_Height / 2, 0);

        viewport = new StretchViewport(Screen_Width, Screen_Height, cam);
        viewport.apply();

        backGroundImage = game.myassetManager.manager.get(VersionPBAssetManager.MenuBackground, Texture.class);
        font = game.myassetManager.manager.get(VersionPBAssetManager.MenuFont, BitmapFont.class);

        // Determine display name and build animation frames
        Array<TextureRegion> birdFrames = new Array<TextureRegion>();

        if (birdId.equals("birdanimation_black")) {
            displayName = "Black Bird";
            Texture tex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_black, Texture.class);
            TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() / 3, tex.getHeight());
            for (TextureRegion frame : tmp[0]) {
                birdFrames.add(frame);
            }
        } else if (birdId.equals("birdanimation_blue")) {
            displayName = "Blue Bird";
            Texture tex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_blue, Texture.class);
            TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() / 3, tex.getHeight());
            for (TextureRegion frame : tmp[0]) {
                birdFrames.add(frame);
            }
        } else if (birdId.equals("birdanimation_green")) {
            displayName = "Green Bird";
            Texture tex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_green, Texture.class);
            TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() / 3, tex.getHeight());
            for (TextureRegion frame : tmp[0]) {
                birdFrames.add(frame);
            }
        } else if (birdId.equals("birdanimation_lightblue")) {
            displayName = "Light Blue Bird";
            Texture tex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_lightblue, Texture.class);
            TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() / 3, tex.getHeight());
            for (TextureRegion frame : tmp[0]) {
                birdFrames.add(frame);
            }
        } else if (birdId.equals("birdanimation_pink")) {
            displayName = "Pink Bird";
            Texture tex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage_pink, Texture.class);
            TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() / 3, tex.getHeight());
            for (TextureRegion frame : tmp[0]) {
                birdFrames.add(frame);
            }
        } else if (birdId.equals("birdanimation")) {
            displayName = "Yellow Bird";
            Texture tex = game.myassetManager.manager.get(VersionPBAssetManager.birdFrameImage, Texture.class);
            TextureRegion[][] tmp = TextureRegion.split(tex, tex.getWidth() / 3, tex.getHeight());
            for (TextureRegion frame : tmp[0]) {
                birdFrames.add(frame);
            }
        } else if (birdId.equals("Redframe-1")) {
            displayName = "Red Bird";
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdRedFrame1, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdRedFrame2, Texture.class)));
        } else {
            displayName = "Classic Bird";
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame1, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame2, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame3, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame4, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame5, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame6, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame7, Texture.class)));
            birdFrames.add(new TextureRegion(game.myassetManager.manager.get(VersionPBAssetManager.birdFrame8, Texture.class)));
        }

        birdAnimation = new Animation<TextureRegion>(0.08f, birdFrames, Animation.PlayMode.LOOP);

        layoutLine1 = new GlyphLayout(font, displayName);
        layoutLine2 = new GlyphLayout(font, "Selected!");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Transition back to main menu after duration
        if (TimeUtils.timeSinceMillis(startTime) > DISPLAY_DURATION_MS) {
            game.setScreen(new BBMainMenuScreen(game));
            dispose();
            return;
        }

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();

        // Render background
        game.getBatch().draw(backGroundImage, 0, 0, Screen_Width, Screen_Height);

        // Render multi-line centered confirmation text
        float yPos1 = Screen_Height * 3 / 4;
        font.draw(game.getBatch(), displayName, Screen_Width / 2 - layoutLine1.width / 2, yPos1);
        font.draw(game.getBatch(), "Selected!", Screen_Width / 2 - layoutLine2.width / 2, yPos1 - layoutLine1.height - 20);

        // Render animating bird (scaled up to 120 x 90)
        stateTime += delta;
        TextureRegion currentFrame = birdAnimation.getKeyFrame(stateTime, true);
        float birdW = 120f;
        float birdH = 90f;
        game.getBatch().draw(currentFrame, Screen_Width / 2 - birdW / 2, Screen_Height / 2 - birdH / 2 - 30, birdW, birdH);

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
