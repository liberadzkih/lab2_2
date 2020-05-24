package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTests {



    @Test
    void calculateJackardSimilarity_2EmptyArrays_Return1() {
        int[] seq1 = {};
        int[] seq2 = {};
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> SearchResult.builder().build());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(1, result);
    }

    @Test
    void calculateJackardSimilarity_2SameArrays_Return1() {
        SimilarityFinder similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2] || key == seq[3])
                return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().withFound(false).build();
        });
        int[] seq1 = {1, 2, 3, 4};
        int[] seq2 = {1, 2, 3, 4};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(1, result);
    }

    @Test
    void calculateJackardSimilarity_2SameArraysDifferentOrder_Return1() {
        SimilarityFinder similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2] || key == seq[3] || key == seq[4])
                return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().withFound(false).build();
        });
        int[] seq1 = {1, 2, 3, 4, 5};
        int[] seq2 = {2, 4, 5, 1, 3};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(1, result);
    }

    @Test
    void calculateJackardSimilarity_2DifferentArrays_Return0() {
        SimilarityFinder similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2] || key == seq[3])
                return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().withFound(false).build();
        });
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {6, 7, 8, 9, 10};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(0, result);
    }

    @Test
    void calculateJackardSimilarity_2HalfDifferentArrays_Return06() {
        SimilarityFinder similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2] || key == seq[3])
                return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().withFound(false).build();
        });
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {1, 2, 3, 4, 5};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(0.6, result);
    }

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