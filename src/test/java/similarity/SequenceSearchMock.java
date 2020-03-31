package similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class SequenceSearchMock implements SequenceSearcher {

    private final Set<MethodInvocation> methodInvocations = new HashSet<>();

    @Override
    public SearchResult search(int elem, int[] seq) {
        methodInvocations.add(new MethodInvocation(elem, seq));
        return SearchResult.builder()
                .withPosition(0)
                .withFound(true)
                .build();
    }

    boolean verifyCalled(int elem, int[] seq) {
        return methodInvocations.contains(new MethodInvocation(elem, seq));
    }

    int countInvocations() {
        return methodInvocations.size();
    }


    private static final class MethodInvocation {
        int elem;
        int[] seq;

        MethodInvocation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            MethodInvocation that = (MethodInvocation) o;
            return elem == that.elem &&
                    seq == that.seq;
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(elem);
            result = 31 * result + Arrays.hashCode(seq);
            return result;
        }
    }
}
