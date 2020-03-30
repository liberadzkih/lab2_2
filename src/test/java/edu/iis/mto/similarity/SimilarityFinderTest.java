package edu.iis.mto.similarity;

import edu.iis.mto.search.MockForSequenceSearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {
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
    SimilarityFinder similarityFinder = new SimilarityFinder(new MockForSequenceSearcher());

    @Test void return_zero_if_two_single_element_sequences_dont_have_the_same_element() {
        assertEquals(0.0,similarityFinder.calculateJackardSimilarity(seq1,seq2));
    }

    @Test void  return_one_if_two_single_element_sequences_have_the_same_element() {
        assertEquals(1.0,similarityFinder.calculateJackardSimilarity(seq5,seq2));
    }

    @Test void  return_zero_if_two_multi_elements_sequences_dont_have_the_same_element() {
        assertEquals(0.0,similarityFinder.calculateJackardSimilarity(seq3,seq4));
    }

    @Test void  return_one_if_two_multi_elements_sequences_have_the_same_elements() {
        assertEquals(1.0,similarityFinder.calculateJackardSimilarity(seq6,seq7));
    }

    @Test void  return_one_if_there_are_two_empty_sequences() {
        assertEquals(1.0,similarityFinder.calculateJackardSimilarity(seq8,seq9));
    }

    @Test void  return_zero_if_there_are_one_empty_and_one_single_element_sequences() {
        assertEquals(0.0,similarityFinder.calculateJackardSimilarity(seq8,seq2));
    }

    @Test void  return_quarter_if_two_five_elements_sequences_have_two_the_same_element() { // 0.25 = 2/(5+5-2)
        assertEquals(0.25,similarityFinder.calculateJackardSimilarity(seq10,seq11));
    }


}
