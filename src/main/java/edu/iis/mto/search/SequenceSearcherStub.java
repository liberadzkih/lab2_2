package edu.iis.mto.search;

import java.util.Arrays;
import java.util.OptionalInt;

public class SequenceSearcherStub implements SequenceSearcher {
    @Override
    public SearchResult search(int elem, int[] seq) {

        SearchResult.Builder builder = SearchResult.builder();

        OptionalInt result = Arrays.stream(seq).filter(i -> seq[i] == elem).findFirst();

        builder.withFound(result.isPresent());
        if(result.isPresent()) builder.withPosition(result.getAsInt());

        return builder.build();
    }
}
