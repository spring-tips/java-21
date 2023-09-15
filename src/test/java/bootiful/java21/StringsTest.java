package bootiful.java21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringsTest {

    @Test
    void nestedIndexOf() throws Exception {
        var letters = "abccdecf";
        Assertions.assertTrue(letters.indexOf("c", 1, 6) != -1);
    }

    @Test
    void emojis() throws Exception {
        var shockedFaceEmoji = "\uD83E\uDD2F";
        System.out.println(shockedFaceEmoji);
        var cp = Character.codePointAt(shockedFaceEmoji.toCharArray(), 0);
        Assertions.assertTrue(Character.isEmoji(cp));
    }

    @Test
    void repeat() {
        var line = new StringBuilder()
                .repeat("=", 10)
                .toString();
        Assertions.assertTrue(line.contains("=========="));
    }
}
