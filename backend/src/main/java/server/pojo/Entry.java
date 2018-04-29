package server.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lesha on 27.04.18.
 */
public class Entry {
    // "display_name": "Maria",
    @SerializedName("display_name")
    private String display_name;
    // "age": 43,
    @SerializedName("age")
    private int age;
    // "job_title": "CEO",
    @SerializedName("job_title")
    private String job_title;
    //"height_in_cm": 175,
    @SerializedName("height_in_cm")
    private int height_in_cm;
    @SerializedName("city")
    private City city;
    // "main_photo": "http://thecatapi.com/api/images/get?format=src&type=gif",
    @SerializedName("main_photo")
    private String main_photo;
    //"compatibility_score": 0.88,
    @SerializedName("compatibility_score")
    private double compatibility_score;
    //"contacts_exchanged": 0,
    @SerializedName("contacts_exchanged")
    private int contacts_exchanged;
    //"favourite": false,
    @SerializedName("favourite")
    private boolean favourite;
    // "religion": "Christian"
    @SerializedName("religion")
    private String religion;


    //Convenience methods below

    public boolean hasPhoto() {
        return main_photo != null;
    }

    public boolean inContact() {
        return contacts_exchanged != 0;
    }

    public boolean favorite() {
        return favourite;
    }

    public double score() {
        return compatibility_score;
    }

    public double age() {
        return age;
    }

    public String photoURI() {
        return main_photo;
    }

    //Service methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (age != entry.age) return false;
        if (height_in_cm != entry.height_in_cm) return false;
        if (Double.compare(entry.compatibility_score, compatibility_score) != 0) return false;
        if (contacts_exchanged != entry.contacts_exchanged) return false;
        if (favourite != entry.favourite) return false;
        if (display_name != null ? !display_name.equals(entry.display_name) : entry.display_name != null) return false;
        if (job_title != null ? !job_title.equals(entry.job_title) : entry.job_title != null) return false;
        if (city != null ? !city.equals(entry.city) : entry.city != null) return false;
        if (main_photo != null ? !main_photo.equals(entry.main_photo) : entry.main_photo != null) return false;
        return religion != null ? religion.equals(entry.religion) : entry.religion == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = display_name != null ? display_name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (job_title != null ? job_title.hashCode() : 0);
        result = 31 * result + height_in_cm;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (main_photo != null ? main_photo.hashCode() : 0);
        temp = Double.doubleToLongBits(compatibility_score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + contacts_exchanged;
        result = 31 * result + (favourite ? 1 : 0);
        result = 31 * result + (religion != null ? religion.hashCode() : 0);
        return result;
    }

    //Getters and setters
    public String getDisplay_name() {
        return display_name;
    }

    public Entry setDisplay_name(String display_name) {
        this.display_name = display_name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Entry setAge(int age) {
        this.age = age;
        return this;
    }

    public String getJob_title() {
        return job_title;
    }

    public Entry setJob_title(String job_title) {
        this.job_title = job_title;
        return this;
    }

    public int getHeight_in_cm() {
        return height_in_cm;
    }

    public Entry setHeight_in_cm(int height_in_cm) {
        this.height_in_cm = height_in_cm;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Entry setCity(City city) {
        this.city = city;
        return this;
    }

    public String getMain_photo() {
        return main_photo;
    }

    public Entry setMain_photo(String main_photo) {
        this.main_photo = main_photo;
        return this;
    }

    public double getCompatibility_score() {
        return compatibility_score;
    }

    public Entry setCompatibility_score(double compatibility_score) {
        this.compatibility_score = compatibility_score;
        return this;
    }

    public int getContacts_exchanged() {
        return contacts_exchanged;
    }

    public Entry setContacts_exchanged(int contacts_exchanged) {
        this.contacts_exchanged = contacts_exchanged;
        return this;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public Entry setFavourite(boolean favourite) {
        this.favourite = favourite;
        return this;
    }

    public String getReligion() {
        return religion;
    }

    public Entry setReligion(String religion) {
        this.religion = religion;
        return this;
    }
}
