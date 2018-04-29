package server.data;

import server.pojo.Entry;
import server.Filter;

import java.util.List;

/**
 * Created by lesha on 27.04.18.
 */
public interface PeopleDAO {
    List<Entry> get();
    List<Entry> get(Filter filter);
}
