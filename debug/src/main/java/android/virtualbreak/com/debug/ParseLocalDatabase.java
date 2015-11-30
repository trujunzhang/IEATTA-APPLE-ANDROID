package android.virtualbreak.com.debug;

/**
 * Created by djzhang on 11/30/15.
 */
public class ParseLocalDatabase {

    public static void listLocalDatabase(){

        // =============================================================================
        //        Team
        // =============================================================================
        this.queryLocalDatastoreInBackground(Team().makeParseQuery(),PQeuryModelType.Team);

        // =============================================================================
        //        Restaurant
        // =============================================================================
        this.queryLocalDatastoreInBackground(Restaurant().makeParseQuery(),PQeuryModelType.Restaurant);

        // =============================================================================
        //        Waiter
        // =============================================================================
        this.queryLocalDatastoreInBackground(Waiter().makeParseQuery(),PQeuryModelType.Waiter)

        // =============================================================================
        //        Event
        // =============================================================================
        this.queryLocalDatastoreInBackground(Event().makeParseQuery(),PQeuryModelType.Event)

        // =============================================================================
        //        OrderedPeople
        // =============================================================================
        this.queryLocalDatastoreInBackground(PeopleInEvent().makeParseQuery(),PQeuryModelType.PeopleInEvent)

        // =============================================================================
        //        Recipes
        // =============================================================================
        this.queryLocalDatastoreInBackground(Recipe().makeParseQuery(),PQeuryModelType.Recipe)

        // =============================================================================
        //        Photo
        // =============================================================================
        this.queryLocalDatastoreInBackground(Photo().makeParseQuery(),PQeuryModelType.Photo)

        // =============================================================================
        //        Review
        // =============================================================================
        this.queryLocalDatastoreInBackground(Review().makeParseQuery(),PQeuryModelType.Review)

        // =============================================================================
        //        Local New Record
        // =============================================================================
        this.queryLocalDatastoreInBackground(NewRecord().makeParseQuery(),PQeuryModelType.NewRecord)

        // =============================================================================
        //        Cache image file names
        // =============================================================================
        this.listAllCacheImageNames()
    }



    // MARK: Retrieve offline database for test.
    public static void queryLocalDatastoreInBackground(PFQuery query, PQeuryModelType type ) {
        ParseModelQuery.findLocalObjectsInBackground(query).continueWithSuccessBlock { (task) -> AnyObject? in

            let value = task.result as! NSArray

            if (value.count > 0) {
                let instance = ParseModelAbstract.getInstanceFromType(classType.rawValue)
                let array:[ParseModelAbstract] = instance.convertToParseModelArray(task.result, offline: true) as! [ParseModelAbstract]

                ParseLocalDatabase.printList(classType, array: array)

            }

            return nil
        }
    }

    public static void printList(PQeuryModelType PQeuryModelType,array:[ParseModelAbstract]){
        DDLogVerbose("")
        DDLogVerbose("<<-------------------------------------------------------")
        DDLogVerbose("Count after query * \(PQeuryModelTypeNames[classType.rawValue]) * in background: \(array.count)")
        DDLogVerbose("-------------------------------------------------------")
        for model in array{
            // Print instance's description
            DDLogVerbose(model.printDescription())
        }
        DDLogVerbose("------------------------------------------------------->>")
        DDLogVerbose("")
    }


    // =============================================================================
    //        Cache image file names
    // =============================================================================
    private public static void listAllCacheImageNames(){
        let array:NSMutableArray = OriginalImageUtils.sharedInstance.listCacheImageNames()
        DDLogVerbose("")
        DDLogVerbose("<<-------------------------------------------------------")
        DDLogVerbose("  List Original image file names")
        DDLogVerbose("-------------------------------------------------------")
        for filename in array{
            let name = filename as! String
            DDLogVerbose("cached image file name: \(name)")
        }

        DDLogVerbose("------------------------------------------------------->>")
        DDLogVerbose("")
    }

}
