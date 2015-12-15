package com.ieatta.android.modules.view.photogallery;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.ieatta.android.modules.IEASplitDetailViewController;
import com.ieatta.android.modules.cells.headerfooterview.IEAPhotoGalleryFooterCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAPhotoGalleryHeaderCell;
import com.ieatta.android.modules.cells.photos.IEAPhotoGalleryCell;
import com.ieatta.android.modules.common.edit.PhotoGallery;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryFooterCellModel;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryHeaderCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.observers.EditChangedObserver;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAPhotoGalleryViewController extends IEASplitDetailViewController {

    private IEAPhotoGalleryViewController self = this;

    protected Task fetchedPhotosTask = Task.forResult(new LinkedList<>());

    private PhotoGallery photoGallery = new PhotoGallery(IEAEditKey.photo_gallery, self);
    protected List<ParseModelAbstract/*fetchedPhotos*/> fetchedPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (self.havePhotoGallery() == true) {
//            NSNotificationCenter.defaultCenter().addObserver(self, selector: "TakenPhotoWasChanged:", name: PAModelTakenPhotoNotification, object: nil)
            self.configurePhotoGallery();
        }
    }

    protected void configurePhotoGallery() {
        // Register Cells by class.
        self.setRegisterCellClass(IEAPhotoGalleryCell.getType(), self.getPhotoGallerySectionIndex());

        self.setRegisterHeaderClass(IEAPhotoGalleryHeaderCell.getType());
        self.setRegisterFooterClass(IEAPhotoGalleryFooterCell.getType());
    }


    protected int getPhotoGallerySectionIndex() {
        return Integer.MIN_VALUE;
    }

    /**
     * *** Important ****
     * This method just @IEARestaurantDetailViewController override it.
     * <p/>
     * - parameter completionBlock: callback variable
     */
    protected Task<List<ParseModelAbstract>> queryPhotoGallery() {
        return Photo.queryPhotosByModel(self.getPageModel());
    }

    protected void reloadPhotoGallery() {
        self.queryPhotoGallery().onSuccess(new Continuation<List<ParseModelAbstract>, Object>() {
            @Override
            public Object then(Task<List<ParseModelAbstract>> task) throws Exception {
                self.configurePhotoGallerySection(task);
                return null;
            }
        }).continueWith(new Continuation<Object, Object>() {
            @Override
            public Object then(Task<Object> task) throws Exception {
                if (task.isFaulted()) {

                    return null;
                }
                return null;
            }
        });
    }

    protected void configurePhotoGallerySection(Task<List<ParseModelAbstract>> task) {
        self.fetchedPhotos = task.getResult();

        // 1. Set photo gallery section title(contains a 'take a photo' icon).
        self.appendSectionTitleCell(new SectionPhotoGalleryHeaderCellModel(IEAEditKey.Section_Title, self), self.getPhotoGallerySectionIndex(), IEAPhotoGalleryHeaderCell.getType());
        self.refreshFooterViewAtPhotoGallery();

        // 2. Set empty items for the photo gallery collection cell.
        self.setSectionItems(CollectionUtils.createList(self.photoGallery), self.getPhotoGallerySectionIndex());

        self.photoGallery.refreshCollection(self.fetchedPhotos);
    }

    // Refresh footer view about the photo count at the photo gallery section.
    private void refreshFooterViewAtPhotoGallery() {
        setFooterModelInSection(new SectionPhotoGalleryFooterCellModel(IEAEditKey.Section_Title, self.getPhotoGalleryCount(), self), self.getPhotoGallerySectionIndex(), IEAPhotoGalleryFooterCell.getType());
    }

    public void presentPhotoGallery(int rowIndex) {
//        let photoPagesController: EBPhotoPagesController = EBPhotoPagesController(dataSource: self, delegate: self,photoAtIndex: rowIndex)
//        self.presentViewController(photoPagesController, animated: true, completion: nil)
    }


    protected int getPhotoGalleryCount() {
        return self.fetchedPhotos.size();
    }

    protected Photo getPhoto(int index) {
        return (Photo) self.fetchedPhotos.get(index);
    }

    protected String getPhotoUUID(int index) {
        return ParseModelAbstract.getPoint(self.getPhoto(index));
    }

    // MARK: All the following methonds for taking photos
    protected void takeAPhotoButtonTapped() {
//        let navigationController:TGCameraNavigationController = TGCameraNavigationController.newWithCameraDelegate(self)
//        self.presentViewController(navigationController, animated: true, completion: nil)
    }

    protected void updatePhotoGalleryAfterTakePhoto(Bitmap image) {

        // When taken photo in the edit page, and show the hint if user tapped the left BarButtonItem called "Back".
        EditChangedObserver.sharedInstance.takenPhotoListener();

        Photo newPhoto = Photo.getTakenPhotoInstance(self.getPageModel());

//        Photo.pinPhotoAndCacheImage( newPhoto,  image);
//        .continueWithBlock { (task) -> AnyObject? in
//            // Every offline objects was pushed to Parse.com.
//            if let _ = task.error{
//                AppAlertView.showError(L10n.SavePhotoFailure.string)
//            }else{
//                dispatch_async(dispatch_get_main_queue(), { () -> Void in
//                        // 1. Update photoPoint cache.
//                        IEACache.sharedInstance.setPhotoPoint(newPhoto)
//
//
//                        // 2. Notify "TakenPhotoWasChanged".
//                        NSNotificationCenter.defaultCenter().postNotificationName(PAModelTakenPhotoNotification, object:nil,
//                        userInfo:["newPhoto":newPhoto])
//                })
//            }
//            return nil
//        }
    }

    private void insertItemAtFirstOnCollection(Photo photo) {
//        self.fetchedPhotos.insert(photo,  0);
//        self.photoGallery.insertNewPhotoAtFirst(forNewPhoto: photo)

        self.refreshFooterViewAtPhotoGallery();
    }

    // MARK: NSNotificationCenter notification handlers
//    func TakenPhotoWasChanged(note:NSNotification){
//        if let userInfo = note.userInfo{
//            let dict = userInfo as! [String:Photo]
//            if let newPhoto = dict["newPhoto"]{
//                self.insertItemAtFirstOnCollection(forNewPhoto: newPhoto)
//            }
//        }
//    }


}
