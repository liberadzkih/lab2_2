package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    private class SequenceSearcherMock implements SequenceSearcher {
        private Set<int[]> passedSequences = new HashSet<>();
        private List<Integer> elements= new ArrayList<>();
        private int searchInvocationCounter=0;

        @Override public SearchResult search(int elem, int[] seq) {
            searchInvocationCounter++;
            elements.add(elem);
            passedSequences.add(seq);
            return SearchResult.builder().build();
        }

        public Set<int[]> getPassedSequences() {
            return passedSequences;
        }

        public   boolean hasSameElements(int[] expectedElements) {
            if (elements.size() != expectedElements.length) {
                return false;
            }
            for (int expectedElement : expectedElements) {
                if (!elements.contains(expectedElement)) {
                    return false;
                }
            }
            return true;
        }

        public int getSearchInvocationCounter() {
            return searchInvocationCounter;
        }
    }

    @Test
    void two_same_array_of_one_element_when_dont_have_it_should_evaluate_to_zero(){
        int[] sampleArray={1};
        int[] sampleArray2={3};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherMock());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(0,result);
    }


    @Test
    void two_array_with_one_empty_should_evaluate_to_zero(){
        int[] sampleArray={0};
        int[] sampleArray2={};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherMock());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(0,result);
    }

    @Test
    void two_array_both_empty_should_evaluate_to_one(){
        int[] sampleArray={};
        int[] sampleArray2={};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherMock());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(1,result);
    }

    //testy zachowania
    @Test
    void array_of_size_x_should_inovoke_searcher_x_times(){
        int[] sampleArray={1,2,3,4,5};
        int[] sampleArray2={3,4,5,6,7,8,9};

        SequenceSearcherMock mock =new SequenceSearcherMock();
        SimilarityFinder similarityFinder=new SimilarityFinder(mock);

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(5,mock.getSearchInvocationCounter());
    }

    @Test
    void SequenceSearcher_should_be_invoke_with_parameters_in_array_sequence(){
        int[] sampleArray={1,2,3,4,5};
        int[] sampleArray2={3,4,5,6,7,8,9};
        SequenceSearcherMock mock =new SequenceSearcherMock();
        SimilarityFinder similarityFinder=new SimilarityFinder(mock);

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertTrue(mock.hasSameElements(sampleArray));
    }

    @Test
    void SequenceSearcher_should_be_invoke_passed_same_sequence(){
        int[] sampleArray={1,2,3,4,5};
        int[] sampleArray2={3,4,5,6,7,8,9};
        SequenceSearcherMock mock =new SequenceSearcherMock();
        SimilarityFinder similarityFinder=new SimilarityFinder(mock);

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(1,mock.getPassedSequences().size());
    }
}
