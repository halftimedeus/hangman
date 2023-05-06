import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class GameInstance {
    int guesses;

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    int stageCounter;

    public void setStageCounter(int stageCounter) {
        this.stageCounter = stageCounter;
    }

    public GameInstance(int guesses, int stageCounter) {
        this.guesses = 7;
        this.stageCounter = -1;
    }

    private static final Properties errorProperties = new Properties();

    static void firstRun() throws InterruptedException {
        setErrorFromProperties();
        System.out.println("Welcome to HANGMAN!\n");
        Thread.sleep(500);
        //TODO: main menu, language choice
        GameInstance newRound = new GameInstance(7, -1);
        newRound.newGameRound(newRound);
    }

    public void newGameRound(GameInstance newRound) {
        Word mysteryWord = new Word();
        UserGuess.lettersGuessed.clear();
        gameLoop(mysteryWord, newRound);
    }

    void gameLoop(Word mysteryWord, GameInstance newGameRound) {
        while (true) {
            mysteryWord.printMaskedWord();
            UserGuess.printLettersGuessed(newGameRound);
            if (stageCounter >= 0) { // to avoid printing the gallows in the first stage
                printStage(newGameRound);
            }
            printGuessesLeft(guesses);
            UserGuess.makeGuess(mysteryWord, newGameRound);
            CheckGameState.checkIfGameEnded(mysteryWord, newGameRound);
        }
    }

    static void printGuessesLeft(int guesses) {
        System.out.println("Guesses remaining: " + guesses);
        System.out.println();
    }
    static void printStage (GameInstance newGameRound) {
        int stageCounter = newGameRound.stageCounter;
        GraphicsMisc stage = new GraphicsMisc();
        System.out.println(stage.GALLOWS[stageCounter]);
    }

    private static void setErrorFromProperties() { //load the properties file
        try (InputStream input = GameInstance.class.getResourceAsStream("/errorMessages_en.properties")) {
            errorProperties.load(input);
        } catch (IOException ex) {
            System.out.println("Properties file not found");
            ex.printStackTrace();
        }
    }

    public static void printErrorMessage(String errorCode) { //get and print the error message
        String errorMessage = errorProperties.getProperty(errorCode);
        if (errorMessage != null) {
            System.out.println("Error #" + errorCode + ": " + errorMessage);
        } else {
            System.out.println("Error code " + errorCode + " not found");
        }
    }

}
