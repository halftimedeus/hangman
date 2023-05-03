class Core {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to HANGMAN!");
        System.out.println();
        Thread.sleep(500);

        WordActions.getRandomWord();
        WordActions.maskWord();

        while (true) {
            GameActions.gameLoop();
        }
    }
}

