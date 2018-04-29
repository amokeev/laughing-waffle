package server;

import server.pojo.Entry;

import java.util.List;

/**
 * Created by lesha on 27.04.18.
 */
public class FilteredEntries {
    public List<Entry> getMatched() {
        return matched;
    }

    public FilteredEntries setMatched(List<Entry> matched) {
        this.matched = matched;
        return this;
    }

    private List<Entry> matched;

}
