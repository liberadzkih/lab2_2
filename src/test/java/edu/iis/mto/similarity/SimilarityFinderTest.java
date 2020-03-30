package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SimilarityFinderTest {
    private SimilarityFinder simFinder;

    @Test
    public void findInEmptySequences() {
        int[] sequence1 = { };
        int[] sequence2 = { };
        simFinder = new SimilarityFinder((key, seq) ->  SearchResult.builder().build());
        Assert.assertThat(simFinder.calculateJackardSimilarity(sequence1, sequence2), is(equalTo(1.0d)));
    }

    @Test
    public void findInTheSameSequences() {
        int[] sequence1 = { 0, 1, 2, 3, 4, 5 };
        int[] sequence2 = { 3, 0, 5, 2, 1, 4 };

        simFinder = new SimilarityFinder((findingTheSame, sequence) -> {
            if (IntStream.of(sequence).anyMatch(x -> x == findingTheSame)) {
                return SearchResult.builder().withFound(true).build();
            } else {
                return SearchResult.builder().withFound(false).build();
            }
        });

        Assert.assertThat(simFinder.calculateJackardSimilarity(sequence1, sequence2), is(equalTo(1.0d)));
    }

    @Test
    public void findInTheDifferentSequences() {
        int[] sequence1 = { 0, 1, 2, 3, 4, 5 };
        int[] sequence2 = { 6, 7, 8, 9, 10, 11 };

        simFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(false).build());

        Assert.assertThat(simFinder.calculateJackardSimilarity(sequence1, sequence2), is(equalTo(0d)));
    }

    @Test
    public void findInHalfTheSameSequences() {
        int[] sequence1 = { 0, 1, 2, 3, 4, 5 };
        int[] sequence2 = { 6, 1, 2, 3, 4, 8 };

        simFinder = new SimilarityFinder((findingTheSame, sequence) -> {
            if (IntStream.of(sequence).anyMatch(x -> x == findingTheSame)) {
                return SearchResult.builder().withFound(true).build();
            } else {
                return SearchResult.builder().withFound(false).build();
            }
        });

        Assert.assertThat(simFinder.calculateJackardSimilarity(sequence1, sequence2), is(equalTo(0.5d)));
    }


    private static class SequenceSearcherDoubler implements SequenceSearcher {
        private int callsNumber;

        SequenceSearcherDoubler() {
            callsNumber = 0;
        }

        public int getCallsNumber() {
            return callsNumber;
        }

        @Override
        public SearchResult search(int key, int[] seq) {
            this.callsNumber++;
            return SearchResult.builder().build();
        }
    }
}