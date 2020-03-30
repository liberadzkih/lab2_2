package edu.iis.mto.search;

public class SecondMockForSequenceSearcher implements SequenceSearcher {
    private int counter =0;

    @Override public SearchResult search(int elem, int[] seq) {
        counter = counter +1;
        return SearchResult.builder().build();
    }

    public int getCounter(){
        return counter;
    }
}
