package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SealedTypesPatternMatchingTest {

    sealed interface Animal permits Dog, Cat {
    }

    final class Dog implements Animal {
        String bark() {
            return "woof";
        }
    }

    final class Cat implements Animal {

        String meow() {
            return "meow";
        }
    }

    String messageFromSealedTypes(Animal animal) {
        return switch (animal) {
            case Dog d -> d.bark();
            case Cat c -> c.meow();
            case null, default ->
                throw new IllegalArgumentException("the animal can not be null!");
        };
    }

    @Test
    void sealedPatternMatching() throws Exception {
        Assertions.assertEquals("meow", messageFromSealedTypes(new Cat()));
        Assertions.assertEquals("woof", messageFromSealedTypes(new Dog()));
    }


}
