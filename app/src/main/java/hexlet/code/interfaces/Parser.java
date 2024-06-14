package hexlet.code.interfaces;

import java.util.Map;

public interface Parser {
    Map<String, Object> parse(String path) throws Exception;
}
