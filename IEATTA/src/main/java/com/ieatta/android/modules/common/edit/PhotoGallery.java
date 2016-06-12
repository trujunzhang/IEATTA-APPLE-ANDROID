package com.ieatta.android.modules.common.edit;

import android.support.v7.widget.RecyclerView;

import com.ieatta.android.extensions.storage.DTTableViewManager;
import com.ieatta.android.modules.adapter.IEAPhotoGalleryAdapter;
import com.ieatta.android.modules.cells.photos.IEAAddPhotoCell;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;



import java.util.List;

public class PhotoGallery extends EditBaseCellModel {
    private PhotoGallery self = this;

    private RecyclerView collectionView;
    public IEAPhotoGalleryAdapter adapter;

    public IEAPhotoGalleryViewController viewController;
    private List  fetchedPhotos;
    private DTTableViewManager manager = null;

    public PhotoGallery(IEAEditKey photo_gallery, IEAPhotoGalleryViewController viewController) {
        super(photo_gallery);
        self.viewController = viewController;
    }

    public void insertNewPhotoAtFirst(Photo photo) {
//        self.delegate.
    }

    public void setCollectionView(RecyclerView collectionView, DTTableViewManager manager) {
        self.collectionView = collectionView;
        self.manager = manager;

        self.manager.memoryStorage.setSectionFooterModel(new AddPhotoModel(IEAEditKey.add_Photo), 0, IEAAddPhotoCell.getType());
        self.manager.memoryStorage.setItems(self.viewController.getPhotoGalleryItems(), 0);
    }

    public void refreshCollection(List  fetchedPhotos) {
        if (self.manager != null) {
            self.manager.memoryStorage.setItems(fetchedPhotos, 0);
        }
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
