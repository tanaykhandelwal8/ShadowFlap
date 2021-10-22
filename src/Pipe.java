import bagel.Image;
import bagel.Input;
import bagel.util.Rectangle;

/**
 * Abstract Pipe class: used to implement steel and plastic pipes.
 */
public abstract class Pipe {

    /**
     * Gets type of pipe.
     *
     * @return the type
     */
    public abstract String getType();

    /**
     * Update the state of the pipes.
     *
     * @param input the input
     */
    public abstract void update(Input input);

    /**
     * Sets hit boxes of the pipe.
     */
    public abstract void setHitBoxes();

    /**
     * Render pipes.
     */
    public abstract void renderPipes();

    /**
     * Gets top pipe collision box.
     *
     * @return the top pipe collision box
     */
    public abstract Rectangle getTopPipeCollisionBox();

    /**
     * Gets bottom pipe collision box.
     *
     * @return the bottom pipe collision box
     */
    public abstract Rectangle getBottomPipeCollisionBox();

    /**
     * Shoot flames for steel pipe
     */
    public abstract void shootFlames();

    /**
     * Sets flame hit boxes for steel pipe
     */
    public abstract void setFlameHitBoxes();

    /**
     * Gets top flame hit box for steel pipe
     *
     * @return the top flame hit box
     */
    public abstract Rectangle getTopFlameHitBox();

    /**
     * Gets bottom flame hit box for steel pipe
     *
     * @return the bottom flame hit box
     */
    public abstract Rectangle getBottomFlameHitBox();


}
