package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day3input"));
            System.out.println(sumGearRatios(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String spotAsterisks(String line) {
        return line.replaceAll("\\*", "a");
    }

    public static String eliminateUseless(String line) {
        return line.replaceAll("(?!\\*)\\D", ".");
    }

    public static int sumGearRatios(List<String> lines) {
        int sum = 0;
        Pattern asterisks = Pattern.compile("a");
        List<String> modifiedLines = lines.stream()
                .map(Main2::eliminateUseless)
                .map(Main2::spotAsterisks)
                .toList();
        for (int i = 0; i < modifiedLines.size(); i++) {
            String currentLine = modifiedLines.get(i);
            Matcher a = asterisks.matcher(currentLine);
            while (a.find()) {
                ArrayList<Integer> numbers = new ArrayList<>();
                int indexOfA = currentLine.indexOf("a");
                if (indexOfA > 0 && String.valueOf(currentLine.charAt(indexOfA - 1)).matches("\\d")) {
                    Matcher digit = Pattern.compile("\\d+").matcher(currentLine);
                    while (digit.find()) {
                        int digitIndex = currentLine.indexOf(digit.group());
                        int digitLength = digit.group().length();
                        if (digitIndex + digitLength == indexOfA) {
                            numbers.add(Integer.parseInt(digit.group()));
                            break;
                        }
                        for (int j = 0; j < digit.group().length(); j++)
                            currentLine = currentLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                    }
                }
                if (indexOfA < currentLine.length() - 1 && String.valueOf(currentLine.charAt(indexOfA + 1)).matches("\\d")) {
                    Matcher digit = Pattern.compile("\\d+").matcher(currentLine);
                    while (digit.find()) {
                        int digitIndex = currentLine.indexOf(digit.group());
                        if (digitIndex - 1 == indexOfA) {
                            numbers.add(Integer.parseInt(digit.group()));
                            break;
                        }
                        for (int j = 0; j < digit.group().length(); j++)
                            currentLine = currentLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                    }
                }
                if (i > 0 && modifiedLines.get(i - 1).charAt(indexOfA) == '.') {
                    String prevLine = modifiedLines.get(i - 1);
                    if (indexOfA > 0 && String.valueOf(prevLine.charAt(indexOfA - 1)).matches("\\d")) {
                        Matcher digit = Pattern.compile("\\d+").matcher(prevLine);
                        while (digit.find()) {
                            int digitIndex = prevLine.indexOf(digit.group());
                            int digitLength = digit.group().length();
                            if (digitIndex + digitLength == indexOfA) {
                                numbers.add(Integer.parseInt(digit.group()));
                                break;
                            }
                            for (int j = 0; j < digit.group().length(); j++)
                                prevLine = prevLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                        }
                    }
                    if (indexOfA < prevLine.length() - 1 && String.valueOf(prevLine.charAt(indexOfA + 1)).matches("\\d")) {
                        Matcher digit = Pattern.compile("\\d+").matcher(prevLine);
                        while (digit.find()) {
                            int digitIndex = prevLine.indexOf(digit.group());
                            if (digitIndex - 1 == indexOfA) {
                                numbers.add(Integer.parseInt(digit.group()));
                                break;
                            }
                            for (int j = 0; j < digit.group().length(); j++)
                                prevLine = prevLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                        }
                    }
                }
                if (i < modifiedLines.size() - 1 && modifiedLines.get(i + 1).charAt(indexOfA) == '.') {
                    String nxtLine = modifiedLines.get(i + 1);
                    if (indexOfA > 0 && String.valueOf(nxtLine.charAt(indexOfA - 1)).matches("\\d")) {
                        Matcher digit = Pattern.compile("\\d+").matcher(nxtLine);
                        while (digit.find()) {
                            int digitIndex = nxtLine.indexOf(digit.group());
                            int digitLength = digit.group().length();
                            if (digitIndex + digitLength == indexOfA) {
                                numbers.add(Integer.parseInt(digit.group()));
                                break;
                            }
                            for (int j = 0; j < digit.group().length(); j++)
                                nxtLine = nxtLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                        }
                    }
                    if (indexOfA < nxtLine.length() - 1 && String.valueOf(nxtLine.charAt(indexOfA + 1)).matches("\\d")) {
                        Matcher digit = Pattern.compile("\\d+").matcher(nxtLine);
                        while (digit.find()) {
                            int digitIndex = nxtLine.indexOf(digit.group());
                            if (digitIndex - 1 == indexOfA) {
                                numbers.add(Integer.parseInt(digit.group()));
                                break;
                            }
                            for (int j = 0; j < digit.group().length(); j++)
                                nxtLine = nxtLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                        }
                    }
                }

                if (i > 0 && modifiedLines.get(i - 1).charAt(indexOfA) != '.' && modifiedLines.get(i - 1).charAt(indexOfA) != 'a') {
                    String prevLine = modifiedLines.get(i - 1);
                    Matcher digit = Pattern.compile("\\d+").matcher(prevLine);
                    while (digit.find()) {
                        int index = prevLine.indexOf(digit.group());
                        int end = index + digit.group().length() - 1;
                        if (indexOfA >= index && indexOfA <= end) {
                            numbers.add(Integer.parseInt(digit.group()));
                            break;
                        }
                        for (int j = 0; j < digit.group().length(); j++)
                            prevLine = prevLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                    }
                }
                if (i < modifiedLines.size() - 1 && modifiedLines.get(i + 1).charAt(indexOfA) != '.' && modifiedLines.get(i + 1).charAt(indexOfA) != 'a') {
                    String nxtLine = modifiedLines.get(i + 1);
                    Matcher digit = Pattern.compile("\\d+").matcher(nxtLine);
                    while (digit.find()) {
                        int index = nxtLine.indexOf(digit.group());
                        int end = index + digit.group().length() - 1;
                        if (indexOfA >= index && indexOfA <= end) {
                            numbers.add(Integer.parseInt(digit.group()));
                            break;
                        }
                        for (int j = 0; j < digit.group().length(); j++)
                            nxtLine = nxtLine.replaceFirst(String.valueOf(digit.group().charAt(j)), "d");
                    }
                }
                if (numbers.size() == 2)
                    sum += numbers.stream().reduce(1, (x, y) -> x * y);
                currentLine = currentLine.replaceFirst("a", "b");
            }
        }
        return sum;
    }
}
