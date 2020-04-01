package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.iis.mto.search.SimpleSearcher;

public class SimilarityFinderBehaviorTests {
	private static final int[] SEQUENCE = { 1, 2, 3, 4 };
	
	private static SimilarityFinder similarityFinder = new SimilarityFinder(new SimpleSearcher());
	
	@Test
	public void nullAsSeqencesThrows() {
		assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, null));
	}
	
	public void nullAsFirstSeqencesThrows() {
		assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, SEQUENCE));
	}
	
	public void nullAsSecondSeqencesThrows() {
		assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(SEQUENCE, null));
	}
	
	public void nullAsFinderThrows() {
		similarityFinder = new SimilarityFinder(null);
		assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(SEQUENCE, SEQUENCE));
	}
}
