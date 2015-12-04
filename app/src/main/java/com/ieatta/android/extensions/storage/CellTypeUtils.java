package com.ieatta.android.extensions.storage;

import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/2/15.
 */
public class CellTypeUtils {
    public ModelType getModelType(int viewType) {
        Class aClass = self.rowTypes.get(new Integer(viewType));
        int layResId = self.modelTypes.get(aClass).intValue();
        return new ModelType(aClass,layResId);
    }

    public class ModelType{
        public int layoutResId;
        public Class cellClass;

        public ModelType(Class cellClass, int layoutResId) {
            this.layoutResId = layoutResId;
            this.cellClass = cellClass;
        }
    }

    private CellTypeUtils self = this;
    /**
     * HashMap:
     *   key: cell's type, also is viewType.
     *   value: cell's class.
     */
    private LinkedHashMap<Integer, Class> rowTypes = new LinkedHashMap<>();

    private LinkedHashMap<Class,Integer> modelTypes = new LinkedHashMap<>();

    public void registerType(Class aClass, int layoutResId) {
        modelTypes.put(aClass,layoutResId);

        if (self.isExistRegisterType(aClass) == false) {
            int size = self.rowTypes.size();
            self.rowTypes.put(new Integer(size), aClass);
        }
    }

    public int getRowModelType(RowModel rowModel) {
        for (Integer index : self.rowTypes.keySet()) {
            Class classzz = self.rowTypes.get(index);
            if(classzz == null){
//                assert("not found class type: "+classzz.getName()+",you need to register it.");
            }
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
