package edu.iis.mto.search;

import java.util.Arrays;
import java.util.TreeMap;

class MockSequenceSearcher implements SequenceSearcher {
    private int amountOfInvocations;
    private TreeMap<Integer, int[]> parametersCalled;

    MockSequenceSearcher() {
        amountOfInvocations = 0;
        parametersCalled = new TreeMap<>();
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        amountOfInvocations++;
        parametersCalled.put(elem, seq);
        return SearchResult.builder().build();
    }

    boolean verifyParameter(int elem, int[] seq) {
        if (!parametersCalled.containsKey(elem)) {
            return false;
        }
        return Arrays.equals(parametersCalled.get(elem), seq);
    }
}