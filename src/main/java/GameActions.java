import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameActions {
    static char userGuess;
    static ArrayList<Character> lettersGuessed = new ArrayList<>(); // хранит список всех названных букв
    static Scanner scanner = new Scanner(System.in);

    static void makeGuess() throws InterruptedException {
        System.out.print("Guess a letter: ");
        userGuess = getUserInput().toLowerCase().charAt(0); /* присваивает полю userGuess букву, которую выбрал игрок */
        if (!lettersGuessed.isEmpty()) { /* на второй ход запускает проверку и не дает ввести ту же букву дважды */
            while (isLetterUsed()) {
                System.out.println("Nope, still wrong... Try another letter!");
                userGuess = getUserInput().toLowerCase().charAt(0);
            }
        }
        lettersGuessed.add(userGuess); /* добавляем букву, выбранную игроком, в ArrayList для проверки выше */

        GameState.reduceGuesses();
        putUserGuess();
        Thread.sleep(800);
    }

    private static String getUserInput() {
        //получаем от игрока догадку, проверяем ввод и выдаем ряд ошибок в случае неверного ввода
        String userInput = scanner.nextLine();
        while (isInputValid(userInput) != 0) {
            if (isInputValid(userInput) == 1) {
                System.out.println("Type ONE letter A - Z to make a guess");
            } else if (isInputValid(userInput) == 2) {
                System.out.println("Type one LETTER A - Z to make a guess");
            }
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    static int isInputValid(String str) {
        //проверяем ввод, выдаем код ошибки, где 1 - длина строки больше одного символа, 2 - введена не буква,
        // 0 - ввод верный
        if (str.length() != 1) {
            return 1;
        }
        char c = Character.toLowerCase(str.charAt(0));
        if (!(c >= 'a' && c <= 'z')) {
            return 2;
        }
        return 0;
    }

    static boolean isLetterUsed() { /* проверяем, не пытается ли игрок ввести одну и ту же букву дважды */
        return lettersGuessed.contains(userGuess);
    }

    static void printGuessesLeft() {
        System.out.println("Guesses remaining: " + GameState.guesses);
        System.out.println();
    }

    static void printGuessedLetters() {
        System.out.println("Your guesses: " + formatGuessedLetters());
    }

    static String formatGuessedLetters() { // форматирует угаданные буквы в виде ['$буква', '$буква']
        StringBuilder guessedLetters = new StringBuilder("[");
        for (int i = lettersGuessed.size() - 1; i >= 0; i--) {
            guessedLetters.append("'").append(lettersGuessed.get(i)).append("'");
            if (i != 0) { // не добавляем запятую после последней буквы
                guessedLetters.append(",");
            }
        }
        guessedLetters.append("]");
        return guessedLetters.toString();
    }

    static ArrayList<Integer> indices = new ArrayList<>(); // ищем букву, которую назвал игрок, среди всех букв слова
    // храним индексы, если догадка = буква в слове

    static void putUserGuess() throws InterruptedException {
        // если игрок угадал букву, использует индексы из листа выше, чтобы заменить все угаданные буквы
        // в загаданном слове на нужные буквы
        if (checkCorrectLetter(userGuess)) {
            findAllGuessIndices();
            for (Integer index : indices) {
                WordActions.maskedWord.setCharAt(index, userGuess);
            }
            indices.clear();
        }
    }

    static void findAllGuessIndices() { //находит все совпадения между догадкой игрока и буквами слова
        int index = WordActions.word.indexOf(userGuess);
        while (index != -1) {
            indices.add(index);
            index = WordActions.word.indexOf(userGuess, index + 1);
        }
    }

    static boolean checkCorrectLetter(char userInput) throws InterruptedException {
        //поощряет игрока за угаданную букву
        if (WordActions.word.indexOf(userInput) != -1) {
            System.out.println(getRandomPhrase());
            System.out.println();
            return true;
        } else {
            System.out.println();
            System.out.println("Alas, your guess was wrong");
            if (GameState.guesses > 0) {
                System.out.println("Have another try!");
                Thread.sleep(300);
            }
            System.out.println();
            return false;
        }
    }

    static String getRandomPhrase() {
        //генерирует рандомную поощрительную фразу
        Random random = new Random();
        String[] phrases = {"You're on fire, keep it up!",
                "You're a rockstar, don't ever change!",
                "You must be a magician, because that was magical!",
                "Holy guacamole, you nailed it!",
                "You just crushed it like a grape, nice work!",
                "Well, aren't you just a smarty pants!",
                "You're a genius, no really, you are!",
                "Wow, you must have been born to do this!",
                "You're killing it, keep slaying!",
                "Go get 'em, tiger!"};
        int randomPhraseIndex = random.nextInt(phrases.length);
        return phrases[randomPhraseIndex];
    }

    public static void gameLoop() throws InterruptedException {
        WordActions.printMaskedWord();
        GameActions.printGuessedLetters();
        if (!GameActions.lettersGuessed.isEmpty()) {
            GameState.printStage();
        }
        GameActions.printGuessesLeft();
        GameActions.makeGuess();
        GameState.checkGameState();
    }

}
