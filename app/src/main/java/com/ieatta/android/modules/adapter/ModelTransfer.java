package com.ieatta.android.modules.adapter;

import com.ieatta.android.modules.adapter.enums.ViewHolderType;

/**
 * Created by djzhang on 12/1/15.
 */
public interface ModelTransfer {
    void render(Object value);

    ViewHolderType getViewHolderType();
}
