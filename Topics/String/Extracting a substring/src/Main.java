import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String text = scanner.nextLine();


        int start = scanner.nextInt();
        int end = scanner.nextInt();

        if (start >= 0 && end < text.length()) {
            System.out.println(text.substring(start, end + 1));
        } else {
            System.out.println("Invalid range values");
        }

        scanner.close();
    }
}