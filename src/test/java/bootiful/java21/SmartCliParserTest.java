package bootiful.java21;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class SmartCliParserTest {

    static class SmartArgsParser {
        sealed interface Option {
            record InputFile(Path path) implements Option {
            }

            record OutputFile(Path path) implements Option {
            }

            record MaxLines(int maxLines) implements Option {
            }

            record PrintLineNumbers() implements Option {
            }
        }

        static List<Option> parse(String[] args) {
            var list = new ArrayList<Option>();
            for (var a : BasicCliParserTest.BasicArgsParser.parse(args)) {
                list.add(switch (a.option()) {
                    case OUTPUT_FILE -> new Option.OutputFile(Path.of(a.optionValue()));
                    case INPUT_FILE -> new Option.InputFile(Path.of(a.optionValue()));
                    case MAX_LINES -> new Option.MaxLines(Integer.parseInt(a.optionValue()));
                    case PRINT_LINE_NUMBERS -> new Option.PrintLineNumbers();
                });
            }
            return list;
        }
    }

    private final String[] argsArray = new String[]{
            "--input-file=input.txt", "--print-line-numbers"};

    @Test
    void smart() {
        for (var a : SmartArgsParser.parse(argsArray)) {
            var message = switch (a) {
                case SmartArgsParser.Option.InputFile(Path path) ->
                        "the input file is " + path;
                case SmartArgsParser.Option.OutputFile(var path) ->
                        "the output file is " + path;
                case SmartArgsParser.Option.MaxLines(var maxLines) ->
                        "the max lines is " + maxLines;
                case SmartArgsParser.Option.PrintLineNumbers pln ->
                        "printing lines";
            };
            System.out.println(message);
        }

    }
}
