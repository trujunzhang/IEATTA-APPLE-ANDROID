package com.ieatta.android.modules.common.edit;

import com.ieatta.android.modules.view.IEAChoicePeopleViewController;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;

public class PhotoGallery extends EditBaseCellModel {
    public PhotoGallery(IEAEditKey editKey) {
        super(editKey);
    }

    public PhotoGallery(IEAEditKey photo_gallery, IEAEditBaseViewController viewController) {
        super(photo_gallery);
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
