import bagel.*;

public class ShadowFlap extends AbstractGame {

    /** SWEN20003 Project 2; SEM 2, 2021
     * @Author: Tanay Khandelwal
     */

    /**
     * Main class for Project 2, creates the game window and calls level0 and level1 objects
     */

    private Level0 level0;
    private Level1 level1;

    public ShadowFlap() {
        super(1024, 768, "ShadowFlap");
        level0 = new Level0();
        level1 = new Level1();
    }


    /**
     * Driver function for project 2: the game begins here
     * @param args: arguments that are taken from the command line
     */
    public static void main(String[] args) {
        ShadowFlap game = new ShadowFlap();
        game.run();
    }

    /**
     * Function which updates the state of the game
     * @param input: input object, which reads in keyboard inputs, uses the Input class from Bagel.
     */
    @Override
    public void update(Input input) {
        if(!level0.getGoToLevel1()) {
            level0.update(input);
        }
        else
            level1.update(input);

    }

}
