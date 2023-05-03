import java.util.Scanner;

public class GameState {
    static Scanner scanner = new Scanner(System.in);
    static int guesses = 7;

    static void reduceGuesses () {
        guesses--;
    }

    static int stageCounter = 0; // счетчик игровых этапов
    static void printStage () { // печатает ASCII виселицу
        Stage_ASCII stage = new Stage_ASCII();
        System.out.println(stage.STAGE_HANGMAN[stageCounter++]);
    }

    static void checkGameState () throws InterruptedException { // проверяет состояние игрового поля - в случае выигрыша или проигрыша
        //предлагает начать заново или выйти
        checkWin();
        checkLose();
    }
    static void checkWin() throws InterruptedException {
        if (WordActions.maskedWord.toString().equalsIgnoreCase(WordActions.word)) {
            System.out.println("YOU WIN! Congratulations!!");
            askNewGame();
        }
    }

    static void checkLose () throws InterruptedException {
        if (guesses == 0) {
            WordActions.printMaskedWord();
            printStage();
            System.out.println("You couldn't save your " + WordActions.word + "!");
            askNewGame();
        }
    }

    static void askNewGame() throws InterruptedException {
        System.out.println("Would you like to play again? Y / N");
        String tryAgain = scanner.nextLine();
        tryAgain = checkNewGameInput(tryAgain);
        if (tryAgain.equalsIgnoreCase("y")) {
            System.out.println("Sure, get ready!");
            Thread.sleep(500);
            System.out.println();
            startNewGame();

        } else if (tryAgain.equalsIgnoreCase("n")) {
            System.out.println("Till next time!");
            System.exit(0);
        }
    }
    static void startNewGame () {
        GameActions.lettersGuessed.clear();
        guesses = 7;
        WordActions.maskedWord.setLength(0);
        WordActions.getRandomWord();
        WordActions.maskWord();
    }

    static String checkNewGameInput(String tryAgain) {
        while (GameActions.isInputValid(tryAgain) != 0) {
            if (GameActions.isInputValid(tryAgain) == 1) {
                System.out.println("Type Y to play again. Type N to exit");
            } else if (GameActions.isInputValid(tryAgain) == 2) {
                System.out.println("You should really type Y or N...");
            }
            tryAgain = scanner.nextLine();
        }
        return tryAgain;
    }
}
