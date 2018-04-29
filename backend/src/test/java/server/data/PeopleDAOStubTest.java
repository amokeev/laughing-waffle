package server.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.junit.Test;
import server.pojo.Entry;
import server.Filter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lesha on 27.04.18.
 */
public class PeopleDAOStubTest {
    @Test
    public void get() throws Exception {
        List<Entry> entries = PeopleDAOStub.instance().get(new Filter());
        assertEquals(entries.size(), 25);
    }

    @Test
    public void instance() throws Exception {
        PeopleDAOStub inst1 = PeopleDAOStub.instance();
        PeopleDAOStub inst2 = PeopleDAOStub.instance();
        assertEquals(inst1,inst2);
    }


    /*
    This test helps to veirify JSON->POJO->JSON mapping. We check two things: completness and correctness.
     */
    @Test
    public void decoding() throws Exception {
        PeopleDAOStub inst = PeopleDAOStub.instance();
        List<Entry> entries = inst.get(new Filter());
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        String decoded = gson.toJson(entries);
        BufferedReader br = new BufferedReader(new InputStreamReader(inst.getClass().getClassLoader().getResourceAsStream(PeopleDAOStub.STUB_DATA)));
        JsonElement originalElem = gson.fromJson(br, JsonElement.class).getAsJsonObject().get("matches");
        JsonElement decodedElem = gson.fromJson(decoded, JsonElement.class).getAsJsonArray();

        assertEquals(originalElem, decodedElem);

        //Good for debug - to see the detailed difference.
        //String original = gson.toJson(originalElem);
        //assertEquals(decoded, original);
    }

}