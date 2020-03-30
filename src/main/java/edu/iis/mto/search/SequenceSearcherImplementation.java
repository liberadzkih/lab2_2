package edu.iis.mto.search;

public class SequenceSearcherImplementation implements SequenceSearcher{
    @Override public SearchResult search(int elem, int[] seq) {
        for(int i=0;i<seq.length;i++){
            if(seq[i]==elem){
                return SearchResult.builder()
                                   .withPosition(i)
                                   .withFound(true)
                                   .build();

            }

        }
        return SearchResult.builder()
                           .withPosition(-1)
                           .withFound(false)
                           .build();
    }

}
