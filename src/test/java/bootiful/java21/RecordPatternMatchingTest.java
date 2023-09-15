package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecordPatternMatchingTest {

    record User(String name, long accountNumber) {
    }

    record UserDeletedAccountEvent(User user) {
    }

    record UserCreatedAccountEvent(String name) {
    }

    private String handleEvent(Object object) {
        return switch (object) {
            case UserCreatedAccountEvent(String user) -> "congrats, " + user + ", on creating a new account";
            case UserDeletedAccountEvent(User(String name, long __)) -> "we're sorry to see you go, " + name;
            case null, default -> throw new IllegalArgumentException("the record is null");
        };
    }

    @Test
    void recordsPatterns() {
        Assertions.assertEquals(handleEvent(new UserCreatedAccountEvent("Josh")),
                "congrats, Josh, on creating a new account");
        Assertions.assertEquals(handleEvent(new UserDeletedAccountEvent(new User("Josh", 2343L))),
                "we're sorry to see you go, " + "Josh");
    }
}
