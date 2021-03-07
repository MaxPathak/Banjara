package src;

public class Launcher {
    public static void main(String[] args) {
        // Initialize the Game object
        // Resolutions
        /*
         * 1138 x 640 = 3 : 2
         */
        Game game = new Game("Banjara", 900, 600);
        // Start the game
        game.start();
    }
}