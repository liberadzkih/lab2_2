package edu.iis.mto.search;

public class SequenceSearcherMock implements SequenceSearcher {

    @Override
    public SearchResult search(int elem, int[] seq) {
        if (seq == null || seq.length == 0) {
            throw new IllegalArgumentException("Sequence is empty");
        }

        for (int i = 0; i < seq.length; i++) {
            if (elem == seq[i]) {
                return SearchResult.builder().withFound(true).withPosition(i).build();
            }
        }
        return SearchResult.builder().withPosition(-1).withFound(false).build();
    }
}
