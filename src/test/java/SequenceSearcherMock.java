import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SequenceSearcherMock implements SequenceSearcher {
    private Set<Invocation> invocationSet = new HashSet<>();

    private class Invocation {
        int element;
        int[] sequence;

        Invocation(int element, int[] sequence) {
            this.element = element;
            this.sequence = sequence;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Invocation that = (Invocation) o;
            return element == that.element &&
                    Arrays.equals(sequence, that.sequence);
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, Arrays.hashCode(sequence));
        }
    }

    boolean checkIfCalled(int element, int[] sequence) {
        return invocationSet.contains(new Invocation(element, sequence));
    }

    int getNumberOfInvocations() {
        return invocationSet.size();
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        invocationSet.add(new Invocation(elem, seq));
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] == elem) {
                return SearchResult.builder().withFound(true).withPosition(i).build();
            }
        }
        return SearchResult.builder().withFound(false).withPosition(-1).build();
    }
}
