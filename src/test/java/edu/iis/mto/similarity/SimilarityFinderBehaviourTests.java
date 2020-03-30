package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTests {

    SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> {
        for (int i = 0; i < seq.length; i++) {
            if (elem == seq[i]) {
                return SearchResult.builder().withFound(true).build();
            }
        }
        return SearchResult.builder().withFound(false).build();
    });

    @Test
    void calculateJackardSimilarity_2EmptyArrays_Return1() {
        int[] seq1 = {};
        int[] seq2 = {};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(1, result);
    }

    @Test
    void calculateJackardSimilarity_2SameArrays_Return1() {
        int[] seq1 = {1, 2, 3, 4, 5};
        int[] seq2 = {1, 2, 3, 4, 5};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(1, result);
    }

    @Test
    void calculateJackardSimilarity_2SameArraysDifferentOrder_Return1() {
        int[] seq1 = {1, 2, 3, 4, 5};
        int[] seq2 = {2, 4, 5, 1, 3};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(1, result);
    }

    @Test
    void calculateJackardSimilarity_2DifferentArrays_Return0() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {6, 7, 8, 9, 10};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(0, result);
    }

    @Test
    void calculateJackardSimilarity_2HalfDifferentArrays_Return06() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {1, 2, 3, 4, 5};
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(0.6, result);
    }

}