
import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.*;

public class SequenceSearcherMock implements SequenceSearcher {

    SequenceSearcher sequenceSearcher;
    private Set<Invocation> invocations = new HashSet<>();




    @Override
    public SearchResult search(int elem, int[] seq) {
        invocations.add(new Invocation(elem, seq));
        return SearchResult.builder().build();
    }

    private static final class Invocation {
        int elem;
        int[] seq;

        public Invocation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Invocation that = (Invocation) o;
            return elem == that.elem &&
                    Arrays.equals(seq, that.seq);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(elem);
            result = 31 * result + Arrays.hashCode(seq);
            return result;
        }


    }

    void verifyInvocation(int elem, int[] seq) {
        if (!invocations.contains(new Invocation(elem, seq)))
            throw new AssertionError("Invocation did't contains! ");
    }

    void verifyInvocationSize(double invocationSize) {
        if (invocationSize != invocations.size())
            throw new AssertionError("Wrong Invocation size!");
    }

}
