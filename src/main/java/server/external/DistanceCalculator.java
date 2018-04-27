package server.external;


import server.pojo.City;

/**
 * Created by lesha on 27.04.18.
 */
public class DistanceCalculator {
    //NOTE: It's my personal key, I'll rewoke it in a few weeks.
    public String API_KEY = "AIzaSyBw24o3V54SLtNdpzPgoH4lk9ldUXQFKPI";

    public double get(City myCity, City targetCity) {
        return Double.MAX_VALUE;
    }

    public static DistanceCalculator instance() {

        if (instance == null) {
            synchronized(DistanceCalculator.class) {
                if (instance == null)
                    instance = new DistanceCalculator();
            }
        }
        return instance;
    }

    private static volatile DistanceCalculator instance;


}
