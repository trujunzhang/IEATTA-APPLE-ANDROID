package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.commons.io.FileExistsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */

/// How to store a taken photo to decrease client storage.
///   1.  When taken a photo, save it as original and thumbnail formats.
///   2.1 When pushing to server, push original and thumbnail images to server.
///   2.2 When pushed successfully, delete offline original image.
///   3.  When pulling from server, just download a thumbnail image from server.
public abstract class AbstractImageUtils {
    private AbstractImageUtils self = this;

    protected abstract UnlimitedDiskCache getImageCache();


    /**
     * Query the disk cache's url path.
     * <p/>
     * - parameter objectUUID: Photo's objectUUID
     * <p/>
     * - returns: Image Cache's url
     */
    public File getCacheImageUrl(String objectUUID) {
        return this.getImageCache().get(objectUUID);
    }

    public File getCacheImageUrl(Photo model) {
        return this.getCacheImageUrl(ParseModelAbstract.getPoint(model));
    }

    public boolean diskImageExistsWithKey(Photo model) {
        File file = this.getImageCache().get(ParseModelAbstract.getPoint(model));
        if (file == null || file.exists() == false) {
            return false;
        }
        return true;
    }

    public File getTakenPhotoFile(String objectUUID) {
        return this.getImageCache().get(objectUUID);
    }

    /**
     * Query the disk cache synchronously after checking the memory cache.
     * <p/>
     * - parameter objectUUID: photo's objectUUID
     * <p/>
     * - returns: Image Cache
     */
    public Bitmap getTakenPhoto(String objectUUID) {
        File imageFile = this.getTakenPhotoFile(objectUUID);
        if (imageFile == null || imageFile.exists() == false) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
    }

    /**
     * Query the disk cache synchronously after checking the memory cache.
     * <p/>
     * - parameter model: Photo's instance
     * <p/>
     * - returns: Image Cache
     */
    public Bitmap getTakenPhoto(Photo model) {
        return this.getTakenPhoto(ParseModelAbstract.getPoint(model));
    }

    public List listCacheImageNames() {
        return this.getImageCache().getCacheFileList();
    }

    /**
     * Save photo's image as a offline file.
     * <p/>
     * - parameter image:           saved image
     * - parameter model:           photo's instance
     * - parameter completionBlock: callback variable
     */
    public Task<Bitmap> saveTakenPhoto(Bitmap image, Photo model) {

        // ** Important ** Must store to Disk.
        try {
            this.getImageCache().save(ParseModelAbstract.getPoint(model), image);
        } catch (IOException e) {
            return Task.forError(e);
        }

        return Task.forResult(image);
    }

    public Task<Void> saveTakenPhoto(InputStream inputStream, Photo model) {

        // ** Important ** Must store to Disk.
        try {
            this.getImageCache().save(ParseModelAbstract.getPoint(model), inputStream, null);
        } catch (IOException e) {
            return Task.forError(e);
        }

        return Task.forResult(null);
    }

    /**
     * Generate a specail type image, then save it as the offline image.
     * <p/>
     * - parameter image: taken photo
     * - parameter model: photo's instance
     * <p/>
     * - returns: task's instance
     */
    public Task<Bitmap> generateTakenPhoto(Bitmap image, Photo model) {
        return Task.forResult(null);
    }

    OkHttpClient client = new OkHttpClient();

//    InputStream doGetRequest(String URL) throws IOException {
//        Request request = new Request.Builder()
//                .url(URL)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return response.body().byteStream();
//    }

    public Task<InputStream> downloadImageWithURL(String URL) {
        final Task.TaskCompletionSource tcs = Task.create();

        Request request = new Request.Builder()
                .url(URL).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                tcs.setError(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                tcs.setResult(inputStream);
//                tcs.setError(new FileExistsException("wanghao's test"));
            }
        });

        return tcs.getTask();
    }

    public Task<Void> downloadImageFromServer(final Photo model, String url) {
        final Task.TaskCompletionSource tcs = Task.create();

        /// If the image already exist on the cache folder, we don't download it from the Parse.com.
        File imageFile = this.getTakenPhotoFile(ParseModelAbstract.getPoint(model));
        if (imageFile != null && imageFile.exists() == true) {
            return Task.forResult(null);
        }
        if (url == null || url.isEmpty() == true) {
            return Task.forError(new NullPointerException(model.printDescription()));
        }

        this.downloadImageWithURL(url)
                .onSuccessTask(new Continuation<InputStream, Task<Void>>() {
                    @Override
                    public Task<Void> then(Task<InputStream> task) throws Exception {
                        return self.saveTakenPhoto(task.getResult(), model);
                    }
                }).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    tcs.setError(task.getError());
                } else {
                    tcs.setResult(null);
                }
                return null;
            }
        });

        return tcs.getTask();
    }


}
