import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviourTests {

    private int[] emptySeq = {};
    private int[] seq1 = {0, 1, 2};
    private int[] seq2 = {10, 20, 30};

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
    }

    @Test
    public void oneEmptySequenceTest() {
        similarityFinder.calculateJackardSimilarity(emptySeq, seq1);
        sequenceSearcherMock.verifyInvocationNumber(0);
    }

    @Test
    public void twoEmptySequenceTest() {
        similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq);
        sequenceSearcherMock.verifyInvocationNumber(0);
    }

    @Test
    public void invocationNumberTest() {
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        sequenceSearcherMock.verifyInvocationNumber(3);
    }
}
