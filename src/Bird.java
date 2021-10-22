import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * SWEN20003 Project 2, Semester 2, 2021
 * @author: Tanay Khandelwal
 * Student ID: 1168569
 */

/**
 * The type Bird.
 */
public class Bird {


    private Image birdUp; // stores the image of the bird with its wings up
    private Image birdDown; // stores the image of the bird with its wings down
    private static int frameCounter; // counts the frame, so that the flapping motion can be shown


    private double birdPositionX; // stores the X coordinate of the bird
    private double birdPositionY; // stores the Y coordinate of the bird
    private static double birdAcceleration;
    private final int INITIAL_X = 200;
    private final int INITIAL_Y = 350;

    private Rectangle birdCollisionBox; // hitbox for the bird, to identify collision between the bird and pipes

    private final double FLY_SPEED = 6;
    private final double FALL_ACCELERATION = 0.4;
    private final double MAX_FALLING_SPEED = 10;

    private final double FRAME_SWITCH = 10;
    // getters and setters

    /**
     * Instantiates a new Bird.
     *
     * @param level: if level 0, initialise with level 0 assets, otherwise initialise with level 1 assets
     */
    public Bird(int level) {

        if(level == 0) {
            birdUp = new Image("res/level-0/birdWingUp.png");
            birdDown = new Image("res/level-0/birdWingDown.png");
        }
        else {
            birdUp = new Image("res/level-1/birdWingUp.png");
            birdDown = new Image("res/level-1/birdWingDown.png");
        }
        frameCounter = 0;

        birdPositionX = INITIAL_X;
        birdPositionY = INITIAL_Y;
        birdAcceleration = 0;
    }

    /**
     * update method called in the level classes, used to render the bird and update it's hitbox
     *
     * @param input the input
     */
    public void update(Input input) {
        /* adapted from SWEN20003 Project 1 Solution released by Betty Lin */

        /* if SPACE is pressed, the bird should start flying with a velocity of 6 */
        if(input.wasPressed(Keys.SPACE)) {
            birdAcceleration = -FLY_SPEED;
            birdDown.draw(birdPositionX, birdPositionY);
            birdCollisionBox = birdDown.getBoundingBoxAt(new Point(birdPositionX, birdPositionY));
        }
        /* otherwise, fall with an increasing acceleration*/
        else {
            birdAcceleration = Math.min(birdAcceleration + FALL_ACCELERATION, MAX_FALLING_SPEED);
            if(frameCounter % FRAME_SWITCH == 0) {
                birdUp.draw(birdPositionX, birdPositionY);
                birdCollisionBox = birdUp.getBoundingBoxAt(new Point(birdPositionX, birdPositionY));
            }
            else {
                birdDown.draw(birdPositionX, birdPositionY);
                birdCollisionBox = birdDown.getBoundingBoxAt(new Point(birdPositionX, birdPositionY));
            }
        }
        birdPositionY += birdAcceleration;
        frameCounter++;
    }

    /**
     * Sets bird position x.
     *
     * @param birdPositionX the bird position x
     */
    public void setBirdPositionX(double birdPositionX) {
        this.birdPositionX = birdPositionX;
    }

    /**
     * Sets bird position y.
     *
     * @param birdPositionY the bird position y
     */
    public void setBirdPositionY(double birdPositionY) {
        this.birdPositionY = birdPositionY;
    }

    /**
     * Gets bird collision box.
     *
     * @return the bird collision box
     */
    public Rectangle getBirdCollisionBox() {
        return birdCollisionBox;
    }

    /**
     * Sets bird collision box.
     *
     * @param birdCollisionBox the bird collision box
     */
    public void setBirdCollisionBox(Rectangle birdCollisionBox) {
        this.birdCollisionBox = birdCollisionBox;
    }

    /**
     * Gets bird position x.
     *
     * @return the bird position x
     */
    public double getBirdPositionX() {
        return birdPositionX;
    }

    /**
     * Gets bird position y.
     *
     * @return the bird position y
     */
    public double getBirdPositionY() {
        return birdPositionY;
    }


}
