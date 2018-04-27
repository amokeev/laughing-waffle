package server.pojo;

/**
 * Created by lesha on 27.04.18.
 */
public class City {
    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public City setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public City setLon(double lon) {
        this.lon = lon;
        return this;
    }

    //"name": "London",
    private String name;
    //"lat": 51.509865,
    private double lat;
    // "lon": -0.118092
    private double lon;
}
