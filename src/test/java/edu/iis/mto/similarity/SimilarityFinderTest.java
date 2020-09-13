package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SimilarityFinderTest {

    private SimilarityFinder similarityFinder;

    @BeforeEach
    void setUpSimilarityFinder() {
        similarityFinder = new SimilarityFinder((elem, seq) -> {
            for (int i = 0; i < seq.length; i++) {
                if (elem == seq[i]) {
                    return SearchResult.builder().withFound(true).withPosition(i).build();
                }
            }
            return SearchResult.builder().withPosition(-1).withFound(false).build();
        });
    }

    @Test
    public void jaccardSimilarity_firstSequenceEmpty() {
        int seq1[] = {};
        int seq2[] = {1, 2, 3};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo(0.0));
    }

    @Test
    public void jaccardSimilarity_secondSequenceEmpty() {
        int seq1[] = {1, 2, 3};
        int seq2[] = {};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo(0.0));
    }

    @Test
    public void jaccardSimilarity_bothSequencesEmpty() {
        int seq1[] = {};
        int seq2[] = {};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo(1.0));
    }

    @Test
    public void jaccardSimilarity_sequencesSameContent() {
        int seq1[] = {-11, 0, 2, 3, 4, 5};
        int seq2[] = {-11, 0, 2, 3, 4, 5};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo((double) 6 / 6));
    }

    @Test
    public void jaccardSimilarity_sequencesSameContent_sequencesDifferentOrder() {
        int seq1[] = {-11, 0, 2, 3, 4, 5};
        int seq2[] = {-11, 5, 3, 4, 0, 2};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo((double) 6 / 6));
    }

    @Test
    public void jaccardSimilarity_sequencesAlmostSameContent_sequencesDifferentLength() {
        int seq1[] = {-11, 0, 2, 3, 4, 5, 18, 19, 999, 1001};
        int seq2[] = {-11, 0, 2, 3, 4, 5, 18};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo((double) 7 / 10));
    }

    @Test
    public void jaccardSimilarity_sequencesDifferentContent_sequencesSameLength() {
        int seq1[] = {-116, 0};
        int seq2[] = {-11, 13};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo((double) 0 / 6));
    }

    @Test
    public void jaccardSimilarity_sequencesDifferentContent_sequencesDifferentLength() {
        int seq1[] = {-116, 0};
        int seq2[] = {-11, 13, 19, 20};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo((double) 0 / 6));
    }

    @Test
    public void jaccardSimilarity_almostSameContent_sequencesSameLength() {
        int seq1[] = {-1, 0, 1, 2};
        int seq2[] = {-1, 0, 1, 3};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo((double) 3 / 5));
    }

    @Test
    public void jaccardSimilarity_almostSameContent_oneMoreElementInSecondSequence() {
        int seq1[] = {-1, 0, 1, 2, 3};
        int seq2[] = {-1, 0, 1, 2, 3, 7};
        double jaccardIndexOfSimilarity = similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(jaccardIndexOfSimilarity, equalTo((double) 5 / 6));
    }

    @Test
    public void jaccardSimilarity_bothSequencesAreNull_testForNullPointerException() {
        int seq1[] = null;
        int seq2[] = null;
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJaccardSimilarity(seq1, seq2));
    }

    @Test
    public void jaccardSimilarity_firstSeqIsEmpty_getCountOfSearchMethodCall() {
        int seq1[] = {};
        int seq2[] = {1, 2, 3, 7};
        SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
        similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(sequenceSearcherMock.getSearchCalls(), equalTo(seq1.length));
    }

    @Test
    public void jaccardSimilarity_secondSeqIsEmpty_getCountOfSearchMethodCall() {
        int seq1[] = {1, 2};
        int seq2[] = {};
        SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
        similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(sequenceSearcherMock.getSearchCalls(), equalTo(seq1.length));
    }

    @Test
    public void jaccardSimilarity_getCountOfSearchMethodCall() {
        int seq1[] = {1, 2, 234, 678, 890};
        int seq2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SequenceSearcherMock sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
        similarityFinder.calculateJaccardSimilarity(seq1, seq2);
        assertThat(sequenceSearcherMock.getSearchCalls(), equalTo(seq1.length));
    }

}
