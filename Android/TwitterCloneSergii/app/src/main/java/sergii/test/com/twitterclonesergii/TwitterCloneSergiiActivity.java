package sergii.test.com.twitterclonesergii;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterCloneSergiiActivity extends AppCompatActivity {

    public final static String TWIT_URL = "//tnice-android:///";
    private static final String TAG = TwitterCloneSergiiActivity.class.getSimpleName();
    /**
     * shared preferences to store user details
     */
    private SharedPreferences nicePrefs;
    private ExecutorService executorService;
    private Navigator navigator;
    private RequestToken twitterToken;
    private Twitter twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);
        //get the preferences for the app
        nicePrefs = getSharedPreferences("TwitNicePrefs", 0);
        //find out if the user preferences are set
        if (nicePrefs.getString("user_token", null) == null) {
            //login();
            navigator.navigateTo(Type.EViewType.Login);
        } else {
            //user preferences are set - get timeline
            setupTimeline();
        }
    }

    private void init() {
        executorService = Executors.newFixedThreadPool(1);
        navigator = new Navigator(getFragmentManager());
    }

    public void runInWorkThread(Runnable r) {
        executorService.execute(r);
    }

    private void setupTimeline() {

    }

    /*
    * onNewIntent fires when user returns from Twitter authentication Web page
    */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        extractData(intent);
    }

    private void extractData(Intent intent) {
        //get the retrieved data
        Uri twitURI = intent.getData();
        //make sure the url is correct
        Log.d(TAG, "onNewIntent: twitURI: " + twitURI);

        //attempt to retrieve access token
        setupPreference(twitURI);
    }

    @Override
    protected void onResume() {
        super.onResume();
        extractData(getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        extractData(data);
    }

    private void setupPreference(Uri twitURI) {
        Log.d(TAG, "setupPreference: " + twitURI);

        if( twitURI  == null || twitter == null || twitterToken == null){
            return;
        }
        if (twitURI != null && twitURI.toString().startsWith(TWIT_URL) ) {
            return;
        }



        try {
            //try to get an access token using the returned data from the verification page
            //add the token and secret to shared prefs for future reference

            String oaVerifier = twitURI.getQueryParameter("oauth_verifier");
            AccessToken accToken = twitter.getOAuthAccessToken(twitterToken, oaVerifier);
            nicePrefs.edit()
                    .putString("user_token", accToken.getToken())
                    .putString("user_secret", accToken.getTokenSecret())
                    .commit();
            setupTimeline();
            //display the timeline

        } catch (TwitterException te) {
            Log.e(TAG, "Failed to get access token: " + te.getMessage());
        }
    }

    public void setTwitterToken(RequestToken twitterToken) {
        this.twitterToken = twitterToken;
    }

    public void setTwitterInstance(Twitter twitter) {
        this.twitter = twitter;
    }
}
