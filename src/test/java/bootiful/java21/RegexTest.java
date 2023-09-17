package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
class RegexTest {

    @Test
    void regexMatching() throws Exception {
        var pattern = Pattern
                .compile("(?<first>[a-zA-Z]+)\s+(<last>[a-zA-Z]+)$");
        var matcher = pattern.matcher("Josh Long");
        // new in Java 21
        var mr = matcher.toMatchResult();
        if (mr.hasMatch()) {
            var first = matcher.group("first");
            var last = matcher.group("last");
            Assertions.assertEquals(first, "Josh");
            Assertions.assertEquals(last, "Long");
        }

    }
}
