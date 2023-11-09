import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();

        outer:
        for (int seed = k;; seed++) {
            Random random = new Random(seed);
            for (int i = 0; i < n; i++) {
                if (random.nextGaussian() > m) {
                    continue outer;
                }
            }
            System.out.println(seed);
            break;
        }
    }
}
