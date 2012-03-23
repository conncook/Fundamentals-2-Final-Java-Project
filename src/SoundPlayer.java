
import tunes.MusicBox;
import tunes.Note;
import tunes.Tune;

/**
 * Provides an interface to play sounds used through out the game
 */
public class SoundPlayer {

    MusicBox box;

    /**
     * Constructs a new Sound player
     */
    public SoundPlayer() {
        box = new MusicBox();
    }

    /**
     * Plays noise for when a shot is fired
     */
    public void playShotFired() {
        box.playTune(new Tune(0, new Note("A7n16")));
        box.sleepSome(50);
    }

    /**
     * Plays noise for when a player is hit
     */
    public void playPlayerHit() {
        box.playTune(new Tune(0, new Note("A5n16")));
        box.sleepSome(150);
        box.playTune(new Tune(0, new Note("A4n16")));
        box.sleepSome(200);
    }

    /**
     * Plays noise for when an enemy is hit
     */
    public void playEnemyHit() {
        box.playTune(new Tune(0, new Note("A5n16")));
        box.sleepSome(150);
        box.playTune(new Tune(0, new Note("A6n16")));
        box.sleepSome(200);
    }
}
