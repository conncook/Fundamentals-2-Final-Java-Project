import world.Posn;
import image.*;
import image.Scene;

/**
 * Represents an object that is created by one of the ships
 */
public class Particle implements Object {
    FromFile visual;
    Posn position;
    int velx;
    int vely;
    boolean alliegence;
    String file_name;
    /**
     * Constructs a new Particle
     * @param pos Initial position
     * @param file file for picture
     * @param velx x velocity
     * @param vely y velocity
     * @param alliegence whether it was fired by player or an enemy
     */
    Particle(Posn pos, String file, int velx, int vely, boolean alliegence){
        position = pos;
        visual = new FromFile(file);
        this.velx = velx;
        this.vely = vely;
        this.alliegence = alliegence;
        file_name = file;
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
     * Accessor for x velocity
     * @return x velocity
     */
    public int getXVel(){
        return velx;
    }
    /**
     * Accessor for y velocity
     * @return y velocity
     */
    public int getYVel(){
        return vely;
    }
    /**
     * Advances the projectile by its velocity
     */
    public void move(){
        position = new Posn(position.x + velx, position.y + vely);
    }

    private void increaseXVel(){
        if(velx < 0)
            velx--;
        else if(velx > 0)
            velx++;
    }
    private void increaseYVel(){
        if(vely < 0)
            vely--;
        else if(vely > 0)
            vely++;
    }
    /**
     * Increases the velocity of the particle
     */
    public void increaseXandY(){
        increaseXVel();
        increaseYVel();
    }

    /**
     * Creates an identical particle without altering this one
     * @return the new particle
     */
    public Particle clone(){
        return new Particle(position, file_name, velx, vely, alliegence);
    }
    /**
     * Mutator for the position of this particle
     * @param newpos
     */
    public void update(Posn newpos){
        position = newpos;
    }
    /**
     * Determines if this particle has left the screen
     * @return whether it is out of bounds
     */
    public boolean outsideGame(){
        if(position.x < 0 || position.x > Screen.gameWidth || 
                position.y < 0 || position.y > Screen.gameHeight)
            return true;
        return false;
        
    }
    

}
