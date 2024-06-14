package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Json {
    public static String format(Map<String, Map<String, Object>> map) throws JsonProcessingException {
        ObjectMapper result = new ObjectMapper();
        Map<String, Object> resultJson = new LinkedHashMap<>();

        for (String key : map.keySet()) {
            Map<String, Object> innerMap = map.get(key);
            String status = innerMap.get("status").toString();
            resultJson.put(key, status);
        }
        return result.writeValueAsString(resultJson);
    }
}
