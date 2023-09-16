package bootiful.java21;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

class DatesTest {

    @Test
    void format() throws Exception {


        // todo
        var formatter = DateTimeFormatter.ofLocalizedPattern("") ;
        var builder = new DateTimeFormatterBuilder().appendLocalized();

    }
}
