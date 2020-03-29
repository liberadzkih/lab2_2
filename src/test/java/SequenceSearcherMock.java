import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class SequenceSearcherMock implements SequenceSearcher {

    private static class Invocation {

        int elem;
        int[] seq;

        Invocation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            Invocation invocation = (Invocation) object;
            return invocation.elem == this.elem && Arrays.equals(invocation.seq, this.seq);
        }

        @Override
        public int hashCode() {
            return Objects.hash(elem, Arrays.hashCode(seq));
        }


    }

    private HashSet<Invocation> invocations = new HashSet<>();

    void verifyInvocationNumber(int invocationNumber){
        if(invocationNumber != invocations.size())
            throw new AssertionError("Bad number of invocations.");
    }
    void verifyInvocation(int elem, int[] seq) {
        if (!invocations.contains(new Invocation(elem, seq)))
            throw new AssertionError("Didn't match invocation.");
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        invocations.add(new Invocation(elem, seq));
        return SearchResult.builder().build();
    }
}
