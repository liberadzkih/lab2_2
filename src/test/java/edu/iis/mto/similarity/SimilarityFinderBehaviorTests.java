package edu.iis.mto.similarity;

import org.junit.Test;

import edu.iis.mto.search.SimpleSearcher;

public class SimilarityFinderBehaviorTests {
	private static final int[] EMPTY_SEQUENCE = { };
	private static final int[] SEQUENCE = { 1, 2, 3, 4 };
	
	private static SimilarityFinder similarityFinder = new SimilarityFinder(new SimpleSearcher());
	
	@Test(expected = NullPointerException.class)
	public void nullAsSeqencesThrows() {
		similarityFinder.calculateJackardSimilarity(null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void nullAsFirstSeqencesThrows() {
		similarityFinder.calculateJackardSimilarity(null, SEQUENCE);
	}
	
	@Test(expected = NullPointerException.class)
	public void nullAsSecondSeqencesThrows() {
		similarityFinder.calculateJackardSimilarity(SEQUENCE, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void nullAsFinderThrows() {
		similarityFinder = new SimilarityFinder(null);
		similarityFinder.calculateJackardSimilarity(SEQUENCE, SEQUENCE);
	}
}
