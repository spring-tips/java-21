package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathTest {

    @Test
    void division() {
        var five = Math.divideExact(10, 2);
        Assertions.assertEquals(five, 5.0);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math.divideExact(Integer.MIN_VALUE, -1);
        });

    }
}
