import java.util.Random;

class GraphicsMisc {
    public final String[] GALLOWS = {
            // stage 1, [0]
            """
             ___
            |   |____
            |________|
             """,

            // stage 2, [1]
            """
              _____
              |
              |
              |
              |
             _|_
            |   |____
            |________|
            """,

            // stage 3, [2]
            """
              _____
              |  |
              |
              |
              |
             _|_
            |   |____
            |________|
            """,

            // stage 4, [3]
            """
              _____
              |  |
              |  O
              |
              |
             _|_
            |   |____
            |________|
            """,

            // stage 5, [4]
            """
              _____
              |  |
              |  O
              | / \\
              |
             _|_
            |   |____
            |________|
            """,

            // stage 6, [5]
            """
              _____
              |  |
              |  O
              | /|\\
              |
             _|_
            |   |____
            |________|
            """,

            // stage 7, [6]
            """
              _____
              |  |
              |  O
              | /|\\
              | / \\
             _|_
            |   |____
            |________|
            """
    };

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
}
