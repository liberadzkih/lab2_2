package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class FakeSearcher implements SequenceSearcher {
	private int invocations = 0;
	private boolean found;
	private int position;
	
	public FakeSearcher() {
		found = false;
		position = -1;
	}
	
	public FakeSearcher(boolean found, int position) {
		this.found = found;
		this.position = position;
	}

	public int getInvocations() {
		return invocations;
	}
	
	@Override
	public SearchResult search(int elem, int[] seq) {
		invocations += 1;
		return SearchResult.builder().withFound(found).withPosition(position).build();
	}
}
