package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecordTest {

    record JdkReleasedEvent(String name) { }

    @Test
    void records() throws Exception {
        var event = new JdkReleasedEvent("Java21");
        Assertions.assertEquals( event.name() , "Java21");
        System.out.println(event);

    }
}
