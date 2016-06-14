package org.ieatta.tasks.edit;

import android.app.Activity;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import android.view.View;

import org.ieatta.activity.gallery.GalleryCollection;

import org.ieatta.tasks.FragmentTask;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;
import io.realm.RealmResults;

public class EventEditTask extends FragmentTask {


//    enum EditEventSection {
//        sectionInformation,//= 0
//        section_gallery_thumbnail,//= 1
//        sectionDurationDate,//= 2
//    }
//
//    public DBEvent mEvent = new DBEvent();
//
//    private DBEvent makeNewEvent(){
//        DBEvent event = new DBEvent();
//
//        event.setDisplayName("");
//        event.setStartDate(new Date());
//        event.setEndDate(DBUtil.getNextHourDate());
//
//        return event;
//    }
//
//    /**
//     * Execute Task for Event edit.
//     *
//     * @return
//     */
//    @Override
//    public Task<Void> executeTask() {
//        final String _eventUUID = this.entry.getHPara();
//
//        return new RealmModelReader<DBEvent>(DBEvent.class).getFirstObject(LocalDatabaseQuery.get(_eventUUID), false, this.realmList).onSuccessTask(new Continuation<DBEvent, Task<RealmResults<DBPhoto>>>() {
//            @Override
//            public Task<RealmResults<DBPhoto>> then(Task<DBEvent> task) throws Exception {
//                mEvent = task.getResult();
//                return LocalDatabaseQuery.queryPhotosByModel(_eventUUID, PhotoUsedType.PU_Waiter.getType(), realmList);
//            }
//        }).onSuccessTask(new Continuation<RealmResults<DBPhoto>, Task<Void>>() {
//            @Override
//            public Task<Void> then(Task<RealmResults<DBPhoto>> task) throws Exception {
//                thumbnailGalleryCollection = new GalleryCollection(DBConvert.toGalleryItem(task.getResult()));
//                return null;
//            }
//        });
//    }
//
//    @Override
//    public void prepareUI() {
//        super.prepareUI();
//
//        // Add rows for sections.
//        this.manager.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Event_Information), EditEventSection.sectionInformation.ordinal());
//        this.manager.appendSectionTitleCell(new SectionTitleCellModel(IEAEditKey.Section_Title, R.string.Date_of_Event), EditEventSection.sectionDurationDate.ordinal());
//        this.manager.setRegisterCellClass(IEAEditTextFieldCell.getType(), EditEventSection.sectionInformation.ordinal());
//        this.manager.setRegisterCellClassInSpecialRow(IEAEditWaiterTextFieldCell.getType(), EditEventSection.sectionInformation.ordinal(), 1);
//
//        this.manager.setRegisterCellClass(IEADatePickerCell.getType(), EditEventSection.sectionDurationDate.ordinal());
//    }
//
//    @Override
//    public void postUI() {
//        this.manager.setHeaderItem(new IEAHeaderViewModel(this.model.getActionbarHeight()), IEAHeaderView.getType());
//        this.manager.setFooterItem(new IEAFooterViewModel(), IEAFooterView.getType());
//
//        List<EditCellModel> infoSectionList = new LinkedList<EditCellModel>() {{
//            add(new EditCellModel(IEAEditKey.event_name, mEvent.getDisplayName(), R.string.Event_Name_info));
//            add(new EditWaiterCellModel(IEAEditKey.event_nameofserver, mEvent.getWaiter(), R.string.Name_of_Server));
//        }};
//        this.manager.setSectionItems(infoSectionList, EditEventSection.sectionInformation.ordinal());
//
//        final FragmentManager fm = ((PageActivity) activity).getSupportFragmentManager();
//        List<DatePickerCellModel> dateSectionlList = new LinkedList<DatePickerCellModel>() {{
//            add(new DatePickerCellModel(IEAEditKey.event_starttime, mEvent.getStartDate(), R.string.Start_Time,fm));
//            add(new DatePickerCellModel(IEAEditKey.event_endtime, mEvent.getEndDate(), R.string.End_Time,fm));
//        }};
//        this.manager.setSectionItems(dateSectionlList, EditEventSection.sectionDurationDate.ordinal());
//
//        postPhotosGallery(EditEventSection.section_gallery_thumbnail.ordinal());
//
//        model.setPage(new Page());
//    }

}
