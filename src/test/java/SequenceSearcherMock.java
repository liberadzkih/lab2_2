import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.HashSet;
import java.util.Set;

public class SequenceSearcherMock implements SequenceSearcher {

    private Set<Invocation> invocationSet = new HashSet<>();

    @Override
    public SearchResult search(int elem, int[] seq) {
        invocationSet.add(new Invocation(elem, seq));
        return SearchResult.builder().build();
    }

    void verifyInvocationNumber(int invocationNumber){
        if(invocationNumber != invocationSet.size())
            throw new AssertionError("Bad number of invocations.");
    }
    void verifyInvocation(int elem, int[] seq) {
        if (!invocationSet.contains(new Invocation(elem, seq)))
            throw new AssertionError("Invocation didn't match.");
    }

    private static class Invocation {

        int elem;
        int[] seq;

        Invocation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }
    }
}
