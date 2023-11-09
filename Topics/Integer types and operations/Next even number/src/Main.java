import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int number = scanner.nextInt();

        // Find the next even number following the given number
        int nextEven = (number % 2 == 0) ? number + 2 : number + 1;

        System.out.println(nextEven);
    }
}