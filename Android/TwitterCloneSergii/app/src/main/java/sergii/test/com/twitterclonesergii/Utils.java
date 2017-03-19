package sergii.test.com.twitterclonesergii;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by s.muzychuk on 2/10/2017.
 */

public class Utils {

    public static boolean isOnline(Context aContext) {
        if(aContext == null) {
            return false;
        }
        final ConnectivityManager cm = (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
