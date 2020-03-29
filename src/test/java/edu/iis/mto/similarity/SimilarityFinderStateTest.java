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
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, seq2), 0.2, 0.01);
    }

    @Test
    void calculateJackardSimilarity_firstSequenceHasElements_secondSequenceIsEmpty() {
        assertEquals(similarityFinder.calculateJackardSimilarity(seq1, empty), 0, 0.01);
    }

    @Test
    void calculateJackardSimilarity_firstSequenceIsEmpty_secondSequenceHasElements() {
        assertEquals(similarityFinder.calculateJackardSimilarity(empty, seq2), 0, 0.01);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesAreEmpty() {
        assertEquals(similarityFinder.calculateJackardSimilarity(empty, empty), 1, 0.01);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesHaveElements_noMatchingElements() {
        assertEquals(similarityFinder.calculateJackardSimilarity(seq2, seq3), 0, 0.01);
    }

    @Test
    void calculateJackardSimilarity_sequencesAreTheSame() {
        assertEquals(similarityFinder.calculateJackardSimilarity(seq2, seq2), 1, 0.01);
    }

}