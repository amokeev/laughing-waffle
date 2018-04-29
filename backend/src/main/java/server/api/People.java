package server.api;

import server.Filter;
import server.FilteredEntries;
import server.data.PeopleDAO;
import server.data.PeopleDAOStub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;


/**
 * Created by lesha on 27.04.18.
 */
@Path("people")
public class People {
    private static final Logger log = Logger.getLogger(People.class);
    private static final PeopleDAO peopleDao = PeopleDAOStub.instance();


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public FilteredEntries filter(@QueryParam("has_photo") String hasPhoto,
                                  @QueryParam("contact") String inContact,
                                  @QueryParam("favorite") String favorite,
                                  @QueryParam("compatibility_score_min") double compatibilityScoreMin,
                                  @QueryParam("compatibility_score_max") double compatibilityScoreMax,
                                  @QueryParam("age_min") int ageMin, @QueryParam("age_max") int ageMax,
                                  @QueryParam("height_min") int heightMin, @QueryParam("height_max") int heightMax,
                                  @QueryParam("distance") int distance) {
        log.info("Filtering " + hasPhoto + "," + inContact + "," +  favorite + "," +  compatibilityScoreMin + "-" +  compatibilityScoreMax + "," +  ageMin + "-" +  ageMax + "," +  heightMin + "-" +  heightMax + "," +  distance);
        Filter flt = new Filter();
        if(hasPhoto != null) flt.setPhoto(Boolean.parseBoolean(hasPhoto));
        if(inContact != null) flt.setContact(Boolean.parseBoolean(inContact));
        if(favorite !=null) flt.setFavorite(Boolean.parseBoolean(favorite));
        if(compatibilityScoreMin >0.0) flt.setScoreMin(compatibilityScoreMin);
        if(compatibilityScoreMax >0.0) flt.setScoreMax(compatibilityScoreMax);
        if(ageMin !=0) flt.setAgeMin(ageMin);
        if(heightMin !=0) flt.setHeightMin(heightMin);
        if(heightMax !=0) flt.setHeightMax(heightMax);
        if(distance !=0) flt.setDistance(distance);

        return new FilteredEntries().setMatched(peopleDao.get(flt));

    }


}
