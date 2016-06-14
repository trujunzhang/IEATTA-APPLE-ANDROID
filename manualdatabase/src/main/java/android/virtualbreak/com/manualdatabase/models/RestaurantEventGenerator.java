package android.virtualbreak.com.manualdatabase.models;



import java.util.Date;
import java.util.LinkedList;

/**
 * Created by djzhang on 7/20/15.
 */
public class RestaurantEventGenerator {
    private static LinkedList<Event> EventList = new LinkedList<>();

    static {
        EventList.add(new Event("Vegas Baby!", new Date(), new Date(), "Street Food Cinema summer event series (through October) features cool outdoor movies, tasty food trucks, good music, interactive games + more every Saturday night at select venues throughout LA!", "remarks1"));
        EventList.add(new Event("California Hot Sauce Expo", new Date(), new Date(), "Avengers Assemble! Join the Avengers team for the Avengers Super Heroes Half Marathon Weekend at the Disneyland® Resort - the extraordinary course on Earth hosted by Earth's Mightiest Heroes! It's a power-packed weekend of fantastic fun and amazing excitement with mighty runs, incredible surprises, a night time pasta party, and the chance to take home your own Avengers Super Heroes Finisher Medal. Unleash your inner Super Hero abilities! Join the fun during the Avengers Super Heroes Weekend.", "remarks2"));
        EventList.add(new Event("Claremont Restaurant Week", new Date(), new Date(), "This annual event includes an early morning 5K/10K run, a delicious pancake breakfast sponsored by the local Kiwanis club, a car show, chili cook-off, family games, food booths, the Chamber of Commerce business expo, children's rides, arts and crafts booths and four stages with continuous entertainment. This is the largest one day festival in Orange County!", "remarks3"));
        EventList.add(new Event("Kiehl's Since 1851 Grand Opening Event", new Date(), new Date(), "DOG WALK & FESTIVAL A family-friendly, fundraising event celebrating pets and the people who love them.", "remarks4"));
    }

    public static LinkedList<Event> getAsyncEventList() {
        return EventList;
    }

    public static LinkedList<LinkedList<Event>> getCurrentEventList() {
        LinkedList<Event> events = EventList;

        LinkedList<LinkedList<Event>> list = new LinkedList<>();

        LinkedList<Event> list01 = new LinkedList<>();
        list01.add(events.get(0));

        LinkedList<Event> list02 = new LinkedList<>();
        list02.add(events.get(1));
        list02.add(events.get(2));

        LinkedList<Event> list03 = new LinkedList<>();
        list03.add(events.get(3));

        list.add(list01);
        list.add(list02);
        list.add(list03);

        return list;
    }

}
