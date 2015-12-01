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
import com.ieatta.com.parse.models.enums.PQeuryModelType;
import com.parse.Parse;
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
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Team().makeParseQuery(), PQeuryModelType.Team);

        // =============================================================================
        //        Restaurant
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Restaurant().makeParseQuery(), PQeuryModelType.Restaurant);

        // =============================================================================
        //        Event
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Event().makeParseQuery(), PQeuryModelType.Event);

        // =============================================================================
        //        OrderedPeople
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new PeopleInEvent().makeParseQuery(), PQeuryModelType.PeopleInEvent);

        // =============================================================================
        //        Recipes
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Recipe().makeParseQuery(), PQeuryModelType.Recipe);

        // =============================================================================
        //        Photo
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Photo().makeParseQuery(), PQeuryModelType.Photo);

        // =============================================================================
        //        Review
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new Review().makeParseQuery(), PQeuryModelType.Review);

        // =============================================================================
        //        Local New Record
        // =============================================================================
        ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeParseQuery(), PQeuryModelType.NewRecord);

        // =============================================================================
        //        Cache image file names
        // =============================================================================
//        ParseLocalDatabase.listAllCacheImageNames();
    }



    // MARK: Retrieve offline database for test.
    public static void queryLocalDatastoreInBackground(ParseQuery query, final PQeuryModelType classType ) {
        ParseModelQuery.findLocalObjectsInBackground(query).continueWith(new Continuation<List<ParseObject>, Object>() {
            @Override
            public Object then(Task<List<ParseObject>> task) throws Exception {
                LinkedList<Object> value = new LinkedList<Object>(task.getResult());
                if(value.size() > 0){
                    ParseModelAbstract instance = ParseModelAbstract.getInstanceFromType(classType);
                    LinkedList<ParseModelAbstract> array = instance.convertToParseModelArray(value, true);
                    ParseLocalDatabase.printList(classType,array);
                }
                
                return null;
            }
        });
    }

    public static void printList(PQeuryModelType type,LinkedList<ParseModelAbstract> array){
        LogConfigure.DDLogVerbose("");
        LogConfigure.DDLogVerbose("<<-------------------------------------------------------");
//        LogConfigure.DDLogVerbose("Count after query * \(PQeuryModelTypeNames[classType.rawValue]); * in background: \(array.count);");
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
