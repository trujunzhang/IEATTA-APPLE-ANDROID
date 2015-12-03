package com.ieatta.android.modules.cells.model;

import com.ieatta.android.modules.view.IEARecipeDetailViewController;
import com.ieatta.com.parse.models.Recipe;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEARecipeHeader {
    public IEARecipeDetailViewController viewController ;
    public Recipe model;

    public IEARecipeHeader(IEARecipeDetailViewController viewController, Recipe model) {
        this.viewController = viewController;
        this.model = model;
    }
}
