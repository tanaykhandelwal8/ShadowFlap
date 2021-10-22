import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.Window;
import bagel.util.Rectangle;

/**
 * The type Plastic pipe.
 */
public class PlasticPipe extends Pipe{
    private Image topPipe;
    private Image bottomPipe;
    private Rectangle topPipeCollisionBox;
    private Rectangle bottomPipeCollisionBox;
    private int currentXValue;
    private TimeScale timeScale;
    private final int[][] pipeSpawnValues = {{-668, 268}, {-468, 468}, {-268, 668}}; // harcoded values for high, medium and low gaps respectively
    private final int pipeSpawnValue = (int)(Math.random() * 3); // random value to choose a pipe gap in level 0
    private int level;
    private int random_value = (int)(Math.random() * 400 + 100); // random value to spawn a pipe between 100 and 500 in level 1
    private final int TOP_PIPE_Y = random_value - 768; // top pipe starting position for level 1
    private final int BOTTOM_PIPE_Y = random_value + 168; // bottom pipe starting position for level 1

    /**
     * Instantiates a new Plastic pipe object.
     *
     * @param timeScale the time scale
     * @param level     the level
     */
    public PlasticPipe(TimeScale timeScale, int level) {
        topPipe = new Image("res/level/plasticPipe.png");
        bottomPipe = new Image("res/level/plasticPipe.png");
        currentXValue = Window.getWidth();
        this.timeScale = timeScale;
        this.level = level;

    }

    /**
     * get type of pipe, plastic in this scenario
     * @return
     */
    @Override
    public String getType() {
        return "PLASTIC";
    }

    /**
     * updates the state of the pipe
     * @param input the input
     */
    @Override
    public void update(Input input) {
        renderPipes();
        currentXValue -= timeScale.getPipeMovingSpeed();
        setHitBoxes();

    }

    /**
     * sets the hitboxes of the pipe
     */
    @Override
    public void setHitBoxes() {
        /*
         * set hitboxes for pipes for level 0 and 1 respectively
         */
        if(level == 0) {
            topPipeCollisionBox = new Rectangle(currentXValue, pipeSpawnValues[pipeSpawnValue][0], 65, 768);
            bottomPipeCollisionBox = new Rectangle(currentXValue, pipeSpawnValues[pipeSpawnValue][1], 65, 768);
        }
        else {
            topPipeCollisionBox = new Rectangle(currentXValue, TOP_PIPE_Y, 65, 768);
            bottomPipeCollisionBox = new Rectangle(currentXValue, BOTTOM_PIPE_Y, 65, 768);
        }
    }

    /**
     * Render the pipe onto the screen using values for level 0 and level 1
     */
    @Override
    public void renderPipes() {
        if(level == 0) {
            topPipe.drawFromTopLeft(currentXValue, pipeSpawnValues[pipeSpawnValue][0]);
            bottomPipe.drawFromTopLeft(currentXValue, pipeSpawnValues[pipeSpawnValue][1], new DrawOptions().setRotation(Math.PI));
        }
        else {
            topPipe.drawFromTopLeft(currentXValue, TOP_PIPE_Y);
            bottomPipe.drawFromTopLeft(currentXValue, BOTTOM_PIPE_Y, new DrawOptions().setRotation(Math.PI));
        }
    }

    /**
     * get top pipe collision box
     * @return
     */
    @Override
    public Rectangle getTopPipeCollisionBox() {
        return topPipeCollisionBox;
    }

    /**
     * get bottom pipe collision box
     * @return
     */
    @Override
    public Rectangle getBottomPipeCollisionBox() {
        return bottomPipeCollisionBox;
    }

    /**
     * shootFlames: no flames for plastic pipe, not used
     */
    @Override
    public void shootFlames() {

    }

    /**
     * setFlameHitBoxes(): no flames for plastic pipe, not used
     */
    @Override
    public void setFlameHitBoxes() {

    }

    /**
     * getTopFlameHitBox(): no flames for plastic pipe, not used
     * @return
     */
    @Override
    public Rectangle getTopFlameHitBox() {
        return null;
    }

    /**
     * getBottomFlameHitBox(): no flames for plastic pipe, not used
     * @return
     */
    @Override
    public Rectangle getBottomFlameHitBox() {
        return null;
    }


}
