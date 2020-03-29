package edu.iis.mto.similarity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimilarityFinderBehaviourTest {
    int[] testseqEmpty = {};
    int[] testseqOneElement = {1};
    int[] testseqFiveElements = {1, 2, 3, 4, 5};
    SimilarityFinder similarityFinder;
    SequenceSearcherDubler sequenceSearcherDubler;

    @BeforeEach
    void setUp() {
        sequenceSearcherDubler = new SequenceSearcherDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcherDubler);
    }

    @Test
    void calculateJackardSimilarity_bothSeqAreEmpty() {
        similarityFinder.calculateJackardSimilarity(testseqEmpty, testseqEmpty);
        assertEquals(0, sequenceSearcherDubler.getSearchInvocationAmout());
    }

    @Test
    void calculateJackardSimilarity_seq1IsEmpty() {
        similarityFinder.calculateJackardSimilarity(testseqEmpty, testseqFiveElements);
        assertEquals(0, sequenceSearcherDubler.getSearchInvocationAmout());
    }

    @Test
    void calculateJackardSimilarity_seq2IsEmpty() {
        similarityFinder.calculateJackardSimilarity(testseqFiveElements, testseqEmpty);
        assertEquals(5, sequenceSearcherDubler.getSearchInvocationAmout());

        sequenceSearcherDubler.verifySearchInvocation(testseqFiveElements[0], testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(testseqFiveElements[1], testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(testseqFiveElements[2], testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(testseqFiveElements[3], testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(testseqFiveElements[4], testseqEmpty);

    }

    @Test
    void calculateJackardSimilarity_oneElementMatching() {
        similarityFinder.calculateJackardSimilarity(testseqOneElement, testseqFiveElements);
        assertEquals(1, sequenceSearcherDubler.getSearchInvocationAmout());

        sequenceSearcherDubler.verifySearchInvocation(testseqOneElement[0], testseqFiveElements);
    }
}
