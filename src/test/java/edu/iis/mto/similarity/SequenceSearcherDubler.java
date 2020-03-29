package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.ArrayList;
import java.util.List;

public class SequenceSearcherDubler implements SequenceSearcher {

    @Override
    public SearchResult search(int elem, int[] seq) {
        behaviuorInformationList.add(new BehaviuorInformation(elem, seq));
        return null;
    }

    private static class BehaviuorInformation {
        int elem;
        int[] seq;

        public BehaviuorInformation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    private List<BehaviuorInformation> behaviuorInformationList = new ArrayList<>();

    public int getSearchInvocationAmout() {
        return behaviuorInformationList.size();
    }

    public void verifySearchInvocation(int elem, int[] seq) {
        if (behaviuorInformationList.contains(new BehaviuorInformation(elem, seq))) {
            throw new AssertionError("Expected method execution didn't happen or was not registered!");
        }
    }
}
