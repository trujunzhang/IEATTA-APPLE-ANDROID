package android.yelp.com.commonlib;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by djzhang on 12/1/15.
 */
public class EnvironmentUtils {
    public static final EnvironmentUtils sharedInstance = new EnvironmentUtils();

    private AppCompatActivity activity;

    private EnvironmentUtils() {
    }

    public void registerGlobalContext(AppCompatActivity activity){
        this.activity = activity;
    }

    public Context getGlobalContext(){
        return this.activity;
    }

    public android.support.v4.app.FragmentManager getCurrentFragmentManager() {
        return this.activity.getSupportFragmentManager();
    }
}
