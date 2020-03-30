package edu.iis.mto.similarity;

import edu.iis.mto.search.MockForSequenceSearcher;
import edu.iis.mto.search.SecondMockForSequenceSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTest {

    int[] seq1={2};
    int[] seq2={3};
    int[] seq3={2,3,5,6};
    int[] seq4={9,7,4,1};
    int[] seq5={3};
    int[] seq6={4,5,6};
    int[]seq7={5,6,4};
    int[] seq8={};
    int[] seq9 ={};
    int[] seq10={6,9,1,3,0};
    int[] seq11={6,2,9,7,8};
    SecondMockForSequenceSearcher mock = new SecondMockForSequenceSearcher();
    SimilarityFinder similarityFinder = new SimilarityFinder(mock);

    @Test void return_number_of_iterations_equal_size_of_first_seq() {
        similarityFinder.calculateJackardSimilarity(seq3,seq10);
        assertEquals(4,mock.getCounter());
    }
}
