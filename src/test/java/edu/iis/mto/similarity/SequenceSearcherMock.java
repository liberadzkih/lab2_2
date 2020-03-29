package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherMock implements SequenceSearcher {

    @Override
    public SearchResult search(int elem, int[] seq) {
        SearchResult.Builder builder = SearchResult.builder();
        builder.withFound(false);
        builder.withPosition(-1);

        int position = 0;
        for (int key : seq) {
            if (elem == key) {
                builder.withFound(true);
                builder.withPosition(position);
                break;
            }
            position++;
        }
        return builder.build();
    }
}
