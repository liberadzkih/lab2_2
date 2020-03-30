package edu.iis.mto.search;

public class MockForSequenceSearcher implements SequenceSearcher {

    @Override public SearchResult search(int elem, int[] seq) {
        SearchResult.Builder object = SearchResult.builder();
        for(int i=0;i<seq.length;i++){
            if(seq[i]==elem)
                return object.withFound(true).withPosition(i).build();
        }
        return object.withFound(false).withPosition(-1).build();
    }
}
