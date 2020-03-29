import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimilarityFinderStateTests {

    int[] seq1 = {0, 1, 2, 3, 4};
    int[] seq2 = {5, 6, 7, 8, 9};

    @Test
    public void zeroJaccardIndexTest(){
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(false).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq1);

        Assertions.assertEquals(0.0d, jackardSimilarity);
    }

    @Test
    public void oneJaccardIndexTest(){
        SequenceSearcher stub = (elem, seq) -> SearchResult.builder().withFound(true).build();

        SimilarityFinder similarityFinder = new SimilarityFinder(stub);
        double jackardSimilarity = similarityFinder.calculateJackardSimilarity(seq1, seq1);

        Assertions.assertEquals(1.0d, jackardSimilarity);
    }
}
