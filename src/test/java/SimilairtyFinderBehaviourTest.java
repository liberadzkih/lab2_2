import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SimilairtyFinderBehaviourTest {
    private SequenceSearcherMock sequenceSearcherMock;
    private SimilarityFinder similarityFinder;

    private int[] emptySeq = {};
    private int[] firstSeq = {1, 4, 5, 6, 8, 9};
    private int[] secondSeq = {2, 3, 5, 7, 10, 11};


    @BeforeEach
    void init() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    void numberInvocationTest() {
        similarityFinder.calculateJackardSimilarity(firstSeq, secondSeq);
        sequenceSearcherMock.verifyInvocationSize(6);
    }

    @Test
    void emptyInvocationTest() {
        similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq);
        sequenceSearcherMock.verifyInvocationSize(0);
    }

    @Test
    void numberInvocationWithEmptySeqTest1() {
        similarityFinder.calculateJackardSimilarity(secondSeq, emptySeq);
        sequenceSearcherMock.verifyInvocationSize(6);
    }

    @Test
    void numberInvocationWithEmptySeqTest2() {
        similarityFinder.calculateJackardSimilarity(emptySeq, secondSeq);
        sequenceSearcherMock.verifyInvocationSize(0);
    }

    @Test
    void ContainsElem() {
        similarityFinder.calculateJackardSimilarity(firstSeq, secondSeq);
        for (int i = 0; i < firstSeq.length; i++)
            sequenceSearcherMock.verifyInvocation(firstSeq[i], secondSeq);
    }

}
