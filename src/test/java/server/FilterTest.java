package server;

import org.junit.Test;
import server.pojo.Entry;

import static org.junit.Assert.*;

/**
 * Created by lesha on 27.04.18.
 */
public class FilterTest {
    @Test
    public void setPhoto() throws Exception {
        Entry eP = new Entry().setDisplay_name("DummyPositive").setMain_photo("http://some.com");
        Entry eN = new Entry().setDisplay_name("DummyNegative");
        Filter fPhoto = new Filter().setPhoto(true);
        assertTrue(fPhoto.pass(eP));
        assertFalse(fPhoto.pass(eN));

        Filter fWOPhoto = new Filter().setPhoto(false);
        assertFalse(fWOPhoto.pass(eP));
        assertTrue(fWOPhoto.pass(eN));
    }

    @Test
    public void setContact() throws Exception {
        Entry eP = new Entry().setDisplay_name("DummyPositive").setContacts_exchanged(5);
        Entry eN = new Entry().setDisplay_name("DummyNegative");
        Filter filter = new Filter().setContact(true);
        assertTrue(filter.pass(eP));
        assertFalse(filter.pass(eN));

        filter = new Filter().setContact(false);
        assertFalse(filter.pass(eP));
        assertTrue(filter.pass(eN));
    }

    @Test
    public void setFavorite() throws Exception {
        Entry eP = new Entry().setDisplay_name("DummyPositive").setFavourite(true);
        Entry eN = new Entry().setDisplay_name("DummyNegative");
        Filter filter = new Filter().setFavorite(true);
        assertTrue(filter.pass(eP));
        assertFalse(filter.pass(eN));

        filter = new Filter().setFavorite(false);
        assertFalse(filter.pass(eP));
        assertTrue(filter.pass(eN));
    }

    @Test
    public void setScoreMin() throws Exception {
        Entry e50 = new Entry().setDisplay_name("Dummy").setCompatibility_score(0.5);
        Entry e70 = new Entry().setDisplay_name("Dummy").setCompatibility_score(0.7);
        Filter filter1 = new Filter().setScoreMin(0.3);
        Filter filter2 = new Filter().setScoreMin(0.6);
        Filter filter3 = new Filter().setScoreMin(0.8);
        assertTrue(filter1.pass(e50));
        assertTrue(filter1.pass(e70));

        assertFalse(filter2.pass(e50));
        assertTrue(filter2.pass(e70));

        assertFalse(filter3.pass(e50));
        assertFalse(filter3.pass(e70));

    }

    @Test
    public void setScoreMax() throws Exception {
        Entry e50 = new Entry().setDisplay_name("Dummy").setCompatibility_score(0.5);
        Entry e70 = new Entry().setDisplay_name("Dummy").setCompatibility_score(0.7);
        Filter filter1 = new Filter().setScoreMax(0.3);
        Filter filter2 = new Filter().setScoreMax(0.6);
        Filter filter3 = new Filter().setScoreMax(0.8);
        assertFalse(filter1.pass(e50));
        assertFalse(filter1.pass(e70));

        assertTrue(filter2.pass(e50));
        assertFalse(filter2.pass(e70));

        assertTrue(filter3.pass(e50));
        assertTrue(filter3.pass(e70));
    }

    @Test
    public void setAgeMin() throws Exception {
        Entry e25 = new Entry().setDisplay_name("Dummy").setAge(25);
        Entry e35 = new Entry().setDisplay_name("Dummy").setAge(35);
        Filter filter1 = new Filter().setAgeMin(20);
        Filter filter2 = new Filter().setAgeMin(30);
        Filter filter3 = new Filter().setAgeMin(40);
        assertTrue(filter1.pass(e25));
        assertTrue(filter1.pass(e35));

        assertFalse(filter2.pass(e25));
        assertTrue(filter2.pass(e35));

        assertFalse(filter3.pass(e25));
        assertFalse(filter3.pass(e35));
    }

    @Test
    public void setAgeMax() throws Exception {
        Entry e25 = new Entry().setDisplay_name("Dummy").setAge(25);
        Entry e35 = new Entry().setDisplay_name("Dummy").setAge(35);
        Entry e105 = new Entry().setDisplay_name("Dummy").setAge(105);
        Filter filter1 = new Filter().setAgeMax(20);
        Filter filter2 = new Filter().setAgeMax(30);
        Filter filter3 = new Filter().setAgeMax(40);
        assertFalse(filter1.pass(e25));
        assertFalse(filter1.pass(e35));

        assertTrue(filter2.pass(e25));
        assertFalse(filter2.pass(e35));

        assertTrue(filter3.pass(e25));
        assertTrue(filter3.pass(e35));
        assertFalse(filter3.pass(e105));

        Filter filter0 = new Filter().setAgeMin(30);
        assertFalse(filter0.pass(e25));
        assertTrue(filter0.pass(e35));
        assertTrue(filter0.pass(e105));
    }

    @Test
    public void setHeightMin() throws Exception {
        Entry e140 = new Entry().setDisplay_name("Dummy").setHeight_in_cm(140);
        Entry e160 = new Entry().setDisplay_name("Dummy").setHeight_in_cm(160);
        Filter filter1 = new Filter().setHeightMin(120);
        Filter filter2 = new Filter().setHeightMin(150);
        Filter filter3 = new Filter().setHeightMin(170);
        assertTrue(filter1.pass(e140));
        assertTrue(filter1.pass(e160));

        assertFalse(filter2.pass(e140));
        assertTrue(filter2.pass(e160));

        assertFalse(filter3.pass(e140));
        assertFalse(filter3.pass(e160));
    }

    @Test
    public void setHeightMax() throws Exception {
        Entry e140 = new Entry().setDisplay_name("Dummy").setHeight_in_cm(140);
        Entry e160 = new Entry().setDisplay_name("Dummy").setHeight_in_cm(160);
        Entry e250 = new Entry().setDisplay_name("Dummy").setHeight_in_cm(250);
        Filter filter1 = new Filter().setHeightMax(120);
        Filter filter2 = new Filter().setHeightMax(150);
        Filter filter3 = new Filter().setHeightMax(170);
        assertFalse(filter1.pass(e140));
        assertFalse(filter1.pass(e160));

        assertTrue(filter2.pass(e140));
        assertFalse(filter2.pass(e160));

        assertTrue(filter3.pass(e140));
        assertTrue(filter3.pass(e160));
        assertFalse(filter3.pass(e250));

        Filter filter0 = new Filter().setHeightMin(110);
        assertTrue(filter0.pass(e140));
        assertTrue(filter0.pass(e160));
        assertTrue(filter0.pass(e250));
    }

    @Test
    public void setDistance() throws Exception {

    }

    //TODO: test filter combinations
    @Test
    public void pass() throws Exception {
    }

}