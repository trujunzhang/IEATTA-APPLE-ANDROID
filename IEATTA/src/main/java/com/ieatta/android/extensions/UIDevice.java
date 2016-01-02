package com.ieatta.android.extensions;

/**
 * Created by djzhang on 12/10/15.
 */
public class UIDevice {
    public static UIDevice sharedDevice = new UIDevice();
    public UIUserInterfaceIdiom userInterfaceIdiom = UIUserInterfaceIdiom.Phone;

    public static UIDevice currentDevice() {
        return UIDevice.sharedDevice;
    }
}
