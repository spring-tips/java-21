package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SealedTypesTest {

    sealed interface Animal permits Dog, Cat {
    }

    static final class Dog implements Animal {
        String bark() {
            return "woof";
        }
    }

    static final class Cat implements Animal {
        String meow() {
            return "meow";
        }
    }

    String messageFromSealedTypes(Animal animal) {
        return switch (animal) {
            case Dog d -> d.bark();
            case Cat c -> c.meow();
        };
    }

    @Test
    void sealedPatternMatching() throws Exception {
        Assertions.assertEquals("meow", messageFromSealedTypes(new Cat()));
        Assertions.assertEquals("woof", messageFromSealedTypes(new Dog()));
    }


}
