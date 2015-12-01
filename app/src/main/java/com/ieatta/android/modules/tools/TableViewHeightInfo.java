package com.ieatta.android.modules.tools;

import com.ieatta.android.extensions.viewkit.NSIndexPath;

import java.util.LinkedHashMap;

/**
 * Created by djzhang on 12/1/15.
 */
public class TableViewHeightInfo {

    private TableViewHeightInfo self = this;

    public LinkedHashMap<Integer,Integer> heightForHeaderInSection =new LinkedHashMap<>();
    public LinkedHashMap<Integer,Integer> heightForRowAtIndexPath =new LinkedHashMap<>();

//
//    var accessoryType:[Int:UITableViewCellAccessoryType] = [Int:UITableViewCellAccessoryType]()
//    var selectionStyle:[Int:UITableViewCellSelectionStyle] = [Int:UITableViewCellSelectionStyle]()

//    var reviewSectionIndex: Int = Int.min
    public boolean hasReviewSection = true;

    static TableViewHeightInfo getEmptyInfo()   {
//        return TableViewHeightInfo(heightForHeaderInSection: [Int:CGFloat](),heightForRowAtIndexPath: [Int:CGFloat]())
        return  null;
    }

    public Integer getHeightForRowAtIndexPath(NSIndexPath indexPath)  {
        //        print("getHeightForRowAtIndexPath: \(self.printDescription())")
//        return heightForRowAtIndexPath[indexPath.section]
        return null;
    }

    public Integer getHeightForHeaderInSection( int section) {
        if(hasReviewSection == true){
//            if(section > reviewSectionIndex){
//                return DEFAULT_SECTION_HEADER_HEIGHT
//            }
        }
//        return heightForHeaderInSection[section]
        return  null;
    }

    public void setHeightForRowAtIndexPath(int section,int value){
//        heightForRowAtIndexPath[section] = value
    }

    public int getRowCount() {
        return self.heightForRowAtIndexPath.keySet().size();
    }

//    init(heightForHeaderInSection:[Int:CGFloat],heightForRowAtIndexPath:[Int:CGFloat],accessoryType:[Int:UITableViewCellAccessoryType] = [Int:UITableViewCellAccessoryType](),selectionStyle:[Int:UITableViewCellSelectionStyle] = [Int:UITableViewCellSelectionStyle](),reviewSectionIndex: Int = Int.min) {
//
//        self.heightForHeaderInSection = heightForHeaderInSection
//        self.heightForRowAtIndexPath  = heightForRowAtIndexPath
//        self.accessoryType            = accessoryType
//        self.selectionStyle           = selectionStyle
//
//        self.setReviewSectionIndex(reviewSectionIndex)
//    }

//    func setReviewSectionIndex(reviewSectionIndex: Int = Int.min){
//        self.reviewSectionIndex       = reviewSectionIndex
//        if(reviewSectionIndex == Int.min){
//            hasReviewSection = false
//        }
//    }

//    func printDescription(){
//        print("heightForHeaderInSection: \(heightForHeaderInSection)")
//        print("heightForRowAtIndexPath: \(heightForRowAtIndexPath)")
//    }


}
