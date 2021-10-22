import bagel.*;

import java.util.Iterator;

/**
 *
 */
public class Level0 extends Level {
    private int frameCounter = 0;
    private boolean goToLevel1 = false;
    private final int LEVEL_UP_MESSAGE_FRAMES = 150;


    /**
     * Instantiates a new Level 0 objeect.
     */
    public Level0() {
        background = new Image("res/level-0/background.png");
        font = new Font("res/font/slkscr.ttf", FONT_SIZE);
        bird = new Bird(0);
        lives = new Lives(0);
        pipes = new PipeList(0);
        timeScale = new TimeScale();
    }


    /**
     * Updates the state of the game, level 0 in this case.
     * @param input: Input
     */
    @Override
    public void update(Input input) {
        background.drawFromTopLeft(0,0); // draw the background
        /* game has started if the user presses space */
        if(input.wasPressed(Keys.SPACE))
            hasGameStarted = true;
        /* if user presses escape, the game should exit */
        if(input.wasPressed(Keys.ESCAPE))
            System.exit(0);

        if(!isGameOver) {
            /* if game hasn't started, render the start message, otherwise perform update operations */
            if (!hasGameStarted)
                renderStartMessage();
            else {
                bird.update(input);
                pipes.update(input);
                lives.update();
                checkBirdCollisionAndUpdateScore(input);
                renderScore();
                checkLevelUpCondition();
                isBirdOutOfBounds();
            }
        }
        else {
            /* if game over, check if the user was successful in completing the level
             * if yes, render level up message for 150 frames and go to level 1
             * otherwise, render game over message
             */
            if(isLevel0Complete && frameCounter++ <= LEVEL_UP_MESSAGE_FRAMES)
                renderLevelUpMessage();
            else if(!isLevel0Complete)
                renderGameOverMessage();
            else if(frameCounter > 150)
                goToLevel1 = true;
        }

    }

    /**
     * Render the "PRESS SPACE TO START" message in the centre of the screen
     */
    @Override
    public void renderStartMessage() {
        font.drawString(INITIAL_MESSAGE, Window.getWidth() / 2.0 - font.getWidth(INITIAL_MESSAGE) / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0);
    }

    /**
     * Render the score in the top left
     */
    @Override
    public void renderScore() {
        font.drawString("SCORE: " + score, 100, 100);
    }

    /**
     * Check if the bird has collided with a pipe and update score.
     * If yes, reduce the number of lives
     * @param input the input
     */
    @Override
    public void checkBirdCollisionAndUpdateScore(Input input) {
        /* iterate through an ArrayList of pipes, check if the bird has collided with any pipe */
        Iterator<Pipe> pipe = pipes.getPipesArrayList().iterator();
        while (pipe.hasNext()) {
            Pipe p = pipe.next();
            if (bird.getBirdCollisionBox().intersects(p.getTopPipeCollisionBox()) ||
                    bird.getBirdCollisionBox().intersects(p.getBottomPipeCollisionBox())) {
                /* if no lives left, game over
                 * otherwise, reduce the number of lives left
                 */
                if (lives.getLives() == 0)
                    isGameOver = true;
                lives.setLives(lives.getLives() - 1);
                pipe.remove();
                pipes.update(input);
            }

            /* update score */
            if (bird.getBirdCollisionBox().centre().x > p.getTopPipeCollisionBox().right()) {
                score = pipes.getPipesArrayList().indexOf(p) + 1;
            }
        }

    }

    /**
     * Renders game over message
     */
    @Override
    public void renderGameOverMessage() {
        font.drawString("GAME OVER", Window.getWidth() / 2.0 - font.getWidth("GAME OVER") / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0);
        font.drawString("FINAL SCORE: " + score,
                Window.getWidth() / 2.0 - font.getWidth("FINAL SCORE" + score) / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0 + 75);
    }


    /**
     * If game over, check the level up condition.
     * returns true if the condition is met,
     * otherwise returns false.
     * @return the outcome of the condition
     */
    public boolean checkLevelUpCondition() {
        if(score == 10) {
            isGameOver = true;
            isLevel0Complete = true;
            return true;
        }
        return false;
    }

    /**
     * if level 0 complete, go to level 1
     *
     * @return: boolean
     */
    public boolean getGoToLevel1() {
        return goToLevel1;
    }

}