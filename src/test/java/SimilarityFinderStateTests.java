import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimilarityFinderStateTests {
    private int[] seq1 = {0, 1, 2, 3, 4};

    @Test
    public void twoSameSequencesJaccardTest(){
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(false).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq1);

        Assertions.assertEquals(0, jackardSimilarity, 0.001);
    }
}
