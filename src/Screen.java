import image.EmptyScene;
import image.Scene;

import java.util.ArrayList;

/**
 * Draws the game state and holds all the objects being used by the game
 */
public abstract class Screen implements Object {
    // A screen is a representation of the current game state
    // ie; Title screen, Game screen, etc
    // It contains all given elements that is on the Screen
    // enemies, buttons, the player, etc
    public static final int gameHeight = 500;
    public static final int gameWidth = 300;
    public static final int barrier = 150;
    public static final int status_bar_height = 100;
    ArrayList<Object> Elements;

    /**
     * Constructs a new Screen
     */
    Screen(){
        Elements = new ArrayList<Object>();
    }

    /**
     * Draws this Object into the given screen
     * @param scn Scene to draw on
     * @return Scene after rendering
     */
    public Scene render(Scene scn){
        Scene temp = new EmptyScene(gameWidth, gameHeight + status_bar_height);
        compile();
        for(int a = 0; a < Elements.size(); a++){
            temp = Elements.get(a).render(temp);
        }
        return temp;
    }

    /**
     * Puts all of the objects that should be on the screen into a list in the
     * proper order
     */
    public abstract void compile();

    /**
     * Moves the player in the given direction
     * @param dir Direction to move the player
     */
    public abstract void movePlayer(String dir);

    /**
     * Advances game play by moving each Object on the screen
     */
    public abstract void moveAll();
    
}
