package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultilineStringTest {

    @Test
    void multiline() throws Exception {

        var shakespeare = """
                                
                To be, or not to be, that is the question:
                Whether 'tis nobler in the mind to suffer
                The slings and arrows of outrageous fortune,
                Or to take arms against a sea of troubles
                And by opposing end them. To die—to sleep,
                No more; and by a sleep to say we end
                The heart-ache and the thousand natural shocks
                That flesh is heir to: 'tis a consummation
                Devoutly to be wish'd. To die, to sleep;
                To sleep, perchance to dream—ay, there's the rub:
                For in that sleep of death what dreams may come,
                """;
        Assertions.assertNotEquals(shakespeare.charAt(0), 'T');

        shakespeare = shakespeare.stripLeading();
        Assertions.assertEquals(shakespeare.charAt(0), 'T');
    }

}
