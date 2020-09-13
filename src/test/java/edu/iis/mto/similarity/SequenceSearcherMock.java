package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherMock implements SequenceSearcher {

    private int searchCalls = 0;
    
    @Override
    public SearchResult search(int elem, int[] seq) {
        searchCalls++;
        return SearchResult.builder().build();
    }

    public int getSearchCalls() {
        return searchCalls;
    }
}
