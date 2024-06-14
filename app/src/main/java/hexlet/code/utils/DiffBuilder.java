package hexlet.code.utils;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {
    public static Map<String, Map<String, Object>> build(Map<String, Object> firstFile,
                                                         Map<String, Object> secondFile) {
        Set<String> keys = new TreeSet<>();
        Map<String, Map<String, Object>> comparedMap = new LinkedHashMap<>();

        keys.addAll(firstFile.keySet());
        keys.addAll(secondFile.keySet());

        for (String key : keys) {
            if (!firstFile.containsKey(key)) {
                Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("status", "added");
                tempMap.put("old_value", "");
                tempMap.put("new_value", secondFile.get(key));
                comparedMap.put(key, tempMap);
            } else if (!secondFile.containsKey(key)) {
                Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("status", "deleted");
                tempMap.put("old_value", firstFile.get(key));
                tempMap.put("new_value", "");
                comparedMap.put(key, tempMap);
            } else {
                var firstValue = firstFile.get(key);
                var secondValue = secondFile.get(key);
                Map<String, Object> tempMap = new HashMap<>();
                if (!ObjectUtils.notEqual(firstValue, secondValue)) {
                    tempMap.put("status", "unchanged");
                } else {
                    tempMap.put("status", "changed");
                }
                tempMap.put("old_value", firstValue);
                tempMap.put("new_value", secondValue);
                comparedMap.put(key, tempMap);
            }
        }
        return comparedMap;
    }
}
