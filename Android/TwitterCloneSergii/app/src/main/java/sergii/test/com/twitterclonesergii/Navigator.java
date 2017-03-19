package sergii.test.com.twitterclonesergii;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by sergii on 10.02.17.
 */

public class Navigator {

    private FragmentManager fragmentManager;

    public Navigator(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public void navigateTo( Type.EViewType type ){

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch ( type ){
            case Login:
                fragmentTransaction.add(R.id.root, new LoginFragment());
                break;
        }
        fragmentTransaction.commit();
    }
}
