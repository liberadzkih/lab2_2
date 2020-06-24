package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherMock;
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
        SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    public void firstSeqHasZeroSecondHasFive() {
        double actual = similarityFinder.calculateJackardSimilarity(emptySeq, fiveElementsSeq);
        double expected = 0.0d;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void firstSeqHasFiveSecondHasZero() {
        double actual = similarityFinder.calculateJackardSimilarity(fiveElementsSeq, emptySeq);
        double expected = 0.0d;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void firstSeqHasOneSecondHasFive() {
        double actual = similarityFinder.calculateJackardSimilarity(oneElementSeq, fiveElementsSeq);
        double expected = 0.2d;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void firstSeqHasFiveSecondHasOne() {
        double actual = similarityFinder.calculateJackardSimilarity(fiveElementsSeq, oneElementSeq);
        double expected = 0.2d;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void bothSequencesAreEmpty() {
        double actual = similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq);
        double expected = 1.0d;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void bothSequencesHaveFiveElements() {
        double actual = similarityFinder.calculateJackardSimilarity(fiveElementsSeq, fiveElementsSeq);
        double expected = 1.0d;

        Assertions.assertEquals(expected, actual);
    }
}