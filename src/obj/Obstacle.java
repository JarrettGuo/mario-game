package obj;

import util.BackGround;
import util.StaticValue;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class represents an obstacle in the game.
 */
public class Obstacle implements Runnable {
    // Coordinates
    private int x;
    private int y;
    // Obstacle type
    private int type;
    // Image to display
    private BufferedImage show = null;
    // Current background scene
    private BackGround bg = null;
    // Thread for obstacle behavior
    private Thread thread = new Thread(this);

    /**
     * Constructor for the obstacle.
     *
     * @param x   The x-coordinate of the obstacle.
     * @param y   The y-coordinate of the obstacle.
     * @param type The type of the obstacle.
     * @param bg  The background scene in which the obstacle is placed.
     */
    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstacle.get(type);
        // If it's a flag, start the thread
        if (type == 8) {
            thread.start();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BufferedImage getShow() {
        return show;
    }

    @Override
    public void run() {
        while (true) {
            if (this.bg.isReach()) {
                if (this.y < 374) {
                    this.y += 5;
                } else {
                    this.bg.setBase(true);
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the boundary of the obstacle as a rectangle.
     *
     * @return A Rectangle object representing the bounds of the obstacle.
     */
    public Rectangle getRec() {
        return new Rectangle(this.x, this.y, 30, 30);
    }
}
