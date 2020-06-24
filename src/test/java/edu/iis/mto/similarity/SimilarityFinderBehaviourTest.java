package edu.iis.mto.similarity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviourTest {

    private SimilarityFinder similarityFinder;
    private SequenceSearcherMock sequenceSearcherMock;
    private final int[] EMPTY_SET = {};
    private final int[] SEQ_WITH_ONE_ELEMENT = { 1 };
    private final int[] SEQ_WITH_MULTIPLE_ELEMENTS1 = { 1, 2, 3, 4, 5 };
    private final int[] SEQ_WITH_MULTIPLE_ELEMENTS2 = { 10, 20, 30, 40 };

    @BeforeEach
    void setUp() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    void shouldNotBeInvokedWithTwoEmptySets() {
        similarityFinder.calculateJackardSimilarity(EMPTY_SET, EMPTY_SET);
        assertThat(sequenceSearcherMock.getNumberOfInvocations(), is(0));
    }

    @Test
    void shouldBeInvokedOneTimeWithOneElementSeqAndEmptySeq() {
        similarityFinder.calculateJackardSimilarity(SEQ_WITH_ONE_ELEMENT, EMPTY_SET);
        assertThat(sequenceSearcherMock.getNumberOfInvocations(), is(1));
        assertThat(sequenceSearcherMock.checkIfContainsInvocation(SEQ_WITH_ONE_ELEMENT[0], EMPTY_SET), is(true));
    }

    @Test
    void shouldNotBeInvokedWithEmptySetAsFirstParam() {
        similarityFinder.calculateJackardSimilarity(EMPTY_SET, SEQ_WITH_MULTIPLE_ELEMENTS1);
        assertThat(sequenceSearcherMock.getNumberOfInvocations(), is(0));
    }

    @Test
    void shouldBeInvokedFiveTimesWithParticularDataSets() {
        similarityFinder.calculateJackardSimilarity(SEQ_WITH_MULTIPLE_ELEMENTS1, SEQ_WITH_MULTIPLE_ELEMENTS2);
        assertThat(sequenceSearcherMock.getNumberOfInvocations(), is(5));
        for (int i = 0; i < SEQ_WITH_MULTIPLE_ELEMENTS1.length; i++) {
            assertThat(sequenceSearcherMock.checkIfContainsInvocation(SEQ_WITH_MULTIPLE_ELEMENTS1[i],
                    SEQ_WITH_MULTIPLE_ELEMENTS2), is(true));
        }
    }
}
