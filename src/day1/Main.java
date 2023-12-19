package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Path input = Paths.get("input");
        int partOneSum =
                Files.lines(input)
                        .mapToInt(Main::firstAndLast)
                        .sum();
        int partTwoSum =
                Files.lines(input)
                        .map(Main::toDigits)
                        .mapToInt(Main::firstAndLast)
                        .sum();

        System.out.printf("answer to part one: %d // part one only asked to give the first and last occurrence of digits " +
                "%nanswer to part two: %d // part two asked to include the numbers written in words too%n", partOneSum, partTwoSum);
    }
    private static String toDigits(String line) {
        return line.replaceAll("one", "o1e")
                .replaceAll("two", "t2o")
                .replaceAll("three", "t3e")
                .replaceAll("four", "f4r")
                .replaceAll("five", "f5e")
                .replaceAll("six", "s6x")
                .replaceAll("seven", "s7n")
                .replaceAll("eight", "e8t")
                .replaceAll("nine", "n9e");
    }

    private static int firstAndLast(String line) {
        String digits = line.replaceAll("\\D+", "");
        String firstAndLast = String.format("%c%c", digits.charAt(0), digits.charAt(digits.length() - 1));
        return Integer.parseInt(firstAndLast);
    }
}