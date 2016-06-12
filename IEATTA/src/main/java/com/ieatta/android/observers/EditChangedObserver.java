package com.ieatta.android.observers;

public class EditChangedObserver {
    private EditChangedObserver self = this;
    public static EditChangedObserver sharedInstance = new EditChangedObserver();

    public void takenPhotoListener() {
        self.hasTakenPhoto = true;
    }

    //    var hasEditChanged = false
    public boolean hasTakenPhoto = false;

//    var contents:[String:String] = [String:String]()

    public void resetObserver() {
        self.hasTakenPhoto = false;
//        self.contents = [String:String]()
    }

//    func editChangedListener(model:EditCellModel?){
//        if let _model = model{
//            self.contents[_model.editKey.rawValue] = _model.editValue
//        }else{
//            assert(false, "Must allocate a 'EditCellModel' object.")
//        }
//    }


}
