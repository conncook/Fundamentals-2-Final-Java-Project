import java.util.ArrayList;

import world.Posn;

/**
 * Represents the players ship
 */
public class Player extends Ship {
    // Representation of the Player
    int lag;
    int lives;
    int movespeed;
    int next_level;
    ArrayList<Particle> arsenal;

    /**
     * Constructs a new Player
     */
    Player(){
        super("player.png", 50, 50, Ship.good, 
                new Posn(Screen.gameWidth / 2, Screen.gameHeight - 40));
        lag = 15;
        lives = 3;
        arsenal = new ArrayList<Particle>();
        arsenal.add(new Generic(new Posn(position.x, position.y + getHeight()/2), -6, Ship.good));
        movespeed = 2;
        next_level = 100;
    }
    /**
     * Returns the list of particles that "emerge" from this Player each time
     * it shoots
     * @return the Particles fired
     */
    ArrayList<Particle> shoot(){
        ArrayList<Particle> temp = new ArrayList<Particle>();
        for(int a = 0; a < arsenal.size();a++){
            temp.add(arsenal.get(a).clone());
        }
        return temp;
    }

    /**
     * Returns the time allowed between shots
     * @return the lag
     */
    int getLag(){
        return lag;
    }

    /**
     * Decrements number of lives
     * @param s Sound player used to play the noise made
     */
    void die(SoundPlayer s){
        if(lives > 0)
            lives--;
        position = new Posn(Screen.gameWidth / 2, Screen.gameHeight - 40);
        s.playPlayerHit();
    }

    private void UpgradeAddParticle(){
        if(arsenal.size()<2){
            arsenal.add(new Mini(new Posn(position.x - getWidth()/2,
                    position.y - getHeight()/2), -1, -4, Ship.good));
            arsenal.add(new Mini(new Posn(position.x + getWidth()/2,
                    position.y - getHeight()/2), 1, -4, Ship.good));
        }
        else if(arsenal.size() > 2 && arsenal.size() < 4){
            arsenal.add(new Mini(new Posn(position.x - getWidth()/3,
                    position.y - getHeight()/2), -1, -4, Ship.good));
            arsenal.add(new Mini(new Posn(position.x + getWidth()/3,
                    position.y - getHeight()/2), 1, -4, Ship.good));
        }
        else return;
    }

    private void UpgradeAttackSpeed(){
        if(lag>3)
            lag-=3;
    }
    private void UpgradeWeaponSpeed(){
        for(int a = 0; a < arsenal.size();a++){
            arsenal.get(a).increaseXandY();
        }
    }

    private void UpgradeMoveSpeed(){
        movespeed+=3;
    }

    /**
     * Increases number of particles fired
     */
    public void updateArsenal(){
        for(int a = 0;a<arsenal.size();a++){
            arsenal.get(a).update(position);
        }
    }

    /**
     * Accessor for speed
     * @return speed
     */
    int getMoveSpeed(){
        return movespeed;
    }

    /**
     * Moves Player left
     * @param x distance
     */
    public void moveLeft(int x){
        if(position.x - visual.width()/2 > x)
            move(0 - x, 0);
        
    }

    /**
     * Moves player right
     * @param x distance
     */
    void moveRight(int x){
        if(position.x + visual.width()/2 < Screen.gameWidth)
            move(x, 0);
    }
    /**
     * moves player up
     * @param y distance
     */
    void moveUp(int y){
        if(position.y > Screen.gameHeight - Screen.barrier)
            move(0, 0 - y);
    }
    /**
     * moves player down
     * @param y distance
     */
    void moveDown(int y){
        if(position.y + visual.height()/2 < Screen.gameHeight)
            move(0, y);
    }

    /**
     * Increases level and upgrades various components of player
     * @param level level to move to
     * @return String to display to user
     */
    String levelUp(int level){
        String temp = "";
        if(level % 4 == 0){
            UpgradeMoveSpeed();
            temp = "+ MoveSpeed";
        }
        else if(level % 4 == 1){
            UpgradeAttackSpeed();
            temp = "+ AttackRate";
        }
        else if(level % 4 == 2){
            UpgradeWeaponSpeed();
            temp = "+ WeaponSpeed";
        }
        else if(level % 4 == 3){
            UpgradeAddParticle();
            temp = "+ WeaponAbility";
        }
        next_level = (int) (next_level*1.5);
        return temp;
    }
    /**
     * Accessor for next level
     * @return next level
     */
    int getNextLevel(){
        return next_level;
    }  
}
