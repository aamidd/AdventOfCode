package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int sum =
                Files.lines(Paths.get("day4input"))
                        .mapToInt(Main::worthOfCard)
                        .sum();
        System.out.println(sum);
    }

    private static int worthOfCard(String line) {
        String card = line.substring(line.indexOf(":") + 2);
        ArrayList<String> ownedNumbers = new ArrayList<>(List.of(card.split("\\|")[1].split(" ")));

        long count = Arrays.stream(card.split("\\|")[0].split(" "))
                .filter(number -> !number.isEmpty())
                .filter(ownedNumbers::contains)
                .count();
        if (count == 0)
            return 0;
        return (int) Math.pow(2, count - 1);
    }
}
