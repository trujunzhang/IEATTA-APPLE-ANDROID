package android.virtualbreak.com.debug;

/**
 * Created by djzhang on 11/30/15.
 */
public class AppDebugManager {

    public static void show() {
        LogConfigure.setLogOption();
        ParseLocalDatabase.listLocalDatabase();
    }
}
