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
        String digits = line.replaceAll("one", "one1one")
                .replaceAll("two", "two2two")
                .replaceAll("three", "three3three")
                .replaceAll("four", "four4four")
                .replaceAll("five", "five5five")
                .replaceAll("six", "six6six")
                .replaceAll("seven", "seven7seven")
                .replaceAll("eight", "eight8eight")
                .replaceAll("nine", "nine9nine")
                .replaceAll("\\D+", "");

        String firstAndLast = String.valueOf(digits.charAt(0)) + digits.charAt(digits.length() - 1);
        return Integer.parseInt(firstAndLast);
    }
}
