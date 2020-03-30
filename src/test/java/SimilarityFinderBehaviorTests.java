import edu.iis.mto.search.SearchResult;
import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimilarityFinderBehaviorTests {
    private SimilarityFinder similarityFinder;
    private SequenceSearcherMock sequenceSearcherMock;
    private int[] emptySequence = {};
    private int[] singleElementSequence = {1};
    private int[] multipleElementsSequence = {-4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
    private int[] multipleElementsSequenceFromZero = {0, 1, 2, 3, 4};

    @BeforeEach
    public void prepareEnvironmentToTest() {
        sequenceSearcherMock = new SequenceSearcherMock();
        similarityFinder = new SimilarityFinder(sequenceSearcherMock);
    }

    @Test
    public void searchShouldNotBeInvokedForTwoEmptySequences() {
        similarityFinder.calculateJackardSimilarity(emptySequence, emptySequence);
        assertEquals(0, sequenceSearcherMock.getNumberOfInvocations());
    }

    @Test
    public void searchShouldNotBeInvokedForEmptySequenceAsFirstArgument() {
        similarityFinder.calculateJackardSimilarity(emptySequence, singleElementSequence);
        assertEquals(0, sequenceSearcherMock.getNumberOfInvocations());
    }

    @Test
    public void searchShouldBeInvokedOnceForSingleElementSequenceAsFirstArgument() {
        similarityFinder.calculateJackardSimilarity(singleElementSequence, emptySequence);
        assertEquals(1, sequenceSearcherMock.getNumberOfInvocations());
        assertTrue(sequenceSearcherMock.checkIfCalled(singleElementSequence[0], emptySequence));
    }
}
