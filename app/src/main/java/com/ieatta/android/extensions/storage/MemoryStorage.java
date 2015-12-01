package com.ieatta.android.extensions.storage;

import com.ieatta.android.extensions.viewkit.NSIndexPath;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by djzhang on 12/1/15.
 */
public class MemoryStorage {

    public LinkedHashMap<Integer, LinkedList<Object>> sections = new LinkedHashMap<>();


    /// Add items to section with `toSection` number.
    /// - Parameter items: items to add
    /// - Parameter toSection: index of section to add items
    public void addItems(LinkedList<Object> items, int toSection) {
        this.sections.put(new Integer(toSection), items);
    }


    /// Set section header model for MemoryStorage
    /// - Note: This does not update UI
    /// - Parameter model: model for section header at index
    /// - Parameter sectionIndex: index of section for setting header
    public void setSectionHeaderModel(Object model, int forSectionIndex) {

    }


    /// Set section footer model for MemoryStorage
    /// - Note: This does not update UI
    /// - Parameter model: model for section footer at index
    /// - Parameter sectionIndex: index of section for setting footer
    public void setSectionFooterModel(Object model, int forSectionIndex) {

    }

    /// Remove items at index paths.
    /// - Parameter indexPaths: indexPaths to remove item from. Any indexPaths that will not be found, will be skipped
    public void removeItemsAtIndexPaths(NSIndexPath[] indexPaths) {
    }


}
