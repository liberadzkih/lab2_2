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

}
