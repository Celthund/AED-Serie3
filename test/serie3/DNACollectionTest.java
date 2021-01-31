package serie3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DNACollectionTest {

    private DNACollection dnaCollection = createCollection();


    @Test
    public void prefixCount_with3LettersPrefix(){
        assertEquals(5, dnaCollection.prefixCount("AAA"));
    }

    private DNACollection createCollection(){
        DNACollection dnaCollection = new DNACollection();
//        put('A', 0);
//        put('C', 1);
//        put('T', 2);
//        put('G', 3);
        dnaCollection.add("CCA");
        dnaCollection.add("TAC");
        dnaCollection.add("AAA");
        dnaCollection.add("AAAC");
        dnaCollection.add("AAAT");
        dnaCollection.add("AAAG");
        dnaCollection.add("AAACA");
        return dnaCollection;
    }

}
