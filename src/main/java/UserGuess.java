import java.util.ArrayList;
import java.util.Scanner;

public class UserGuess {
    // ----- USER MAKES A GUESS -----
    static ArrayList<Character> lettersGuessed = new ArrayList<>(); //a list of all user guesses to be displayed

    // 1. get a valid guess from the player
    // 2. add the guessed letter to the ArrayList
    // 3. check if the letter is present in the word
    // 4. change the corresponding mask symbols so that letter
    // 5. print all guesses made

    static void makeGuess(Word mysteryWord, GameInstance newGameRound){
        int guesses = newGameRound.guesses;
        int stageCounter = newGameRound.stageCounter;

        System.out.print("Guess a letter: ");

        char validGuess = getValidatedUserInput(lettersGuessed);
        lettersGuessed.add(validGuess); /* add the guessed letter to the ArrayList */

        if (checkGuessInWord(validGuess, mysteryWord)) {
            putUserGuess(validGuess, mysteryWord);
        } else {
            System.out.println();
            System.out.println("Alas, your guess was wrong");

            newGameRound.setGuesses(guesses - 1);
            newGameRound.setStageCounter(stageCounter + 1);

            if (newGameRound.guesses != 0) {
                System.out.println("Try again!");
            }
            System.out.println();
        }
    }

    public static void printLettersGuessed(GameInstance newGameInstance) {
        System.out.println("Your guesses: " + formatGuessedLetters(lettersGuessed));
    }

    // ----- CHECK GUESS VALIDITY -----
    private static char getValidatedUserInput(ArrayList<Character> lettersGuessed) {
        Scanner scanner = new Scanner(System.in);
        //get user input, validate it, print corresponding error message if input is invalid

        while (true) {
            String userInput = scanner.nextLine();
            String validationResult = isInputValid(userInput);

            if (validationResult.equals ("correct")) {
                char userGuess = userInput.toLowerCase().charAt(0);
                if (!(lettersGuessed.contains(userGuess))) { // double check that the entered letter has not been used
                    return userGuess;
                } else {
                    GameInstance.printErrorMessage("3");
                }
            } else {
                GameInstance.printErrorMessage(validationResult); // if the first check doesn't pass, get back
            }
        }
    }

    private static String isInputValid(String str) {
        //check input and display error code
        if (str.length() != 1) {
            return "1";
        }
        char c = Character.toLowerCase(str.charAt(0));
        if (!(c >= 'a' && c <= 'z')) {
            return "2";
        }
        return "correct";
    }

    // ----- CHECK IF WORD HAS GUESSED LETTER, IF YES, PUT IT IN THE WORD -----
    private static boolean checkGuessInWord(char validGuess, Word mysteryWord) {
        // check if the word contains the guessed letters
        String word = mysteryWord.getWord();
        if (word.indexOf(validGuess) != -1) {
            System.out.println(GraphicsMisc.getRandomPhrase());
            System.out.println();
            return true;
        } else {
            return false;
        }
    }

    static void putUserGuess(char validGuess, Word mysteryWord) {
        ArrayList<Integer> indices = findGuessIndicesInWord(mysteryWord, validGuess);
        for (Integer index : indices) {
            mysteryWord.maskedWord.setCharAt(index, validGuess);
        }
        indices.clear();
    }
    private static ArrayList<Integer> findGuessIndicesInWord(Word mysteryWord, char validGuess) { //находит все совпадения между догадкой игрока и буквами слова
        String word = mysteryWord.getWord();
        ArrayList<Integer> indices = new ArrayList<>();
        int index = word.indexOf(validGuess);
        while (index != -1) {
            indices.add(index);
            index = word.indexOf(validGuess, index + 1);
        }
        return indices;
    }

    // ----- FORMAT AND PRINT THE ARRAY LIST OF ALL LETTERS ALREADY GUESSED -----
    private static String formatGuessedLetters(ArrayList<Character> lettersGuessed) { // форматирует угаданные буквы в виде ['$буква', '$буква']
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
}
