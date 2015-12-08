package com.ieatta.android.modules.common.edit;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;

import java.util.List;

public class PhotoGallery extends EditBaseCellModel {
    private PhotoGallery self = this;

    public DTTableViewManager delegate;
    private IEAPhotoGalleryViewController viewController;
    private List<ParseModelAbstract> fetchedPhotos;

    public PhotoGallery(IEAEditKey photo_gallery, IEAPhotoGalleryViewController viewController) {
        super(photo_gallery);
        self.viewController = viewController;
    }

    public void insertNewPhotoAtFirst(Photo photo) {
//        self.delegate.
    }

    public void setDelegate(DTTableViewManager delegate){
        self.delegate = delegate;
        self.delegate.memoryStorage.setItems(self.fetchedPhotos,0);
    }

    public void refreshCollection(List<ParseModelAbstract> fetchedPhotos) {
        self.fetchedPhotos = fetchedPhotos;
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
