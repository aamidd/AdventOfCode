package day6;

public class Main {
    public static void main(String[] args) {
        // part one
        int[] times = {52, 94, 75, 94};
        int[] distances = {426, 1374, 1279, 1216};
        int multi = 1;
        for (int i = 0; i < times.length; i++)
            multi *= (int) numberOfOptions(times[i], distances[i]);
        System.out.println(multi);
        // part two
        long numberOfOptions = numberOfOptions(52947594, 426137412791216L);
        System.out.println(numberOfOptions);
    }
    private static long numberOfOptions(long time, long distance) {
        double delta = Math.pow(time, 2) - (4 * distance);
        long min = (int) Math.floor((time - Math.sqrt(delta)) / 2) + 1;
        long max = (int) Math.ceil((time + Math.sqrt(delta)) / 2) - 1;
        return max - min + 1;
    }
}
