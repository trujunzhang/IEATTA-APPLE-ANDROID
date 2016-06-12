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
//
//    public static LeadImageCollection toLeadImageCollection(RealmResults<DBPhoto> photos) {
//        List<LeadImage> leadImages = new LinkedList<>();
//        for (DBPhoto photo : photos) {
//            File localFile = ThumbnailImageUtil.sharedInstance.getCacheImageUrl(photo);
//            LeadImage item = new LeadImage("file://" + localFile.getAbsolutePath(), photo.getOriginalUrl());
//            new DBConvertFunnel().logToLeadImageCollection(item.getLocalUrl(), item.getOnlineUrl());
//            leadImages.add(item);
//        }
//        return new LeadImageCollection(leadImages);
//    }
//
//
//    public static GalleryCollection toGalleryCollection(List<File> files) {
//        List<GalleryItem> galleryItems = new LinkedList<>();
//        for (File photoFile : files) {
//            String uuid = photoFile.getName().split("_")[1];
//            String thumbUrl = "file://" + photoFile.getAbsolutePath();
//            GalleryItem item = new GalleryItem(uuid, thumbUrl);
//            galleryItems.add(item);
//        }
//        return new GalleryCollection(galleryItems);
//    }

    private static DBTeam getTeam(String userRef, RealmResults<DBTeam> teams) {
        for (DBTeam team : teams) {
            if (team.getUUID().equals(userRef))
                return team;
        }
//        return AppConstant.getAnonymousUser();
        return null;
    }

    public static List<IEAReviewsCellModel> toReviewsCellModels(RealmResults<DBReview> reviews, RealmResults<DBTeam> teams) {
        List<IEAReviewsCellModel> list = new LinkedList<>();

        for (int i = 0; i < teams.size(); i++) {
            DBReview review = reviews.get(i);
//            list.add(new IEAReviewsCellModel(review, DBConvert.getTeam(review.getUserRef(), teams)));
        }
        return list;
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
