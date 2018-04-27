package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import server.pojo.Entry;

/**
 * Created by lesha on 27.04.18.
 */
public class Context {
    private static Entry me;
    public static Entry me() {
        if(me == null) {
            String meText = " {\n" +
                    "      \"display_name\": \"Angie\",\n" +
                    "      \"age\": 50,\n" +
                    "      \"job_title\": \"Accountant\",\n" +
                    "      \"height_in_cm\": 153,\n" +
                    "      \"city\": {\n" +
                    "        \"name\": \"Ayr\",\n" +
                    "        \"lat\": 55.458565,\n" +
                    "        \"lon\": -4.629179\n" +
                    "      },\n" +
                    "      \"main_photo\": \"http://thecatapi.com/api/images/get?format=src&type=gif\",\n" +
                    "      \"compatibility_score\": 0.93,\n" +
                    "      \"contacts_exchanged\": 8,\n" +
                    "      \"favourite\": true,\n" +
                    "      \"religion\": \"Atheist\"\n" +
                    "    }";
            me = new Gson().fromJson(meText, Entry.class);
        }
        return me;
    }
}
