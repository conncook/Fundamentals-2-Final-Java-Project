import image.EmptyScene;
import image.Scene;


import world.VoidWorld;

/**
 * Handles graphics
 */
public class GameWorld extends VoidWorld{
    public static void main(String[] s){        
        new GameWorld().bigBang();
    }
    
    Scene scn;
    Game screen;
    int ticker;

    /**
     * Constructs a new GameWorld
     */
    GameWorld(){
        screen = new Game();
        scn = new EmptyScene(300,300);
        scn = screen.render(scn);
        ticker = 0;
    }
    /**
     * Renders the screen
     * @return the new Scene
     */
    public Scene onDraw(){
        return screen.render(scn);
    }
    /**
     * Advances game play incrementally
     */
    public void onTick(){
        ticker = screen.tick(ticker);
    }

    /**
     * Responds to key events
     * @param ke the key that was pressed
     */
    @Override
    public void onKey(String ke){
        if(ke.equals(" ") && ticker > screen.getPlayer().getLag() - 1){
            screen.playerShoot();
            ticker--;
        }
        else screen.movePlayer(ke);
    }
}

