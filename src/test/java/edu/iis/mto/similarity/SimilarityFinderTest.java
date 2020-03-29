package edu.iis.mto.similarity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimilarityFinderTest {

    private SimilarityFinder similarityFinder;
    private SequenceSearcherMock sequenceSearcherMock;
    private static final double FULL_MATCH = 1.0d;
    private static final double ONE_FIFTH_MATCH = 0.2d;
    private static final double ZERO_MATH = 0.0d;
    
    private int[] emptySeq = {};
    private int[] sequenceWithOneElement = { 1 };
    private int[] sequenceWithMultipleElements1 = { 1, 2, 3, 4, 5 };
    private int[] sequenceWithMultipleElements2 = { 10, 20, 30, 40 };

    @BeforeEach
    void setUp() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    void shouldReturnOneWhenTwoEmptySeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq), is(equalTo(FULL_MATCH)));
    }

    @Test
    void shouldReturnZeroWhenOneEmptySeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(emptySeq, sequenceWithMultipleElements1),
                is(equalTo(ZERO_MATH)));
    }

    @Test
    void shouldReturnZeroWithTwoDifferentMultipleSeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(sequenceWithMultipleElements1,
                sequenceWithMultipleElements2), is(equalTo(ZERO_MATH)));
    }

    @Test
    void shouldReturnZeroWithTwoDifferentSeqWhenOneContainsOneElement() {
        assertThat(similarityFinder.calculateJackardSimilarity(sequenceWithMultipleElements2, sequenceWithOneElement),
                is(equalTo(ZERO_MATH)));
    }

    @Test
    void shouldReturnOneFifthWhenOneElementTheSameInBothSeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(sequenceWithMultipleElements1, sequenceWithOneElement),
                is(equalTo(ONE_FIFTH_MATCH)));
    }

    @Test
    void shouldReturnOneWhenTwoTheSameSeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(sequenceWithMultipleElements1,
                sequenceWithMultipleElements1), is(equalTo(FULL_MATCH)));
    }

    @Test
    void shouldThrowExceptionWhenNullParameter1() {
        assertThrows(NullPointerException.class,
                () -> similarityFinder.calculateJackardSimilarity(null, sequenceWithOneElement));
    }

    @Test
    void shouldThrowExceptionWhenNullParameter2() {
        assertThrows(NullPointerException.class,
                () -> similarityFinder.calculateJackardSimilarity(sequenceWithOneElement, null));
    }
}
