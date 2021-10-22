import bagel.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to perform all level 1 tasks
 */
public class Level1 extends Level {

    private final String INITIAL_MESSAGE_2 = "PRESS 'S' TO SHOOT";
    private final String SUCCESS_MESSAGE = "CONGRATULATIONS!";
    private int frameCounter = 0;
    private Weapons weapons;

    /**
     * Instantiates a new Level 1 object.
     */
    public Level1() {
        background = new Image("res/level-1/background.png");
        font = new Font("res/font/slkscr.ttf", FONT_SIZE);
        bird = new Bird(1);
        lives = new Lives(1);
        pipes = new PipeList(1);
    }

    /**
     * updates the state of the game
     * @param input: Input
     */
    @Override
    public void update(Input input) {

            background.drawFromTopLeft(0, 0); // draw the background

            if (input.wasPressed(Keys.SPACE))
                hasGameStarted = true;
            if (input.wasPressed(Keys.ESCAPE))
                System.exit(0);

            if (!isGameOver) {
                /* if game hasn't started, render the start message for level 1
                 * otherwise, perform updates on all the objects for this level
                 */
                if (!hasGameStarted)
                    renderStartMessage();
                else {
                    bird.update(input);
                    lives.update();
                    pipes.update(input);
                    checkBirdCollisionAndUpdateScore(input);
                    renderScore();
                    isBirdOutOfBounds();
                    checkGameOverCondition();
                    /* add new weapon randomly*/
                    if(frameCounter++ % 633 == 0)
                        if(Math.random() <= 0.5)
                            weapons = new Rock(pipes.getTimeScale());
                        else
                            weapons = new Bomb(pipes.getTimeScale());
                    if(attachToBird()) {
                        shootObject(input);
                        checkWeaponAndPipeCollision();
                    }
                    weapons.update(input);
                }
            } else {
                /* if game over, check if win condition has been satisfied
                 * if yes, render success message
                 * otherwise, render game over message
                 */
                if (score == 30)
                    renderSuccessMessage();
                else
                    renderGameOverMessage();
            }
    }

    /**
     * Check game over condition.
     */
    public void checkGameOverCondition() {
        if(lives.getLives() == 0 || score == 30)
            isGameOver = true;
    }

    /**
     * Check if the weapon collides with a pipe once shot.
     * If yes, remove the pipe given appropriate weapon and pipe combination and update score
     */
    public void checkWeaponAndPipeCollision() {
        Iterator<Pipe> pipeIterator = pipes.getPipesArrayList().iterator();
        while (pipeIterator.hasNext()) {
            Pipe p = pipeIterator.next();
            if (p.getType().equals("PLASTIC") && weapons.getWeaponType().equals("ROCK")) {
                if (p.getTopPipeCollisionBox().intersects(weapons.getWeaponHitBox()) ||
                        p.getBottomPipeCollisionBox().intersects(weapons.getWeaponHitBox())) {
                    pipeIterator.remove();
                    score += 1;
                }
            }
            else if (weapons.getWeaponType().equals("BOMB") && (p.getType().equals("PLASTIC" ) || p.getType().equals("STEEL"))) {
                if (p.getTopPipeCollisionBox().intersects(weapons.getWeaponHitBox()) ||
                        p.getBottomPipeCollisionBox().intersects(weapons.getWeaponHitBox())) {
                    pipeIterator.remove();
                    score += 1;
                }
            }
        }
    }

    /**
     * Shoot weapon if S is pressed
     * @param input the input
     */
    public void shootObject(Input input) {
        if(input.wasPressed(Keys.S)) {
            weapons.setMovingSpeed(-20);
            weapons.update(input);
            weapons.setAttachedToBird(false);
        }
    }

    /**
     * Attach to bird boolean.
     * @return the boolean
     */
    public boolean attachToBird() {
        if(bird.getBirdCollisionBox() != null && weapons != null) {
            if (bird.getBirdCollisionBox().intersects(weapons.getWeaponHitBox())) {
                weapons.setWeaponXVal(bird.getBirdPositionX() + 15);
                weapons.setWeaponYVal(bird.getBirdPositionY());
                return true;
            }
        }
        return false;
    }

    /**
     * Render success message.
     */
    public void renderSuccessMessage() {
        font.drawString(SUCCESS_MESSAGE, Window.getWidth() / 2.0 - font.getWidth(SUCCESS_MESSAGE) / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0);
    }

    /***
     * Render Start message
     */
    @Override
    public void renderStartMessage() {
        font.drawString(INITIAL_MESSAGE, Window.getWidth() / 2.0 - font.getWidth(INITIAL_MESSAGE) / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0);
        font.drawString(INITIAL_MESSAGE_2, Window.getWidth() / 2.0 - font.getWidth(INITIAL_MESSAGE_2) / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0 + 68);

    }

    /**
     * render score on top left
     */
    @Override
    public void renderScore() {
        font.drawString("SCORE: " + score, 100, 100);
    }

    /**
     * check if bird collides with a pipe.
     * If yes, remove the pipe and reduce a life
     * @param input: Input
     */
    @Override
    public void checkBirdCollisionAndUpdateScore(Input input) {
        Iterator<Pipe> pipe = pipes.getPipesArrayList().iterator();
        while (pipe.hasNext()) {
            Pipe p = pipe.next();
            if (p.getType().equals("STEEL")) {
                if (bird.getBirdCollisionBox().intersects(p.getTopPipeCollisionBox()) ||
                        bird.getBirdCollisionBox().intersects(p.getBottomPipeCollisionBox()) ||
                        bird.getBirdCollisionBox().intersects(p.getTopFlameHitBox()) ||
                        bird.getBirdCollisionBox().intersects(p.getBottomFlameHitBox())) {
                    if (lives.getLives() == 0)
                        isGameOver = true;
                    lives.setLives(lives.getLives() - 1);
                    pipe.remove();
                    pipes.update(input);
                }
            } else {
                if (bird.getBirdCollisionBox().intersects(p.getTopPipeCollisionBox()) ||
                        bird.getBirdCollisionBox().intersects(p.getBottomPipeCollisionBox())) {
                    if (lives.getLives() == 0)
                        isGameOver = true;
                    lives.setLives(lives.getLives() - 1);
                    pipe.remove();
                    pipes.update(input);
                }

                if (bird.getBirdCollisionBox().centre().x > p.getTopPipeCollisionBox().right()) {
                    score = pipes.getPipesArrayList().indexOf(p) + 1;
                }
            }
        }
    }

    /**
     * render game over message
     */
    @Override
    public void renderGameOverMessage() {
        font.drawString("GAME OVER", Window.getWidth() / 2.0 - font.getWidth("GAME OVER") / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0);
        font.drawString("FINAL SCORE: " + score,
                Window.getWidth() / 2.0 - font.getWidth("FINAL SCORE" + score) / 2,
                Window.getHeight() / 2.0 + FONT_SIZE / 4.0 + 75);
    }
}

