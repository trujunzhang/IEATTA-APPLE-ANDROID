package com.ieatta.android.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class IEACache {
    private IEACache self = this;
    public static final IEACache sharedInstance = new IEACache();

    private HashMap<String, Integer> averageRatingsCache = new LinkedHashMap<>();

    public void setAvarageRating(int value, Review review) {
        self.averageRatingsCache.put(review.reviewRef, new Integer(value));
    }

    public Integer avarageRating(Review review) {
        if (self.averageRatingsCache.containsKey(review.reviewRef)) {
            return self.averageRatingsCache.get(review.reviewRef);
        }
        return null;
    }

    public void clearAvarageRatingCache() {
        self.averageRatingsCache = new LinkedHashMap<>();
    }

    private HashMap<String, String> photoPointCache = new LinkedHashMap<>();

    public void setPhotoPoint(Photo photo) {
//        assert(photo.usedRef.isEmpty == false, "Must setup usedRef on the setPhotoPoint!")

        self.photoPointCache.put(photo.usedRef, ParseModelAbstract.getPoint(photo));
    }

    public void copyPhotoPoint(RatedModelReviewCount review, ParseModelAbstract model) {
//        if let value = self.photoPoint(model){
//            self.photoPointCache.setObject(value, forKey: ParseModelAbstract.getPoint(review))
//        }
    }

    public String photoPoint(ParseModelAbstract model) {
        if (self.photoPointCache.containsKey(ParseModelAbstract.getPoint(model))) {
            return self.photoPointCache.get(ParseModelAbstract.getPoint(model));
        }
        return null;
    }

    public boolean setPhotoPointForModels(Task<List<ParseModelAbstract>> previous) {
        List<ParseModelAbstract> fetchedPhotos = previous.getResult();
        for (Object photo : fetchedPhotos) {
            self.setPhotoPoint((Photo) photo);
        }

        return true;
    }

    public void clearPhotoPointCache() {
        self.photoPointCache = new LinkedHashMap<>();
    }


}
