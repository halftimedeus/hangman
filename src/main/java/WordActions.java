import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class WordActions {
    public static String word;
    final static char mask = '-';
    static StringBuilder maskedWord = new StringBuilder();
    static Random random = new Random();

    static void getRandomWord() { // достает случайное слово из словаря
        InputStream resource = WordActions.class.getClassLoader().getResourceAsStream("dictionary.txt");
        List<String> words = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().toList();

        int index = random.nextInt(words.size());
        word = words.get(index);
    }


    static void maskWord() { // маскирует слово через StringBuilder
        for (int i = 0; i < word.length(); i++) {
            maskedWord.append(mask);
        }
    }

    static void printMaskedWord() {
        System.out.print("Mystery Word: ");
        System.out.println(maskedWord.toString());
    }
}
