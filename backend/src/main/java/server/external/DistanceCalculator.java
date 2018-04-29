package server.external;


import server.pojo.City;

import static java.lang.Math.*;


/**
 * Created by lesha on 27.04.18.
 */
public class DistanceCalculator {

    //TODO: Make use of distance by roads.
    //NOTE: It's my personal key, I'll rewoke it in a few weeks.
    //public String API_KEY = "AIzaSyBw24o3V54SLtNdpzPgoH4lk9ldUXQFKPI";

    /*
    Harvesine formula. See https://en.wikipedia.org/wiki/Haversine_formula
     */
    private double haversine(double lat1, double lat2, double lon1, double lon2) {
        int r = 6371; //Earth radius
        return 2*r*asin(sqrt(pow(sin(0.5*toRadians(lat1-lat2)),2)+cos(toRadians(lon1))*cos(toRadians(lon2))*pow(sin(0.5*toRadians(lon1-lon2)),2)));
    }

    public double get(City city1, City city2) {
        return haversine(city1.getLat(), city2.getLat(), city1.getLon(), city2.getLon());
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
