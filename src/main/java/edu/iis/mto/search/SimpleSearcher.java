package edu.iis.mto.search;

import edu.iis.mto.search.SearchResult.Builder;

public class SimpleSearcher implements SequenceSearcher {

	@Override
	public SearchResult search(int elem, int[] seq) {
		Builder builder = SearchResult.builder();
		builder.withFound(false);
		builder.withPosition(-1);
		
		for (int i = 0; i < seq.length; i++) {
			if (seq[i] == elem) {
				builder.withFound(true);
				builder.withPosition(i);
				break;
			}
		}
		
		return builder.build();
	}
}
