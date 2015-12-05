package com.ieatta.android.extensions.storage;

import com.badoo.mobile.util.WeakHandler;
import com.ieatta.android.extensions.viewkit.NSIndexPath;
import com.ieatta.android.modules.adapter.IEATableViewControllerAdapter;
import com.ieatta.com.parse.ParseModelAbstract;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by djzhang on 12/1/15.
 */
public class MemoryStorage {
    private MemoryStorage self = this;

    public IEATableViewControllerAdapter adapter;
    private TableViewUtils tableViewUtils = new TableViewUtils();

    public HashMap<Integer, SectionModel> sections = new LinkedHashMap<>();
    public CellTypeUtils cellTypeUtils = new CellTypeUtils();

    private WeakHandler mHandler  = new WeakHandler();; // We still need at least one hard reference to WeakHandler


    public MemoryStorage(IEATableViewControllerAdapter adapter) {
        self.adapter = adapter;
    }

    /// Add items to section with `toSection` number.
    /// - Parameter items: items to add
    /// - Parameter toSection: index of section to add items
    public void addItems(List<Object> items, int toSection) {
//        this.sections.put(new Integer(toSection), new SectionModel(items));
    }

    private void reloadTableView() {
        self.tableViewUtils.generateItems(self.sections);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                self.adapter.notifyDataSetChanged();
            }
        }, 1);
    }

    /// Set items for specific section. This will reload UI after updating.
    /// - Parameter items: items to set for section
    /// - Parameter forSectionIndex: index of section to update
    public void setItems(List items, int forSectionIndex) {
        SectionModel section = self.verifySection(forSectionIndex);
        section.items = items;
        self.sections.put(new Integer(forSectionIndex), section);

        self.reloadTableView();
    }

    /// Set section header model for MemoryStorage
    /// - Note: This does not update UI
    /// - Parameter model: model for section header at index
    /// - Parameter sectionIndex: index of section for setting header
    public void setSectionHeaderModel(Object model, int forSectionIndex, CellType type) {
        // Step1: Register cell type.
        cellTypeUtils.registerType(type);

        // Step2: Create/Add a Header Section.
        SectionModel section = self.verifySection(forSectionIndex);
        section.setHeaderModel(new HeaderModel(model, type));

//        self.reloadTableView();
    }

    /// Set section footer model for MemoryStorage
    /// - Note: This does not update UI
    /// - Parameter model: model for section footer at index
    /// - Parameter sectionIndex: index of section for setting footer
    public void setSectionFooterModel(Object model, int forSectionIndex, CellType type) {
        SectionModel section = self.verifySection(forSectionIndex);
        section.setFooterModel(new FooterModel(model, type));

//        self.reloadTableView();
    }

    private SectionModel verifySection(int forSectionIndex) {
        SectionModel model = self.sections.get(new Integer(forSectionIndex));
        if (model != null) {
            return model;
        }
        model = new SectionModel(forSectionIndex);
        self.sections.put(new Integer(forSectionIndex), model);
        return model;
    }

    /// Remove items at index paths.
    /// - Parameter indexPaths: indexPaths to remove item from. Any indexPaths that will not be found, will be skipped
    public void removeItemsAtIndexPaths(NSIndexPath[] indexPaths) {

    }

    public void registerCellClass(CellType type, int forSectionIndex) {
        // Step1: Register class type
        cellTypeUtils.registerType(type);

        // Step2: Create/Modify a section.
        SectionModel section = self.verifySection(forSectionIndex);
        section.cellType = type;
    }

    public void registerCellClassInSpecialRow(CellType type, int forSectionIndex, int forRowIndex) {
        // Step1: Register class type
        cellTypeUtils.registerType(type);

        // Step2: Create/Modify a section.
        SectionModel section = self.verifySection(forSectionIndex);
        section.cellType = type;
    }


    public RowModel getRowModelFromPosition(int position) {
        return self.tableViewUtils.getItem(position);
    }

    public Object getRowModel(int position) {
        RowModel rowModel = self.getRowModelFromPosition(position);
        return rowModel.model;
    }

    public int getItemCount() {
        return tableViewUtils.getItemCount();
    }

    public int getItemViewType(int position) {
        RowModel rowModel = self.getRowModelFromPosition(position);

        int type = cellTypeUtils.getRowModelType(rowModel);

        if (type == 1) {
            int x = 0;
        }
        if (position == 6) {
            int x = 0;
        }

        return type;
    }


}
