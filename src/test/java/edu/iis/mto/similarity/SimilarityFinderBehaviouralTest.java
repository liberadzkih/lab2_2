package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimilarityFinderBehaviouralTest {

    int[] oneElementSeq = new int[]{0};
    int[] twoElementsSeq = new int[]{1,2};
    int[] fourElementsSeq = new int[]{1,2,3,4};
    SimilarityFinder finder;
    SequenceSearcherStub stub;

    @BeforeEach
    public void init () {
        stub = new SequenceSearcherStub();
        finder = new SimilarityFinder(stub);
    }

    @Test
    public void should_NotCallStubMethods_When_SequencesAreNull () {
        stub.setElemParam(0);
        stub.setSeqParam(oneElementSeq);

        assertThrows(NullPointerException.class, () -> finder.calculateJackardSimilarity(null, null));

        assertEquals(0, stub.getElemParam());
        assertEquals(oneElementSeq, stub.getSeqParam());
    }

    @Test
    public void should_CallStubMethodsAndModifyStubFields_When_SequencesAreNotNull () {
        stub.setElemParam(4);
        stub.setSeqParam(new int[]{0,0,0});

        assertEquals(0, finder.calculateJackardSimilarity(oneElementSeq, twoElementsSeq));

        assertEquals(0, stub.getElemParam());
        assertEquals(twoElementsSeq, stub.getSeqParam());
    }

    @Test
    public void should_CallSearchMethodAsManyTimesAsManyElementsInFirstSeq () {
        SequenceSearcherStub.setCount(0);

        assertEquals(0, finder.calculateJackardSimilarity(oneElementSeq, fourElementsSeq));
        assertEquals(oneElementSeq.length, SequenceSearcherStub.getCount());

        SequenceSearcherStub.setCount(0);

        assertEquals(0.5, finder.calculateJackardSimilarity(twoElementsSeq, fourElementsSeq));
        assertEquals(twoElementsSeq.length, SequenceSearcherStub.getCount());

        SequenceSearcherStub.setCount(0);

        assertEquals(1, finder.calculateJackardSimilarity(fourElementsSeq, fourElementsSeq));
        assertEquals(fourElementsSeq.length, SequenceSearcherStub.getCount());
    }
}