package com.ieatta.android.extensions.storage;

import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/2/15.
 */
public class CellTypeUtils {
    private CellTypeUtils self = this;
    public LinkedHashMap<Integer, Class> rowTypes = new LinkedHashMap<>();

    public void registerType(Class aClass, int layoutResId) {
        if (self.isExistRegisterType(aClass) == false) {
            int size = self.rowTypes.size();
            self.rowTypes.put(new Integer(size), aClass);
        }
    }

    public int getRowModelType(RowModel rowModel) {
        for (Integer index : self.rowTypes.keySet()) {
            Class classzz = self.rowTypes.get(index);
            if (rowModel.cellClass.equals(classzz)) {
                return index.intValue();
            }
        }
        return 0;
    }

    private boolean isExistRegisterType(Class aClass) {
        for (Integer index : self.rowTypes.keySet()) {
            Class classzz = self.rowTypes.get(index);
            if (aClass.equals(classzz)) {
                return true;
            }
        }
        return false;
    }
}
