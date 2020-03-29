package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
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
}