package com.ieatta.android.modules;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAAppTableViewController extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        self.view.backgroundColor = UIColor(named: .MainBody)
//        self.tableView.backgroundColor = UIColor(named: .MainBody)
//
//        if(self.showNavigationTitle() == true){
//            self.navigationItem.titleView = setTitle(L10n.AppTitle.string,subtitle: L10n.AppSubtitle.string);
//        }
    }

    public boolean navigationShouldPopOnBackButton() {
        return true;
    }

    public boolean showNavigationTitle()  {
        return true;
    }

    public boolean havePhotoGallery() {
        return false;
    }

    public boolean shouldShowHUD() {
        return false;
    }

    public View setTitle(String title, String subtitle)  {
//        let titleLabel = UILabel(frame: CGRectMake(0, -5, 0, 0))
//
//        titleLabel.backgroundColor = UIColor.clearColor()
//        titleLabel.textColor = UIColor.blackColor()
//        titleLabel.font = UIFont.boldSystemFontOfSize(17)
//        titleLabel.text = title
//        titleLabel.sizeToFit()
//
//        let subtitleLabel = UILabel(frame: CGRectMake(0, 18, 0, 0))
//        subtitleLabel.backgroundColor = UIColor.clearColor()
//        subtitleLabel.textColor = UIColor.blackColor()
//        subtitleLabel.font = UIFont.systemFontOfSize(12)
//        subtitleLabel.text = subtitle
//        subtitleLabel.sizeToFit()
//
//        let titleView = UIView(frame: CGRectMake(0, 0, max(titleLabel.frame.size.width, subtitleLabel.frame.size.width), 30))
//        titleView.addSubview(titleLabel)
//        titleView.addSubview(subtitleLabel)
//
//        let widthDiff = subtitleLabel.frame.size.width - titleLabel.frame.size.width
//
//        if widthDiff > 0 {
//            var frame = titleLabel.frame
//            frame.origin.x = widthDiff / 2
//            titleLabel.frame = CGRectIntegral(frame)
//        } else {
//            var frame = subtitleLabel.frame
//            frame.origin.x = abs(widthDiff) / 2
//            titleLabel.frame = CGRectIntegral(frame)
//        }

//        return titleView
        return null;
    }

     public void didReceiveMemoryWarning() {
        // Dispose of any resources that can be recreated.
    }


}