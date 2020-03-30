import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;


public class SequenceSearcherMock implements SequenceSearcher {
    @Override
    public SearchResult search(int elem, int[] seq) {
        return SearchResult.builder().build();
    }
}
