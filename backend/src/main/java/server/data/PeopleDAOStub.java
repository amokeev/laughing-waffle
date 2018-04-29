package server.data;

import com.google.gson.reflect.TypeToken;
import server.pojo.Entry;
import server.Filter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;


/**
 * Created by lesha on 27.04.18.
 */
public class PeopleDAOStub implements PeopleDAO {
    public static String STUB_DATA = "dummydata.json";
    private List<Entry> entries;


    private List<Entry> filter(List<Entry> entries, Filter f) {
        ArrayList<Entry> result = new ArrayList<>();
        for(Entry e:entries) {
            if(f.pass(e)) result.add(e);
        }
        return result;
    }

    public List<Entry> get() {
        return entries;
    }

    public List<Entry> get(Filter filter) {
        return filter(entries, filter);
    }


    private PeopleDAOStub() {
        InputStream jsonIS = this.getClass().getClassLoader().getResourceAsStream(STUB_DATA);
        BufferedReader br = new BufferedReader(new InputStreamReader(jsonIS));
        Gson gson = new Gson();
        //TODO: The below code can be simplified by introducing extra class Matches, that describes full payload
        JsonArray jo = gson.fromJson(br, JsonElement.class).getAsJsonObject().getAsJsonArray("matches");
        this.entries = gson.fromJson(jo, new TypeToken<List<Entry>>(){}.getType());
    }

    public static PeopleDAOStub instance() {

        if (instance == null) {
            synchronized(PeopleDAOStub.class) {
                if (instance == null)
                    instance = new PeopleDAOStub();
            }
        }
        return instance;
    }

    private static volatile PeopleDAOStub instance;
}
