package hexlet.code.parsers;

import hexlet.code.interfaces.Parser;

import java.util.HashMap;
import java.util.Map;

public class ParserFactory {
    private static final Map<String, Class<? extends Parser>> REGISTRY = new HashMap<>();

    static {
        REGISTRY.put("json", JsonParser.class);
        REGISTRY.put("yaml", YamlParser.class);
        REGISTRY.put("yml", YamlParser.class);
    }

    public static Parser getParser(String extension) throws RuntimeException {
        Class<? extends Parser> extensionClass = REGISTRY.get(extension);
        if (extensionClass != null) {
            try {
                return extensionClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create extension instance", e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported extension");
        }
    }
}
