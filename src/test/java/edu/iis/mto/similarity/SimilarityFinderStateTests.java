package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityFinderStateTests {

    @Test
    void calculateJackardSimilarity_2EmptyArrays_Return0() {
        SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock((elem, seq) -> SearchResult.builder().build());
        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcherMock);
        int[] seq1 = {};
        int[] seq2 = {};
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(0, sequenceSearcherMock.invocationList.size());
    }

    @Test
    void calculateJackardSimilarity_SecondArrayEmpty_Return3() {
        SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock((elem, seq) -> SearchResult.builder().build());
        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcherMock);
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {};
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(3, sequenceSearcherMock.invocationList.size());
    }


    @Test
    void calculateJackardSimilarity_FirstArrayEmpty_Return0() {
        SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock((elem, seq) -> SearchResult.builder().build());
        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcherMock);
        int[] seq1 = {};
        int[] seq2 = {1, 2, 3};
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(0, sequenceSearcherMock.invocationList.size());
    }
}