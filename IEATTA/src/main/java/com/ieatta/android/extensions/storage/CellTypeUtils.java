package com.ieatta.android.extensions.storage;

import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.extensions.storage.models.RowModel;

import java.util.LinkedHashMap;


public class CellTypeUtils {
    public CellType getModelType(int viewType) {
        Class aClass = this.rowTypes.get(new Integer(viewType));
        int layResId = this.modelTypes.get(aClass).intValue();
        return new CellType(aClass, layResId);
    }
    /**
     * HashMap:
     * key: cell's type, also is viewType.
     * value: cell's class.
     */
    private LinkedHashMap<Integer, Class> rowTypes = new LinkedHashMap<>();

    private LinkedHashMap<Class, Integer> modelTypes = new LinkedHashMap<>();

    public void registerType(CellType type) {
        modelTypes.put(type.cellClass, type.layoutResId);

        if (this.isExistRegisterType(type.cellClass) == false) {
            int size = this.rowTypes.size();
            this.rowTypes.put(new Integer(size), type.cellClass);
        }
    }

    public int getRowModelType(RowModel rowModel) {
        for (Integer index : this.rowTypes.keySet()) {
            Class classzz = this.rowTypes.get(index);
            if (classzz == null) {
                int x = 0;
//                assert("not found class type: "+classzz.getName()+",you need to register it.");
            }
            if (rowModel.cellType.cellClass.equals(classzz)) {
                return index.intValue();
            }
        }
        return 0;
    }

    private boolean isExistRegisterType(Class aClass) {
        for (Integer index : this.rowTypes.keySet()) {
            Class classzz = this.rowTypes.get(index);
            if (aClass.equals(classzz)) {
                return true;
            }
        }
        return false;
    }
}
