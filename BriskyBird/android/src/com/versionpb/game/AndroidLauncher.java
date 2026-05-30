package com.versionpb.game;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.versionpb.game.BriskyBird;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;

public class AndroidLauncher extends AndroidApplication implements AdHandler,PlayServices {
    private static final String TAG = "AndroidLauncher";
    private static final String leaderboard_Easy = "CgkIqZPG8KYYEAIQAQ";
    //private  static final String leaderboard_Classic = "CgkIqZPG8KYYEAIQBQ";
    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;
    protected AdView adView;

    private boolean mIsSignedIn = false;




    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ADS:
                    adView.setVisibility(View.VISIBLE);
                    break;
                case HIDE_ADS:
                    adView.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PlayGamesSdk.initialize(this);
        PlayGames.getGamesSignInClient(this).isAuthenticated().addOnCompleteListener(task -> {
            mIsSignedIn = task.isSuccessful() && task.getResult().isAuthenticated();
            System.out.println("LBVPB : Silent Sign In result: " + mIsSignedIn);
        });

        MobileAds.initialize(this, initializationStatus -> {});


        RelativeLayout layout = new RelativeLayout(this);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        View gameView = initializeForView(new BriskyBird(this,this), config);
        layout.addView(gameView);
        adView = new AdView(this);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "Ad Loaded ... ");
            }
        });

        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-2542346272130601/7300518177");

        AdRequest.Builder builder = new AdRequest.Builder();

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(adView, adParams);

        adView.loadAd(builder.build());

        setContentView(layout);

    }

    @Override
    public void showAds(boolean show) {
        handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("LBVPB : onStart Method");
    }

    @Override
    public void onStartMethod() {
        super.onStart();
        System.out.println("LBVPB : onStart Method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("LBVPB : onStop Method");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("LBVPB : onActivityResult Method");
    }

    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("LBVPB:Signin In");
                    PlayGames.getGamesSignInClient(AndroidLauncher.this).signIn().addOnCompleteListener(task -> {
                        mIsSignedIn = task.isSuccessful() && task.getResult().isAuthenticated();
                        System.out.println("LBVPB : signIn Method result: " + mIsSignedIn);
                    });
                }
            });
        } catch (Exception e) {
            System.out.println("LBVPB: ExceptionSignin in Failed");
        }
    }

    @Override
    public void signOut() {
        mIsSignedIn = false;
        System.out.println("LBVPB : SignOut Method reset locally");
    }

    @Override
    public void rateGame() {

        String str = "Your PlayStore Link";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void unlockAchievement(String str) {
        if (mIsSignedIn) {
            PlayGames.getAchievementsClient(this).unlock(str);
        }
    }

    @Override
    public void submitScore(String LeaderBoard,int highScore) {
        if (isSignedIn()) {
            System.out.println("LBVPB : submitScore Start ");
            PlayGames.getLeaderboardsClient(this).submitScore(LeaderBoard, highScore);
            System.out.println("LBVPB : submitScoreEasy Succes ");
        }
        else{
            System.out.println("LBVPB : Not signin Yaar ");
        }

    }



    @Override
    public void submitLevel(int highLevel) {
        if (isSignedIn()) {
            PlayGames.getLeaderboardsClient(this).submitScore(leaderboard_Easy, highLevel);
        }
    }

    @Override
    public void showAchievement() {
        if (isSignedIn()) {
            PlayGames.getAchievementsClient(this)
                .getAchievementsIntent()
                .addOnSuccessListener(intent -> startActivityForResult(intent, 1));
        } else {
            signIn();
        }
    }

    @Override
    public void showScore(String leaderboard_Easy) {
        if (isSignedIn()) {
            PlayGames.getLeaderboardsClient(this)
                .getLeaderboardIntent(leaderboard_Easy)
                .addOnSuccessListener(intent -> startActivityForResult(intent, 1));
        } else {
            signIn();
        }
    }



    @Override
    public void showLevel() {
        if (isSignedIn()) {
            PlayGames.getLeaderboardsClient(this)
                .getLeaderboardIntent(String.valueOf(R.string.leaderboard_easy))
                .addOnSuccessListener(intent -> startActivityForResult(intent, 1));
        } else {
            signIn();
        }
    }

    @Override
    public boolean isSignedIn() {
        return mIsSignedIn;
    }

}
