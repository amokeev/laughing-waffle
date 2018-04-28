package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import server.dao.PeopleDAOStub;
import server.pojo.Entry;

/**
 * Created by lesha on 27.04.18.
 */
/*
This class is used for tests. Has to be replaced with a real, security related context in the app.
 */
public class Context {
    private Entry me;

    //By default, we assume that 17th user is logged in.
    private Context() {
        me = PeopleDAOStub.instance().get().get(17);
    }


    public Entry me() {
        return me;
    }

    public Context setMe(Entry me) {
        this.me = me;
        return this;
    }

    public static Context instance() {
        if(instance == null) {
            synchronized(Context.class) {
                if (instance == null)
                    instance = new Context();
            }
        }
        return instance;
    }

    private static Context instance;
}
