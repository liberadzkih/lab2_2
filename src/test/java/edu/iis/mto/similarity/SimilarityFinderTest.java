package edu.iis.mto.similarity;

import edu.iis.mto.search.MockForSequenceSearcher;
import edu.iis.mto.search.SearchResult;
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
    SimilarityFinder similarityFinder;

    @Test void return_zero_if_two_single_element_sequences_dont_have_the_same_element() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(false).build());
        assertEquals(0.0,similarityFinder.calculateJackardSimilarity(seq1,seq2));
    }

    @Test void  return_one_if_two_single_element_sequences_have_the_same_element() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(true).build());
        assertEquals(1.0,similarityFinder.calculateJackardSimilarity(seq5,seq2));
    }

    @Test void  return_zero_if_two_multi_elements_sequences_dont_have_the_same_element() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(false).build());
        assertEquals(0.0,similarityFinder.calculateJackardSimilarity(seq3,seq4));
    }

    @Test void  return_one_if_two_multi_elements_sequences_have_the_same_elements() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(true).build());
        assertEquals(1.0,similarityFinder.calculateJackardSimilarity(seq6,seq7));
    }

    @Test void  return_one_if_there_are_two_empty_sequences() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(true).build());
        assertEquals(1.0,similarityFinder.calculateJackardSimilarity(seq8,seq9));
    }

    @Test void  return_zero_if_there_are_one_empty_and_one_single_element_sequences() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(false).build());
        assertEquals(0.0,similarityFinder.calculateJackardSimilarity(seq8,seq2));
    }

    @Test void  return_quarter_if_two_five_elements_sequences_have_two_the_same_element() { // 0.25 = 2/(5+5-2)
        similarityFinder = new SimilarityFinder((key, seq) -> {
            for(int i =0;i<seq.length;i++){
                if(key == seq[i]){
                    return SearchResult.builder().withFound(true).build();
                }
            }
            return SearchResult.builder().withFound(false).build();
        });
        assertEquals(0.25,similarityFinder.calculateJackardSimilarity(seq10,seq11));
    }

    // behaviour
    int[] seq12={2};
    int[] seq13={2,3,5,6};
    int[] seq14={4,5,6};
    int[] seq15={6,9,1,3,0};

    @Test void return_number_of_iterations_equal_size_of_first_seq() {
        MockForSequenceSearcher mock =new MockForSequenceSearcher();
        similarityFinder = new SimilarityFinder(mock);
        similarityFinder.calculateJackardSimilarity(seq13,seq15);
        assertEquals(4,mock.getCounter());
    }

    @Test void return_number_of_iterations_equal_size_of_first_seq2() {
        MockForSequenceSearcher mock =new MockForSequenceSearcher();
        similarityFinder = new SimilarityFinder(mock);
        similarityFinder.calculateJackardSimilarity(seq12,seq14);
        assertEquals(1,mock.getCounter());
    }

}
