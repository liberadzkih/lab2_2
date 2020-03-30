package edu.iis.mto.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SequenceSearcherMock implements SequenceSearcher{
    private Set<int[]> passedSequences = new HashSet<>();
    private List<Integer> elements= new ArrayList<>();
    private int searchInvocationCounter=0;

    @Override public SearchResult search(int elem, int[] seq) {
        searchInvocationCounter++;
        elements.add(elem);
        passedSequences.add(seq);
        return SearchResult.builder().build();
    }

    public Set<int[]> getPassedSequences() {
        return passedSequences;
    }

    public   boolean hasSameElements(int[] expectedElements) {
        if (elements.size() != expectedElements.length) {
            return false;
        }
        for (int expectedElement : expectedElements) {
            if (!elements.contains(expectedElement)) {
                return false;
            }
        }
        return true;
    }

    public int getSearchInvocationCounter() {
        return searchInvocationCounter;
    }
}
