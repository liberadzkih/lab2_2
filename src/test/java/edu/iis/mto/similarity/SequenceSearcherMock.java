package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.ArrayList;
import java.util.List;

public class SequenceSearcherMock implements SequenceSearcher {

    List<Invocation> invocationList;
    SequenceSearcher sequenceSearcher;

    public SequenceSearcherMock(SequenceSearcher sequenceSearcher) {
        this.sequenceSearcher = sequenceSearcher;
        this.invocationList = new ArrayList<>();
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        invocationList.add(new Invocation(elem, seq));
        return sequenceSearcher.search(elem, seq);
    }

    private static class Invocation {
        int elem;
        int[] seq;

        public Invocation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }
    }
}



