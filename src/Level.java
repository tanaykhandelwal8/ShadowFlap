import bagel.Font;
import bagel.Image;
import bagel.Input;
import bagel.Window;

/**
 * Abstract level class, which is used by Level0 and Level1.
 */
public abstract class Level {

    protected Image background;
    protected static Font font;
    protected final int FONT_SIZE = 48;
    protected final String INITIAL_MESSAGE = "PRESS SPACE TO START";
    protected int score = 0;
    protected Bird bird;
    protected TimeScale timeScale;
    protected Lives lives;
    protected PipeList pipes;
    protected boolean isGameOver = false;
    protected boolean hasGameStarted = false;
    protected boolean isLevel0Complete = false;
    protected boolean isSuccess = false;
    private final String LEVEL_UP = "LEVEL-UP!";


    /**
     * Update the state of the level
     *
     * @param input: Input
     */
    public abstract void update(Input input);

    /**
     * Render the start message of the level
     */
    public abstract void renderStartMessage();

    /**
     * Render score on the top left
     */
    public abstract void renderScore();

    /**
     * Check bird collision and update score accordingly
     *
     * @param input the input
     */
    public abstract void checkBirdCollisionAndUpdateScore(Input input);

    /**
     * Render game over message once complete.
     */
    public abstract void renderGameOverMessage();

    /**
     * checks if bird has gone out of bounds. If yes, the number of lives remaining is updated
     */
    public void isBirdOutOfBounds() {
        if (bird.getBirdPositionY() < 0 || bird.getBirdPositionY() > Window.getHeight()) {
            if (lives.getLives() == 0) {
                isGameOver = true;
            } else {
                bird.setBirdPositionX(200);
                bird.setBirdPositionY(350);
                lives.setLives(lives.getLives() - 1);
            }

        }
    }

    /**
     * Render level up message once level 0 is complete.
     */
    public void renderLevelUpMessage() {
        background.drawFromTopLeft(0,0);
        font.drawString(LEVEL_UP, Window.getWidth()/2.0 - font.getWidth(LEVEL_UP)/2.0,
                Window.getHeight()/2.0 + FONT_SIZE/4.0);
    }


}
