package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequenceSearcherDubler implements SequenceSearcher {

    public SequenceSearcherDubler() {
        this.behaviuorInformationList = new ArrayList<>();
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        behaviuorInformationList.add(new BehaviuorInformation(elem, seq));
        return SearchResult.builder().build();
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
            if(this == obj) return true;
            else if (obj == null || getClass() != obj.getClass()) return false;
            BehaviuorInformation that = (BehaviuorInformation) obj;
            return elem == that.elem && Arrays.equals(seq,that.seq);
        }
    }

    private List<BehaviuorInformation> behaviuorInformationList;

    public int getSearchInvocationAmout() {
        return behaviuorInformationList.size();
    }

    public void verifySearchInvocation(int elem, int[] seq) {
        if (!behaviuorInformationList.contains(new BehaviuorInformation(elem, seq))) {
            throw new AssertionError("Expected method execution didn't happen or was not registered!");
        }
    }
}
