package com.versionpb.game.helpers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class VersionPBAssetManager {

    public AssetManager manager = new AssetManager();

    public static final String splash_skin = "splash/uiskin.json";
    public static final String splashBGImage = "splash/splash.jpg";
    public static final String splashFont = "splash/fonts/AlexBrush-Regular.ttf";
    public static final String shade_skin = "splash/shadeui/uiskin.json";
    public static final String MenuFont = "menu/GreatVibes-Regular.otf";
    public static final String MenuBackground = "backgrounds/MainMenuBackGround.jpg";
    public static final String vpb_skin = "skins/VersionPBSkin/VersionPBSkin.json";
    public static final String freezing_skin = "skins/freezing/skin/freezing-ui.json";
    public static final String SignInButtonImage = "images/SignIn.png";
    public static final String birdYellowFlapUp = "images/yellowbird-upflap.png";
    public static final String birdFrame1 = "birds/frame-1.png";
    public static final String birdFrame2 = "birds/frame-2.png";
    public static final String birdFrame3 = "birds/frame-3.png";
    public static final String birdFrame4 = "birds/frame-4.png";
    public static final String birdFrame5 = "birds/frame-5.png";
    public static final String birdFrame6 = "birds/frame-6.png";
    public static final String birdFrame7 = "birds/frame-7.png";
    public static final String birdFrame8 = "birds/frame-8.png";
    public static final String birdRedFrame1 = "birds/Redframe-1.png";
    public static final String birdRedFrame2 = "birds/Redframe-2.png";
    public static final String MenuMusicFile = "music/musicbg.ogg";

    // Classic Level
    public static final String playBg_v1 = "backgrounds/background_play_1.jpg";
    public static final String playBg_v2 = "backgrounds/background_play_2.jpg";
    public static final String playBg_v3 = "backgrounds/background_play_3.jpg";
    public static final String playBg_v4 = "backgrounds/background_play_4.jpg";
    public static final String playBg_v5 = "backgrounds/background_play_5.jpg";
    public static final String playBg_v6 = "backgrounds/background_play_6.jpg";
    public static final String playBg_v7 = "backgrounds/background_play_7.jpg";
    public static final String playBg_v8 = "backgrounds/background_play_8.jpg";
    public static final String playBg_v9 = "backgrounds/background_play_9.jpg";
    public static final String playBg_v10 = "backgrounds/background_play_10.jpg";
    public static final String playBg_v11 = "backgrounds/background_play_11.jpg";
    public static final String playBg_v12 = "backgrounds/background_play_12.jpg";
    public static final String playBg_v13 = "backgrounds/background_play_13.png";
    public static final String ground = "common/base.png";
    public static final String pauseImage = "common/pause.png";
    public static final String playImage = "common/play.png";
    public static final String muteImage = "common/mute.png";
    public static final String unmuteImage = "common/unmute.png";
    public static final String tubeUp = "common/pipe_up.png";
    public static final String tubeDown = "common/pipe_down.png";

    public static final String birdFrameImage = "birds/birdanimation.png";
    public static final String birdFrameImage_blue = "birds/birdanimation_blue.png";
    public static final String birdFrameImage_black = "birds/birdanimation_black.png";
    public static final String birdFrameImage_green = "birds/birdanimation_green.png";
    public static final String birdFrameImage_lightblue = "birds/birdanimation_lightblue.png";
    public static final String birdFrameImage_pink = "birds/birdanimation_pink.png";

    public static final String dieMusicFile = "music/die.ogg";
    public static final String pointMusicFile = "music/point.ogg";
    public static final String wingMusicFile = "music/wing.ogg";

    public static final String fontChillar = "fonts/chillar_100.fnt";
    public static final String fontChillar_LevelInfo = "fonts/chillar_100LevelInfo.fnt";
    public static final String fontChillar_highScoreInfo = "fonts/chillar_100highScoreInfo.fnt";
    public static final String fontChillar_hotToPlayInfo = "fonts/chillar_100howToPlayScreen.fnt";
    public static final String fontVPB = "fonts/vpb.fnt";

    // HighScoreScreen

    public static final String backButtonImage = "highScoreScreen/backButton80.png";
    public static final String highScoreScreenBackGroundImage = "highScoreScreen/HighScoreBG.jpg";

    // HowToPlayScreen
    public static final String backgroundHowToPlayScreen = "howToPlayScreen/background_pink_howtoplay.jpg";

    // Game Over Screen
    public static final String background = "gameOver/background_landscape_1.jpg";
    public static final String gameOver = "gameOver/gameover.png";
    public static final String playButton = "gameOver/playButton.png";
    public static final String medalBox = "gameOver/medalbox.png";
    public static final String medal = "gameOver/medal.png";
    public static final String medal_B = "gameOver/medal_bronze.png";
    public static final String medal_S = "gameOver/medal_silver.png";
    public static final String medal_G = "gameOver/medal_gold.png";
    public static final String fontGameOver = "fonts/vpb_gameover.fnt";

    public void loadGameOverImages() {
        manager.load(background, Texture.class);
        manager.load(gameOver, Texture.class);
        manager.load(playButton, Texture.class);
        manager.load(medalBox, Texture.class);
        manager.load(medal, Texture.class);
        manager.load(medal_B, Texture.class);
        manager.load(medal_S, Texture.class);
        manager.load(medal_G, Texture.class);

    }

    public void unloadGameOverImages() {
        manager.unload(background);
        manager.unload(gameOver);
        manager.unload(playButton);
        manager.unload(medalBox);
        manager.unload(medal);
        manager.unload(medal_B);
        manager.unload(medal_S);
        manager.unload(medal_G);

    }

    public void loadhighScoreScreenImages() {
        manager.load(highScoreScreenBackGroundImage, Texture.class);
        manager.load(backButtonImage, Texture.class);

    }

    public void unloadhighScoreScreenImages() {
        manager.unload(highScoreScreenBackGroundImage);
        manager.unload(backButtonImage);

    }

    public void loadHowToPlayScreenImages() {
        manager.load(backgroundHowToPlayScreen, Texture.class);
    }

    public void unloadHowToPlayScreenImages() {
        manager.unload(backgroundHowToPlayScreen);

    }

    public void loadClassicLevelTextures() {
        manager.load(playBg_v1, Texture.class);
        manager.load(playBg_v2, Texture.class);
        manager.load(playBg_v3, Texture.class);
        manager.load(playBg_v4, Texture.class);
        manager.load(playBg_v5, Texture.class);
        manager.load(playBg_v6, Texture.class);
        manager.load(playBg_v7, Texture.class);
        manager.load(playBg_v8, Texture.class);
        manager.load(playBg_v9, Texture.class);
        manager.load(playBg_v10, Texture.class);
        manager.load(playBg_v11, Texture.class);
        manager.load(playBg_v12, Texture.class);
        manager.load(playBg_v13, Texture.class);
        manager.load(ground, Texture.class);
        manager.load(pauseImage, Texture.class);
        manager.load(playImage, Texture.class);
        manager.load(muteImage, Texture.class);
        manager.load(unmuteImage, Texture.class);
        manager.load(tubeDown, Texture.class);
        manager.load(tubeUp, Texture.class);

        // load birds
        manager.load(birdFrameImage, Texture.class);
        manager.load(birdFrameImage_blue, Texture.class);
        manager.load(birdFrameImage_black, Texture.class);
        manager.load(birdFrameImage_green, Texture.class);
        manager.load(birdFrameImage_lightblue, Texture.class);
        manager.load(birdFrameImage_pink, Texture.class);
    }

    public void unloadClassicLevelTextures() {
        manager.unload(playBg_v1);
        manager.unload(playBg_v2);
        manager.unload(playBg_v3);
        manager.unload(playBg_v4);
        manager.unload(playBg_v5);
        manager.unload(playBg_v6);
        manager.unload(playBg_v7);
        manager.unload(playBg_v8);
        manager.unload(playBg_v9);
        manager.unload(playBg_v10);
        manager.unload(playBg_v11);
        manager.unload(playBg_v12);
        manager.unload(playBg_v13);
        manager.unload(ground);
        manager.unload(pauseImage);
        manager.unload(playImage);
        manager.unload(muteImage);
        manager.unload(unmuteImage);
        manager.unload(tubeDown);
        manager.unload(tubeUp);

        // unload birds
        manager.unload(birdFrameImage);
        manager.unload(birdFrameImage_blue);
        manager.unload(birdFrameImage_black);
        manager.unload(birdFrameImage_green);
        manager.unload(birdFrameImage_lightblue);
        manager.unload(birdFrameImage_pink);
    }

    public void loadEasyLevelTextures() {
        manager.load(playBg_v1, Texture.class);
        manager.load(playBg_v2, Texture.class);
        manager.load(playBg_v3, Texture.class);
        manager.load(playBg_v4, Texture.class);
        manager.load(playBg_v5, Texture.class);
        manager.load(playBg_v6, Texture.class);
        manager.load(playBg_v7, Texture.class);
        manager.load(playBg_v8, Texture.class);
        manager.load(playBg_v9, Texture.class);
        manager.load(playBg_v10, Texture.class);
        manager.load(playBg_v11, Texture.class);
        manager.load(playBg_v12, Texture.class);
        manager.load(playBg_v13, Texture.class);
        manager.load(ground, Texture.class);
        manager.load(pauseImage, Texture.class);
        manager.load(playImage, Texture.class);
        manager.load(muteImage, Texture.class);
        manager.load(unmuteImage, Texture.class);
        manager.load(tubeDown, Texture.class);
        manager.load(tubeUp, Texture.class);

        // load birds
        manager.load(birdFrameImage, Texture.class);
        manager.load(birdFrameImage_blue, Texture.class);
        manager.load(birdFrameImage_black, Texture.class);
        manager.load(birdFrameImage_green, Texture.class);
        manager.load(birdFrameImage_lightblue, Texture.class);
        manager.load(birdFrameImage_pink, Texture.class);
    }

    public void unloadEasyLevelTextures() {
        manager.unload(playBg_v1);
        manager.unload(playBg_v2);
        manager.unload(playBg_v3);
        manager.unload(playBg_v4);
        manager.unload(playBg_v5);
        manager.unload(playBg_v6);
        manager.unload(playBg_v7);
        manager.unload(playBg_v8);
        manager.unload(playBg_v9);
        manager.unload(playBg_v10);
        manager.unload(playBg_v11);
        manager.unload(playBg_v12);
        manager.unload(playBg_v13);
        manager.unload(ground);
        manager.unload(pauseImage);
        manager.unload(playImage);
        manager.unload(muteImage);
        manager.unload(unmuteImage);
        manager.unload(tubeDown);
        manager.unload(tubeUp);

        // unload birds
        manager.unload(birdFrameImage);
        manager.unload(birdFrameImage_blue);
        manager.unload(birdFrameImage_black);
        manager.unload(birdFrameImage_green);
        manager.unload(birdFrameImage_lightblue);
        manager.unload(birdFrameImage_pink);
    }

    public void loadSplashSkin() {
        manager.load(splash_skin, Skin.class);
    }

    public void loadSplashImage() {
        manager.load(splashBGImage, Texture.class);

    }

    public void unloadSplashImage() {
        manager.unload(splashBGImage);

    }

    /* FONTS */
    public void loadSplashFont() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        // First, let's define the params and then load our smaller font
        /*
         * FreetypeFontLoader.FreeTypeFontLoaderParameter mySmallFont = new
         * FreetypeFontLoader.FreeTypeFontLoaderParameter();
         * mySmallFont.fontFileName = splashFont;
         * mySmallFont.fontParameters.size = 10;
         * manager.load(splashFont, BitmapFont.class, mySmallFont);
         */

        // Next, let's define the params and then load our bigger font
        FreetypeFontLoader.FreeTypeFontLoaderParameter myBigFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        myBigFont.fontFileName = splashFont;

        myBigFont.fontParameters.size = 80;
        myBigFont.fontParameters.color = Color.WHITE;
        myBigFont.fontParameters.borderWidth = 2;
        myBigFont.fontParameters.borderColor = Color.FIREBRICK;
        myBigFont.fontParameters.borderStraight = true;
        myBigFont.fontParameters.shadowOffsetX = 10;
        myBigFont.fontParameters.shadowOffsetY = 10;
        myBigFont.fontParameters.shadowColor = new Color(0, 0, 0.1f, 0.75f);
        myBigFont.fontParameters.flip = false;
        manager.load(splashFont, BitmapFont.class, myBigFont);

    }

    public void unloadSplashFont() {
        manager.unload(splashFont);
    }

    public void loadSkin() {
        manager.load(shade_skin, Skin.class);
        manager.load(vpb_skin, Skin.class);
        manager.load(freezing_skin, Skin.class);
    }

    public void loadMenuFont() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter myBigFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        myBigFont.fontFileName = MenuFont;

        myBigFont.fontParameters.size = 60;
        myBigFont.fontParameters.color = Color.WHITE;
        myBigFont.fontParameters.borderWidth = 2;
        myBigFont.fontParameters.borderColor = Color.FIREBRICK;
        myBigFont.fontParameters.borderStraight = true;
        myBigFont.fontParameters.shadowOffsetX = 10;
        myBigFont.fontParameters.shadowOffsetY = 10;
        myBigFont.fontParameters.shadowColor = new Color(0, 0, 0.1f, 0.75f);
        myBigFont.fontParameters.flip = false;
        manager.load(MenuFont, BitmapFont.class, myBigFont);

    }

    public void unloadMenuFont() {
        manager.unload(MenuFont);
    }

    public void loadClassicLevelFonts() {
        manager.load(fontChillar, BitmapFont.class);
        manager.load(fontChillar_LevelInfo, BitmapFont.class);
        manager.load(fontVPB, BitmapFont.class);
    }

    public void unloadClassicLevelFonts() {
        manager.unload(fontChillar);
        manager.unload(fontChillar_LevelInfo);
        manager.unload(fontVPB);
    }

    public void loadEasyLevelFonts() {
        manager.load(fontChillar, BitmapFont.class);
        manager.load(fontChillar_LevelInfo, BitmapFont.class);
        manager.load(fontVPB, BitmapFont.class);
    }

    public void unloadEasyLevelFonts() {
        manager.unload(fontChillar);
        manager.unload(fontChillar_LevelInfo);
        manager.unload(fontVPB);

    }

    public void loadGameOverFonts() {
        manager.load(fontGameOver, BitmapFont.class);
    }

    public void unloadGameOverFonts() {
        manager.unload(fontGameOver);
    }

    public void loadhighScoreScreenFonts() {
        manager.load(fontChillar_highScoreInfo, BitmapFont.class);
    }

    public void unloadhighScoreScreenFonts() {
        manager.unload(fontChillar_highScoreInfo);
    }

    public void loadHowToPlayScreenFonts() {
        manager.load(fontChillar_hotToPlayInfo, BitmapFont.class);
    }

    public void unloadHowToPlayScreenFonts() {
        manager.unload(fontChillar_hotToPlayInfo);
    }

    public void unloadSkin() {
        manager.unload(shade_skin);
        manager.unload(vpb_skin);
        manager.unload(freezing_skin);
    }

    public void loadImages() {
        // manager.load(deepBackground,Texture.class);
        manager.load(MenuBackground, Texture.class);
        manager.load(SignInButtonImage, Texture.class);
        manager.load(birdYellowFlapUp, Texture.class);
        manager.load(birdFrame1, Texture.class);
        manager.load(birdFrame2, Texture.class);
        manager.load(birdFrame3, Texture.class);
        manager.load(birdFrame4, Texture.class);
        manager.load(birdFrame5, Texture.class);
        manager.load(birdFrame6, Texture.class);
        manager.load(birdFrame7, Texture.class);
        manager.load(birdFrame8, Texture.class);
        manager.load(birdRedFrame1, Texture.class);
        manager.load(birdRedFrame2, Texture.class);
        manager.load(birdFrameImage_blue, Texture.class);
        manager.load(birdFrameImage_green, Texture.class);
        manager.load(birdFrameImage_black, Texture.class);
        manager.load(birdFrameImage, Texture.class);
        manager.load(birdFrameImage_lightblue, Texture.class);
        manager.load(birdFrameImage_pink, Texture.class);
        manager.load(muteImage, Texture.class);
        manager.load(unmuteImage, Texture.class);

    }

    public void unloadImages() {
        // manager.load(deepBackground,Texture.class);
        manager.unload(MenuBackground);
        manager.unload(SignInButtonImage);
        manager.unload(birdYellowFlapUp);
        manager.unload(birdFrame1);
        manager.unload(birdFrame2);
        manager.unload(birdFrame3);
        manager.unload(birdFrame4);
        manager.unload(birdFrame5);
        manager.unload(birdFrame6);
        manager.unload(birdFrame7);
        manager.unload(birdFrame8);
        manager.unload(birdRedFrame1);
        manager.unload(birdRedFrame2);
        manager.unload(birdFrameImage_blue);
        manager.unload(birdFrameImage_green);
        manager.unload(birdFrameImage_black);
        manager.unload(birdFrameImage);
        manager.unload(birdFrameImage_lightblue);
        manager.unload(birdFrameImage_pink);
        manager.unload(muteImage);
        manager.unload(unmuteImage);

    }

    public void loadMenuMusic() {
        manager.load(MenuMusicFile, Music.class);
    }

    public void unloadMenuMusic() {
        manager.unload(MenuMusicFile);
    }

    public void loadClassicMusic() {

        manager.load(dieMusicFile, Sound.class);
        manager.load(pointMusicFile, Sound.class);
        manager.load(wingMusicFile, Sound.class);
    }

    public void unloadClassicMusic() {
        manager.unload(dieMusicFile);
        manager.unload(pointMusicFile);
        manager.unload(wingMusicFile);
    }

    public void loadEasyMusic() {

        manager.load(dieMusicFile, Sound.class);
        manager.load(pointMusicFile, Sound.class);
        manager.load(wingMusicFile, Sound.class);
    }

    public void unloadEasyMusic() {
        manager.unload(dieMusicFile);
        manager.unload(pointMusicFile);
        manager.unload(wingMusicFile);
    }
}
