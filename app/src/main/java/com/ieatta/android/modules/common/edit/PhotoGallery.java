package com.ieatta.android.modules.common.edit;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.models.Photo;

public class PhotoGallery extends EditBaseCellModel {
    private PhotoGallery self = this;

    public DTTableViewManager manager;
    private  IEAEditBaseViewController viewController;

    public PhotoGallery(IEAEditKey photo_gallery, IEAEditBaseViewController viewController) {
        super(photo_gallery);
        self.viewController = viewController;
    }

    private void insertNewPhotoAtFirst(Photo photo) {
//        self.manager.
    }

    private void refreshCollection() {

    }


//    var collectionView: UICollectionView?
//    var delegate:protocol<UICollectionViewDelegate,UICollectionViewDataSource>?
//
//    init(editKey:IEAEditKey,delegate:protocol<UICollectionViewDelegate,UICollectionViewDataSource>){
//        super.init(editKey: editKey)
//        self.delegate = delegate
//    }
//
//    func refreshCollection(){
//        if let _collectionView = self.collectionView{
//            _collectionView.reloadData()
//            _collectionView.setContentOffset(CGPointZero, animated: true)
//        }
//    }
//
//    func insertNewPhotoAtFirst(forNewPhoto photo:Photo){
//        if let _collectionView = self.collectionView{
//            _collectionView.insertItemsAtIndexPaths([NSIndexPath(forRow: 0, inSection: 0)])
//            _collectionView.setContentOffset(CGPointZero, animated: true)
//        }
//    }

}
