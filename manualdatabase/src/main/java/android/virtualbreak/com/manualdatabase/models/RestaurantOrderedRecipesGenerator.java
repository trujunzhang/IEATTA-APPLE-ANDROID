package android.virtualbreak.com.manualdatabase.models;


import android.virtualbreak.com.manualdatabase.R;

import com.ieatta.com.parse.models.Recipe;

import java.util.LinkedList;

/**
 * Created by djzhang on 7/20/15.
 */
public class RestaurantOrderedRecipesGenerator {
    private static LinkedList<Recipe> RecipeList = new LinkedList<>();

    static {

//        // people01
//        RecipeList.add(new Recipe("Amazing sticky rice!", 12.2, R.drawable.food01));
//        RecipeList.add(new Recipe("Drunken Mushroom!", 6.2, R.drawable.food02));
//        RecipeList.add(new Recipe("Vegan Glory", 4.4, R.drawable.food03));
//        // people02
//        RecipeList.add(new Recipe("Avocado curry", 2.4, R.drawable.food11));
//        // people03
//        RecipeList.add(new Recipe("Brown rice.", 3.2, R.drawable.food21));
//        RecipeList.add(new Recipe("Tofu satay", 6.1, R.drawable.food22));
//        RecipeList.add(new Recipe("Red curry tofu and green", 4.1, R.drawable.food23));

    }

    public static LinkedList<LinkedList<Recipe>> getCurrentRecipeList() {
        LinkedList<Recipe> Recipes = RecipeList;

        LinkedList<LinkedList<Recipe>> list = new LinkedList<>();

        LinkedList<Recipe> list01 = new LinkedList<>();
        list01.add(Recipes.get(0));
        list01.add(Recipes.get(1));
        list01.add(Recipes.get(2));

        LinkedList<Recipe> list02 = new LinkedList<>();
        list02.add(Recipes.get(3));

        LinkedList<Recipe> list03 = new LinkedList<>();
        list03.add(Recipes.get(4));
        list03.add(Recipes.get(5));
        list03.add(Recipes.get(6));

        list.add(list01);
        list.add(list02);
        list.add(list03);

        return list;
    }


}
