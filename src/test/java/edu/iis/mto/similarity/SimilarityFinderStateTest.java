package edu.iis.mto.similarity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.tools.classfile.Annotation.element_value;

import edu.iis.mto.search.SearchResult;

class SimilarityFinderStateTest {

    private static final double FULL_MATCH = 1.0d;
    private static final double ONE_FIFTH_MATCH = 0.2d;
    private static final double ZERO_MATH = 0.0d;

    private final int[] EMPTY_SET = {};
    private final int[] SEQ_WITH_ONE_ELEMENT = { 1 };
    private final int[] SEQ_WITH_MULTIPLE_ELEMENTS1 = { 1, 2, 3, 4, 5 };
    private final int[] SEQ_WITH_MULTIPLE_ELEMENTS2 = { 10, 20, 30, 40 };

    SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> {
        for (int i = 0; i < seq.length; i++) {
            if (elem == seq[i]) {
                return SearchResult.builder().withFound(true).build();
            }
        }
        return SearchResult.builder().withFound(false).build();
    });

    @Test
    void shouldReturnOneWhenTwoEmptySeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(EMPTY_SET, EMPTY_SET), is(equalTo(FULL_MATCH)));
    }

    @Test
    void shouldReturnZeroWhenOneEmptySeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(EMPTY_SET, SEQ_WITH_MULTIPLE_ELEMENTS1),
                is(equalTo(ZERO_MATH)));
    }

    @Test
    void shouldReturnZeroWithTwoDifferentMultipleSeq() {
        assertThat(
                similarityFinder.calculateJackardSimilarity(SEQ_WITH_MULTIPLE_ELEMENTS1, SEQ_WITH_MULTIPLE_ELEMENTS2),
                is(equalTo(ZERO_MATH)));
    }

    @Test
    void shouldReturnZeroWithTwoDifferentSeqWhenOneContainsOneElement() {
        assertThat(similarityFinder.calculateJackardSimilarity(SEQ_WITH_MULTIPLE_ELEMENTS2, SEQ_WITH_ONE_ELEMENT),
                is(equalTo(ZERO_MATH)));
    }

    @Test
    void shouldReturnOneFifthWhenOneElementTheSameInBothSeq() {
        assertThat(similarityFinder.calculateJackardSimilarity(SEQ_WITH_MULTIPLE_ELEMENTS1, SEQ_WITH_ONE_ELEMENT),
                is(equalTo(ONE_FIFTH_MATCH)));
    }

    @Test
    void shouldReturnOneWhenTwoTheSameSeq() {
        assertThat(
                similarityFinder.calculateJackardSimilarity(SEQ_WITH_MULTIPLE_ELEMENTS1, SEQ_WITH_MULTIPLE_ELEMENTS1),
                is(equalTo(FULL_MATCH)));
    }
}
