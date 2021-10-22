import bagel.Image;
import bagel.Input;
import bagel.Window;
import bagel.util.Rectangle;

/**
 * The Weapon type rock.
 */
public class Rock extends Weapons{

    private boolean isAttachedToBird = false;
    private int distanceTravelled = 0;

    /**
     * Instantiates a new Rock object.
     *
     * @param timeScale the time scale
     */
    public Rock(TimeScale timeScale) {
        type = "ROCK";
        weapon = new Image("res/level-1/rock.png");
        xVal = Window.getWidth();
        yVal = Math.random()*400 + 100;
        this.timeScale = timeScale;
        weaponHitBox = new Rectangle(xVal, yVal, 32, 32);
        moving_speed = timeScale.getPipeMovingSpeed();
    }

    /**
     * update the state of the rock
     * @param input
     */
    @Override
    public void update(Input input) {
        if(distanceTravelled < 100) {
            renderWeapon();
            xVal -= moving_speed;
            if(moving_speed < 0)
                distanceTravelled -= moving_speed;
            setWeaponHitBox();
        }
    }

    /**
     * set the moving speed of the rock
     * @param movingSpeed
     */
    @Override
    public void setMovingSpeed(double movingSpeed) {
        moving_speed = movingSpeed;
    }

    /**
     * get the hitbox of the rock
     * @return
     */
    @Override
    public Rectangle getWeaponHitBox() {
        return weaponHitBox;
    }

    /**
     * get the type of the weapon: rock in this scenario
     * @return
     */
    @Override
    public String getWeaponType() {
        return type;
    }

    /**
     * set the hitbox of the rock
     */
    @Override
    public void setWeaponHitBox() {
        weaponHitBox = new Rectangle(xVal, yVal, 32, 32);
    }

    /**
     * render the weapon on the screen
     */
    @Override
    public void renderWeapon() {
        weapon.drawFromTopLeft(xVal, yVal);
    }

    /**
     * set the x coordinate of the rock
     * @param x
     */
    @Override
    public void setWeaponXVal(double x) {
        this.xVal = x;
    }

    /**
     * set the y coordinate of the rock
     * @param y
     */
    @Override
    public void setWeaponYVal(double y) {
        this.yVal = y;
    }

    /**
     * returns whether the weapon is attached to a bird
     */
    @Override
    public boolean getAttachedToBird() {
        return isAttachedToBird;
    }

    /**
     * change whether the weapon is attached to a bird/not
     * @param attachedToBird
     */
    @Override
    public void setAttachedToBird(boolean attachedToBird) {
        isAttachedToBird = attachedToBird;
    }


}
