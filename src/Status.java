
import world.Posn;
import image.*;

/**
 * Represents a graphical display that tells the user information about the game
 */
public class Status implements Object {

    int points;
    int wave_number;
    int level;
    String buffer;

    /**
     * Constructs a new Status
     */
    Status() {
        points = 0;
        wave_number = 1;
        buffer = "";
    }

    /**
     * Draws this Object into the given screen
     * @param scn Scene to draw on
     * @return Scene after rendering
     */
    public Scene render(Scene scn) {
        scn = scn.placeImage(new Rectangle(Screen.gameWidth, Screen.status_bar_height, "outline", "black"),
                new Posn(Screen.gameWidth / 2, Screen.gameHeight + Screen.status_bar_height / 2));
        scn = scn.placeImage(new Text("Wave: " + wave_number, 20, "black"),
                new Posn(Screen.gameWidth / 5, Screen.gameHeight + Screen.status_bar_height / 3));
        scn = scn.placeImage(new Text("Level: " + level, 20, "black"),
                new Posn(Screen.gameWidth / 2, Screen.gameHeight + Screen.status_bar_height / 3));
        scn = scn.placeImage(new Text("Score: " + points, 20, "black"),
                new Posn((int) (Screen.gameWidth / 1.3), Screen.gameHeight + Screen.status_bar_height / 3));
        scn = scn.placeImage(new Text(buffer, 20, "black"),
                new Posn(Screen.gameWidth / 2, (int) (Screen.gameHeight + Screen.status_bar_height / 1.5)));
        return scn;
    }

    /**
     * Accessor for wave number
     * @return wave number
     */
    public int waveNum() {
        return wave_number;
    }

    /**
     * Accessor for current level
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Accessor for score
     * @return points
     */
    public int getScore() {
        return points;
    }

    /**
     * increases wave number
     */
    public void incrementWave() {
        wave_number++;
    }

    /**
     * increases score by given amt
     * @param score amount to increase score
     */
    public void increaseScore(int score) {
        points += score;
    }

    /**
     * Sets the status bar to display the given string
     * @param temp String to temporarily display
     */
    public void setTemp(String temp) {
        buffer = temp;
    }

    /**
     * increments level
     */
    public void incrementLevel() {
        level++;
    }
}
