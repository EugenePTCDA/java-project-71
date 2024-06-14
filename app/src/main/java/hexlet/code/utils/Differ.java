package hexlet.code.utils;

import hexlet.code.interfaces.Parser;
import hexlet.code.parsers.ParserFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String path1, String path2) throws Exception {
        final String defaultFormat = "stylish";
        return generate(path1, path2, defaultFormat);
    }

    public static String generate(String path1, String path2, String format) throws Exception {
        final Map<String, Object> data1 = getData(path1);
        final Map<String, Object> data2 = getData(path2);
        Map<String, Map<String, Object>> difference = DiffBuilder.build(data1, data2);
        return Formatter.choiceFormat(difference, format);
    }

    private static String getExtension(String path) {
        int lastDotIndex = path.lastIndexOf(".");
        if (lastDotIndex >= 0) {
            return path.substring(lastDotIndex + 1);
        } else {
            throw new IllegalArgumentException("Invalid path: " + path);
        }
    }
    private static Map<String, Object> getData(String path) throws Exception {
        String absolutePath = Paths.get(path).toAbsolutePath().normalize().toString();
        String content = Files.readString(Paths.get(absolutePath));
        Parser parser = ParserFactory.getParser(getExtension(absolutePath));
        return parser.parse(content);
    }
}
