import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviourTests {

    int[] seq1 = {0, 1, 2, 3, 4};
    int[] seq2 = {5, 6, 7, 8, 9};
    int[] emptySeq = {};

    SequenceSearcherMock sequenceSearcherMock;
    SimilarityFinder similarityFinder;

    @BeforeEach
    public void init() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    public void particularInvocationTest() {
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        sequenceSearcherMock.verifyInvocation(0, seq2);
        sequenceSearcherMock.verifyInvocation(1, seq2);
        sequenceSearcherMock.verifyInvocation(2, seq2);
        sequenceSearcherMock.verifyInvocation(3, seq2);
        sequenceSearcherMock.verifyInvocation(4, seq2);
    }

    @Test
    public void numberOfInvocationsTest() {
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        sequenceSearcherMock.verifyInvocationNumber(5);
    }

    @Test
    public void emptySequencesTest() {
        similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq);
        sequenceSearcherMock.verifyInvocationNumber(0);
    }

    @Test
    public void emptySequenceTest1() {
        similarityFinder.calculateJackardSimilarity(emptySeq, seq1);
        sequenceSearcherMock.verifyInvocationNumber(0);
    }

    @Test
    public void emptySequenceTest2() {
        similarityFinder.calculateJackardSimilarity(seq1, emptySeq);
        sequenceSearcherMock.verifyInvocationNumber(5);
    }
}
