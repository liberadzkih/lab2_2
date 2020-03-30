package edu.iis.mto.similarity;

import edu.iis.mto.search.SequenceSearcherImplementation;
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
    void two_array_of_two_the_same_element_it_should_evaluate_to_half(){
        int[] sampleArray={1,2,5,6};
        int[] sampleArray2={3,4,5,6};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherImplementation());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(0.5,result);
    }

    @Test
    void two_array_of_one_the_same_element_it_should_evaluate_to_quarter(){
        int[] sampleArray={1,2,5,6};
        int[] sampleArray2={3,4,5,7};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherImplementation());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(0.25,result);
    }

    @Test
    void two_empty_array_should_evaluate_to_one(){
        int[] sampleArray={};
        int[] sampleArray2={};
        SimilarityFinder similarityFinder=new SimilarityFinder(new SequenceSearcherImplementation());

        double result = similarityFinder.calculateJackardSimilarity(sampleArray,sampleArray2);

        assertEquals(1,result);
    }

}
