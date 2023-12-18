package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        int sum =
                Files.lines(Paths.get("input"))
                        .mapToInt(Main::firstAndLast)
                        .sum();

        System.out.println(sum);
    }

    private static int firstAndLast(String line) {
        String digits = line.replaceAll("one", "o1e")
                .replaceAll("two", "t2o")
                .replaceAll("three", "t3e")
                .replaceAll("four", "f4r")
                .replaceAll("five", "f5e")
                .replaceAll("six", "s6x")
                .replaceAll("seven", "s7n")
                .replaceAll("eight", "e8t")
                .replaceAll("nine", "n9e")
                .replaceAll("\\D+", "");

        String firstAndLast = String.valueOf(digits.charAt(0)) + digits.charAt(digits.length() - 1);
        return Integer.parseInt(firstAndLast);
    }
}
