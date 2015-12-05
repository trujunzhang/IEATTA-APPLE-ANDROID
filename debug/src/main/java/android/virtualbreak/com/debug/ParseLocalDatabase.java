package android.virtualbreak.com.debug;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelQuery;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Recipe;
import com.ieatta.com.parse.models.Restaurant;
import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.Team;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;


/**
 * Created by djzhang on 11/30/15.
 */
public class ParseLocalDatabase {

    public static void listLocalDatabase(){

        // =============================================================================
        //        Team
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeParseQuery(), PQueryModelType.Team);

        // =============================================================================
        //        Restaurant
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Restaurant().makeParseQuery(), PQueryModelType.Restaurant);

        // =============================================================================
        //        Event
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Event().makeParseQuery(), PQueryModelType.Event);

        // =============================================================================
        //        OrderedPeople
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new PeopleInEvent().makeParseQuery(), PQueryModelType.PeopleInEvent);

        // =============================================================================
        //        Recipes
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Recipe().makeParseQuery(), PQueryModelType.Recipe);

        // =============================================================================
        //        Photo
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Photo().makeParseQuery(), PQueryModelType.Photo);

        // =============================================================================
        //        Review
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Review().makeParseQuery(), PQueryModelType.Review);

        // =============================================================================
        //        Local New Record
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeParseQuery(), PQueryModelType.NewRecord);

        // =============================================================================
        //        Cache image file names
        // =============================================================================
//        ParseLocalDatabase.listAllCacheImageNames();
    }

    // MARK: Retrieve offline database for test.
    public static Task<Void> queryLocalDatastoreInBackground(ParseQuery query, final PQueryModelType classType ) {
        return ParseModelQuery.findLocalObjectsInBackground(query)
                .continueWith(new Continuation<List<ParseObject>, Void>() {
            @Override
            public Void then(Task<List<ParseObject>> task) throws Exception {
                List<ParseObject> value = task.getResult();
                if(value.size() > 0){
                    ParseModelAbstract instance = ParseModelAbstract.getInstanceFromType(classType);
                    List<ParseModelAbstract> array = instance.convertToParseModelArray(value, true);
                    ParseLocalDatabase.printList(classType,array);
                }

                return null;
            }
        });
    }

    public static void printList(PQueryModelType type,List<ParseModelAbstract> array){
        LogConfigure.DDLogVerbose("");
        LogConfigure.DDLogVerbose("<<-------------------------------------------------------");
        LogConfigure.DDLogVerbose("Count after query * "+ PQueryModelType.getString(type) +" * in background: "+array.size());
        LogConfigure.DDLogVerbose("-------------------------------------------------------");
        for(ParseModelAbstract model:array){
            // Print instance's description
            LogConfigure.DDLogVerbose(model.printDescription());
        }
        LogConfigure.DDLogVerbose("------------------------------------------------------->>");
        LogConfigure.DDLogVerbose("");
    }


    // =============================================================================
    //        Cache image file names
    // =============================================================================
     public static void listAllCacheImageNames(){
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
