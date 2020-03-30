package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherImplementation;
import edu.iis.mto.search.SequenceSearcherMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest {

    @Test
    void two_same_array_of_one_element_when_has_it_should_evaluate_to_one(){
        int[] sampleArray={1};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherImplementation());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray);

        assertEquals(1,result);
    }

    @Test
    void two_same_array_of_one_element_when_dont_have_it_should_evaluate_to_zero(){
        int[] sampleArray={1};
        int[] sampleArray2={3};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherImplementation());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(0,result);
    }


    @Test
    void two_array_with_one_empty_should_evaluate_to_zero(){
        int[] sampleArray={0};
        int[] sampleArray2={};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherImplementation());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(0,result);
    }

    @Test
    void two_array_both_empty_should_evaluate_to_one(){
        int[] sampleArray={};
        int[] sampleArray2={};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherImplementation());

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
