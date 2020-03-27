package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTest {

    SimilarityFinder similarityFinder;
    SequenceSearcher sequenceSearcher;

    @BeforeEach
    public void setup() {
        sequenceSearcher = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcher);
    }



}