package bootiful.java21;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class BasicCliParserTest {


    static class BasicArgsParser {

        public enum CliOptions {
            INPUT_FILE, OUTPUT_FILE,
            MAX_LINES, PRINT_LINE_NUMBERS
        }

        public record OptionValue(
                CliOptions option,
                String optionValue) {
        }

        private static CliOptions argToOption(String arg) {
            return CliOptions.valueOf(arg
                    .toUpperCase(Locale.ENGLISH)
                    .replace("-", "_")
            );
        }

        static List<OptionValue> parse(String[] args) {
            var list = new ArrayList<OptionValue>();
            for (var arg : args) {

                if (arg.startsWith("--"))
                    arg = arg.substring(2);

                if (arg.contains("=")) {
                    var strings = arg.split("=");
                    list.add(new OptionValue(argToOption(strings[0]), strings[1]));
                }//
                else {
                    list.add(new OptionValue(argToOption(arg), null));
                }
            }
            return list;
        }
    }

    private final String[] argsArray = new String[]{
            "--input-file=input.txt", "--print-line-numbers"};

    @Test
    void basic() {
        for (var a : BasicArgsParser.parse(argsArray)) {
            var value = a.optionValue();
            var message = switch (a.option()) {
                case INPUT_FILE -> "the input file is " + value;
                case OUTPUT_FILE -> "the output file is " + value;
                case MAX_LINES -> "the max lines is " + Integer.parseInt(value);
                case PRINT_LINE_NUMBERS -> "printing lines ";
            };
            System.out.println(message);
        }
    }


}
