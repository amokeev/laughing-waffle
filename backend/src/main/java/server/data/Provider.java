package server.data;

/**
 * Created by lesha on 30.04.18.
 */
public class Provider {
    /*
    Differnetiate this logic depending on implementations available or application settings.
     */
    public static PeopleDAO getPeopleDAO() {
        return PeopleDAOStub.instance();
    }
}
