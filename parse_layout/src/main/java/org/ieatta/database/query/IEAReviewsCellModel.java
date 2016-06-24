package org.ieatta.database.query;


import com.ieatta.provide.IEAEditKey;
import com.tableview.model.EditBaseCellModel;

import org.ieatta.database.models.DBReview;
import org.ieatta.database.models.DBTeam;
import org.ieatta.database.utils.DBUtil;

public class IEAReviewsCellModel extends EditBaseCellModel {
    public String reviewUUID;
    public String userUUID;
    public String title;

    public String timeAgoString;
    public int ratingValue;
    public String reviewContent;

    public IEAReviewsCellModel(DBReview review, DBTeam team) {
        super(IEAEditKey.Unknow);

        this.reviewUUID = review.getUUID();
        this.ratingValue = review.getRate();
        this.reviewContent = review.getContent();
        this.timeAgoString = DBUtil.getTimeAgoString(review.getObjectCreatedDate());

        this.userUUID = team.getUUID();
        this.title = team.getDisplayName();
    }

}