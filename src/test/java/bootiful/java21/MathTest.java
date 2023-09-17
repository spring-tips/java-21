package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class MathTest {

    @Test
    void bigInteger() throws Exception {
        var start = BigInteger.valueOf(102);
        var result = start.parallelMultiply(BigInteger.TEN);
        Assertions.assertEquals(result, BigInteger.valueOf(102 * 10));
    }

    @Test
    void division() {
        var five = Math.divideExact(10, 2);
        Assertions.assertEquals(five, 5.0);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Math.divideExact(Integer.MIN_VALUE, -1);
        });

    }
}
