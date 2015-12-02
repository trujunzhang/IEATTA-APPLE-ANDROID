package com.ieatta.android.extensions.storage;

import com.ieatta.android.extensions.viewkit.NSIndexPath;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by djzhang on 12/1/15.
 */
public class MemoryStorage {
    private MemoryStorage self = this;
    public LinkedHashMap<Integer, SectionModel> sections = new LinkedHashMap<>();


    /// Add items to section with `toSection` number.
    /// - Parameter items: items to add
    /// - Parameter toSection: index of section to add items
    public void addItems(LinkedList<Object> items, int toSection) {
//        this.sections.put(new Integer(toSection), new SectionModel(items));
    }

    /// Set items for specific section. This will reload UI after updating.
    /// - Parameter items: items to set for section
    /// - Parameter forSectionIndex: index of section to update
    public void setItems(LinkedList<Object> items, int forSectionIndex) {
        SectionModel section = self.verifySection(forSectionIndex);
        section.items = items;
        self.sections.put(new Integer(forSectionIndex), section);
    }

    /// Set section header model for MemoryStorage
    /// - Note: This does not update UI
    /// - Parameter model: model for section header at index
    /// - Parameter sectionIndex: index of section for setting header
    public void setSectionHeaderModel(Object model, int forSectionIndex) {

    }

    public SectionModel getSectionFromPosition(int viewType){
      return self.sections.get(new Integer(0));
    };

    private SectionModel verifySection(int forSectionIndex) {
        SectionModel model = self.sections.get(new Integer(forSectionIndex));
        if (model != null) {
            return model;
        }
        model = new SectionModel(forSectionIndex);
        self.sections.put(new Integer(forSectionIndex),model);
        return  model;
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

    public void registerCellClass(Class cellClass, int forSectionIndex, int layoutResId) {
        SectionModel section = self.verifySection(forSectionIndex);
        section.layoutResId = layoutResId;
        section.cellClass =cellClass;
    }

    public int getItemCount() {
        int sum = 0;
        for (SectionModel model : self.sections.values()) {
            sum += model.numberOfItems();
        }
        return sum;
    }
}
