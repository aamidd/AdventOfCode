package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) {
        try {
            Map<Integer, Integer> map = Files.lines(Paths.get("day4input"))
                    .map(Part2::parseList)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            int sum = map.size();
            for (int i = map.size(); i >= 1; i--) {
                sum += calcScore(i, map);
            }
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static Map.Entry<Integer, Integer> parseList(String card) {
        ArrayList<String> winningNumbers = new ArrayList<>(List.of(card.split("\\|")[0].split("\\D+")));
        ArrayList<String> ownedNumbers = new ArrayList<>(List.of(card.split("\\|")[1].split(" ")));
        winningNumbers.removeFirst();
        int id = Integer.parseInt(winningNumbers.removeFirst());
        ownedNumbers.removeFirst();
        ownedNumbers.retainAll(winningNumbers);
        return Map.entry(id, ownedNumbers.size());
    }

    private static int calcScore(int index, Map<Integer, Integer> map) {
        int startOfIteration = Math.min(index + map.get(index), map.size());
        int sum = 0;
        if (index + map.get(index) > map.size())
            map.put(index, map.size() - index);
        for (int i = startOfIteration; i >= index; i--) {
            sum += map.get(i);
        }
        map.put(index, sum);
        return sum;
    }
}
