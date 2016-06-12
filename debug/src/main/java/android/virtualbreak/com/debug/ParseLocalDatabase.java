package android.virtualbreak.com.debug;


import com.ieatta.com.parse.ParseModelLocalQuery;
import com.ieatta.com.parse.ParseModelOnlineQuery;

import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.PeopleInEvent;

import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;

import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseQuery;

import java.util.List;

import bolts.Continuation;
import bolts.Task;


/**
 * Created by djzhang on 11/30/15.
 */
public class ParseLocalDatabase {

    public static void listLocalDatabase() {

        // =============================================================================
        //        Team
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeLocalQuery(), PQueryModelType.Team);

        // =============================================================================
        //        Restaurant
        // =============================================================================
        ParseLocalDatabase.queryParseDatastoreInBackground(new Restaurant().makeParseQuery(), PQueryModelType.Restaurant);

        // =============================================================================
        //        Event
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Event().makeLocalQuery(), PQueryModelType.Event);

        // =============================================================================
        //        OrderedPeople
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new PeopleInEvent().makeLocalQuery(), PQueryModelType.PeopleInEvent);

        // =============================================================================
        //        Recipes
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Recipe().makeLocalQuery(), PQueryModelType.Recipe);

        // =============================================================================
        //        Photo
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Photo().makeLocalQuery(), PQueryModelType.Photo);

        // =============================================================================
        //        Review
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Review().makeLocalQuery(), PQueryModelType.Review);

        // =============================================================================
        //        Local New Record
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeLocalQuery(), PQueryModelType.NewRecord);

//        // =============================================================================
//        //        Cache image file names
//        // =============================================================================
//        ParseLocalDatabase.listAllCacheImageNames();
    }

    // MARK: Retrieve offline database for test.
    public static Task<Void> queryLocalDatastoreInBackground(LocalQuery query, final PQueryModelType classType) {
        return ParseModelLocalQuery.queryFromRealm(classType, query)
                .onSuccess(new Continuation<List , Void>() {
            @Override
            public Void then(Task<List > task) throws Exception {
                ParseLocalDatabase.printList(classType, task.getResult());
                return null;
            }
        }).continueWith(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
                    String message = error.getMessage();
                    int x = 0;
                    Throwable cause = error.getCause();
                }
                return null;
            }
        });
    }

    public static  Task<Void>  queryParseDatastoreInBackground(ParseQuery parseQuery,  final PQueryModelType classType) {
        return ParseModelOnlineQuery.queryFromParse(classType,parseQuery).onSuccess(new Continuation<List , Void>() {
            @Override
            public Void then(Task<List > task) throws Exception {
                ParseLocalDatabase.printList(classType,task.getResult());
                return null;
            }
        });
    }

    // com.parse.ParseObject cannot be cast to
    public static void printList(PQueryModelType type, List  array) {
        LogConfigure.DDLogVerbose("");
        LogConfigure.DDLogVerbose("<<-------------------------------------------------------");
        LogConfigure.DDLogVerbose("Count after query * " + PQueryModelType.getString(type) + " * in background: " + array.size());
        LogConfigure.DDLogVerbose("-------------------------------------------------------");
        for (ParseModelAbstract model : array) {
            LogConfigure.DDLogVerbose(model.printDescription());
        }
        LogConfigure.DDLogVerbose("------------------------------------------------------->>");
        LogConfigure.DDLogVerbose("");
    }


    // =============================================================================
    //        Cache image file names
    // =============================================================================
    public static void listAllCacheImageNames() {
//        let array:NSMutableArray = OriginalImageUtils.sharedInstance.listCacheImageNames();
        LogConfigure.DDLogVerbose("");
        LogConfigure.DDLogVerbose("<<-------------------------------------------------------");
        LogConfigure.DDLogVerbose("  List Original image file names");
        LogConfigure.DDLogVerbose("-------------------------------------------------------");
//        for filename in array{
//            let name = filename as! String
//            LogConfigure.DDLogVerbose("cached image file name: \(name);");
//        }

        LogConfigure.DDLogVerbose("------------------------------------------------------->>");
        LogConfigure.DDLogVerbose("");
    }

}
