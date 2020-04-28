package edu.iis.mto.search;

import java.util.Arrays;
import java.util.OptionalInt;

public class SequenceSearcherMock implements SequenceSearcher {

    int elemParam;
    int[] seqParam;

    static int count = 0;

    @Override
    public SearchResult search(int elem, int[] seq) {
//
//        count++;
//
//        elemParam = elem;
//        seqParam = seq;
//
//        SearchResult.Builder builder = SearchResult.builder();
//
//        OptionalInt result = Arrays.stream(seq, 0, seq.length).filter(var -> var == elem).findFirst();
//
//        builder.withFound(result.isPresent());
//        if(result.isPresent()) builder.withPosition(result.getAsInt());
//
//        return builder.build();
        return SearchResult.builder().withFound(true).withPosition(0).build();
    }

    public int getElemParam() {
        return elemParam;
    }

    public int[] getSeqParam() {
        return seqParam;
    }

    public void setElemParam(int elemParam) {
        this.elemParam = elemParam;
    }

    public void setSeqParam(int[] seqParam) {
        this.seqParam = seqParam;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        SequenceSearcherMock.count = count;
    }

}
