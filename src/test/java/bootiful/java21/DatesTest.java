package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

class DatesTest {

    @Test
    void format() throws Exception {
        // todo
        var now = Instant.now().atZone(ZoneId.systemDefault())
                .toLocalDate();
        System.out.println(now.toString());
        var formatter = DateTimeFormatter
                .ofLocalizedPattern("yMMM");
        var manual = formatter.format(now);
        var builder = new DateTimeFormatterBuilder()
                .appendLocalized("yMMM")
                .toFormatter();
        var built = builder.format(now);
        Assertions.assertEquals(built, manual, "the manual and the built String should be the same!");


    }
}
