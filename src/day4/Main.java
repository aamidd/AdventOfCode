package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        int sum =
                Files.lines(Paths.get("day4input"))
                        .mapToInt(Main::cardsWorth)
                        .sum();
        System.out.println(sum);
    }

    private static int cardsWorth(String line) {
        long count = numberOfMatches(line);
        if (count == 0)
            return 0;
        return (int) Math.pow(2, count - 1);
    }

    private static int numberOfMatches(String line) {
        String card = line.substring(line.indexOf(":") + 2);
        ArrayList<String> ownedNumbers = new ArrayList<>(List.of(card.split("\\|")[1].split(" ")));

        return (int) Arrays.stream(card.split("\\|")[0].split(" "))
                .filter(number -> !number.isEmpty())
                .filter(ownedNumbers::contains)
                .count();
    }
}
