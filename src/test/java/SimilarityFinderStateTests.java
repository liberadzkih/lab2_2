import edu.iis.mto.search.SearchResult;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimilarityFinderStateTests {
    private int[] emptySequence = {};
    private int[] regularSequence = {-2, -1, 0, 1, 2};

    @Test
    public void emptySequencesTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> SearchResult.builder().withFound(false).build());
        assertEquals(1, similarityFinder.calculateJackardSimilarity(emptySequence, emptySequence));
    }

    @Test
    public void nullSequencesTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> SearchResult.builder().withFound(false).build());
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, null));
    }

    @Test
    public void emptySequenceTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> SearchResult.builder().withFound(false).build());
        assertEquals(0, similarityFinder.calculateJackardSimilarity(regularSequence, emptySequence));
    }
}
