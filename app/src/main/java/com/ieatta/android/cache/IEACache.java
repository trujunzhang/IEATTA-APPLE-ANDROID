package com.ieatta.android.cache;

import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Photo;
import com.ieatta.com.parse.models.Review;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEACache {
    private IEACache self = this;
    public static final IEACache sharedInstance = new IEACache();

    private LinkedHashMap<String,Integer> averageRatingsCache = new LinkedHashMap<>();

    public void setAvarageRating(int value,Review review){
        self.averageRatingsCache.put(review.reviewRef,new Integer(value));
    }

    public Integer avarageRating(Review review) {
        Integer integer = self.averageRatingsCache.get(review.reviewRef);

        return integer;
    }

    public void clearAvarageRatingCache(){
        self.averageRatingsCache = new LinkedHashMap<>();
    }

    private LinkedHashMap<String,String> photoPointCache = new LinkedHashMap<>();

    public void setPhotoPoint(Photo photo){
//        assert(photo.usedRef.isEmpty == false, "Must setup usedRef on the setPhotoPoint!")

        self.photoPointCache.put(photo.usedRef,ParseModelAbstract.getPoint(photo));
    }

    public void copyPhotoPoint(RatedModelReviewCount review,ParseModelAbstract model){
//        if let value = self.photoPoint(model){
//            self.photoPointCache.setObject(value, forKey: ParseModelAbstract.getPoint(review))
//        }
    }

    public String photoPoint(ParseModelAbstract model){
        return  self.photoPointCache.get(ParseModelAbstract.getPoint(model));
    }

    public Task<Object> setPhotoPointForModels(Task<Object> previous){
        TaskCompletionSource result = (TaskCompletionSource) previous.getResult();

        LinkedList<Photo> fetchedPhotos = (LinkedList<Photo>) result.getTask().getResult();
        for(Photo photo : fetchedPhotos){
            self.setPhotoPoint(photo);
        }

        TaskCompletionSource finalTask = new TaskCompletionSource();
        finalTask.setResult(true);
        return finalTask.getTask();
    }

    public void  clearPhotoPointCache(){
        self.photoPointCache = new LinkedHashMap<>();
    }



}
