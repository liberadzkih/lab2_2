import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimilarityFinderStateTests {
    private int[] seq1 = {0, 1, 2, 3, 4};
    private int[] emptySeq = {};

    @Test
    public void twoSameSequencesJaccardTest(){
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(false).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq1);

        Assertions.assertEquals(0, jackardSimilarity, 0.001);
    }

    @Test
    public void twoEmptySequencesJaccardTest() {
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(false).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq);

        Assertions.assertEquals(1, jackardSimilarity, 0.001);
    }

    @Test
    public void oneEmptySequenceJaccardTest() {
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(false).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(emptySeq, seq1);

        Assertions.assertEquals(0, jackardSimilarity, 0.001);
    }
}
