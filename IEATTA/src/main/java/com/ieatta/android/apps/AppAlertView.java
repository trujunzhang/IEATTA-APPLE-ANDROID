package com.ieatta.android.apps;

import android.content.DialogInterface;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.ieatta.android.R;

public class AppAlertView {

    public static void showSuccess(int  subTitle)  {
        AppAlertView.showSuccess(R.string.Hold_On,  subTitle);
    }

    public static void showSuccess(int  title, int  subTitle)  {
        new AlertDialogWrapper.Builder(EnvironmentUtils.sharedInstance.getGlobalContext())
                .setTitle(title)
                .setMessage(subTitle)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showError(int  subTitle)  {
        AppAlertView.showError(R.string.Hold_On ,  subTitle);
    }

    public static void showError(int  title, int  subTitle)  {
        new AlertDialogWrapper.Builder(EnvironmentUtils.sharedInstance.getGlobalContext())
                .setTitle(title)
                .setMessage(subTitle)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showWarning(int  subTitle)  {
        AppAlertView.showWarning(R.string.Hold_On ,  subTitle);
    }

    public static void showWarning(int  title, int  subTitle)  {
//        SCLAlertView().showWarning(title, subTitle, closeButtonTitle:L10n.OK.int )
    }




}
