import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviourTests {

    private int[] seq1 = {0, 1, 2, 3, 4};
    private int[] seq2 = {10, 20, 30, 40, 50};

    private SequenceSearcherMock sequenceSearcherMock;
    private SimilarityFinder similarityFinder;

    @BeforeEach
    public void setUp() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    public void invocationsTest() {
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        sequenceSearcherMock.verifyInvocation(0, seq2);
        sequenceSearcherMock.verifyInvocation(1, seq2);
        sequenceSearcherMock.verifyInvocation(2, seq2);
        sequenceSearcherMock.verifyInvocation(3, seq2);
        sequenceSearcherMock.verifyInvocation(4, seq2);
    }
}
