import hexlet.code.App;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class AppTest {

    @Test
    void testFindDifferences() {
        App app = new App();

        Map<String, Object> map1 = new TreeMap<>();
        map1.put("host", "hexlet.io");
        map1.put("timeout", 50);
        map1.put("proxy", "123.234.53.22");
        map1.put("follow", false);

        Map<String, Object> map2 = new TreeMap<>();
        map2.put("timeout", 20);
        map2.put("verbose", true);
        map2.put("host", "hexlet.io");

        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String result = app.findDifferences(map1, map2);
        assertLinesMatch(expected.lines().toList(), result.lines().toList());
    }
}
