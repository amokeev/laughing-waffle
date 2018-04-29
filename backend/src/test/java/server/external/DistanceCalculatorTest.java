package server.external;

import org.junit.Test;
import server.pojo.City;

import static org.junit.Assert.*;

/**
 * Created by lesha on 28.04.18.
 */
public class DistanceCalculatorTest {
    @Test
    public void get() throws Exception {
        DistanceCalculator dc = DistanceCalculator.instance();

        City city1 = new City().setLat(0).setLon(0);
        City city2 = new City().setLat(45).setLon(45);

        assertEquals(dc.get(city1,city2), dc.get(city2,city1),0.1);

        assertEquals(6672, dc.get(city1,city2),0.5);

        City city3 = new City().setLat(125).setLon(125);
        assertEquals(7872, dc.get(city1,city3),0.5);

        assertEquals(6607, dc.get(city2,city3),0.5);

        City city4 = new City().setLat(-125).setLon(-125);
        assertEquals(7872, dc.get(city1,city4),0.5);

        City city5 = new City().setLat(-45).setLon(-45);
        assertEquals(6607, dc.get(city4,city5),0.5);

    }

    @Test
    public void instance() throws Exception {
        DistanceCalculator inst1 = DistanceCalculator.instance();
        DistanceCalculator inst2 = DistanceCalculator.instance();
        assertEquals(inst1,inst2);
    }

}