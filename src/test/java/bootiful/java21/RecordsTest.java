package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class RecordsTest {

    record User(String name, long accountNumber) {
    }

    record UserDeletedEvent(User user) {
    }

    record UserCreatedEvent(String name) {
    }

    record ShutdownEvent(Instant instant) {
    }

    @Test
    void respondToEvents() throws Exception {
        Assertions.assertEquals(
                respond(new UserCreatedEvent("jlong")), "the new user with name jlong has been created"
        );
        Assertions.assertEquals(
                respond(new UserDeletedEvent(new User("jlong", 1))),
                "the user jlong has been deleted"
        );
    }

    String respond(Object o) {
        // <1>
        if (o instanceof ShutdownEvent(Instant instant)) {
            System.out.println(
                "going to to shutdown the system at " + instant.toEpochMilli());
        }
        return switch (o) {
            // <2>
            case UserDeletedEvent(var user) -> "the user " + user.name() + " has been deleted";
            // <3>
            case UserCreatedEvent(var name) -> "the new user with name " + name + " has been created";
            default -> null;
        };
    }


}

