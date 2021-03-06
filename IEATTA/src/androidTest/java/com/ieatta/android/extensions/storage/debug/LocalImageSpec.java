package com.ieatta.android.extensions.storage.debug;

import android.test.InstrumentationTestCase;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.engine.realm.LocalQuery;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.utils.cache.OriginalImageUtils;
import com.ieatta.com.parse.utils.cache.ThumbnailImageUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/29/15.
 */
public class LocalImageSpec extends InstrumentationTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testImageNames() throws Exception {
        LocalQuery query = new Photo().makeLocalQuery();
        final List<ParseModelAbstract> notFoundList = new LinkedList<>();

        query.findInBackground()
                .onSuccessTask(new Continuation<List<ParseModelAbstract>, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<List<ParseModelAbstract>> results) throws Exception {
                        List<ParseModelAbstract> photos = results.getResult();

                        Task<Void> task = Task.forResult(null);
                        for (final ParseModelAbstract photo : photos) {
                            // For each item, extend the task with a function to delete the item.
                            task = task.continueWithTask(new Continuation<Void, Task<Void>>() {
                                public Task<Void> then(Task<Void> ignored) throws Exception {
                                    // Return a task that will be marked as completed when the delete is finished.
                                    return verifyImageNameFromPhoto((Photo) photo).continueWith(new Continuation<Void, Void>() {
                                        @Override
                                        public Void then(Task<Void> task) throws Exception {
                                            if (task.isFaulted()) {
                                                Exception error = task.getError();
                                                String message = error.getMessage();
                                                notFoundList.add(photo);
                                            }
                                            return null;
                                        }
                                    });
                                }
                            });
                        }

                        return task;
                    }
                }).continueWith(new Continuation() {
            @Override
            public Object then(Task task) throws Exception {
                if (task.isFaulted()) {
                    Exception error = task.getError();
                    String message = error.getMessage();
                }
                return null;
            }
        });

        if(notFoundList.size() != 0){
            int size = notFoundList.size();
        }else{
            int size = notFoundList.size();
        }
    }

    private Task<Void> verifyImageNameFromPhoto(final Photo photo) {
        String uuid = ParseModelAbstract.getPoint(photo);

        File originalFile = OriginalImageUtils.sharedInstance.getTakenPhotoFile(uuid);
        File thumbnailFile = ThumbnailImageUtils.sharedInstance.getTakenPhotoFile(uuid);

        if (originalFile == null || originalFile.exists() == false) {
            return Task.forError(new NullPointerException("not found bitmaps for " + photo.printDescription()));
        }

        if (thumbnailFile == null || thumbnailFile.exists() == false) {
            return Task.forError(new NullPointerException("not found bitmaps for " + photo.printDescription()));
        }

        return Task.forResult(null);
    }

    public void texstMd5FileName() throws Exception {
//        String uuid = "E7D63E85-26BE-4856-AD7E-EC8D99C046BC";
//        File takenPhotoFile = OriginalImageUtils.sharedInstance.getTakenPhotoFile(uuid);
//        byte[] bytes = FileUtils.toByteArray(takenPhotoFile.getAbsolutePath());
//
//        String generate = new Md5FileNameGenerator().generate(uuid);
//        int length = generate.length();

    }
}
