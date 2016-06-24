//package com.ieatta.android.extensions.storage;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.tableview.storage.models.CellType;
//import com.ieatta.android.modules.adapter.IEATableViewControllerAdapter;
//import com.tableview.adapter.IEAViewHolder;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//
//public class DTTableViewManager {
//    public TableViewConfiguration configuration;
//    public MemoryStorage memoryStorage;
//
//    public DTTableViewManager(TableViewConfiguration configuration) {
//        this.configuration = configuration;
//        this.memoryStorage = new MemoryStorage(new IEATableViewControllerAdapter(configuration.builder.context, this, configuration.builder.itemClickListener));
//    }
//
//    public IEATableViewControllerAdapter getAdapter() {
//        return this.memoryStorage.adapter;
//    }
//
//    public int getItemCount() {
//        return this.memoryStorage.getItemCount();
//    }
//
//    public void registerHeaderClass(CellType type) {
//        this.memoryStorage.cellTypeUtils.registerType(type);
//    }
//
//    public void registerFooterClass(CellType type) {
//        this.memoryStorage.cellTypeUtils.registerType(type);
//    }
//
//    public IEAViewHolder createViewHolder(ViewGroup parent, int viewType) {
//        CellType cellType = this.memoryStorage.cellTypeUtils.getModelType(viewType);
//        Class cellClass = cellType.cellClass;
//        int layoutResId = cellType.layoutResId;
//
//        Constructor[] ctors = cellClass.getDeclaredConstructors();
//        Constructor viewConstructor = TableViewFactory.getConstructorForView(ctors);
//        View view = LayoutInflater.from(this.configuration.builder.context).inflate(layoutResId, parent, false);
//
//        IEAViewHolder viewHolder = null;
//        try {
//            viewHolder = (IEAViewHolder) viewConstructor.newInstance(new Object[]{view});
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return viewHolder;
//    }
//
//    public void registerCellClass(CellType type, int forSectionIndex) {
//        this.memoryStorage.registerCellClass(type, forSectionIndex);
//    }
//
//    public void registerCellClassInSpecialRow(CellType type, int forSectionIndex, int forRowIndex) {
//        this.memoryStorage.registerCellClassInSpecialRow(type, forSectionIndex, forRowIndex);
//    }
//}
