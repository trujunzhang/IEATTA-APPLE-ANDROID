package android.yelp.com.commonlib;

import android.content.Context;

/**
 * Created by djzhang on 12/1/15.
 */
public class EnvironmentUtils {
    public static final EnvironmentUtils sharedInstance = new EnvironmentUtils();

    private Context context;

    private EnvironmentUtils() {
    }

    public void registerGlobalContext(Context context){
        this.context = context;
    }

    public Context getGlobalContext(){
        return this.context;
    }
}
