package android.virtualbreak.com.manualdatabase.migration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.InstrumentationTestCase;
import android.virtualbreak.com.manualdatabase.migration.MigrateUtils;
import android.yelp.com.commonlib.EnvironmentUtils;
import android.yelp.com.commonlib.LogUtils;

import com.ieatta.com.parse.ParseJsoner;

import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.NewRecord;

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
 * Created by djzhang on 12/29/15.
 */
public class CompareDatabaseUtils {

    public void testMatchedPhotoFromNewRecord() {
//        ParseJsoner.parseJsonFileToArray()

        LocalQuery localQuery = new Photo().makeLocalQuery();
        final List [] fetchedPhotos = new List[]{new LinkedList<>()};
        localQuery.findInBackground()
                .onSuccessTask(new Continuation<Task<List >, Task<List >>() {
                    @Override
                    public Task<List > then(Task task) throws Exception {
                        fetchedPhotos[0] = (List ) task.getResult();
                        return ParseJsoner.parseJsonFileToArray(PQueryModelType.NewRecord);
                    }
                }).onSuccess(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                List  jsonNewRecord = (List ) task.getResult();
                matchPhotoFromNewRecord(fetchedPhotos[0], jsonNewRecord);
                return null;
            }
        });
    }

    private void matchPhotoFromNewRecord(List  fetchedPhoto, List  jsonNewRecord) {
        for (ParseModelAbstract model : jsonNewRecord) {
            NewRecord newRecord = (NewRecord) model;
            if (newRecord.modelType != PQueryModelType.Photo) {
                continue;
            }
            if (containedInPhoto(newRecord, fetchedPhoto)) {

            }
        }
    }

    private boolean containedInPhoto(NewRecord jsonModel, List  fetchedPhoto) {
        for (ParseModelAbstract model : fetchedPhoto) {
            if (ParseModelAbstract.getPoint(model).equals(jsonModel.modelPoint)) {
                if(jsonModel.modelPoint.contains("69418898")){
                    int x = 0;
                    PQueryModelType modelType = jsonModel.modelType;
                }
                return true;
            }
        }

        LogUtils.debug(jsonModel.printDescription());
        return false;
    }


}
