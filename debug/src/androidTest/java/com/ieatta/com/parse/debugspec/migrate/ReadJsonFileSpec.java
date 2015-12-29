package com.ieatta.com.parse.debugspec.migrate;


import android.content.Context;
import android.test.InstrumentationTestCase;
import android.yelp.com.commonlib.EnvironmentUtils;
import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.ParseJsoner;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.NewRecord;
import com.ieatta.com.parse.models.enums.PQueryModelType;

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

    private List<ParseModelAbstract> fetchedPhotos = new LinkedList<>();

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


    public void testMatchedPhotoFromNewRecord() {
        ParseJsoner.parseJsonFileToArray(PQueryModelType.Photo).onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<List<ParseModelAbstract>>>() {
            @Override
            public Task<List<ParseModelAbstract>> then(Task<List<ParseModelAbstract>> task) throws Exception {
                fetchedPhotos =  task.getResult();
                return ParseJsoner.parseJsonFileToArray(PQueryModelType.NewRecord);
            }
        }).onSuccess(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                List<ParseModelAbstract> newRecords = (List<ParseModelAbstract>) task.getResult();

                List<ParseModelAbstract> photos = fetchedPhotos; // 683
                List<NewRecord> newRecordForPhoto = getNewRecordForPhoto(newRecords); // 683-6

                matchPhotoFromNewRecord(newRecordForPhoto, photos);
                return null;
            }
        });
    }

    private List<NewRecord> getNewRecordForPhoto(List<ParseModelAbstract> newRecords) {
        List<NewRecord> list = new LinkedList<>();
        for (ParseModelAbstract model : newRecords) {
            if (((NewRecord) model).modelType == PQueryModelType.Photo) {
                list.add((NewRecord) model);
            }
        }

        return list;
    }

    private void matchPhotoFromNewRecord(List<NewRecord> newRecordForPhoto, List<ParseModelAbstract> photos) {
        LinkedList<ParseModelAbstract> matchedPhotos = new LinkedList<>();
        for (ParseModelAbstract photo : photos) {

            ParseModelAbstract item = containedInPhoto(photo, newRecordForPhoto,matchedPhotos);

        }

        int size = matchedPhotos.size();

        int length = photos.size();
    }

    private ParseModelAbstract containedInPhoto(ParseModelAbstract photo, List<NewRecord> newRecordForPhoto, LinkedList<ParseModelAbstract> matchedPhotos) {
        for (NewRecord newRecord : newRecordForPhoto) {
            if (ParseModelAbstract.getPoint(photo).equals(newRecord.modelPoint)) {
                return null;
            }
        }

        matchedPhotos.add(photo);
//        LogUtils.debug(photo.printDescription());
        return photo;
    }

}
