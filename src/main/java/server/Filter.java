package server;

import server.external.DistanceCalculator;
import server.pojo.City;
import server.pojo.Entry;

/**
 * Created by lesha on 27.04.18.
 */
public class Filter {

    BooleanCriteria photo = null;
    BooleanCriteria contact = null;
    BooleanCriteria favorite = null;
    RangeCriteria score = null;
    RangeCriteria age = null;
    RangeCriteria height = null;
    RangeCriteria distance = null;


    public Filter() {
    }

    public Filter setPhoto(boolean val) {
        photo = new BooleanCriteria(val);
        return this;
    }

    public Filter setContact(boolean val) {
        contact = new BooleanCriteria(val);
        return this;
    }

    public Filter setFavorite(boolean val) {
        favorite = new BooleanCriteria(val);
        return this;
    }

    public Filter setScoreMin(double val) {
        if(score == null) score = new RangeCriteria(0.099, 0.991);
        score.setMin(val);
        return this;
    }

    public Filter setScoreMax(double val) {
        if(score == null) score = new RangeCriteria(0.099, 0.991);
        score.setMax(val);
        return this;
    }

    public Filter setAgeMin(int val) {
        if(age == null) age = new RangeCriteria(18, RangeCriteria.max);
        age.setMin(val);
        return this;
    }

    public Filter setAgeMax(int val) {
        if(age == null) age = new RangeCriteria(18, RangeCriteria.max);
        age.setMax(val);
        if(val >=95) age.setMax(RangeCriteria.max);
        return this;
    }

//135 .. 210
    public Filter setHeightMin(int val) {
        if(height == null) height = new RangeCriteria(135, RangeCriteria.max);
        height.setMin(val);
        return this;
    }

    public Filter setHeightMax(int val) {
        System.out.println("SHM" + val);
        if(height == null) height = new RangeCriteria(135, RangeCriteria.max);
        height.setMax(val);
        return this;
    }
    public Filter setDistance(int dst) {
        distance = new RangeCriteria(0,RangeCriteria.max);
        if (dst > 30) distance.setMax(dst);
        if (dst >= 300) distance.setMax(RangeCriteria.max);
        return this;
    }

    public boolean pass(Entry entry) {
        if (photo != null && !photo.pass(entry.hasPhoto())) return false;
        if (contact != null && !contact.pass(entry.inContact())) return false;
        if (favorite != null && !favorite.pass(entry.favorite())) return false;
        if (score != null && !score.pass(entry.score())) return false;
        if (age != null && !age.pass(entry.age())) return false;
        if (height != null && !height.pass(entry.getHeight_in_cm())) return false;
//        if (distance != null && !distance.pass(entry.get))
        return true;
    }
}

class DistanceCriteria extends RangeCriteria {
    City myCity;

    public DistanceCriteria(City myCity) {
        this.myCity = myCity;
    }
    public boolean pass(City targetCity) {
        double distance = DistanceCalculator.instance().get(myCity, targetCity);
        return pass(distance);
    }
}

class RangeCriteria {
    public static final double min = Double.MIN_VALUE;
    public static final double max = Double.MAX_VALUE;
    double unbeatableMin;
    double unbeatableMax;
    double minValue;
    double maxValue;

    public RangeCriteria() {
        this(min,max);
    }
    public RangeCriteria(double unbeatableMin, double unbeatableMax) {
        this.unbeatableMin = unbeatableMin;
        this.unbeatableMax = unbeatableMax;
        this.minValue = unbeatableMin;
        this.maxValue = unbeatableMax;
    }

    public RangeCriteria setMin(double val) {
        if (val < unbeatableMin) {
            this.minValue = unbeatableMin;
        } else {
            this.minValue = val;
        }
        return this;
    }

    public RangeCriteria setMax(double val) {
        if (val > unbeatableMax) {
            this.maxValue = unbeatableMax;
        } else {
            this.maxValue = val;
        }
        return this;
    }

    public boolean pass(double val) {
        System.out.println("RANGE CHECK " + val);
        System.out.println( minValue  +":" + maxValue);
        return val >= minValue && val <=maxValue;
    }
}


class BooleanCriteria {
    boolean target;
    public BooleanCriteria(boolean target) {
        this.target = target;
    }

    public boolean pass(boolean check) {
        return target == check;
    }
}