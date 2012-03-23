import world.Posn;

/**
 * Represents a default particle
 */
public class Generic extends Particle {

    /**
     * Construcst a new particle
     * @param pos initial position
     * @param vely initial velocity
     * @param alliegence enemy or player
     */
    Generic(Posn pos, int vely, boolean alliegence) {
        super(pos, "generic.gif", 0, vely, alliegence);
    }
    
}
