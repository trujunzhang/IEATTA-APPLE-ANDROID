package com.ieatta.android.modules.view;

import com.ieatta.android.modules.IEAReviewsInDetailTableViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.PeopleInEvent;
import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;

/**
 * Created by djzhang on 12/1/15.
 */
enum EventDetailSection  {
         sectionHeader,        //= 0
         sectionOrderedPeople, //= 1
         sectionReviews,       //= 2
        }
public class IEAEventDetailViewController extends IEAReviewsInDetailTableViewController implements IEAChoicePeopleViewController.IEAChoicePeopleViewProtocol {
    private IEAEventDetailViewController self = this;


public  ParseModelAbstract getPageModel(){
        return self.event;
    }

    public  boolean shouldShowHUD() {
        return true;
    }

    // Transferd Model from previous page.
    private Event event;
    // Selected model from tableview.
    private Team selectedModel;
    // Fetched list by quering database.
    private LinkedList<Team> fetchedPeople;
    private LinkedList<PeopleInEvent> fetchedPeopleInEvent;
    private boolean isTabBarHidden = false;





    @Override
    public void didSelectPeople(Team people) {

    }
}
