package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SealedTypesTest {

    sealed interface Animal permits Bird, Cat, Dog {
    }

    final class Cat implements Animal {
        String meow() {
            return "meow";
        }
    }

    final class Dog implements Animal {
        String bark() {
            return "woof";
        }
    }

    final class Bird implements Animal {
        String chirp() {
            return "chirp";
        }
    }

    @Test
    void dolittleTest() {
        Assertions.assertEquals(communicate(new Dog()), "woof");
        Assertions.assertEquals(communicate(new Cat()), "meow");
    }

    String communicate(Animal animal) {
        return switch (animal) {
            case Cat cat -> cat.meow();
            case Dog dog -> dog.bark();
            case Bird bird -> bird.chirp();
        };
    }

    String classicCommunicate(Animal animal) {
        var message = (String) null;
        if (animal instanceof Dog dog) {
            message = dog.bark();
        }
        if (animal instanceof Cat cat) {
            message = cat.meow();
        }
        if (animal instanceof Bird bird) {
            message = bird.chirp();
        }
        return message;
    }


}
