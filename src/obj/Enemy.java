package obj;

import util.BackGround;
import util.StaticValue;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class represents an enemy in the game.
 */
public class Enemy implements Runnable {
    // Current coordinates
    private int x;
    private int y;
    // Enemy type
    private int type;
    // Direction of enemy movement
    public boolean face_to = true;
    // Image for the enemy
    private BufferedImage show;
    // Background scene object
    private BackGround bg;
    // Movement limits for the man-eating flower type enemy
    private int max_up = 0;
    private int max_down = 0;
    // Thread for enemy behavior
    private Thread thread = new Thread(this);
    // Current state of the image
    private int image_type = 0;

    public Enemy() {
    }

    /**
     * Constructor for mushroom type enemy.
     */
    public Enemy(int x, int y, boolean face_to, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.bg = bg;
        show = StaticValue.mogu.get(0);
        thread.start();
    }

    /**
     * Constructor for man-eating flower type enemy.
     */
    public Enemy(int x, int y, boolean face_to, int type, int max_up, int max_down, BackGround bg) {
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.max_up = max_up;
        this.max_down = max_down;
        this.bg = bg;
        show = StaticValue.flower.get(0);
        thread.start();
    }

    /**
     * Method to handle enemy's death.
     */
    public void death() {
        if (this.type == 1) {
            show = StaticValue.mogu.get(1);
            this.bg.getEnemyList().remove(this);
        } else if (this.type == 3) {
            type = 4;
        } else if (this.type == 4) {
            show = StaticValue.shell.get(0);
            this.bg.getEnemyList().remove(this);
            System.out.println(this.y);
        } else if (this.type == 2) {
            this.bg.getEnemyList().remove(this);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public int getType() {
        return type;
    }


    /**
     * Gets the boundary of the enemy as a rectangle.
     *
     * @return A Rectangle object representing the bounds of the enemy.
     */
    public Rectangle getRec() {
        return new Rectangle(this.x, this.y, 35, 35);
    }

    @Override
    public void run() {
        while (true) {
            // Determine if it's a mushroom enemy
            if (type == 1) {
                if (face_to) {
                    this.x -= 2;
                } else {
                    this.x += 2;
                }
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.mogu.get(image_type);
            } else if (type == 3) {
                if (face_to) {
                    this.x -= 2;
                    show = StaticValue.toise_L.get(image_type);
                } else {
                    this.x += 2;
                    show = StaticValue.toise_R.get(image_type);
                }
                image_type = image_type == 1 ? 0 : 1;
            } else if (type == 4) {
                if (face_to) {
                    this.x -= 5;
                } else {
                    this.x += 5;
                }
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.shell.get(image_type);
            }
            boolean canLeft = true;
            boolean canRight = true;
            // Iterate over obstacle objects
            for (int i = 0; i < bg.getObstacleList().size(); i++) {
                Obstacle ob1 = bg.getObstacleList().get(i);
                // Determine if you can go right
                if (ob1.getX() == this.x + 36 && (ob1.getY() + 50 > this.y && ob1.getY() - 26 < this.y)) {
                    canRight = false;
                }

                // Determine if you can go left
                if (ob1.getX() == this.x - 36 && (ob1.getY() + 50 > this.y && ob1.getY() - 26 < this.y)) {
                    canLeft = false;
                }
            }

            if ((face_to && !canLeft) || this.x == 20) {
                face_to = false;
            } else if (((!face_to) && (!canRight)) || this.x == 754) {
                face_to = true;
            }

            // Determine if it's a cannibalistic enemy
            if (type == 2) {
                if (face_to) {
                    this.y -= 2;
                } else {
                    this.y += 2;
                }

                image_type = image_type == 1 ? 0 : 1;

                //Cannibalistic flower reached the limit position or not
                if (face_to && (this.y == max_up)) {
                    face_to = false;
                }
                if ((!face_to) && (this.y == max_down)) {
                    face_to = true;
                }

                show = StaticValue.flower.get(image_type);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
