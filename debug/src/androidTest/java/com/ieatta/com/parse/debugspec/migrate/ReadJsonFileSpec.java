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
        final List<ParseModelAbstract>[] fetchedPhotos = new List[]{new LinkedList<>()};

        ParseJsoner.parseJsonFileToArray(PQueryModelType.Photo).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                fetchedPhotos[0] = (List<ParseModelAbstract>) task.getResult();
                return ParseJsoner.parseJsonFileToArray(PQueryModelType.NewRecord);
            }
        }).onSuccess(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                List<ParseModelAbstract> newRecords = (List<ParseModelAbstract>) task.getResult();

                List<ParseModelAbstract> photos = fetchedPhotos[0]; // 683
                List<NewRecord> newRecordForPhoto = getNewRecordForPhoto(newRecords); // 683-6

                matchPhotoFromNewRecord(photos,newRecords);
                return null;
            }
        });
    }

    private List<NewRecord> getNewRecordForPhoto(List<ParseModelAbstract> newRecords){
        List<NewRecord> list = new LinkedList<>();
        for(ParseModelAbstract model : newRecords){
            if(((NewRecord)model).modelType == PQueryModelType.Photo){
                list.add((NewRecord)model);
            }
        }

        return list;
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
