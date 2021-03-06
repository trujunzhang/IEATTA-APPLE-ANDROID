package com.ieatta.android.modules.cells.model;

import com.ieatta.android.modules.tools.CollectionUtils;
import com.ieatta.android.modules.view.IEAOrderedRecipesViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Event;
import com.ieatta.com.parse.models.Team;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAOrderedPeople {
    private IEAOrderedPeople self = this;
    public Team model;
    public Event event;
    public boolean hideButton = true;
    public IEAOrderedRecipesViewController viewController;

    public IEAOrderedPeople(Team model, Event event) {
        this.model = model;
        this.event = event;
    }

    public IEAOrderedPeople(Team model, Event event, IEAOrderedRecipesViewController viewController) {
        this(model, event);
        self.hideButton = false;
        this.viewController = viewController;
    }

    public static List convertToOrderedPeople(List<ParseModelAbstract> fetchedOrderedPeople, Event event) {
        List list = new LinkedList<>();
        for (ParseModelAbstract model : fetchedOrderedPeople) {
            list.add(new IEAOrderedPeople((Team) model, event));
        }
        return list;
    }

    public static List convertToOrderedPeople(Team orderedPeople, Event event, IEAOrderedRecipesViewController viewController) {
        IEAOrderedPeople people = new IEAOrderedPeople(orderedPeople, event, viewController);
        return CollectionUtils.createList(people);
    }


}
