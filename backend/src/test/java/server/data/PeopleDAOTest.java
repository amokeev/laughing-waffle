package server.data;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.Filter;
import server.pojo.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lesha on 30.04.18.
 */
public class PeopleDAOTest {
    private static List<Entry> allAvailableEntries;
    private static PeopleDAO peopleDAO;

    @BeforeClass
    public static void beforeClass() throws Exception {
        peopleDAO = Provider.getPeopleDAO();
        allAvailableEntries = peopleDAO.get();
    }

    @Test
    public void applyRandomFilter() throws Exception {
        int minFilteredSize = 10;
        int maxAttmpts = 10000;


        List<Entry> includedEntries = new ArrayList<>();
        RandomFilter rf = null;
        int attemptCount = 0;
        while (includedEntries.size() <minFilteredSize && attemptCount <maxAttmpts) {
            rf = new RandomFilter();
            includedEntries = peopleDAO.get(rf.filter());
            attemptCount++;
        }
        assertNotEquals("Unable to find suitable filter", attemptCount, maxAttmpts);

        List<Entry> excludedEntries = new ArrayList<>(allAvailableEntries);
        excludedEntries.removeAll(includedEntries);

        if(rf.photo)
            if(!rf.photoVal)
                assertThat(includedEntries, everyItem(hasProperty("main_photo", nullValue())));
            else
                assertThat(includedEntries, everyItem(hasProperty("main_photo", not(nullValue()))));

        if(rf.contact)
            if(!rf.contactVal)
                assertThat(includedEntries, everyItem(hasProperty("contacts_exchanged", not(greaterThan(0)))));
            else
                assertThat(includedEntries, everyItem(hasProperty("contacts_exchanged", greaterThan(0))));

        if(rf.favorite)
            assertThat(includedEntries, everyItem(hasProperty("favourite", is(rf.favoriteVal))));

        //....

        if(rf.ageMin) {
            int minBoundary = rf.ageMinVal < 18 ? 18:rf.ageMinVal;
            assertThat(includedEntries, everyItem(hasProperty("age", greaterThanOrEqualTo(rf.ageMinVal))));
        }

        if(rf.ageMax) {
            assertThat(includedEntries, everyItem(hasProperty("age", lessThanOrEqualTo(rf.ageMaxVal))));
            assertThat(includedEntries, everyItem(hasProperty("age", greaterThanOrEqualTo(18))));
        }

        //...
        //The test can be extended to cover all the fields
    }

    /* The below test is necessary to make sure that we test many possible combinations. use count to givern execution time */

    @Test
    public void applyRepeatedly() throws Exception {
        int count = 100;
        while(count>0) {
            applyRandomFilter();
            count--;
        }
    }
}


class RandomFilter {
    private static Random rnd = new Random();
    boolean photo = rnd.nextBoolean();
    boolean photoVal = rnd.nextBoolean();
    boolean contact = rnd.nextBoolean();
    boolean contactVal = rnd.nextBoolean();
    boolean favorite = rnd.nextBoolean();
    boolean favoriteVal = rnd.nextBoolean();
    boolean scoreMin = rnd.nextBoolean();
    double scoreMinVal = rnd.nextDouble()*0.9 + 0.1;
    boolean scoreMax = rnd.nextBoolean();
    double scoreMaxVal = rnd.nextDouble()*0.9 + 0.1;
    boolean ageMin = rnd.nextBoolean();
    int ageMinVal = rnd.nextInt(99-15) + 15;
    boolean ageMax = rnd.nextBoolean();
    int ageMaxVal = rnd.nextInt(99-15) + 15;
    boolean heightMin = rnd.nextBoolean();
    int heightMinVal = rnd.nextInt(215-134) + 134;
    boolean heightMax = rnd.nextBoolean();
    int heightMaxVal = rnd.nextInt(215-134) + 134;
    boolean distance = rnd.nextBoolean();
    int distanceVal = rnd.nextInt(305-25) + 25;
    Filter filter;

    public RandomFilter() {
        filter = new Filter();
        if(photo) filter.setPhoto(photoVal);
        if(contact) filter.setContact(contactVal);
        if(favorite) filter.setFavorite(favoriteVal);
        if(scoreMin) filter.setScoreMin(scoreMinVal);
        if(scoreMax) filter.setScoreMax(scoreMaxVal);
        if(ageMin) filter.setAgeMin(ageMinVal);
        if(ageMax) filter.setAgeMax(ageMaxVal);
        if(heightMin) filter.setHeightMin(heightMinVal);
        if(heightMax) filter.setHeightMax(heightMaxVal);
        if(distance) filter.setDistance(distanceVal);

    }

    public Filter filter() {
        return filter;
    }
}