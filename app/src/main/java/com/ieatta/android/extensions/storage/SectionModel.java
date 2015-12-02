package com.ieatta.android.extensions.storage;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by djzhang on 12/1/15.
 */
public class SectionModel {

    /// Items for current section
    /// - Warning: If you try to set new array to this property [T], the only way to do this without exception is to wrap it into items.map { $0 }. This is a workaround that exists because of Swift inability to cast [T] to [Any]. You can call `setItems` method instead of doing so.
    /// - SeeAlso: `setItems:`
    public LinkedList<Object> items = new LinkedList<>();
    public int sectionIndex;
    public int layoutResId;
    public Class cellClass;

    public HeaderModel headerModel;
    public FooterModel footerModel;

    public SectionModel(int sectionIndex) {
        this.sectionIndex = sectionIndex;
    }

    /// Set items of specific time to items property.
    /// - Parameter items: items to set
    /// - Note: This method exists because of inability of Swift to cast [T] to [Any].
    public void setItems(LinkedList<Object> items){
        this.items = items;
    }

    /// Number of items in current section
    public int numberOfItems(){
        return this.items.size();
    }

}
