package edu.iis.mto.similarity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest {

    private int[] empty = {};
    private int[] seq1 = {1};
    private int[] seq2 = {1, 2, 3, 4, 5};
    private int[] seq3 = {10, 9, 8, 7, 6};
    private SimilarityFinder similarityFinder;


    @Test
    void calculateJackardSimilarity_bothSequencesHaveElements_thereAreSameElements() {
        assertEquals(0.2, similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.01);
    }

    @Test
    void calculateJackardSimilarity_firstSequenceHasElements_secondSequenceIsEmpty() {
        assertEquals(0, similarityFinder.calculateJackardSimilarity(seq1, empty), 0.01);
    }

    @Test
    void calculateJackardSimilarity_firstSequenceIsEmpty_secondSequenceHasElements() {
        assertEquals(0, similarityFinder.calculateJackardSimilarity(empty, seq2), 0.01);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesAreEmpty() {
        assertEquals(1, similarityFinder.calculateJackardSimilarity(empty, empty), 0.01);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesHaveElements_noMatchingElements() {
        assertEquals(1, similarityFinder.calculateJackardSimilarity(seq2, seq3), 0.01);
    }

    @Test
    void calculateJackardSimilarity_sequencesAreTheSame() {
        assertEquals(1, similarityFinder.calculateJackardSimilarity(seq2, seq2), 0.01);
    }

}