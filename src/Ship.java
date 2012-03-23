import world.*;
import image.*;

/**
 * Represents Data that is common to both the player and it's enemies
 */
public abstract class Ship implements Object{
    FromFile visual;
    int max_health;
    int current_health;
    String image_loc;
    Posn position;
    boolean alliegence;
    public static boolean evil = false;
    public static boolean good = true;

    /**
     * Constructs a new Ship
     * @param image_loc Location of image used when drawing this ship
     * @param max_health initial health of ship
     * @param current_health the current health of the ship
     * @param alliegence whether this Ship is the player or the ship
     * @param pos position of ship
     */
    Ship(String image_loc, int max_health, int current_health, 
            boolean alliegence, Posn position) {
        visual = new FromFile(image_loc);
        this.max_health = max_health;
        this.current_health = current_health;
        this.position = position;
        this.alliegence = alliegence;
        this.image_loc = image_loc;
    }

    /**
     * Accessor for max health
     * @return initial health
     */
    public int getMax_health() {
        return max_health;
    }
    /**
     * Mutator for max health
     * @param max_health new initial health
     */
    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }
    /**
     * Accessor for current health
     * @return current health
     */
    public int getCurrent_health() {
        return current_health;
    }
    /**
     * Mutator for current health
     * @param current_health new current health
     */
    public void setCurrent_health(int current_health) {
        this.current_health = current_health;
    }

    /**
     * Draws this Object into the given screen
     * @param scn Scene to draw on
     * @return Scene after rendering
     */
    public Scene render(Scene scn) {
        return scn.placeImage(visual, position);
    }
    /**
     * Accessor for height
     * @return height
     */
    public int getHeight(){
        return visual.height();
    }
    /**
     * Accessor for width
     * @return width
     */
    public int getWidth(){
        return visual.width();
    }

    /**
     * Moves the Ship to the new position
     * @param x new x position
     * @param y new y position
     */
    void move(int x, int y){
        position = new Posn(position.x + x, position.y + y);
    }

    /**
     * Determines if this ships position overlaps the given position within the given
     * height
     * @param pos position to test
     * @param height the height
     * @return whether there is an overlap
     */
    boolean checkCollision(Posn pos, int height){
        if(position.x - getWidth()/2 < pos.x && position.x + getWidth()/2 > pos.x
                && position.y - getHeight()/2  < pos.y
                && position.y + getHeight()/2 > pos.y){
            return true;
        }
        return false;
    }
    


}
