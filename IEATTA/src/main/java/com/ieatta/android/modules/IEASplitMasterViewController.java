package com.ieatta.android.modules;

import android.content.Intent;

import com.ieatta.android.extensions.UIDevice;
import com.ieatta.android.modules.common.MainSegueIdentifier;
import org.ieatta.activity.restaurant.IEARestaurantDetailViewController;
import com.ieatta.android.modules.view.edit.IEAEditRestaurantViewController;

public class IEASplitMasterViewController extends IEABaseTableViewController {
    private IEASplitMasterViewController self = this;

    // Selected model from tableview.
//    protected Restaurant selectedModel;

    protected void whenSelectedCellTaped(MainSegueIdentifier type) {
        switch (UIDevice.currentDevice().userInterfaceIdiom) {
            case Pad:
//                IEASplitMasterViewController.whenSelectedCellTapedForiPad(type, self, self.selectedModel);
                break;
            case Phone:
                self.performSegueWithIdentifier(type, self);
                break;
            default:
                break;
        }
    }


//    static void whenSelectedCellTapedForiPad(MainSegueIdentifier type, IEAAppSegureTableViewController rootViewController, Object selectedModel) {
//        var viewController = UIViewController()
//        switch(type){
//            // 4 more cell items.
//            case .editRestaurantSegueIdentifier:
//            viewController = UIStoryboard.Storyboard.Controllers.editRestaurantViewController().setEditModel(Restaurant(), newModel: true)
//            break;
//            case .searchRestaurantSegueIdentifier:
//            viewController =  UIStoryboard.Storyboard.Controllers.searchRestaurantViewController()
//            break;
//            case .managerPeopleSegueIdentifier:
//            viewController =  UIStoryboard.Storyboard.Controllers.managerPeopleViewController()
//            break;
//            case .readReviewsSegueIdentifier:
//            viewController =  UIStoryboard.Storyboard.Controllers.readReviewsViewController()
//            break;
//            // detail restaurant cell item.
//            case .detailRestaurantSegueIdentifier:
//            viewController = UIStoryboard.Storyboard.Controllers.restaurantDetailViewController().transfer(selectedModel!)
//            break;
//            default:
//                break;
//        }
//
//        /// Create detail viewcontroller from IEASplitDetailViewController.
//        let detailViewController = IEASplitViewController.getDetailViewController(viewController)
//        let splitViewController = rootViewController as! UISplitViewController
//        splitViewController.viewControllers = [(splitViewController.viewControllers.first)!,detailViewController]
//    }

    @Override
    protected void segueForEditRestaurantViewController(IEAEditRestaurantViewController destination, Intent sender) {
        // TODO djzhang(used for iPhone)
//        if (UIDevice.currentDevice().userInterfaceIdiom == UIUserInterfaceIdiom.Phone) {
//            /// Add restaurant
//            self.setTransferedModelForEdit(sender, new Restaurant(), true);
//        }
    }

    @Override
    protected void segueForRestaurantDetailViewController(IEARestaurantDetailViewController destination, Intent sender) {
        // TODO djzhang(used for iPhone)
//        if (UIDevice.currentDevice().userInterfaceIdiom == UIUserInterfaceIdiom.Phone) {
//            /// Show detailed restaurant
//            self.setTransferedModel(sender, self.selectedModel);
//        }
    }

}
