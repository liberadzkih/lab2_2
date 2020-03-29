package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimilarityFinderTest {
    private SimilarityFinder similarityFinder;

    @Test
    public void jaccardSimilarityForEmptyCollections() {
        similarityFinder = new SimilarityFinder((key, seq) ->  SearchResult.builder().build());
        int[] collection1 = {};
        int[] collection2 = {};
        Assert.assertThat(similarityFinder.calculateJaccardSimilarity(collection1, collection2), is(equalTo(1.0d)));
    }

    @Test
    public void jaccardSimilarityForDifferentCollections() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(false).build());
        int[] collection1 = {1,2,3};
        int[] collection2 = {4,5,6};
        Assert.assertThat(similarityFinder.calculateJaccardSimilarity(collection1, collection2), is(equalTo(0d)));
    }

    @Test
    public void jaccardSimilarityOnEqualCollections() {
        similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2] || key == seq[3])
                return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().withFound(false).build();
        });
        int[] collection1 = {1,2,3};
        int[] collection2 = {3,2,1};
        Assert.assertThat(similarityFinder.calculateJaccardSimilarity(collection1, collection2), is(equalTo(1d)));
    }
}
