import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.Window;
import bagel.util.Rectangle;

/**
 * Steel pipe class; uses the pipe class as a template
 */
public class SteelPipe extends Pipe{

    private Image topPipe; // object to store the image of the top pipe
    private Image bottomPipe; // object to store the image of the bottom pipe
    private int currentXValue;
    private int random_value = (int)(Math.random() * 400 + 100); // gets a random spawn value between 100 and 500
    private final int TOP_PIPE_Y = random_value - 768;
    private final int BOTTOM_PIPE_Y = random_value + 168;
    private TimeScale timeScale;
    private Rectangle topPipeCollisionBox; // hitbox for the top pipe
    private Rectangle bottomPipeCollisionBox; // hitbox for the bottom pipe
    private final String type = "STEEL";
    private int frameCounter = 0;
    private Image topFlame; // flame object for top pipe
    private Image bottomFlame; // flame object for bottom pipe
    private Rectangle topFlameHitBox; // hitbox for top pipe's flame
    private Rectangle bottomFlameHitBox; // hitbox for bottom pipe's flame

    /**
     * Instantiates a new Steel pipe object.
     *
     * @param timeScale the time scale
     */
    public SteelPipe(TimeScale timeScale) {
        topPipe = new Image("res/level-1/steelPipe.png");
        bottomPipe = new Image("res/level-1/steelPipe.png");
        topFlame = new Image("res/level-1/flame.png");
        bottomFlame = new Image("res/level-1/flame.png");
        currentXValue = Window.getWidth();
        this.timeScale = timeScale;
    }

    /**
     * update the state of the pipe
     * @param input the input
     */
    @Override
    public void update(Input input) {
        renderPipes();
        currentXValue -= timeScale.getPipeMovingSpeed();
        setHitBoxes();
        /* every 20 frames, render the flames */
        if(frameCounter++ % 20 == 0) {
            shootFlames();
            setFlameHitBoxes();
        }
    }

    /**
     * render flames for the pipes
     */
    public void shootFlames() {
        topFlame.drawFromTopLeft(currentXValue, TOP_PIPE_Y + 768);
        bottomFlame.drawFromTopLeft(currentXValue, BOTTOM_PIPE_Y, new DrawOptions().setRotation(Math.PI));
    }

    /**
     * set hitboxes for the flames
     */
    public void setFlameHitBoxes() {
        topFlameHitBox = new Rectangle(currentXValue, TOP_PIPE_Y + 768, 65, 39);
        bottomFlameHitBox = new Rectangle(currentXValue, BOTTOM_PIPE_Y, 65, 39);
    }

    /**
     * get the hitbox for the top pipe's flame
     * @return
     */
    @Override
    public Rectangle getTopFlameHitBox() {
        return topFlameHitBox;
    }

    /**
     * get the hitbox for the bottom pipe's flame
     * @return
     */
    @Override
    public Rectangle getBottomFlameHitBox() {
        return bottomFlameHitBox;
    }

    /**
     * set hitboxes for the pipes
     */
    @Override
    public void setHitBoxes() {
        topPipeCollisionBox = new Rectangle(currentXValue, TOP_PIPE_Y, 65, 768);
        bottomPipeCollisionBox = new Rectangle(currentXValue, BOTTOM_PIPE_Y, 65, 768);
    }

    /**
     * render the pipes onto the screen
     */
    @Override
    public void renderPipes() {
        topPipe.drawFromTopLeft(currentXValue, TOP_PIPE_Y);
        bottomPipe.drawFromTopLeft(currentXValue, BOTTOM_PIPE_Y + 39, new DrawOptions().setRotation(Math.PI));
    }

    /**
     * get top pipe hitbox
     * @return: Rectangle
     */
    @Override
    public Rectangle getTopPipeCollisionBox() {
        return topPipeCollisionBox;
    }

    /**
     * get bottom pipe hitbox
     * @return: Rectangle
     */
    @Override
    public Rectangle getBottomPipeCollisionBox() {
        return bottomPipeCollisionBox;
    }

    /**
     * get type of pipe: Steel in this scenario
     */
    public String getType() {
        return type;
    }

}
