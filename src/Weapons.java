import bagel.Image;
import bagel.Input;
import bagel.util.Rectangle;

/**
 *  Weapons -> rocks and bombs
 */
public abstract class Weapons {

    protected Image weapon;
    protected Rectangle weaponHitBox;
    protected String type;
    protected final int ROCK_RANGE = 25;
    protected final int BOMB_RANGE = 50;
    protected static TimeScale timeScale;
    protected double xVal;
    protected double yVal;
    protected boolean isAttachedToBird;
    protected double moving_speed;


    /**
     * Update the state of the weapon
     * @param input the input
     */
    public abstract void update(Input input);

    /**
     * Gets weapon hit box.
     * @return the weapon hit box
     */
    public abstract Rectangle getWeaponHitBox();

    /**
     * Gets weapon type.
     * @return the weapon type
     */
    public abstract String getWeaponType();

    /**
     * Sets weapon hit box.
     */
    public abstract void setWeaponHitBox();

    /**
     * Render weapon.
     */
    public abstract void renderWeapon();

    /**
     * Sets weapon x val.
     * @param x the x
     */
    public abstract void setWeaponXVal(double x);

    /**
     * Sets weapon y val.
     * @param y the y
     */
    public abstract void setWeaponYVal(double y);

    /**
     * Sets attached to bird.
     * @param value the value
     */
    public abstract void setAttachedToBird(boolean value);

    /**
     * Gets attached to bird.
     * @return the attached to bird
     */
    public abstract boolean getAttachedToBird();

    /**
     * Sets moving speed.
     * @param movingSpeed the moving speed
     */
    public abstract void setMovingSpeed(double movingSpeed);
}

