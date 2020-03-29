package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherDubler;
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
        similarityFinder.calculateJackardSimilarity(testseqEmpty,testseqEmpty);
        assertEquals(0,sequenceSearcherDubler.getSearchInvocationAmout());
    }

}
