package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String format(Map<String, Map<String, Object>> map) {
        StringBuilder result = new StringBuilder("{\n");

        for (String key : map.keySet()) {
            Map<String, Object> innerMap = map.get(key);
            String status = innerMap.get("status").toString();
            Object oldValue = innerMap.get("old_value");
            Object newValue = innerMap.get("new_value");

            switch (status) {
                case "added" -> {
                    result.append("  ");
                    result.append("+ ");
                    result.append(key).append(": ");
                    result.append(newValue).append("\n");
                }
                case "deleted" -> {
                    result.append("  ");
                    result.append("- ");
                    result.append(key).append(": ");
                    result.append(oldValue).append("\n");
                }
                case "unchanged" -> {
                    result.append("    ");
                    result.append(key).append(": ");
                    result.append(oldValue).append("\n");
                }
                case "changed" -> {
                    result.append("  ");
                    result.append("- ");
                    result.append(key).append(": ");
                    result.append(oldValue).append("\n");
                    result.append("  ");
                    result.append("+ ");
                    result.append(key).append(": ");
                    result.append(newValue).append("\n");
                }
                default -> throw new RuntimeException("Unexpected value: " + status);
            }
        }
        result.append("}");
        return result.toString();
    }
}
