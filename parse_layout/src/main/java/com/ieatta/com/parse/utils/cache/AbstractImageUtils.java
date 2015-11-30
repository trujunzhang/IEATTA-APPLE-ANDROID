package com.ieatta.com.parse.utils.cache;

/**
 * Created by djzhang on 11/30/15.
 */

/// How to store a taken photo to decrease client storage.
///   1.  When taken a photo, save it as original and thumbnail formats.
///   2.1 When pushing to server, push original and thumbnail images to server.
///   2.2 When pushed successfully, delete offline original image.
///   3.  When pulling from server, just download a thumbnail image from server.
public class AbstractImageUtils {


//    func getImageCache() -> SDImageCache{
//        fatalError("Must Override")
//    }
//
//    /**
//     Query the disk cache's url path.
//
//     - parameter objectUUID: Photo's objectUUID
//
//     - returns: Image Cache's url
//     */
//    func getCacheImageUrl(objectUUID:String) -> NSURL{
//        return self.getImageCache().getStoreImageUrl(objectUUID)
//    }
//
//    func getCacheImageUrl(model:Photo) -> NSURL{
//        return self.getCacheImageUrl(ParseModelAbstract.getPoint(model))
//    }
//
//    func diskImageExistsWithKey(model:Photo) -> Bool{
//        return self.getImageCache().diskImageExistsWithKey(ParseModelAbstract.getPoint(model))
//    }
//
//    /**
//     Query the disk cache synchronously after checking the memory cache.
//
//     - parameter objectUUID: photo's objectUUID
//
//     - returns: Image Cache
//     */
//    func getTakenPhoto(objectUUID:String) -> UIImage?{
//        if(objectUUID.isEmpty == true){
//            return nil
//        }
//
//        return self.getImageCache().imageFromDiskCacheForKey(objectUUID)
//    }
//
//    /**
//     Query the disk cache synchronously after checking the memory cache.
//
//     - parameter model: Photo's instance
//
//     - returns: Image Cache
//     */
//    func getTakenPhoto(model:Photo) -> UIImage?{
//        return self.getTakenPhoto(ParseModelAbstract.getPoint(model))
//    }
//
//    func listCacheImageNames() -> NSMutableArray{
//        return self.getImageCache().getCacheFileList()
//    }
//
//    /**
//     Save photo's image as a offline file.
//
//     - parameter image:           saved image
//     - parameter model:           photo's instance
//     - parameter completionBlock: callback variable
//     */
//    func saveTakenPhoto(image:UIImage,model:Photo) -> BFTask{
//
//        // ** Important ** Must store to Disk.
//        self.getImageCache().storeImage(image, forKey: ParseModelAbstract.getPoint(model),toDisk: true)
//
//        return BFTask(result: image)
//    }
//
//    /**
//     Generate a specail type image, then save it as the offline image.
//
//     - parameter image: taken photo
//     - parameter model: photo's instance
//
//     - returns: task's instance
//     */
//    func generateTakenPhoto(image:UIImage,model:Photo) -> BFTask{
//        fatalError("Must Override")
//    }
//
//    func downloadImageWithURL(URL:NSURL) -> BFTask{
//        let downloadTask = BFTaskCompletionSource()
//
//        SDWebImageManager.sharedManager().downloadImageWithURL(URL, options: SDWebImageOptions.LowPriority, progress: nil) { (image, error, cacheType, finished, imageURL) -> Void in
//            if let _image = image{
//
//                downloadTask.setResult(_image)
//            }else {
//                downloadTask.setError(error)
//            }
//        }
//
//        return downloadTask.task
//    }
//
//    func downloadImageFromServer(forPhoto model:Photo,url:String) -> BFTask{
//        /// If the image already exist on the cache folder, we don't download it from the Parse.com.
//        if let _image = self.getTakenPhoto(model){
//            return BFTask(result: _image)
//        }
//        else if(url.isEmpty == true){
//            return BFTask(error: NSError.getError(IEAErrorType.EmptyURL, description: "\(model.printDescription())"))
//        }
//        if let URL = NSURL(string: url){
//            return self.downloadImageWithURL(URL).continueWithBlock { (task) -> AnyObject? in
//
//                if let _ = task.error{
//                    return BFTask(error: NSError.getError(IEAErrorType.OnlineImage, description: "\(model.printDescription())"))
//                }else{
//                    return self.saveTakenPhoto(task.result as! UIImage, model: model)
//                }
//            }
//        }else{
//            return BFTask(error: NSError.getError(IEAErrorType.OnlineImage, description: "\(model.printDescription())"))
//        }
//    }


}
