package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff", //Хер знает, почему не "App", но поменять всегда успею
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true, //Одной строкой добавляет --help
        version = "gendiff 1.0" //Пусть пока так
)

public final class App implements Callable<Integer> {

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
        var result = Differ.generate(filepath1, filepath2, format);
        System.out.println(result);
        return 0;
    }
}
