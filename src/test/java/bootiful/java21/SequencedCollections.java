package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.SequencedCollection;


class SequencedCollections {

    @Test
    void ordering() throws Exception {
        var list = new ArrayList<String>();
        if (list instanceof SequencedCollection<String> sequencedCollection) {
            sequencedCollection.add("ciao");
            Assertions.assertEquals(list.get(0), "ciao");
            sequencedCollection.addFirst("hello");
            Assertions.assertEquals(list.get(0), "hello");
            sequencedCollection.add("hola");
            sequencedCollection.add("ni hao");
            sequencedCollection.addLast("salut");
            Assertions.assertEquals(list.get(4), "salut");
        }


    }
}
