package com.ieatta.android.modules.common.edit;

import android.support.v7.widget.RecyclerView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.badoo.mobile.util.WeakHandler;
import com.ieatta.android.modules.adapter.IEAPhotoGalleryAdapter;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;

import java.util.List;

public class PhotoGallery extends EditBaseCellModel {
    private PhotoGallery self = this;

    private RecyclerView collectionView;
    public IEAPhotoGalleryAdapter adapter;

    private IEAPhotoGalleryViewController viewController;
    private List<ParseModelAbstract> fetchedPhotos;

    private WeakHandler mHandler  = new WeakHandler();; // We still need at least one hard reference to WeakHandler


    public PhotoGallery(IEAEditKey photo_gallery, IEAPhotoGalleryViewController viewController) {
        super(photo_gallery);
        self.viewController = viewController;
    }

    public void insertNewPhotoAtFirst(Photo photo) {
//        self.delegate.
    }

    public void setCollectionView(RecyclerView collectionView){
        self.collectionView = collectionView;
        self.collectionView.setAdapter(self.adapter);

        self.adapter.notifyDataSetChanged();
    }

    public void refreshCollection(List<ParseModelAbstract> fetchedPhotos) {
        self.fetchedPhotos = fetchedPhotos;

        self.adapter = new IEAPhotoGalleryAdapter(EnvironmentUtils.sharedInstance.getGlobalContext(),self.fetchedPhotos);
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
