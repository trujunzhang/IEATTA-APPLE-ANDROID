package com.ieatta.com.parse.utils.cache;

import android.graphics.Bitmap;

import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.tools.Thumbnail;
import com.parse.ParseFile;

import java.io.File;

/**
 * Created by djzhang on 11/30/15.
 */
public class ImageOptimizeUtils {

    /**
     Get a PFFile instance of the original image.

     - parameter photo: photo's instance

     - returns:  PFFile's instance of the original image
     */
    public static ParseFile getPFFileForOrginalImage(Photo photo) {
        File _originalImage = OriginalImageUtils.sharedInstance.getTakenPhoto(photo);
        if(_originalImage!=null){
            return new ParseFile(_originalImage);
        }

        return null;
    }

    /**
     Get a PFFile instance of the thumbnail image.

     - parameter photo: photo's instance

     - returns:  PFFile's instance of the thumbnail image
     */
    public static ParseFile getPFFileForThumbnailImage(Photo photo) {
        File _thumbnailImage = ThumbnailImageUtils.sharedInstance.getTakenPhoto(photo);
        if(_thumbnailImage != null){
            return new ParseFile(_thumbnailImage);
        }

        return null;
    }

    public static Bitmap generateOriginalImage(Bitmap anImage) {
        Bitmap orignailImage = Thumbnail.create(anImage).centerCrop(560, 560).getBitmap();
//        let orignailImage = anImage.resizedImageWithContentMode(.ScaleAspectFit, bounds: CGSizeMake(560.0, 560.0), interpolationQuality: .Medium)

        return  orignailImage;
    }

    public static Bitmap generateThumbnailImage(Bitmap anImage) {
        Bitmap thumbnailImage = Thumbnail.create(anImage).centerCrop(86, 86).getBitmap();
//        let  = anImage.thumbnailImage(86, transparentBorder: 0, cornerRadius: 0, interpolationQuality: .Default)

        return thumbnailImage;
    }
}
