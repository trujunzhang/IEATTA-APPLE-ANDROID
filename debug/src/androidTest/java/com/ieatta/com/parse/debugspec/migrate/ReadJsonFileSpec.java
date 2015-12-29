package com.ieatta.com.parse.debugspec.migrate;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.InstrumentationTestCase;
import android.virtualbreak.com.debug.AppDebugManager;
import android.virtualbreak.com.debug.ParseLocalDatabase;
import android.virtualbreak.com.debug.R;
import android.virtualbreak.com.manualdatabase.migration.MigrateUtils;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.com.parse.ParseJsoner;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.enums.PQueryModelType;
import com.ieatta.com.parse.utils.cache.ImageOptimizeUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/6/15.
 */
public class ReadJsonFileSpec extends InstrumentationTestCase {
    private ReadJsonFileSpec self = this;
    private Context context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        self.context = this.getInstrumentation().getContext();
        EnvironmentUtils.sharedInstance.registerGlobalContext(this.context);
    }

    public void testConstructor() throws Exception {
//        ParseJsoner.parseJsonFileToArray(PQueryModelType.NewRecord);

//        new MigrateUtils().executeMigrate()
//                .onSuccess(new Continuation<Void, Object>() {
//            @Override
//            public Object then(Task<Void> task) throws Exception {
//                return ParseLocalDatabase.queryLocalDatastoreInBackground(new NewRecord().makeLocalQuery(), PQueryModelType.NewRecord);
//            }
//        });
    }


    public void testMatchedPhotoFromNewRecord(){
//        ParseJsoner.parseJsonFileToArray()

        LocalQuery localQuery = new  Photo().makeLocalQuery();
        final List<ParseModelAbstract>[] fetchedPhotos = new List[]{new LinkedList<>()};
        localQuery.findInBackground()
                .onSuccessTask(new Continuation<Task<List<ParseModelAbstract>>,Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task task) throws Exception {
                fetchedPhotos[0] = (List<ParseModelAbstract>) task.getResult();
                return ParseJsoner.parseJsonFileToArray(PQueryModelType.Photo);
            }
        }).onSuccess(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                List<ParseModelAbstract> jsonModels = (List<ParseModelAbstract>) task.getResult();
                matchPhotoFromNewRecord(fetchedPhotos[0],jsonModels);
                return null;
            }
        });
    }

    private void matchPhotoFromNewRecord(List<ParseModelAbstract> fetchedPhoto,List<ParseModelAbstract> jsonModels){
        for(ParseModelAbstract model : jsonModels){
            if(containedInPhoto(model,fetchedPhoto)){

            }
        }
    }

    private boolean containedInPhoto(ParseModelAbstract jsonModel,List<ParseModelAbstract> fetchedPhoto){
        for(ParseModelAbstract model :fetchedPhoto){
            if(model.equals(jsonModel)){
                return true;
            }
        }
        return false;
    }

}
