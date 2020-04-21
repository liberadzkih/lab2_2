package edu.iis.mto.similarity;


import edu.iis.mto.search.MockSequenceSearcher;
import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimilarityFinderBehaviourTest {
    private int[] empty = {};
    private int[] seq1 = {1};
    private int[] seq2 = {1, 2, 3, 4, 5};
    private int[] seq3 = {10, 9, 8, 7, 6};
    SimilarityFinder similarityFinder;
    MockSequenceSearcher mockSequenceSearcher;

    @BeforeEach
    void init() {
        mockSequenceSearcher = new MockSequenceSearcher();
        similarityFinder = new SimilarityFinder(mockSequenceSearcher);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesHaveElements_thereAreSameElements() {
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        assertEquals(1, mockSequenceSearcher.amountOfInvocations);
        assertTrue(mockSequenceSearcher.verifyParameter(1, seq2));
        assertFalse(mockSequenceSearcher.verifyParameter(0, seq2));
        assertFalse(mockSequenceSearcher.verifyParameter(1, seq1));
    }

    @Test
    void calculateJackardSimilarity_firstSequenceHasElements_secondSequenceIsEmpty() {
        similarityFinder.calculateJackardSimilarity(seq1, empty);
        assertEquals(1, mockSequenceSearcher.amountOfInvocations);
        assertTrue(mockSequenceSearcher.verifyParameter(1, empty));
        assertFalse(mockSequenceSearcher.verifyParameter(0, empty));
        assertFalse(mockSequenceSearcher.verifyParameter(1, seq1));

    }

    @Test
    void calculateJackardSimilarity_firstSequenceIsEmpty_secondSequenceHasElements() {
        similarityFinder.calculateJackardSimilarity(empty, seq2);
        assertEquals(0, mockSequenceSearcher.amountOfInvocations);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesAreEmpty() {
        similarityFinder.calculateJackardSimilarity(empty, empty);
        assertEquals(0, mockSequenceSearcher.amountOfInvocations);
    }

    @Test
    void calculateJackardSimilarity_bothSequencesHaveElements_noMatchingElements() {
        similarityFinder.calculateJackardSimilarity(seq2, seq3);
        assertEquals(seq2.length, mockSequenceSearcher.amountOfInvocations);
        assertTrue(mockSequenceSearcher.verifyParameter(5, seq3));
        assertFalse(mockSequenceSearcher.verifyParameter(-1, seq3));
        assertFalse(mockSequenceSearcher.verifyParameter(3, seq2));
    }

    @Test
    void calculateJackardSimilarity_sequencesAreTheSame() {
        similarityFinder.calculateJackardSimilarity(seq2, seq2);
        assertEquals(seq2.length, mockSequenceSearcher.amountOfInvocations);
        assertTrue(mockSequenceSearcher.verifyParameter(5, seq2));
        assertFalse(mockSequenceSearcher.verifyParameter(-1, seq2));
    }
}