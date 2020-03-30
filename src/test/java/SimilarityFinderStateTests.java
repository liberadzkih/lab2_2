import edu.iis.mto.search.SearchResult;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimilarityFinderStateTests {
    private int[] emptySequence = {};
    private int[] singleElementSequence = {1};
    private int[] multipleElementsSequence = {-4, -3, -2, -1, 0, 1, 2, 3, 4, 5};

    @Test
    public void nullSequencesTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> SearchResult.builder().withFound(false).build());
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, null));
    }

    @Test
    public void emptySequencesTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> SearchResult.builder().withFound(false).build());
        assertEquals(1, similarityFinder.calculateJackardSimilarity(emptySequence, emptySequence));
    }

    @Test
    public void oneEmptySequenceTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, seq) -> SearchResult.builder().withFound(false).build());
        assertEquals(0, similarityFinder.calculateJackardSimilarity(multipleElementsSequence, emptySequence));
    }

    @Test
    public void oneElementMatchTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder(((elem, seq) -> {
            for (int sequenceElement : seq) {
                if (sequenceElement == elem) {
                    return SearchResult.builder().withFound(true).build();
                }
            }
            return SearchResult.builder().withFound(false).build();
        }));
        assertEquals(0.1, similarityFinder.calculateJackardSimilarity(singleElementSequence, multipleElementsSequence), 0.0001);
    }
}
