
import image.Scene;

import java.util.ArrayList;
import java.util.Random;

import world.Posn;

/**
 * Represents a group of enemies
 */
public class Wave implements Object{
    ArrayList<Enemy> enemies;
    int difficulty;
    int score_store;
    public static int buffer = 10;

    /**
     * Constructs a new Wave
     * @param difficulty the difficulty of the Wave
     */
    Wave(int difficulty){
        this.difficulty = difficulty;
        enemies = new ArrayList<Enemy>();
        generateEnemies(difficulty);
    }

    private void generateEnemies(int difficulty){
        Enemy enemy = chooseEnemy(difficulty);
        int wave_height_acc = enemy.getHeight() + buffer;
        int row_width_acc;
        while(wave_height_acc < (Screen.gameHeight - Screen.barrier)){
            row_width_acc = enemy.getWidth();
            while(row_width_acc < Screen.gameWidth - enemy.getWidth()){
                enemies.add(enemy.createClone(new Posn(row_width_acc, wave_height_acc)));
                row_width_acc = (enemy.getWidth() + buffer + row_width_acc);
            }
            wave_height_acc = wave_height_acc + 30+ buffer + enemy.getHeight();
        }

    }
    private Enemy chooseEnemy(int difficulty) {
        return new EvilFace(new Posn(1,1));
    }

    /**
     * Determines if the waves contents have been hit, and plays a sound for each one that has
     * @param bullets The particles to cross-reference with the wave occupants when checking for collisions
     * @param s SoundPlayer to play a noise when an enemy in the wave is hit
     * @return the Particles that have not collided
     */
    public ArrayList<Particle> checkCollisions(ArrayList<Particle> bullets, SoundPlayer s){
        int points = 0;
        // Because if a bullet or enemy is removed while still looping
        // through the list, we can to "mark" them for removal and 
        // remove them at the end of the function
        ArrayList<Enemy> enemies_for_removal = new ArrayList<Enemy>();
        ArrayList<Particle> bullets_for_removal = new ArrayList<Particle>();
        for(int b = 0; b < bullets.size(); b++){
            for(int a = 0; a < enemies.size(); a++){
                if(enemies.get(a).checkCollision(bullets.get(b).position, bullets.get(b).visual.height()) &&
                        bullets.get(b).alliegence == Ship.good){
                    points+= enemies.get(a).point_value;
                    enemies_for_removal.add(enemies.get(a));
                    bullets_for_removal.add(bullets.get(b));
                    s.playEnemyHit();
                }
            }
        }
        // Remove all enemies marked for removal
        while(!enemies_for_removal.isEmpty()){
            enemies.remove(enemies_for_removal.get(0));
            enemies_for_removal.remove(0);
        }
        // Remove all bullets marked for removal
        while(!bullets_for_removal.isEmpty()){
            bullets.remove(bullets_for_removal.get(0));
            bullets_for_removal.remove(0);
        }
        score_store = points;
        // Return the modified list of bullets
        return bullets;

    }

    /**
     * Draws this Object into the given screen
     * @param scn Scene to draw on
     * @return Scene after rendering
     */
    public Scene render(Scene scn) {
        for(int a = 0; a < enemies.size(); a++){
            scn = enemies.get(a).render(scn);
        }
        return scn;
    }

    /**
     * Generates particles "shot" by random enemies in the wave
     * @return
     */
    ArrayList<Particle> shootAll(){
        ArrayList<Particle> particles = new ArrayList<Particle>();
        Random rand = new Random();
        int r;
        for(int a = 0; a < enemies.size(); a++){
            r = rand.nextInt(enemies.size() * 20);
            if(r == 1){
                particles.add(new Particle(new Posn(enemies.get(a).position.x, 
                        enemies.get(a).position.y), "generic.gif", 0, 2, Ship.evil));
            } 
        }
        return particles;
    }

    /**
     * Accessor for amount to be added to the score
     * @return amount of points to add to score
     */
    public int releaseScore(){
        int temp = score_store;
        score_store = 0;
        return temp;
    }

    /**
     * Determines if there are any enemies left in the wave
     * @return whether or not there are ships in the wave
     */
    public boolean isEmpty(){
        return enemies.isEmpty();
    }
    
}
