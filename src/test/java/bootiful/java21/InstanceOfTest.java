package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InstanceOfTest {

    class Animal  {
    }

    class Cat extends Animal {
        String meow() {
            return "meow!";
        }
    }

    class Dog extends Animal {
        String bark() {
            return "bark!";
        }
    }

    private String communicate(Animal animal) {
        var message = (String) null;
        if (animal instanceof Dog dog) message = dog.bark();
        if (animal instanceof Cat cat) message = cat.meow();
        return message;
    }

    @Test
    void instanceOfCheck() {
        var animal = (Animal) new Cat();
        Assertions.assertEquals(communicate(animal), "meow!");
    }


}
