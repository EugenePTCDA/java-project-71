package hexlet.code.utils;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String choiceFormat(Map<String, Map<String, Object>> diffMap, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.format(diffMap);
            case "plain" -> Plain.format(diffMap);
            case "json" -> Json.format(diffMap);
            default -> throw new Exception("There is unknown output format.");
        };
    }
}
