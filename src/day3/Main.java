package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day3input"));
            System.out.println(sumPartNumbers(lines.stream().map(Main::spotSymbols).toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String spotSymbols(String line) {
        return line.replaceAll("(?!\\.)\\D", "s");
    }

    private static int sumPartNumbers(List<String> lines) {
        Pattern digits = Pattern.compile("\\d+");
        int sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            Matcher matcher = digits.matcher(currentLine);
            while (matcher.find()) {
                int digitLength = matcher.group().length();
                int digitIndex = currentLine.indexOf(matcher.group());
                int number = Integer.parseInt(matcher.group());
                boolean notAtTheEnd = digitIndex + digitLength < currentLine.length() - 2;

                if (digitIndex > 0 && currentLine.charAt(digitIndex - 1) == 's') {
                    sum += number;
                } else if (notAtTheEnd && currentLine.charAt(digitIndex + digitLength) == 's') {
                    sum += number;
                } else if (i > 0 && digitIndex > 0 && lines.get(i - 1).substring(digitIndex - 1, digitIndex + digitLength).contains("s")) {
                    sum += number;
                } else if (i > 0 && notAtTheEnd && lines.get(i - 1).substring(digitIndex, digitIndex + digitLength + 1).contains("s")) {
                    sum += number;
                } else if (i < lines.size() - 1 && digitIndex > 0 && lines.get(i + 1).substring(digitIndex - 1, digitIndex + digitLength).contains("s")) {
                    sum += number;
                } else if (i < lines.size() - 1 && notAtTheEnd && lines.get(i + 1).substring(digitIndex, digitIndex + digitLength + 1).contains("s")) {
                    sum += number;
                }
                for (int j = 0; j < matcher.group().length(); j++)
                    currentLine = currentLine.replaceFirst(String.valueOf(matcher.group().charAt(j)), "d");
            }
        }
        return sum;
    }
}
