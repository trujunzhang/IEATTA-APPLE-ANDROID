package com.ieatta.android.modules.cells.model;

import com.ieatta.android.modules.view.IEAEventDetailViewController;
import com.ieatta.com.parse.models.Event;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAEventHeader {

    public IEAEventDetailViewController viewController;
    public Event model;

    public IEAEventHeader(IEAEventDetailViewController viewController, Event model) {
        this.viewController = viewController;
        this.model = model;
    }
}
