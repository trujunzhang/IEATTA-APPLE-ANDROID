package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;
import android.yelp.com.commonlib.io.FileUtils;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.tools.Thumbnail;
import com.parse.ParseFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bolts.Task;

/**
 * Created by djzhang on 11/30/15.
 */
public class ImageOptimizeUtils {

    /**
     * Get a PFFile instance of the original image.
     * <p/>
     * - parameter photo: photo's instance
     * <p/>
     * - returns:  PFFile's instance of the original image
     */
    public static Task<ParseFile> getPFFileForOrginalImage(Photo photo) {
        String uuid = ParseModelAbstract.getPoint(photo);
        File _originalImage = OriginalImageUtils.sharedInstance.getTakenPhotoFile(uuid);

        if (_originalImage != null) {
            byte[] bytes;
            try {
                bytes = FileUtils.toByteArray(_originalImage.getAbsolutePath());
            } catch (IOException e) {
                return Task.forError(new FileNotFoundException(photo.printDescription()));
            }
            return Task.forResult(new ParseFile("O-" + uuid, bytes));
        }

        return Task.forError(new FileNotFoundException(photo.printDescription()));
    }

    /**
     * Get a PFFile instance of the thumbnail image.
     * <p/>
     * - parameter photo: photo's instance
     * <p/>
     * - returns:  PFFile's instance of the thumbnail image
     */
    public static Task<ParseFile> getPFFileForThumbnailImage(Photo photo) {
        String uuid = ParseModelAbstract.getPoint(photo);
        File _thumbnailImage = ThumbnailImageUtils.sharedInstance.getTakenPhotoFile(uuid);

        if (_thumbnailImage != null) {
            byte[] bytes;
            try {
                bytes = FileUtils.toByteArray(_thumbnailImage.getAbsolutePath());
            } catch (IOException e) {
                return Task.forError(new FileNotFoundException(photo.printDescription()));
            }
            return Task.forResult(new ParseFile("T-" + uuid, bytes));
        }

        return Task.forError(new FileNotFoundException(photo.printDescription()));
    }

    public static Bitmap generateOriginalImage(Bitmap anImage) {
        Bitmap orignailImage = Thumbnail.create(anImage).zoom(560, 560).getBitmap();
//        let orignailImage = anImage.resizedImageWithContentMode(.ScaleAspectFit, bounds: CGSizeMake(560.0, 560.0), interpolationQuality: .Medium)

        return orignailImage;
    }

    public static Bitmap generateThumbnailImage(Bitmap anImage) {
        Bitmap thumbnailImage = Thumbnail.create(anImage).zoom(86, 86).getBitmap();
//        let  = anImage.thumbnailImage(86, transparentBorder: 0, cornerRadius: 0, interpolationQuality: .Default)

        return thumbnailImage;
    }
}
