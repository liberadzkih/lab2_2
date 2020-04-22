package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest {

    private int[] empty = {};
    private int[] seq1 = {1};
    private int[] seq2 = {1, 2, 3, 4, 5};
    private int[] seq3 = {10, 9, 8, 7, 6};
    private SimilarityFinder similarityFinder;
    private SequenceSearcher stub;

    @BeforeEach
    void intit() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
    }


    @Test
    void calculateJackardSimilarity_bothSequencesHaveElements_thereAreSameElements() {
        stub = (elem, seq) -> SearchResult.builder().withFound(true).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(0.2, similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.01);
    }

    @Test
    void calculateJackardSimilarity_firstSequenceHasElements_secondSequenceIsEmpty() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(0, similarityFinder.calculateJackardSimilarity(seq1, empty), 0.01);
    }

    @Test
    void calculateJackardSimilarity_firstSequenceIsEmpty_secondSequenceHasElements() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(0, similarityFinder.calculateJackardSimilarity(empty, seq2), 0.01);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesAreEmpty() {
        stub = (elem, seq) -> SearchResult.builder().withFound(false).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(1, similarityFinder.calculateJackardSimilarity(empty, empty), 0.01);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesHaveElements_noMatchingElements() {
        stub = (elem, seq) -> SearchResult.builder().withFound(true).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(1, similarityFinder.calculateJackardSimilarity(seq2, seq3), 0.01);
    }

    @Test
    void calculateJackardSimilarity_sequencesAreTheSame() {
        stub = (elem, seq) -> SearchResult.builder().withFound(true).build();
        similarityFinder = new SimilarityFinder(stub);
        assertEquals(1, similarityFinder.calculateJackardSimilarity(seq2, seq2), 0.01);
    }

}