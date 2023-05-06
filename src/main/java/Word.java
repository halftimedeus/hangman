import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class Word {
    final char mask = '-'; // change the mask symbol
    StringBuilder maskedWord;

    private final String word;

    public Word() {
        this.word = getRandomWord();
        this.maskedWord = maskWord(word);
    }
    public String getWord() {
        return word;
    }

    private String getRandomWord() { // get random word from a dictionary file
        InputStream resource = Word.class.getClassLoader().getResourceAsStream("dictionary.txt");
        List<String> words = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().toList();

        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index); /* return random word from a dictionary */
    }

    private StringBuilder maskWord(String word) { // маскирует слово через StringBuilder
        maskedWord.append(String.valueOf(mask).repeat(word.length()));
        return maskedWord;
    }

    void printMaskedWord() {
        System.out.print("Mystery Word: ");
        System.out.println(this.maskedWord.toString());
    }
}
