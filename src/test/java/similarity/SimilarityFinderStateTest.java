package similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimilarityFinderStateTest {

    SequenceSearcher searcherStubFalse = (elem, seq) -> SearchResult.builder()
            .withFound(false)
            .withPosition(-1)
            .build();

    SequenceSearcher searcherStubTrue = (elem, seq) -> SearchResult.builder()
            .withFound(true)
            .withPosition(0)
            .build();

    @Test
    void shouldReturnSimilarity() {
        int[] seq1 = new int[]{1, 2, 3};
        int[] seq2 = new int[]{1, 2, 3};

        SimilarityFinder similarityFinder = new SimilarityFinder(searcherStubTrue);

        assertEquals(1., similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void shouldReturnNoSimilarity() {
        int[] seq1 = new int[]{0, 0, 0};
        int[] seq2 = new int[]{1, 2, 3};

        SimilarityFinder similarityFinder = new SimilarityFinder(searcherStubFalse);

        assertEquals(0., similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void shouldReturnFullSimilarityIfBothSequencesAreEmpty() {
        int[] seq1 = new int[]{};
        int[] seq2 = new int[]{};

        SimilarityFinder similarityFinder = new SimilarityFinder(searcherStubFalse);

        assertEquals(1., similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void shouldReturnNoSimilarityIfOneSequenceIsEmpty() {
        int[] seq1 = new int[]{};
        int[] seq2 = new int[]{1, 2, 3, 4};

        SimilarityFinder similarityFinder = new SimilarityFinder(searcherStubFalse);

        assertEquals(0., similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void shouldReturnThirdOfSimilarity() {
        int[] seq1 = new int[]{1, 2, 5, 5};
        int[] seq2 = new int[]{1, 2, 3, 4};

        SequenceSearcher searcherStub = (elem, seq) -> {
            if (elem == 1 || elem == 2)
                return searcherStubTrue.search(elem, seq);
            else
                return searcherStubFalse.search(elem, seq);
        };

        SimilarityFinder similarityFinder = new SimilarityFinder(searcherStub);

        assertEquals(1. / 3., similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void shouldBeSensitiveToLengthDifference() {
        int[] seq1 = new int[]{1, 2, 5, 5};
        int[] seq2 = new int[]{1, 2, 5, 5, 2, 3, 4, 5};

        SimilarityFinder similarityFinder = new SimilarityFinder(searcherStubTrue);

        assertEquals(.5, similarityFinder.calculateJackardSimilarity(seq1, seq2));
    }

}
