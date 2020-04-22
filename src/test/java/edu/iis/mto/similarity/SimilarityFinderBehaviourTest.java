package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTest {

    SimilarityFinder similarityFinder;
    SequenceSearcherMock sequenceSearcherMock;
    final int[] emptySeq = {};
    final int[] oneElementSeq = {1};
    final int[] threeElementsSeq = {1,2,3};

    @BeforeEach
    public void setup() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    public void bothSequencesAreEmpty() {
        similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq);

        Assertions.assertEquals(0, sequenceSearcherMock.getAmountOfInvocations());
    }

    @Test
    public void firstSeqIsEmptySecondHasThreeElements() {
        similarityFinder.calculateJackardSimilarity(emptySeq, threeElementsSeq);

        Assertions.assertEquals(0, sequenceSearcherMock.getAmountOfInvocations());
    }

    @Test
    public void firstSeqHasThreeElementsSecondIsEmpty() {
        similarityFinder.calculateJackardSimilarity(threeElementsSeq, emptySeq);

        Assertions.assertEquals(3,sequenceSearcherMock.getAmountOfInvocations());
        sequenceSearcherMock.verifyCalled(1, emptySeq);
        sequenceSearcherMock.verifyCalled(2, emptySeq);
        sequenceSearcherMock.verifyCalled(3, emptySeq);
    }

    @Test
    public void firstSeqHasOneElementsSecondsHasThree() {
        similarityFinder.calculateJackardSimilarity(oneElementSeq, threeElementsSeq);

        Assertions.assertEquals(1,sequenceSearcherMock.getAmountOfInvocations());
        sequenceSearcherMock.verifyCalled(1, threeElementsSeq);
    }

}