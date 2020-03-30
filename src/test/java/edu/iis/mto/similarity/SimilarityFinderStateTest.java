package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest {
    int[] testseqEmpty = {};
    int[] testseqOneElement = {1};
    int[] testseqDifferentElement = {41};
    int[] testseqTenElements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    SimilarityFinder similarityFinder;
    SequenceSearcher stub;



    @Test
    void calculateJackardSimilarity_bothSeqAreEmpty() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(1.0d, similarityFinder.calculateJackardSimilarity(testseqEmpty, testseqEmpty));
    }

    @Test
    void calculateJackardSimilarity_seq1IsEmpty() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(0.0d, similarityFinder.calculateJackardSimilarity(testseqEmpty, testseqTenElements));
    }

    @Test
    void calculateJackardSimilarity_seq2IsEmpty() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(0.0d, similarityFinder.calculateJackardSimilarity(testseqTenElements, testseqEmpty));
    }

    @Test
    void calculateJackardSimilarity_oneElementMatches() {
        stub = (elem, seq) -> {
            for (int i : seq) {
                if (i == elem)
                    return SearchResult.builder().withFound(true).build();
            }
            return SearchResult.builder().withFound(false).build();
        };
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(0.1d, similarityFinder.calculateJackardSimilarity(testseqOneElement, testseqTenElements));
        assertEquals(0.1d, similarityFinder.calculateJackardSimilarity(testseqTenElements, testseqOneElement));
    }

    @Test
    void calculateJackardSimilarity_allElementsMatch() {
        stub = (elem, seq) -> SearchResult.builder().withFound(true).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(1.0d, similarityFinder.calculateJackardSimilarity(testseqOneElement, testseqOneElement));
        assertEquals(1.0d, similarityFinder.calculateJackardSimilarity(testseqTenElements, testseqTenElements));
    }

    @Test
    void calculateJackardSimilarity_noMatchingElements() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(0.0d,similarityFinder.calculateJackardSimilarity(testseqOneElement,testseqDifferentElement));
        assertEquals(0.0d,similarityFinder.calculateJackardSimilarity(testseqDifferentElement,testseqOneElement));
        assertEquals(0.0d,similarityFinder.calculateJackardSimilarity(testseqTenElements,testseqDifferentElement));
        assertEquals(0.0d,similarityFinder.calculateJackardSimilarity(testseqDifferentElement,testseqTenElements));
    }
}