package com.ieatta.android.modules;

import android.content.Intent;
import android.os.Bundle;

import com.ieatta.android.modules.common.MainSegueIdentifier;
import com.ieatta.android.modules.view.IEASeeReviewsInDetailViewController;
import com.ieatta.android.modules.view.photogallery.IEAPhotoGalleryViewController;

/**
 * Created by djzhang on 12/1/15.
 */
/// The following children classes:
///   IEABaseReviewsTableViewController
///   IEAReadReviewsBaseViewController
public class IEAReviewSegueTableViewController extends IEAPhotoGalleryViewController {
    private IEAReviewSegueTableViewController self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void segueForSeeReviewsInDetailViewController(IEASeeReviewsInDetailViewController destination,Intent sender){
        // Read Reviews.
        self.setTransferedModel(sender,self.getPageModel());
//        destination.reviewForModel = self.getPageModel();
    }

    /**
     This method supported for IEAReadReviewsViewController,
     Because the IEAReadReviewsResultViewController created programmatically.

     - parameter navigationViewController: UINavigationController's instance
     */
    protected void prepareForSeeReviewSegueIdentifier(/*UINavigationController navigationViewController*/){
        self.performSegueWithIdentifier(MainSegueIdentifier.detailSeeReviewSegueIdentifier,self);
//        navigationViewController.pushViewController(UIStoryboard.Storyboard.Controllers.seeReviewsInDetailViewController().transfer(self.getPageModel()), animated: true)
    }

}
