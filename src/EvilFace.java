import world.Posn;

/**
 * Represents a type of enemy
 */
public class EvilFace extends Enemy{

    /**
     * Constructs a new EvilFace at the given position
     * @param pos initial Posn
     */
    EvilFace(Posn pos) {
        super("evilface.gif", 30, pos, 10);
        // TODO Auto-generated constructor stub
    }

     /**
     * Creates a new identical Enemy at the given position
     * @param pos position of new enemy
     * @return
     */
    Enemy createClone(Posn pos) {
        return new EvilFace(pos);
    }

    /**
     * Creates a new Particle that "emerges" from this enemy
     * @return the new Particle
     */
    Particle shoot(){
        return new Generic(new Posn(position.x, position.y + getHeight()/2), 2, Ship.evil);
    }

}
