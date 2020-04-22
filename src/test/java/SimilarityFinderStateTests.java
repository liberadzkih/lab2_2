import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimilarityFinderStateTests {

    int[] seq1 = {0, 1, 2, 3, 4};
    int[] seq2 = {3, 4, 5, 6, 7};
    int[] emptySeq = {};

    SequenceSearcher standardSequenceSearcher = (elem, seq) -> {
        for (int i : seq) {
            if (i == elem)
                return SearchResult.builder().withFound(true).build();
        }
        return SearchResult.builder().withFound(false).build();
    };

    @Test
    public void twoDifferentSequencesTest() {
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(false).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq1);

        Assertions.assertEquals(0.0d, jackardSimilarity);
    }

    @Test
    public void twoSameSequencesTest() {
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(true).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq1);

        Assertions.assertEquals(1.0d, jackardSimilarity);
    }

    @Test
    public void regularTest() {
        SimilarityFinder similarityFinder = new SimilarityFinder(standardSequenceSearcher);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        Assertions.assertEquals(0.25d, jackardSimilarity);
    }

    @Test
    public void emptySequenceTest1() {
        SimilarityFinder similarityFinder = new SimilarityFinder(standardSequenceSearcher);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(emptySeq, seq1);

        Assertions.assertEquals(0.0d, jackardSimilarity);
    }

    @Test
    public void emptySequenceTest2() {
        SimilarityFinder similarityFinder = new SimilarityFinder(standardSequenceSearcher);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, emptySeq);

        Assertions.assertEquals(0.0d, jackardSimilarity);
    }
}
