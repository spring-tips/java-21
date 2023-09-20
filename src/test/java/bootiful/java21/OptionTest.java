package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OptionTest {

    sealed interface Option<T> {
    }

    record Some<T>(T value) implements Option<T> {
    }

    record None<T>() implements Option<T> {
    }

    @Test
    void someNone() {
        Assertions.assertEquals("the value is HELLO, WORLD",
                handle(new Some<>("hello, world")));
        Assertions.assertEquals(null,
                handle(new None<>()));

    }

    String handle(Option<String> test) {
        return switch (test) {
            case None<String>() -> null;
            case Some<String>(var value) -> "the value is " + value.toUpperCase();
        };
    }
}
