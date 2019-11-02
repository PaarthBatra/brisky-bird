package com.versionpb.game.helpers;

import com.badlogic.gdx.graphics.Color;

public class GameInfo {

    //Old BriskyBird Related details
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Brisky Bird";
    public static final String leaderboard_Easy = "CgkIqZPG8KYYEAIQAQ";
    public  static final String leaderboard_Classic = "CgkIqZPG8KYYEAIQBQ";


    //common
    public static final String PREFERENCES = "My Preferences";
    public static final String TAP_ON_BIRD_TO_BEGIN = "Tap on Bird to Begin";

    //SplashScreen Screen time
    public static final float splashScreen_Milliseconds = 3000;

    public static final float Desktop_GAME_WIDTH = 480;
    public static final float Desktop_GAME_HEIGHT = 800;

    public static final float LineOffsetX = 25;
    public static final float LineAdjustmentOffsetX = 1;
    public static final float GAME_WIDTH = 480;
    public static final float GAME_HEIGHT = 800;

    public static final float Rectangle_Width = 5;


    public static final String str = "VersionPB";
    public  static final Color SplashScreenUnderLineColor = Color.WHITE;

    //Loading Bar Screen
    public static final float labelTopPadding = 200;
    public static final float progressBarXOffset = 100;
    public static final int labelLoadingTopPadding = 200;
    public static  final String loadingLabelText= "Loading . . .";
    public static  final String loadingLabelStyleName = "title-plain";

    //LoadingClassic Level Srceen
    public static  final String loadingClassicScreenLabelText= "Loading Classic Level . . .";

    //Loading Easy Level Screeb
    public static  final String loadingEasyScreenLabelText= "Loading Easy Level . . .";

    //Brisky Bird Menu Screen
    public static final String MenuHeading = "Brisky Bird \n Main Menu";
    public static final String appVersion ="Ver 1.10";
    public static final int rootTableInvisibleLabelPadBottom = 200;
    public static final int allButtonsLeftPadding = -100;
    public static final int versionNumberRightPadding = -150;

    public static final String skin_vpbLabelStyle="vpbLabel";
    public static final String classicButtonText = "Classic";
    public static final String easyButtonText = "Easy";
    public static final String sagaButtonText = "Saga";
    public static final String highScoreButtonText = "High Scores";
    public static final String howToPlayButtonText = "How  To Play";

    //High Score Screen
    public static final String EasyModeHighScores = "Easy Mode";
    public static final String ClassicModeHighScores = "Classic Mode";
    public static final String HIGHSCORES = "High Scores";
    public static final String PlayServicesSignInMsg = "*You wont be able to see Leaderboards until \nyou are logged in to Google Play Services .\n" +
            "To Log in go to Menu State and tap Sign In \n\n**To Add your Name in high score Leader Boards \nSet your google play services profile Public";

    //How To Play Screen
    public static final String howToPlayMessage = "Keep Tapping the screen \n to Make the bird fly \n\n\n Make sure the bird never \n        touches \n Pipes or Ground or Sky \n\n\n Tap to Start Enjoying ...";



    //PlayClassicLevelScreen
    public static final int CLASSIC_LEVEL_TUBE_SPACING = 125;
    public static final int CLASSIC_LEVEL_TUBE_COUNT = 4;
    public static final int CLASSIC_LEVEL_GROUND_OFFSET = -30;
    public static final String CLASSIC_LEVEL_LevelInfo = "Level : Classic";
    public static final String CLASSIC_LEVEL_HighScore = "ClassicHighScore";


    //PlayEasyLevelScreen
    public static final int EASY_LEVEL_TUBE_SPACING = 255;
    public static final int EASY_LEVEL_TUBE_COUNT = 4;
    public static final int EASY_LEVEL_GROUND_OFFSET = -30;
    public static final String EASY_LEVEL_LevelInfo = "Level : Easy";
    public static final String EASY_LEVEL_HighScore = "EasyHighScore";
}
