package com.ieatta.android.modules.view.edit.model;

import com.ieatta.android.R;
import com.ieatta.android.modules.common.edit.EditBaseCellModel;
import com.ieatta.android.modules.common.edit.EditCellModel;
import com.ieatta.android.modules.common.edit.enums.IEAEditKey;
import com.ieatta.android.modules.view.edit.IEAEditBaseViewController;
import com.ieatta.com.parse.ParseModelAbstract;
import com.ieatta.com.parse.models.Recipe;

/**
 * Created by djzhang on 12/4/15.
 */
public class IEAEditRecipeManager extends IEAEditAbstractManager {
    @Override
    public Object[] getRowsInSection(ParseModelAbstract model, IEAEditBaseViewController viewController) {
        Recipe recipe = (Recipe) model;

         EditBaseCellModel[] section1 = {
                new EditCellModel(IEAEditKey.recipe_name, recipe.displayName, R.string.Recipe_Name_info),
                new EditCellModel(IEAEditKey.recipe_price, recipe.price, R.string.recipe_price)
        };
        Object[] sections = {section1};

        return sections;
    }

}
