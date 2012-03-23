import java.util.ArrayList;

/**
 * Integrates the graphics with the game play
 */
public class Game extends Screen {

    Player player;
    Wave wave;
    int wave_num;
    ArrayList<Particle> bullets;
    Status bar;
    SoundPlayer sounds;

    /**
     * Constructs a new Game
     */
    Game(){
        super();
        this.player = new Player();
        wave_num = 0;
        wave = new Wave(wave_num);
        bullets = new ArrayList<Particle>();
        bar = new Status();
        bar.incrementLevel();
        sounds = new SoundPlayer();

    }

    /**
     * Advances game play by moving each Object on the screen
     */
    public void moveAll(){
        for(int a=0;a<bullets.size();a++){
            bullets.get(a).move();
        }
    }

    /**
     * Puts all of the objects that should be on the screen into a list in the
     * proper order
     */
    public void compile() {
        Elements.clear();
        Elements.add(player);
        for(int a = 0; a < bullets.size(); a++)
            Elements.add(bullets.get(a));
        Elements.add(wave);
        Elements.add(bar);

    }

    /**
     * Moves the player in the given direction
     * @param dir Direction to move the player
     */
    public void movePlayer(String dir){
        int rate = player.getMoveSpeed();
        if(dir.equals("left"))
            player.moveLeft(rate);
        else if(dir.equals("right"))
            player.moveRight(rate);
        else if(dir.equals("up"))
            player.moveUp(rate);
        else if(dir.equals("down"))
            player.moveDown(rate);
        player.updateArsenal();
    }

    /**
     * Gets the particles shot by the wave and puts them in the bullets list
     */
    public void waveShoot(){
        ArrayList<Particle> temp = wave.shootAll();
        for(int a = 0; a < temp.size(); a++){
            bullets.add(temp.get(a));
        }
    }

    /**
     * Gets the particle shot by the player and adds it to the list of bullets
     */
    public void playerShoot(){
        ArrayList<Particle> temp = player.shoot();
        for(int a = 0; a < temp.size(); a++){
            bullets.add(temp.get(a));
        }
        sounds.playShotFired();
    }

    /**
     * Accessor for the Player
     * @return the Player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Advances game play according to the given counter
     * @param ticker the counter
     * @return the new counter after game play is advanced
     */
    public int tick(int ticker){
        if(ticker < player.getLag() && ticker > 0)
            ticker--;
        else if(ticker == 0)
            ticker = player.getLag();
        moveAll();
        waveShoot();
        checkCollisions();
        garbageCollect();
        updateLevel();
        if(wave.isEmpty() && ticker == player.getLag()){
            wave_num++;
            bullets = new ArrayList<Particle>();
            wave = new Wave(wave_num);
            bar.incrementWave();
            bar.increaseScore(bar.getLevel() * 50);
            bar.setTemp("Wave Completion Bonus! " + (bar.getLevel() * 50));
        }
        return ticker;
    }

    /**
     * Advances gameplay according to the given key event and counter
     * @param ticker counter
     * @param ke key event
     * @return the new counter
     */
    public int onKey(int ticker, String ke){
        if(ke.equals(" ") && ticker > player.getLag() - 1){
            playerShoot();
            ticker--;
        }
        else movePlayer(ke);
        return ticker;
    }

    /**
     * Determines if there were any collisions and responds appropriately
     */
    public void checkCollisions(){
        if(checkPlayerCollision()){
            player.die(sounds);
        }
        bullets = wave.checkCollisions(bullets, sounds);
        bar.increaseScore(wave.releaseScore());
    }

    private boolean checkPlayerCollision(){
        for(int a = 0; a < bullets.size(); a++){
            if(player.checkCollision(bullets.get(a).position, 
                    bullets.get(a).visual.height()) &&
                    bullets.get(a).alliegence == Ship.evil){
                bullets.remove(a);
                return true;
            }
        }
        return false;
    }
    // Removes all bullets outside the confines of the game
    private void garbageCollect(){
        ArrayList<Particle> bullets_for_removal = new ArrayList<Particle>();
        for(int a = 0;a<bullets.size();a++){
            if(bullets.get(a).outsideGame())
                bullets_for_removal.add(bullets.get(a));
        }
        // Remove all bullets marked for removal
        while(!bullets_for_removal.isEmpty()){
            bullets.remove(bullets_for_removal.get(0));
            bullets_for_removal.remove(0);
        }
    }
    private void updateLevel(){
        if(player.getNextLevel() < bar.getScore()){
            bar.setTemp("Level Up!" + player.levelUp(bar.getLevel()));
            bar.incrementLevel();
            bar.incrementWave();
        }
    }

}
