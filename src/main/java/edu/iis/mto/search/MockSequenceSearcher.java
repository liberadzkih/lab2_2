package edu.iis.mto.search;

import java.util.Arrays;
import java.util.TreeMap;

public class MockSequenceSearcher implements SequenceSearcher {
    public int amountOfInvocations;
    private TreeMap<Integer, int[]> parametersCalled;

    public MockSequenceSearcher() {
        amountOfInvocations = 0;
        parametersCalled = new TreeMap<>();
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        amountOfInvocations++;
        parametersCalled.put(elem, seq);
        return SearchResult.builder().build();
    }

    public boolean verifyParameter(int elem, int[] seq) {
        if (!parametersCalled.containsKey(elem)) {
            return false;
        }
        return Arrays.equals(parametersCalled.get(elem), seq);
    }
}