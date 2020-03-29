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

        sequenceSearcherDubler.verifySearchInvocation(1, testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(2, testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(3, testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(4, testseqEmpty);
        sequenceSearcherDubler.verifySearchInvocation(5, testseqEmpty);

    }
}
