import world.Posn;

/**
 * Represents data common to all enemies.
 */
public abstract class Enemy extends Ship{
    int point_value;

    /**
     * Constructs a new Enemy
     * @param image_loc Location of image used when drawing this enemy
     * @param max_health initial health of enemy
     * @param pos position of enemy
     * @param points points recieved for removing enemy
     */
    Enemy(String image_loc, int max_health, Posn pos, int points) {
        super(image_loc, max_health, max_health, Ship.evil, pos);
        point_value = points;
    }

    /**
     * Creates a new identical Enemy at the given position
     * @param pos position of new enemy
     * @return
     */
    abstract Enemy createClone(Posn pos);

    /**
     * Creates a new Particle that "emerges" from this enemy
     * @return the new Particle
     */
    abstract Particle shoot();


}
