package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.search.SequenceSearcherMock;
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

}