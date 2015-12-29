package android.virtualbreak.com.manualdatabase.migration;

import com.ieatta.com.parse.ParseJsoner;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.ParseModelLocalQuery;
import com.ieatta.com.parse.models.enums.PQueryModelType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/29/15.
 */
public class MigrateUtils {

    private Task<Void> migrateAllTables(){
        PQueryModelType[] types  = {
            PQueryModelType.Recipe,
                                        PQueryModelType.Photo,
                                        PQueryModelType.Team,
                                        PQueryModelType.Review,
                                        PQueryModelType.Event,
                                        PQueryModelType.Restaurant,
                                    PQueryModelType.NewRecord,
                                        PQueryModelType.PeopleInEvent,
        };
        List<PQueryModelType> list = new LinkedList<>(Arrays.asList(types));

        return Task.forResult(list)
                .onSuccessTask(new Continuation<List<PQueryModelType>, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<List<PQueryModelType>> results) throws Exception {
                        // Create a trivial completed task as a base case.
                        Task<Void> task = Task.forResult(null);
                        for (final PQueryModelType result : results.getResult()) {
                            // For each item, extend the task with a function to delete the item.
                            task = task.continueWithTask(new Continuation<Void, Task<Void>>() {
                                public Task<Void> then(Task<Void> ignored) throws Exception {
                                    // Return a task that will be marked as completed when the delete is finished.
//                                    return pushJsonObjectToServer(result);
                                    return saveLocalDatabase(result);
                                }
                            });
                        }
                        return task;
                    }
                });

    }

    private Task<Void> saveLocalDatabase(PQueryModelType type){
        return ParseJsoner.parseJsonFileToArray(type)
                .onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<List<ParseModelAbstract>> results) throws Exception {
                        Task<Void> task = Task.forResult(null);
                        for (final ParseModelAbstract result : results.getResult()) {
                            // For each item, extend the task with a function to delete the item.
                            task = task.continueWithTask(new Continuation<Void, Task<Void>>() {
                                public Task<Void> then(Task<Void> ignored) throws Exception {
                                    // Return a task that will be marked as completed when the delete is finished.
                                    return ((ParseModelLocalQuery)result).saveInBackground();
                                }
                            });
                        }

                        return task;
                    }
                });
    }

    /**
     Example: MigrateUtils().executeMigrate()
     */
    public  void executeMigrate(){
        this.migrateAllTables().onSuccessTask(new Continuation<Void, Task<Void>>() {
            @Override
            public Task<Void> then(Task<Void> task) throws Exception {
                return null;
            }
        });
    }

}
