package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static readability.AgesTable.getGroupByScore;

public class Main {

    private static final double DOUBLE0_5 = 0.5;
    private static final double DOUBLE4_71 = 4.71;
    private static final double DOUBLE21_43 = 21.43;

    public static String readFileToString(String fName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fName)));
    }

    public static int countSyllables(String word) {
        String vowels = "aeiouy";
        word = word.toLowerCase();

        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (vowels.indexOf(word.charAt(i)) >= 0) {
                count++;
                if (i < word.length() - 1 && vowels.indexOf(word.charAt(i + 1)) >= 0) {
                    count--;
                }
            }
        }

        if (word.endsWith("e")) {
            count--;
        }

        if (count == 0) {
            count = 1;
        }

        return count;
    }

    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                String text = readFileToString(args[0]);
                int scoreFloorPrecision = 2;

                int sentences = text.split("[?.!]+\\s*").length;
                String[] wordsArray = text.split("\\s*(?<!\\d)[-?.!,:;\\s]+|[-?.!,:;\\s]+(?!\\d)\\s*");
                int words = wordsArray.length;
                int characters = text.replace("\s", "").length();
                int syllables = Arrays.stream(wordsArray).mapToInt(Main::countSyllables).sum();
                int polysyllables = (int) Arrays.stream(wordsArray).filter(word -> countSyllables(word) > 2).count();
                System.out.printf("The text is:%n%s%n%n", text);
                System.out.printf("Words: %s%n", words);
                System.out.printf("Sentences: %s%n", sentences);
                System.out.printf("Characters: %s%n", characters);
                System.out.printf("Syllables: %s%n", syllables);
                System.out.printf("Polysyllables: %s%n", polysyllables);

                double scoreARI = DOUBLE4_71 * characters / words + DOUBLE0_5 * words / sentences - DOUBLE21_43;
                double scoreFK = 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
                double scoreSMOG = 1.043 * Math.sqrt(polysyllables * 30 / sentences) + 3.1291;
                double scoreCL = 0.0588 * characters / words * 100 - 0.296 * sentences / words * 100 - 15.8;

                double scoreTruncated = truncate(scoreARI, scoreFloorPrecision);

                System.out.printf("The score is: %s%n", scoreTruncated);
                System.out.printf("This text should be understood by %s.%n", getGroupByScore(scoreARI));
                System.out.printf("Automated Readability Index: %.2f (about %s).%n", scoreARI, getGroupByScore(scoreARI));
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s).%n", scoreFK, getGroupByScore(scoreFK));
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s).%n", scoreSMOG, getGroupByScore(scoreSMOG));
                System.out.printf("Coleman–Liau index: %.2f (about %s).%n", scoreCL, getGroupByScore(scoreCL));

            } else {
                System.out.println("Please provide a file name as a command line argument.");
            }

        } catch(IOException err){
            System.out.println(err.getMessage());
        }
    }

    public static double truncate(double n, int decimals) {
        int whole = (int) (n * Math.pow(10, decimals));
        return whole / Math.pow(10, decimals);
    }
}