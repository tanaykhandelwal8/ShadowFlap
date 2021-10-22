import bagel.Image;
import bagel.Input;
import bagel.Window;
import bagel.util.Rectangle;

/**
 * Bomb weapon, builds upon the abstract class Weapons
 */
public class Bomb extends Weapons{

    private int distanceTravelled = 0;

    /**
     * Instantiates a new Bomb.
     *
     * @param timeScale the time scale
     */
    public Bomb(TimeScale timeScale) {
        type = "BOMB";
        weapon = new Image("res/level-1/bomb.png");
        xVal = Window.getWidth();
        yVal = Math.random() * 400 + 100;
        this.timeScale = timeScale;
        weaponHitBox = new Rectangle(xVal, yVal, 32, 32);
        moving_speed = timeScale.getPipeMovingSpeed();
    }
    @Override
    public void update(Input input) {
        if(distanceTravelled < 200) {
            renderWeapon();;
            xVal -= moving_speed;
            if(moving_speed < 0)
                distanceTravelled -= moving_speed;
            setWeaponHitBox();
        }
    }

    /**
     * gets the hitbox of the weapon
     * @return: Rectangle: hitbox of the weapon
     */
    @Override
    public Rectangle getWeaponHitBox() {
        return weaponHitBox;
    }

    /**
     * used to check if a weapon is a bomb or a rock
     * @return: String -> either "BOMB" or "ROCK", "BOMB" in this case
     */
    @Override
    public String getWeaponType() {
        return type;
    }

    /**
     * used to set the hitbox of the weapon as it moves
     */
    @Override
    public void setWeaponHitBox() {
        weaponHitBox = new Rectangle(xVal, yVal, 32, 32);
    }

    /**
     * used to render the weapon on the screen using the x and y coordinates
     */
    @Override
    public void renderWeapon() {
        weapon.drawFromTopLeft(xVal, yVal);
    }

    /**
     * used to change the x coordinate of the weapon
     * @param x: double
     */
    @Override
    public void setWeaponXVal(double x) {
        xVal = x;
    }

    /**
     * used to change the y coordinate of the weapon
     * @param y: double
     */
    @Override
    public void setWeaponYVal(double y) {
        yVal = y;
    }

    /**
     * used to change the status of the weapon i.e. whether it is attached to the bird/not
     * @param value: boolean
     */
    @Override
    public void setAttachedToBird(boolean value) {
        isAttachedToBird = value;
    }

    /**
     * used to get whether the weapon is attached to a bird/not
     * @return: boolean
     */
    @Override
    public boolean getAttachedToBird() {
        return isAttachedToBird;
    }

    /**
     * sets the moving speed of the weapon, which is initially retrieved from the timeScale object,
     * but changes once the weapon is shot
     * @param movingSpeed: double
     */
    @Override
    public void setMovingSpeed(double movingSpeed) {
        moving_speed = movingSpeed;
    }
}
