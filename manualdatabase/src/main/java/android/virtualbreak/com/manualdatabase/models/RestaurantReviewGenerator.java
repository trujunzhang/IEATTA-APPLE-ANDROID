package android.virtualbreak.com.manualdatabase.models;

import com.ieatta.com.parse.models.Review;
import com.ieatta.com.parse.models.enums.ReviewType;

import java.util.LinkedList;

/**
 * Created by djzhang on 7/20/15.
 */
public class RestaurantReviewGenerator {
    private static LinkedList<Review> reviewsForRestaurant = new LinkedList<Review>();
    private static LinkedList<Review> reviewsForRecipe = new LinkedList<Review>();

    static {
        reviewsForRestaurant.add(new Review(ReviewType.Review_Restaurant, "If you've never heard of Noodle World its a great place for what it is.  They serve a variety of better than mediocre Asian cuisines, at a fair price, with good portions.  I mean it's that simple.  People who can't decide Vietnamese, Thai, Chinese, or Japanese food can pick what they want at Noodle World.  They aren't great but they aren't bad. And on the Westside, what options do we have especially if you're farther south than Sawtelle or Westwood?", 3));
        reviewsForRestaurant.add(new Review(ReviewType.Review_Restaurant, "I come here 90% less than I would if they took cards. I don't really enjoy paying fees that cost as much as my burrito to use the ATM.", 3));
        reviewsForRestaurant.add(new Review(ReviewType.Review_Restaurant, "Their best dish the drunken mushroom is sooo good.", 2));
        reviewsForRestaurant.add(new Review(ReviewType.Review_Restaurant, "but hands down the best al pastor tacos I have ever had.", 3));
        reviewsForRestaurant.add(new Review(ReviewType.Review_Restaurant, "So what makes the Corn Man's Elotes oh so good? He does the typical mayo, cheese, chilli, butter, and sometimes lime, but he puts so much love into serving his loyal customers. What's more is he only charges $1.50. I know most places are upping their prices due to the economy, but he doesn't seem to care much for money, just to make his customers happy and full.", 4));
        reviewsForRestaurant.add(new Review(ReviewType.Review_Restaurant, "I've probably walked by this place 50 times without realizing it was there. With that said, this makes it a bit like a getaway from busy Santa Monica Blvd.", 2));
        reviewsForRestaurant.add(new Review(ReviewType.Review_Restaurant, "Awesome food with fresh ingredients and great tasting, strong coffee. Relaxed atmosphere with super friendly and attentive service. Outdoor patio dining is where it's at and everything on the menu is great.", 3));


        reviewsForRecipe.add(new Review(ReviewType.Review_Recipe, "I ordered the baked eggs with chorizo, which was excellent.", 3));
        reviewsForRecipe.add(new Review(ReviewType.Review_Recipe, "I had the Vegan scramble which was different and very good - in that it does not contain tofu.", 3));
        reviewsForRecipe.add(new Review(ReviewType.Review_Recipe, "The 'Scrumptious Latte' and the 'Blackberry Mint Lemonade' were both really delicious.", 2));
        reviewsForRecipe.add(new Review(ReviewType.Review_Recipe, "The ice cream is creamy and delicious. We were allowed as many samples as we wished also! It's even got a clean.", 3));
        reviewsForRecipe.add(new Review(ReviewType.Review_Recipe, "I purchased a Groupon deal & got a week of vegan meals. The food comes frozen, so it's not loaded with preservatives.", 4));
    }

    public static LinkedList<Review> getReviewListForRestaurant() {
        return reviewsForRestaurant;
    }


    public static LinkedList<Review> getReviewListForRecipe() {
        return reviewsForRecipe;
    }
}
