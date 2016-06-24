package org.ieatta.utils;


import org.ieatta.database.models.DBEvent;
import org.ieatta.database.models.DBPeopleInEvent;
import org.ieatta.database.models.DBPhoto;
import org.ieatta.database.models.DBReview;
import org.ieatta.database.models.DBTeam;

import com.ieatta.provide.AppConstant;
import com.tableview.model.IEAOrderedPeople;
import com.tableview.model.IEAReviewsCellModel;

import org.ieatta.server.cache.ThumbnailImageUtil;
import org.wikipedia.analytics.DBConvertFunnel;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import io.realm.RealmResults;

public class DBConvert {

//    public static List<GalleryItem> toGalleryItem(RealmResults<DBPhoto> photos) {
//        List<GalleryItem> list = new LinkedList<>();
//        for (DBPhoto photo : photos) {
//            File file = ThumbnailImageUtil.sharedInstance.getCacheImageUrl(photo);
//            GalleryItem item = new GalleryItem(photo.getUUID(), "file://" + file.getAbsolutePath());
//            new DBConvertFunnel().logToGalleryItem("toGalleryItem", "photo's path: " + file.getAbsolutePath());
//            list.add(item);
//        }
//        return list;
//    }


    // MARK: Anonymous User
    public static DBTeam getAnonymousUser() {
        DBTeam team = new DBTeam();
        team.setDisplayName("Anonymous");

        return team;
    }


    public static List<IEAOrderedPeople> toOrderedPeopleList(RealmResults<DBTeam> teams, DBEvent event) {
        List<IEAOrderedPeople> list = new LinkedList<>();
        for (DBTeam team : teams) {
            list.add(new IEAOrderedPeople(team.getUUID(), team.getDisplayName(), event.getUUID()));
        }
        return list;
    }


    public static List<String> getPeoplePoints(List<DBPeopleInEvent> peopleInEvent) {
        List<String> peoplePoints = new LinkedList<>();

        for (DBPeopleInEvent model : peopleInEvent) {
            peoplePoints.add(model.getUserRef());
        }

        return peoplePoints;
    }
}
