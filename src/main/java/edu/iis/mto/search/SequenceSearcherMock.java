package edu.iis.mto.search;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SequenceSearcherMock implements SequenceSearcher {

    private static final class MethodInvocation {
        int elem;
        int[] seq;

        public MethodInvocation(int elem, int[] seq) {
            this.elem = elem;
            this.seq = seq;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MethodInvocation that = (MethodInvocation) o;

            if (elem != that.elem) return false;
            return Arrays.equals(seq, that.seq);
        }

        @Override
        public int hashCode() {
            int result = elem;
            result = 31 * result + Arrays.hashCode(seq);
            return result;
        }
    }

    private final Set<MethodInvocation> methodInvocationSet = new HashSet<>();

    void verifyCalled(int elem, int[] seq) {
        boolean called = methodInvocationSet.contains(new MethodInvocation(elem, seq));
        if(!called) {
            throw new AssertionError("Expected invocation wasn't called!");
        }
    }

    @Override
    public SearchResult search(int elem, int[] seq) {
        methodInvocationSet.add(new MethodInvocation(elem, seq));
        return null;
    }

    public int getAmountOfInvocations() {
        return methodInvocationSet.size();
    }
}
