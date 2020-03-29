package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherDubler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest {
    int[] testseqEmpty = {};
    int[] testseqOneElement = {1};
    int[] testseqTenElements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    SimilarityFinder similarityFinder;

    @BeforeEach
    void setUp() {
        //similarityFinder = new SimilarityFinder();
    }

    @Test
    void calculateJackardSimilarity_bothSeqAreEmpty() {
        assertEquals(1.0d, similarityFinder.calculateJackardSimilarity(testseqEmpty, testseqEmpty));
    }

    @Test
    void calculateJackardSimilarity_seq1IsEmpty() {
        assertEquals(0.0d,similarityFinder.calculateJackardSimilarity(testseqEmpty,testseqTenElements));
    }

    @Test
    void calculateJackardSimilarity_seq2IsEmpty() {
        assertEquals(0.0d,similarityFinder.calculateJackardSimilarity(testseqTenElements,testseqEmpty));
    }
}