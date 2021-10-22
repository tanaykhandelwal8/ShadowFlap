import bagel.Input;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;

/**
 * ArrayList of Pipes: Used to show multiple pipesets at once on the screen
 */
public class PipeList {

    private ArrayList<Pipe> pipesArrayList;
    private int frameCounter = 0;
    private TimeScale timeScale = new TimeScale();
    private int level = 0;

    /**
     * Instantiates a new Pipe list object.
     *
     * @param level the level
     */
    public PipeList(int level) {
        this.level = level;
        pipesArrayList = new ArrayList<Pipe>();
        pipesArrayList.add(new PlasticPipe(timeScale, this.level));
    }

    /**
     * Gets time scale of the current object.
     *
     * @return the time scale
     */
    public TimeScale getTimeScale() {
        return timeScale;
    }

    /**
     * Gets pipes array list.
     *
     * @return the pipes array list
     */
    public ArrayList<Pipe> getPipesArrayList() {
        return pipesArrayList;
    }

    /**
     * Update.
     *
     * @param input the input
     */
    public void update(Input input) {
        frameCounter++;
            if (frameCounter % (int)timeScale.getPipeSpawnRate() == 0) {
                if(level == 0)
                    pipesArrayList.add(new PlasticPipe(timeScale, level));
                else {
                    if(Math.random() <= 0.5)
                        pipesArrayList.add(new PlasticPipe(timeScale, level));
                    else
                        pipesArrayList.add(new SteelPipe(timeScale));
                }
            }

            for (Pipe p : pipesArrayList) {
                p.update(input);
            }
            timeScale.update(input);


    }

}
