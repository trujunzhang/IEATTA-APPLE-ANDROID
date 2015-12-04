package com.ieatta.android.extensions.storage;


/**
 * Created by djzhang on 12/1/15.
 */
public class HeaderModel {

    /// Items for current section
    /// - Warning: If you try to set new array to this property [T], the only way to do this without exception is to wrap it into items.map { $0 }. This is a workaround that exists because of Swift inability to cast [T] to [Any]. You can call `setItems` method instead of doing so.
    /// - SeeAlso: `setItems:`
    public Object item;
    public int layoutResId;
    public Class cellClass;

    public HeaderModel(Object item) {
        this.item = item;
    }

    public HeaderModel(Object item, Class cellClass, int layoutResId) {
        this.item = item;
        this.layoutResId = layoutResId;
        this.cellClass = cellClass;
    }
}
