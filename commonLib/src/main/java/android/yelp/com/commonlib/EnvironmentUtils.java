package android.yelp.com.commonlib;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by djzhang on 12/1/15.
 */
public class EnvironmentUtils {
    public static final EnvironmentUtils sharedInstance = new EnvironmentUtils();

    private AppCompatActivity activity;
    private Context context;

    private EnvironmentUtils() {
    }

    public void registerCurrentActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    public void registerGlobalContext(Context context){
        this.context = context;
    }

    public Context getGlobalContext(){
        if(this.activity !=null){
            return this.activity.getApplicationContext();
        }
        return this.context;
    }

    public android.support.v4.app.FragmentManager getCurrentFragmentManager() {
        return this.activity.getSupportFragmentManager();
    }
}
