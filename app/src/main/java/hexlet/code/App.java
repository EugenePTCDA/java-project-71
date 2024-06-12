package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff", //Хер знает, почему не "App", но поменять всегда успею
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true, //Одной строкой добавляет --help
        version = "gendiff 1.0" //Пусть пока так
)

public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "Path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "Path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "Output format [default: stylish]")
    private String format = "stylish";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        if (!Files.exists(path1) || !Files.exists(path2)) {
            System.err.println("One or both files do not exist.");
            return 1;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        /*
        Анонимный внутренний класс:

        Мы создаём анонимный внутренний класс, который наследуется от TypeReference<T>.
        Этот анонимный класс фиксирует информацию о типе T во время компиляции.

        TypeReference:

        TypeReference — это вспомогательный класс в библиотеке Jackson, который позволяет захватить и
        сохранить информацию о типе во время выполнения программы.
        Он используется для передачи информации о типе в методы Jackson, такие как readValue.
         */
        Map<String, Object> map1 = objectMapper.readValue(path1.toFile(), new TypeReference<>() { });
        Map<String, Object> map2 = objectMapper.readValue(path2.toFile(), new TypeReference<>() { });

        String differences = findDifferences(map1, map2);
        System.out.println(differences);

        return 0;
    }

    public String findDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("{\n");
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append(String.format("  - %s: %s%n", key, map1.get(key)));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append(String.format("  + %s: %s%n", key, map2.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    result.append(String.format("    %s: %s%n", key, map1.get(key)));
                } else {
                    result.append(String.format("  - %s: %s%n", key, map1.get(key)));
                    result.append(String.format("  + %s: %s%n", key, map2.get(key)));
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
