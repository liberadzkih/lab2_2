package edu.iis.mto.similarity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest {

    SimilarityFinder similarityFinder;
    final int[] emptySeq = {};
    final int[] oneElementSeq = {3};
    final int[] fiveElementsSeq = {1,2,3,4,5};

    @BeforeEach
    public void setup() {
        //MUST BE DONE
        //similarityFinder = new SimilarityFinder();
    }

    @Test
    public void firstSeqHasZeroSecondHasFive() {
        double actual = similarityFinder.calculateJackardSimilarity(emptySeq, fiveElementsSeq);
        double expected = 0;

        Assertions.assertEquals(expected, actual);
    }
}