import hexlet.code.Differ;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;


final public class AppTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).strip();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("textForTest1.txt").strip();
        resultStylish = readFixture("textForTest2.txt").strip();
        resultPlain = readFixture("textForTest3.txt").strip();
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml", "yaml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertLinesMatch(resultStylish.lines().toList(),
                Differ.generate(filePath1, filePath2).lines().toList());

        assertLinesMatch(resultStylish.lines().toList(),
                Differ.generate(filePath1, filePath2, "stylish").lines().toList());

        assertLinesMatch(resultPlain.lines().toList(),
                Differ.generate(filePath1, filePath2, "plain").lines().toList());

        assertLinesMatch(resultJson.lines().toList(),
                Differ.generate(filePath1, filePath2, "json").lines().toList());
    }
}
