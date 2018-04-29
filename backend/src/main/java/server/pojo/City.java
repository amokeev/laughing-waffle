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

    public String toString() {
        return name + ":" + lat + ":" +lon;
    }
    //"name": "London",
    private String name;
    //"lat": 51.509865,
    private double lat;
    // "lon": -0.118092
    private double lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (Double.compare(city.lat, lat) != 0) return false;
        if (Double.compare(city.lon, lon) != 0) return false;
        return name != null ? name.equals(city.name) : city.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
