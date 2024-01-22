package obj;

import util.BackGround;
import util.StaticValue;

import java.awt.image.BufferedImage;

/**
 * This class represents a prop (DaoJu) in the game.
 */
public class DaoJu implements Runnable {
    // Current coordinates
    private int x;
    private int y;
    // Prop type
    private int type;
    // Image for the prop
    private BufferedImage show;
    // Background scene object
    private BackGround bg;
    // Thread for prop behavior
    private Thread thread = new Thread(this);
    // Current state of the image
    private int image_type = 0;

    public DaoJu() {
    }

    /**
     * Constructor for the mushroom prop.
     *
     * @param x    The x-coordinate of the prop.
     * @param y    The y-coordinate of the prop.
     * @param type The type of the prop.
     * @param bg   The background scene in which the prop is present.
     */
    public DaoJu(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.mogu.get(0);
        thread.start();
    }

    /**
     * Method to handle the consumption of the prop.
     */
    public void eat(){
        this.bg.getDaoJuList().remove(this);
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

    public BufferedImage getShow() {
        return show;
    }

    @Override
    public void run() {
        while (true) {
            //Judging the type of props
            if (type == 0) {
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.mg.get(image_type);
            }

            //Determining if it's a floret prop
            if (type == 1) {
                show = StaticValue.xh;
            }

            //Determine if it is a gold prop
            if (type == 2) {
                image_type = image_type == 1 ? 0 : 1;
                show = StaticValue.jinBi.get(image_type);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
