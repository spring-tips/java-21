package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

class SmartSwitchTest {

    private int classicTimeOffCalculator(DayOfWeek dayOfWeek) {

        var timeOff = 0;
        switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY:
                timeOff = 16;
                break;
            case SUNDAY, SATURDAY:
                timeOff = 24;
                break;
        }
        return timeOff;
    }

    private int enhancedTimeOffCalculator(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> 16;
            default -> 24;
        };
    }

    @Test
    void enhancedSwitch() {
        Assertions.assertEquals(classicTimeOffCalculator(DayOfWeek.FRIDAY), enhancedTimeOffCalculator(DayOfWeek.FRIDAY));
        Assertions.assertEquals(classicTimeOffCalculator(DayOfWeek.SATURDAY), enhancedTimeOffCalculator(DayOfWeek.SATURDAY));

    }
}
