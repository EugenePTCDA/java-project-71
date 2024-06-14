package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.interfaces.Parser;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;
import java.util.TreeMap;

public final class YamlParser implements Parser {

    @Override
    public Map<String, Object> parse(String content) throws Exception {
        final ObjectMapper mapper = new YAMLMapper();
        final TreeMap<String, Object> parseData;
        parseData = mapper.readValue(content, new TypeReference<>() {
        });
        return parseData;
    }
}
