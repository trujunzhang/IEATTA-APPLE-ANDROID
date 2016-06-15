package com.ieatta.android.modules.view.photogallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.desmond.squarecamera.CameraActivity;
import com.desmond.squarecamera.TakenPhotoUtils;
import com.ieatta.android.R;
import com.ieatta.android.apps.AppAlertView;
import com.ieatta.android.cache.IEACache;
import com.ieatta.android.cache.IntentCache;
import com.ieatta.android.modules.IEASplitDetailViewController;
import com.ieatta.android.modules.adapter.NSIndexPath;
import com.ieatta.android.modules.cells.headerfooterview.IEAPhotoGalleryFooterCell;
import com.ieatta.android.modules.cells.headerfooterview.IEAPhotoGalleryHeaderCell;
import com.ieatta.android.modules.cells.photos.IEAPhotoGalleryCell;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.common.edit.PhotoGallery;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryFooterCellModel;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryHeaderCellModel;
import com.ieatta.provide.IEAEditKey;
import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.notification.NSNotification;
import com.ieatta.android.notification.NSNotificationCenter;
import com.ieatta.android.notification.NotifyType;
import com.ieatta.android.observers.EditChangedObserver;

import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

public class IEAPhotoGalleryViewController extends IEASplitDetailViewController {

//    private IEAPhotoGalleryViewController self = this;
//
//    private static final int REQUEST_CAMERA = 0;
//    private static final int REQUEST_CAMERA_PERMISSION = 1;
//
//    protected Task fetchedPhotosTask = Task.forResult(new LinkedList<>());
//
//    private PhotoGallery photoGallery = new PhotoGallery(IEAEditKey.photo_gallery, self);
//    protected LinkedList<ParseModelAbstract/*fetchedPhotos*/> fetchedPhotos;
//    private int photoAtIndex;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (self.havePhotoGallery() == true) {
////            NSNotificationCenter.defaultCenter().addObserver(self, selector: "TakenPhotoWasChanged:", name: PAModelTakenPhotoNotification, object: nil)
//            self.configurePhotoGallery();
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        IntentCache.sharedInstance.photoGalleryItem = new LinkedList<>();
//    }
//
//    protected void configurePhotoGallery() {
//        // Register Cells by class.
//        self.setRegisterCellClass(IEAPhotoGalleryCell.getType(), self.getPhotoGallerySectionIndex());
//
//        self.setRegisterHeaderClass(IEAPhotoGalleryHeaderCell.getType());
//        self.setRegisterFooterClass(IEAPhotoGalleryFooterCell.getType());
//    }
//
//
//    protected int getPhotoGallerySectionIndex() {
//        return Integer.MIN_VALUE;
//    }
//
//    /**
//     * *** Important ****
//     * This method just @IEARestaurantDetailViewController override it.
//     * <p/>
//     * - parameter completionBlock: callback variable
//     */
//    protected Task<List > queryPhotoGallery() {
//        return Photo.queryPhotosByModel(self.getPageModel());
//    }
//
//    protected void reloadPhotoGallery() {
//        self.queryPhotoGallery()
//                .onSuccess(new Continuation<List , Object>() {
//                    @Override
//                    public Object then(Task<List > task) throws Exception {
//                        self.configurePhotoGallerySection(task);
//                        return null;
//                    }
//                }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                if (task.isFaulted()) {
//
//                    return null;
//                }
//                return null;
//            }
//        });
//    }
//
//    protected void configurePhotoGallerySection(Task<List > task) {
//        self.fetchedPhotos = new LinkedList<>(task.getResult());
//
//        // 1. Set photo gallery section title(contains a 'take a photo' icon).
//        self.appendSectionTitleCell(new SectionPhotoGalleryHeaderCellModel(IEAEditKey.Section_Title, self), self.getPhotoGallerySectionIndex(), IEAPhotoGalleryHeaderCell.getType());
//        self.refreshFooterViewAtPhotoGallery();
//
//        // 2. Set empty items for the photo gallery collection cell.
//        self.setSectionItems(CollectionUtils.createList(self.photoGallery), self.getPhotoGallerySectionIndex());
//
//        self.photoGallery.refreshCollection(self.fetchedPhotos);
//    }
//
//    // Refresh footer view about the photo count at the photo gallery section.
//    private void refreshFooterViewAtPhotoGallery() {
//        setFooterModelInSection(new SectionPhotoGalleryFooterCellModel(IEAEditKey.Section_Title, self.getPhotoGalleryCount(), self), self.getPhotoGallerySectionIndex(), IEAPhotoGalleryFooterCell.getType());
//    }
//
//    public void presentPhotoGallery(int rowIndex) {
//        self.photoAtIndex = rowIndex;
//        self.performSegueWithIdentifier(MainSegueIdentifier.photoPagesControllerSegueIdentifier, self);
//    }
//
//    @Override
//    protected void segueForPhotoPagesController(PhotoGalleryPagerActivity destination, Intent sender) {
//        sender.putExtra(IntentCache.sharedInstance.photoAtIndex, self.photoAtIndex);
//        IntentCache.sharedInstance.photoGalleryItem = self.fetchedPhotos;
//    }
//
//    protected int getPhotoGalleryCount() {
//        return self.fetchedPhotos.size();
//    }
//
//    protected Photo getPhoto(int index) {
//        return (Photo) self.fetchedPhotos.get(index);
//    }
//
//    protected String getPhotoUUID(int index) {
//        return ParseModelAbstract.getPoint(self.getPhoto(index));
//    }
//
//    // MARK: All the following methonds for taking photos
//    public void takeAPhotoButtonTapped() {
//        // Start CameraActivity
//        Intent startCustomCameraIntent = new Intent(this, CameraActivity.class);
//        startActivityForResult(startCustomCameraIntent, REQUEST_CAMERA);
//    }
//
//    // Check for camera permission in MashMallow
//    public void requestForCameraPermission(View view) {
//        final String permission = Manifest.permission.CAMERA;
//        if (ContextCompat.checkSelfPermission(IEAPhotoGalleryViewController.this, permission)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(IEAPhotoGalleryViewController.this, permission)) {
//                // Show permission rationale
//            } else {
//                // Handle the result in Activity#onRequestPermissionResult(int, String[], int[])
////                ActivityCompat.requestPermissions(IEAPhotoGalleryViewController.this, new String[]{permission}, REQUEST_CAMERA_PERMISSION);
//            }
//        } else {
//            // Start CameraActivity
//        }
//    }
//
//    // Receive Uri of saved square photo
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != RESULT_OK) return;
//
//        if (requestCode == REQUEST_CAMERA) {
//            // Get the bitmap in according to the width of the device
//            Bitmap bitmap = TakenPhotoUtils.bitmap;
//            self.updatePhotoGalleryAfterTakePhoto(bitmap);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    protected void updatePhotoGalleryAfterTakePhoto(Bitmap image) {
//
//        // When taken photo in the edit page, and show the hint if user tapped the left BarButtonItem called "Back".
//        EditChangedObserver.sharedInstance.takenPhotoListener();
//
//        final Photo newPhoto = Photo.getTakenPhotoInstance(self.getPageModel());
//
//        Photo.pinPhotoAndCacheImage(newPhoto, image)
//                .onSuccess(new Continuation<Void, Object>() {
//                    @Override
//                    public Object then(Task<Void> task) throws Exception {
//                        // 1. Update photoPoint cache.
//                        IEACache.sharedInstance.setPhotoPoint(newPhoto);
//
//                        // 2. Notify "TakenPhotoWasChanged".
//                        NSNotificationCenter.defaultCenter().postNotificationName(NotifyType.PAModelTakenPhotoNotification, newPhoto);
//
//                        return null;
//                    }
//                }).continueWith(new Continuation<Object, Object>() {
//            @Override
//            public Object then(Task<Object> task) throws Exception {
//                if (task.isFaulted()) {
//                    if (task.isFaulted()) {
//                        AppAlertView.showError(R.string.Save_photo_Failure_);
//                    }
//
//                    TakenPhotoUtils.bitmap = null;
//                }
//                return null;
//            }
//        });
//
//    }
//
//    private void insertItemAtFirstOnCollection(Photo photo) {
//        self.fetchedPhotos.addFirst(photo);
//        self.photoGallery.insertNewPhotoAtFirst(photo);
//
//        self.refreshFooterViewAtPhotoGallery();
//    }
//
//    // MARK: NSNotificationCenter notification handlers
//    @Override
//    protected void TakenPhotoWasChanged(NSNotification note) {
//        Photo newPhoto = (Photo) note.anObject;
//        self.insertItemAtFirstOnCollection(newPhoto);
//    }
//
//    public void didSelectItemAtIndexPathOnCollectionView(NSIndexPath indexPath) {
//        if (indexPath.row == self.getPhotoGalleryCount()) {
//            self.takeAPhotoButtonTapped();
//        } else {
//            self.presentPhotoGallery(indexPath.row);
//        }
//    }
//
//    public LinkedList  getPhotoGalleryItems() {
//        return self.fetchedPhotos;
//    }
}
