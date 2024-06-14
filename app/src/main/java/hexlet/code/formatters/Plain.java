package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    public static String format(Map<String, Map<String, Object>> map) {
        StringJoiner result = new StringJoiner("\n");
        for (String key : map.keySet()) {
            Map<String, Object> innerMap = map.get(key);
            String status = innerMap.get("status").toString();
            var oldValue = getStringValue(innerMap.get("old_value"));
            var newValue = getStringValue(innerMap.get("new_value"));

            switch (status) {
                case "added":
                    result.add(String.format("Property '%s' was added with value: %s", key, newValue));
                    break;
                case "deleted":
                    result.add(String.format("Property '%s' was removed", key));
                    break;
                case "unchanged":
                    break;
                case "changed":
                    result.add(String.format("Property '%s' was updated. From %s to %s", key, oldValue, newValue));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + status);

            }
        }
        return result.toString();
    }

    private static Object getStringValue(Object value) {
        if (value instanceof ArrayList || value instanceof LinkedHashMap) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value;
        }
    }
}
