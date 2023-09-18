package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class RecordTest {

    record JdkReleasedEvent(Instant instant, String name) {
    }

    @Test
    void records() throws Exception {
        var now = Instant.now();
        var name = "JdkReleasedEvent";
        var event = new JdkReleasedEvent(
                now, name);
        Assertions.assertEquals(
                now, event.instant()
        );
        Assertions.assertEquals(name, event.name());

    }
}
