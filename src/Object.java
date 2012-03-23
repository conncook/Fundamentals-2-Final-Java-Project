import image.*;

/**
 * Represents data that can be drawn on the screen
 */
public interface Object {

    /**
     * Draws this Object into the given screen
     * @param scn Scene to draw on
     * @return Scene after rendering
     */
    Scene render(Scene scn);

}
