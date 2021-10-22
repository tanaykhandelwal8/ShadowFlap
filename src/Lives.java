import bagel.Image;
import java.util.ArrayList;

/**
 * Lives class: used to implement the lives a bird has
 */
public class Lives {

    private int lives;
    private final int NUM_LIVES;
    private final Image fullLifeImage;
    private final Image noLifeImage;
    private ArrayList<Image> livesArrayList;

    /**
     * Instantiates a new Lives object.
     *
     * @param level the level
     */
    public Lives(int level) {
        /* level 0 has 3 lives, level 1 has 6 lives */
        if(level == 0)
            lives = 3;
        else
            lives = 6;

        NUM_LIVES = lives;
        fullLifeImage = new Image("res/level/fullLife.png");
        noLifeImage = new Image("res/level/noLife.png");
        livesArrayList = new ArrayList<Image>();


    }

    /**
     * Update
     */
    public void update() {
        renderLivesImages();
        int x_coordinate = 100;
        for(int i = 0; i < NUM_LIVES; i++, x_coordinate += 50)
            livesArrayList.get(i).drawFromTopLeft(x_coordinate, 15);

    }

    /**
     * render the images of the lives on the top left
     */
    public void renderLivesImages() {
        for(int i = 0; i < lives; i++)
            livesArrayList.add(fullLifeImage);
        for(int i = lives; i < NUM_LIVES; i++)
            livesArrayList.add(i, noLifeImage);
    }


    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }
    /**
     * Gets lives array list.
     *
     * @return the lives array list
     */
    public ArrayList<Image> getLivesArrayList() {
        return livesArrayList;
    }

}
