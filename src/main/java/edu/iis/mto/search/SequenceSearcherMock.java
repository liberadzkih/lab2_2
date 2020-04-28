package edu.iis.mto.search;

import java.util.Arrays;
import java.util.OptionalInt;

public class SequenceSearcherMock implements SequenceSearcher {

    static int count = 0;

    @Override
    public SearchResult search(int elem, int[] seq) {
        count++;
        return SearchResult.builder().withFound(true).withPosition(0).build();
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        SequenceSearcherMock.count = count;
    }

}
