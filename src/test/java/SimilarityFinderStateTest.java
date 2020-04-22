import edu.iis.mto.search.SearchResult;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimilarityFinderStateTest {

    private int[] emptySeq = {};
    private int[] firstSeq = {1, 4, 5, 6, 8, 9};
    private int[] secondSeq = {2, 3, 5, 7, 10};
    private int[] thirddSeq = {2, 3, 7, 10, 11, 12, 13};

    SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> {
        for (int i = 0; i < seq.length; i++) {
            if (elem == seq[i]) {
                return SearchResult.builder().withFound(true).build();
            }
        }
        return SearchResult.builder().withFound(false).build();
    });


    @Test
    void shouldReturnOneIfTwoSeqAreEmpty() {
        assertEquals(1, similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq));
    }

    @Test
    void shouldReturnZeroIfOneSeqAISEmpty() {
        assertEquals(0, similarityFinder.calculateJackardSimilarity(emptySeq, firstSeq));
    }

    @Test
    void shouldReturnZeroIfOneSeqAISEmpty2() {
        assertEquals(0, similarityFinder.calculateJackardSimilarity(firstSeq, emptySeq));
    }

    @Test
    void shouldReturnOneTenthIfTwoSeqAreDiifferentAndHaveOneTheSameElem() {
        assertEquals(0.1d, similarityFinder.calculateJackardSimilarity(firstSeq, secondSeq));
    }

    @Test
    void shouldReturnZeroIfTwoSeqAreDiifferent() {
        assertEquals(0, similarityFinder.calculateJackardSimilarity(firstSeq, thirddSeq));
    }

    @Test
    void shouldReturnAHalfIfTwoSeqAreDiifferentAndHaveThreeTheSameElem() {
        assertEquals(0.5d, similarityFinder.calculateJackardSimilarity(secondSeq, thirddSeq));
    }

}
