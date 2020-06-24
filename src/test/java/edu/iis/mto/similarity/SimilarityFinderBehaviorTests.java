package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviorTests {
	private static final int[] EMPTY_SEQUENCE = { };
	private static final int[] FIRST_SEQUENCE = { 1, 2, 3, 4 };
	private static final int[] SECOND_SEQUENCE = { 5, 6, 7, 8 };
	
	private static FakeSearcher fakeSearcher;
	private static SimilarityFinder similarityFinder;
	
	@BeforeEach
	public void beforeEach() {
		fakeSearcher = new FakeSearcher();
		similarityFinder = new SimilarityFinder(fakeSearcher);
	}
	
	@Test
	public void invocationsWithDifferentSequences() {
		similarityFinder.calculateJackardSimilarity(FIRST_SEQUENCE, SECOND_SEQUENCE);
		assertEquals(4, fakeSearcher.getInvocations());
	}
	
	@Test
	public void invocationsWithFirstEmptySequence() {
		similarityFinder.calculateJackardSimilarity(EMPTY_SEQUENCE, SECOND_SEQUENCE);
		assertEquals(0, fakeSearcher.getInvocations());
	}
	
	@Test
	public void invocationsWithSecondEmptySequence() {
		similarityFinder.calculateJackardSimilarity(FIRST_SEQUENCE, EMPTY_SEQUENCE);
		assertEquals(4, fakeSearcher.getInvocations());
	}
	
	@Test
	public void invocationsWithBothEmptySequences() {
		similarityFinder.calculateJackardSimilarity(EMPTY_SEQUENCE, EMPTY_SEQUENCE);
		assertEquals(0, fakeSearcher.getInvocations());
	}
}
