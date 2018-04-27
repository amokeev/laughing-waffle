package integtest.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import server.pojo.Entry;
import server.FilteredEntries;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * Created by lesha on 27.04.18.
 */
public class PeopleTest {
    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8081/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }
    @Test
    public void simpleGet() {
        FilteredEntries response = given()
                .spec(spec)
                .when()
                .get("people")
                .then()
                .statusCode(200).extract().as(FilteredEntries.class, ObjectMapperType.GSON);

        assertEquals(25, response.getMatched().size());

        Entry first = response.getMatched().get(0);

        assertEquals("Corporate Lawyer", first.getJob_title() );
        assertEquals(153, first.getHeight_in_cm());

        Entry last = response.getMatched().get(24);

        assertEquals("Project Manager", last.getJob_title());
        assertEquals(166,last.getHeight_in_cm());
    }


    @Test
    public void entityCompleteness() {
        String golden = "{\n" +
                "            \"age\": 41,\n" +
                "            \"city\": {\n" +
                "                \"lat\": 53.801277,\n" +
                "                \"lon\": -1.548567,\n" +
                "                \"name\": \"Leeds\"\n" +
                "            },\n" +
                "            \"compatibility_score\": 0.76,\n" +
                "            \"contacts_exchanged\": 2,\n" +
                "            \"display_name\": \"Caroline\",\n" +
                "            \"favourite\": true,\n" +
                "            \"height_in_cm\": 153,\n" +
                "            \"job_title\": \"Corporate Lawyer\",\n" +
                "            \"main_photo\": \"http://thecatapi.com/api/images/get?format=src&type=gif\",\n" +
                "            \"religion\": \"Atheist\"\n" +
                "        }";
        FilteredEntries response = given()
                .spec(spec)
                .when()
                .get("people")
                .then()
                .statusCode(200).extract().as(FilteredEntries.class, ObjectMapperType.GSON);

        Entry first = response.getMatched().get(0);

        Gson gson = new Gson();
        JsonElement originalElem = gson.fromJson(golden, JsonElement.class).getAsJsonObject();
        JsonElement returnedElem = gson.fromJson(gson.toJson(first), JsonElement.class).getAsJsonObject();

        assertEquals(originalElem, returnedElem);
    }
}

