package android.yelp.com.commonlib;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

/**
 * Created by djzhang on 12/1/15.
 */
public class EnvironmentUtils {
    public static final EnvironmentUtils sharedInstance = new EnvironmentUtils();

    private Activity activity;

    private EnvironmentUtils() {
    }

    public void registerGlobalContext(Activity activity){
        this.activity = activity;
    }

    public Context getGlobalContext(){
        return this.activity;
    }

    public FragmentManager getFragmentManager() {
        return this.getFragmentManager();
    }
}
