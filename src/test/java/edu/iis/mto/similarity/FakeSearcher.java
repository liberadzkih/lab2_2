package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class FakeSearcher implements SequenceSearcher {
	private int invocations = 0;
	
	public FakeSearcher() {
		// empty
	}

	public int getInvocations() {
		return invocations;
	}
	
	@Override
	public SearchResult search(int elem, int[] seq) {
		invocations += 1;
		return SearchResult.builder().build();
	}
}
