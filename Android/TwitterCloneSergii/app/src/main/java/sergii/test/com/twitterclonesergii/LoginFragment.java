package sergii.test.com.twitterclonesergii;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * Created by sergii on 10.02.17.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = LoginFragment.class.getSimpleName();

    //check gmail twitter demo
    private Twitter niceTwitter;
    private RequestToken niceRequestToken;
    // TODO: 10.02.17 use interface
    private TwitterCloneSergiiActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, null);
        populdateView( view );
        return view;
    }

    private void populdateView(View view) {
        view.findViewById(R.id.signin).setOnClickListener(this);
        populateTwitter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (TwitterCloneSergiiActivity) getActivity();
    }

    private void populateTwitter() {
        if (!Utils.isOnline(getContext())) {
            // TODO: 2/10/2017 add view in xml: no connection and retry
            Log.d(TAG, "login: no internet");
            return;
        }
        //get a twitter instance for authentication
        niceTwitter = new TwitterFactory().getInstance();
        //pass developer key and secret
        niceTwitter.setOAuthConsumer(TWIT_KEY, TWIT_SECRET);
        activity.setTwitterInstance( niceTwitter );

        //try to get request token
        activity.runInWorkThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: getOAuthRequestToken");
                try {
                    //get authentication request token
                    niceRequestToken = niceTwitter.getOAuthRequestToken("https://api.twitter.com/oauth/authorize");
                    activity.setTwitterToken(niceRequestToken);
                    Log.d(TAG, "run: result " + niceRequestToken);
                } catch (TwitterException te) {
                    Log.e(TAG, "run: error " + te.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        //find view
        switch (v.getId()) {
            //sign in button pressed
            case R.id.signin:
                //take user to twitter authentication web page to allow app access to their twitter account
                if (niceRequestToken != null) {
                    String authURL = niceRequestToken.getAuthenticationURL();

                    startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(authURL)), 123);
                }
                break;
            //other listeners here
            default:
                break;
        }
    }
}
