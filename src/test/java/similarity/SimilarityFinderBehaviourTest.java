package similarity;

import edu.iis.mto.similarity.SimilarityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimilarityFinderBehaviourTest {

    SequenceSearchMock searchMock;
    SimilarityFinder similarityFinder;

    @BeforeEach
    void initializeMock() {
        searchMock = new SequenceSearchMock();
        similarityFinder = new SimilarityFinder(searchMock);
    }

    @Test
    void shouldCallMethodForEveySeq1Elem() {
        int[] seq1 = new int[]{1, 2, 3};
        int[] seq2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        similarityFinder.calculateJackardSimilarity(seq1, seq2);

        assertAll(
                () -> assertTrue(searchMock.verifyCalled(seq1[0], seq2)),
                () -> assertTrue(searchMock.verifyCalled(seq1[1], seq2)),
                () -> assertTrue(searchMock.verifyCalled(seq1[2], seq2)),
                () -> assertEquals(3, searchMock.countInvocations())
        );
    }

    @Test
    void shouldCallNoMethodIfBothSeqAreEmpty() {
        int[] seq1 = new int[]{};
        int[] seq2 = new int[]{};

        similarityFinder.calculateJackardSimilarity(seq1, seq2);

        assertEquals(0, searchMock.countInvocations());
    }

}
