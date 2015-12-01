package com.ieatta.android.modules;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEADTTableViewManagerViewController extends IEAAppSegureTableViewController{


    override func viewDidLoad() {
        super.viewDidLoad()

        getTableManager().startManagingWithDelegate(self)
        setRegisterHeaderClass(IEAViewForHeaderInSectionCell)
    }

    func whenSelectedEvent(model:AnyObject,indexPath:NSIndexPath){
        fatalError("whenSelectedEvent() has not been implemented")
    }

    func setRegisterHeaderClass<T : ModelTransfer where T : UIView>(headerClass: T.Type){
        getTableManager().registerHeaderClass(headerClass)
    }

    func setRegisterFooterClass<T : ModelTransfer where T : UIView>(footerClass: T.Type){
        getTableManager().registerFooterClass(footerClass)
    }

    func setRegisterCellClass<T : ModelTransfer where T : UITableViewCell>(cellClass: T.Type){
        getTableManager().registerCellClass(cellClass)
    }

    func setRegisterCellClassWhenSelected<T : ModelTransfer where T : UITableViewCell>(cellClass: T.Type){
        getTableManager().registerCellClass(cellClass) { (_, model, indexPath) -> Void in
            self.whenSelectedEvent((model as! AnyObject), indexPath: indexPath)
        }
    }

    func setSectionItems<T>(items: [T], forSectionIndex index: Int){
        getMemoryStorage().setItems(items,forSectionIndex: index)
    }

    func setFooterModelInSection<T>(model: T?, forSectionIndex sectionIndex: Int){
        getMemoryStorage().setSectionFooterModel(model,forSectionIndex: sectionIndex)
    }

    func removeSectionItemsAtIndexPaths(indexPaths: [NSIndexPath]){
        getMemoryStorage().removeItemsAtIndexPaths(indexPaths)
    }

    func appendSectionTitleCell(cell:EditBaseCellModel, forSectionIndex index:Int){
        getMemoryStorage().setSectionHeaderModel(cell, forSectionIndex: index)
    }

    func getMemoryStorage() -> DTModelStorage.MemoryStorage{
        return getTableManager().memoryStorage
    }

    func getTableManager() -> DTTableViewManager{
        return manager
    }


}
