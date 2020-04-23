package edu.iis.mto.similarity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherMock implements SequenceSearcher {
    private Set<Invocation> invocationMemory = new HashSet<>();

    public int getNumberOfInvocations() {
        return invocationMemory.size();
    }

    public boolean checkIfContainsInvocation(int elem, int[] seq) {
        if (!invocationMemory.contains(new Invocation(elem, seq))) {
            return false;
        }
        return true;
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        invocationMemory.add(new Invocation(elem, seq));
        return SearchResult.builder().build();
    }

    private static class Invocation {

        int elem;
        int[] seq;

        Invocation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + elem;
            result = prime * result + Arrays.hashCode(seq);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Invocation other = (Invocation) obj;
            if (elem != other.elem)
                return false;
            if (!Arrays.equals(seq, other.seq))
                return false;
            return true;
        }
    }
}
