package com.ieatta.android.extensions.storage;

import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.storage.models.FooterModel;
import com.ieatta.android.extensions.storage.models.HeaderModel;
import com.ieatta.android.extensions.storage.models.RowModel;
import com.ieatta.android.modules.adapter.IEATableViewControllerAdapter;
import com.ieatta.android.modules.adapter.NSIndexPath;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MemoryStorage {
    public IEATableViewControllerAdapter adapter;
    private TableViewUtils tableViewUtils = new TableViewUtils();

    public HashMap<Integer, SectionModel> sections = new LinkedHashMap<>();
    public CellTypeUtils cellTypeUtils = new CellTypeUtils();

    public MemoryStorage(IEATableViewControllerAdapter adapter) {
        this.adapter = adapter;
    }

    /// Add items to section with `toSection` number.
    /// - Parameter items: items to add
    /// - Parameter toSection: index of section to add items
    public void addItems(List<Object> items, int toSection) {
//        this.sections.put(new Integer(toSection), new SectionModel(items));
    }

    private void reloadTableView() {
        this.tableViewUtils.generateItems(this.sections);

        this.adapter.notifyDataSetChanged();
    }

    /// Set items for specific section. This will reload UI after updating.
    /// - Parameter items: items to set for section
    /// - Parameter forSectionIndex: index of section to update
    public void setItems(List items, int forSectionIndex) {
        SectionModel section = this.verifySection(forSectionIndex);
        section.items = items;
        this.sections.put(new Integer(forSectionIndex), section);

        this.reloadTableView();
    }

    /// Set section header model for MemoryStorage
    /// - Note: This does not update UI
    /// - Parameter model: model for section header at index
    /// - Parameter sectionIndex: index of section for setting header
    public void setSectionHeaderModel(Object model, int forSectionIndex, CellType type) {
        // Step1: Register cell type.
        cellTypeUtils.registerType(type);

        // Step2: Create/Add a Header Section.
        SectionModel section = this.verifySection(forSectionIndex);
        section.setHeaderModel(new HeaderModel(model, type));

//        this.reloadTableView();
    }

    /// Set section footer model for MemoryStorage
    /// - Note: This does not update UI
    /// - Parameter model: model for section footer at index
    /// - Parameter sectionIndex: index of section for setting footer
    public void setSectionFooterModel(Object model, int forSectionIndex, CellType type) {
        // Step1: Register cell type.
        cellTypeUtils.registerType(type);

        // Step2: Create/Add a Footer Section.
        SectionModel section = this.verifySection(forSectionIndex);
        section.setFooterModel(new FooterModel(model, type));

        this.reloadTableView();
    }

    private SectionModel verifySection(int forSectionIndex) {
        SectionModel model = this.sections.get(new Integer(forSectionIndex));
        if (model != null) {
            return model;
        }
        model = new SectionModel(forSectionIndex);
        this.sections.put(new Integer(forSectionIndex), model);
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
        SectionModel section = this.verifySection(forSectionIndex);
        section.cellType = type;
    }

    public void registerCellClassInSpecialRow(CellType type, int forSectionIndex, int forRowIndex) {
        // Step1: Register class type
        cellTypeUtils.registerType(type);

        // Step2: Create/Modify a section.
        SectionModel section = this.verifySection(forSectionIndex);
        section.specialRows.put(new Integer(forRowIndex), type);
    }

    public RowModel getRowModelFromPosition(int position) {
        return this.tableViewUtils.getItem(position);
    }

    public Object getRowModel(int position) {
        RowModel rowModel = this.getRowModelFromPosition(position);
        return rowModel.model;
    }

    public int getItemCount() {
        return tableViewUtils.getItemCount();
    }

    public int getItemViewType(int position) {
        RowModel rowModel = this.getRowModelFromPosition(position);
        int type = cellTypeUtils.getRowModelType(rowModel);
        return type;
    }

    public RowModel getItem(int position) {
        return this.tableViewUtils.getItem(position);
    }


}
