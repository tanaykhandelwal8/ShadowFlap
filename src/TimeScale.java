import bagel.Input;
import bagel.Keys;

/**
 * TimeScale class: used to alter the speed of the game
 */
public class TimeScale {
    private int timescale;
    private double pipeMovingSpeed;
    private double pipeSpawnRate;

    /**
     * Instantiates a new Time scale object.
     */
    public TimeScale() {
        timescale = 1;
        pipeMovingSpeed = 5 ;
        pipeSpawnRate = 100;
    }

    /**
     * Gets pipe spawn rate
     * greater the timescale, lower the spawn rate
     *
     * @return the pipe spawn rate
     */
    public double getPipeSpawnRate() {
        return pipeSpawnRate;
    }

    /**
     * Sets pipe spawn rate
     * @param pipeSpawnRate the pipe spawn rate
     */
    public void setPipeSpawnRate(double pipeSpawnRate) {
        this.pipeSpawnRate = pipeSpawnRate;
    }

    /**
     * Gets pipe moving speed.
     * @return the pipe moving speed
     */
    public double getPipeMovingSpeed() {
        return pipeMovingSpeed;
    }

    /**
     * Sets pipe moving speed.
     * @param pipeMovingSpeed the pipe moving speed
     */
    public void setPipeMovingSpeed(double pipeMovingSpeed) {
        this.pipeMovingSpeed = pipeMovingSpeed;
    }

    /**
     * Gets timescale.
     * @return the timescale
     */
    public int getTimescale() {
        return timescale;
    }

    /**
     * Sets timescale.
     * @param timescale the timescale
     */
    public void setTimescale(int timescale) {
        this.timescale = timescale;
    }

    /**
     * Update the state of the object
     * @param input the input
     */
    public void update(Input input) {
        if(input.wasPressed(Keys.L) && getTimescale() < 5) {
            setTimescale(getTimescale() + 1);
            setPipeMovingSpeed((getPipeMovingSpeed() * 1.5)) ;
            setPipeSpawnRate((getPipeSpawnRate()/1.5));
        }
        if(input.wasPressed(Keys.K) && getTimescale() > 1) {
            setTimescale(getTimescale() - 1);
            setPipeMovingSpeed((getPipeMovingSpeed()/1.5));
            setPipeSpawnRate((getPipeSpawnRate() * 1.5));
        }
    }
}
