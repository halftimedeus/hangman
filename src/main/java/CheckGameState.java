import java.util.Scanner;

public class CheckGameState {
    static void checkIfGameEnded(Word mysteryWord, GameInstance newGameRound) {
        int guesses = newGameRound.guesses;
        if (gameLost(mysteryWord, guesses)) { // game is lost
            GameInstance.printStage(newGameRound);
            System.out.println("You couldn't save your " + mysteryWord.getWord() + "!");
            handleNewGame();
        } else if (gameWon(mysteryWord)) { // game  is won
            GameInstance.printStage(newGameRound);
            System.out.println("YOU WIN! Congratulations!!");
            System.out.println("You saved your " + mysteryWord.getWord());
            handleNewGame();
        }
    }

    private static void handleNewGame() {
        if (!askNewGame()) {
            System.out.println("Till next time!");
            System.exit(0);
        } else {
            System.out.println("Sure, get ready!");
            System.out.println();
            GameInstance repeatGameRound = new GameInstance(7, -1);
            repeatGameRound.newGameRound(repeatGameRound);
        }
    }

    static boolean gameWon(Word mysteryWord) {
        return mysteryWord.maskedWord.toString().equalsIgnoreCase(mysteryWord.getWord());
    }
    static boolean gameLost(Word mysteryWord, int guesses) {
        return guesses == 0 && !gameWon(mysteryWord);
    }

    static boolean askNewGame() {
        System.out.println("Would you like to play again? Y / N");
        char answer = getValidNewGameInput();
        return answer == 'y';
    }

    static char getValidNewGameInput() {
        Scanner scanner = new Scanner(System.in);
        //get user input, validate it, print corresponding error message if input is invalid

        while (true) {
            String userInput = scanner.nextLine();
            String validationResult = isGameInputValid(userInput);

            if (validationResult.equals("correct")) {
                return userInput.toLowerCase().charAt(0);
            } else {
                GameInstance.printErrorMessage(validationResult); // if the first check doesn't pass, get back
            }
        }
    }
    private static String isGameInputValid(String str) {
        //check input and display error code
        if (str.length() != 1) {
            return "1";
        }
        char c = Character.toLowerCase(str.charAt(0));
        if ((c != 'y' && c != 'n')) {
            return "4";
        }
        return "correct";
    }
}
