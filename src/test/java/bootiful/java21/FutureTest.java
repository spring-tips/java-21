package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

class FutureTest {

    @Test
    void future() throws Exception {
        try (var executor = Executors.newSingleThreadExecutor()) {
            var future = executor.submit(() -> "hello, world");
            Thread.sleep(1_00);
            var result = switch (future.state()) {
                case CANCELLED, FAILED -> throw new IllegalArgumentException("the thing failed!");
                case SUCCESS -> future.resultNow();
                default -> null;
            };
            Assertions.assertEquals(result, "hello, world");
        }
    }
}
