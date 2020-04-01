package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.iis.mto.search.SimpleSearcher;

public class SimilarityFinderStateTests {
	private static final int[] EMPTY_SEQUENCE = { };
	private static final int[] FIRST_SEQUENCE = { 1, 2, 3, 4 };
	private static final int[] SECOND_SEQUENCE = { 5, 6, 7, 8 };
	private static final int[] INVERTED_FIRST_SEQUENCE = { 4, 3, 2, 1 };
	private static final double FULLY_SIMILAR = 1.0;
	private static final double NONE_SIMILAR = 0.0;
	private static final double TEST_DELTA = 0.001;
	
	private static SimilarityFinder similarityFinder = new SimilarityFinder(new SimpleSearcher());
	
	@Test
	public void emptySeqencesAreSimilar() {
		double result = similarityFinder.calculateJackardSimilarity(EMPTY_SEQUENCE, EMPTY_SEQUENCE);
		assertEquals(FULLY_SIMILAR, result, TEST_DELTA);
	}
	
	@Test
	public void emptyAndFilledSeqencesAreNotSimilar() {
		double result = similarityFinder.calculateJackardSimilarity(EMPTY_SEQUENCE, FIRST_SEQUENCE);
		assertEquals(NONE_SIMILAR, result, TEST_DELTA);
	}
	
	@Test
	public void sameSeqencesAreSimilar() {
		double result = similarityFinder.calculateJackardSimilarity(FIRST_SEQUENCE, FIRST_SEQUENCE);
		assertEquals(FULLY_SIMILAR, result, TEST_DELTA);
	}
	
	@Test
	public void differentSeqencesAreNotSimilar() {
		double result = similarityFinder.calculateJackardSimilarity(FIRST_SEQUENCE, SECOND_SEQUENCE);
		assertEquals(NONE_SIMILAR, result, TEST_DELTA);
	}
	
	@Test
	public void invertedSeqencesAreSimilar() {
		double result = similarityFinder.calculateJackardSimilarity(FIRST_SEQUENCE, INVERTED_FIRST_SEQUENCE);
		assertEquals(FULLY_SIMILAR, result, TEST_DELTA);
	}
}
