import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int seedWithMinMax = -1;
        int minMax = Integer.MAX_VALUE;

        for (int seed = a; seed <= b; seed++) {
            Random random = new Random(seed);
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                int randomNumber = random.nextInt(k);
                max = Math.max(max, randomNumber);
            }

            if (max < minMax) {
                minMax = max;
                seedWithMinMax = seed;
            }
        }

        System.out.println(seedWithMinMax + "\n" + minMax);
    }
}