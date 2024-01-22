package util;

import obj.Enemy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to statically load and store all the game images and resources.
 */
public class StaticValue {
    // Background images
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;
    // Mario jumping left
    public static BufferedImage jump_L = null;
    // Mario jumping right
    public static BufferedImage jump_R = null;
    // Mario standing left
    public static BufferedImage stand_L = null;
    // Mario standing right
    public static BufferedImage stand_R = null;
    // Castle image
    public static BufferedImage tower = null;
    // Mushroom props
    public static List<BufferedImage> mg = new ArrayList<>();
    // Small flower prop
    public static BufferedImage xh = null;
    // Flagpole
    public static BufferedImage gan = null;
    // Fireballs (left and right)
    public static List<BufferedImage> huoQiu_L = new ArrayList<>();
    public static List<BufferedImage> huoQiu_R = new ArrayList<>();
    // Obstacles
    public static List<BufferedImage> obstacle = new ArrayList<>();
    // Mario running left
    public static List<BufferedImage> run_L = new ArrayList<>();
    // Mario running right
    public static List<BufferedImage> run_R = new ArrayList<>();
    // Mushroom enemies
    public static List<BufferedImage> mogu = new ArrayList<>();
    // Man-eating flower enemies
    public static List<BufferedImage> flower = new ArrayList<>();
    // Turtle enemies moving left
    public static List<BufferedImage> toise_L = new ArrayList<>();
    // Turtle enemies moving right
    public static List<BufferedImage> toise_R = new ArrayList<>();
    // Turtle shells
    public static List<BufferedImage> shell = new ArrayList<>();
    // Coins
    public static List<BufferedImage> jinBi = new ArrayList<>();

    // Prefix for the image paths
    public static String path = "src/images/";

    static Enemy enemy = new Enemy();

    /**
     * Initializes and loads all the images and resources used in the game.
     */
    public static void init() {
        try {
            // Load background images
            bg = ImageIO.read(new File(path + "bg.png"));
            bg2 = ImageIO.read(new File(path + "bg2.png"));
            // Load Mario standing images
            stand_L = ImageIO.read(new File(path + "s_mario_stand_L.png"));
            stand_R = ImageIO.read(new File(path + "s_mario_stand_R.png"));
            // Load castle image
            tower = ImageIO.read(new File(path + "tower.png"));
            // Load flagpole image
            gan = ImageIO.read(new File(path + "gan.png"));
            // Load Mario jumping images
            jump_L = ImageIO.read(new File(path + "s_mario_jump1_L.png"));
            jump_R = ImageIO.read(new File(path + "s_mario_jump1_R.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load mushroom props
        for (int i = 1; i <= 3; i++) {
            try {
                mg.add(ImageIO.read(new File(path + "mushroom" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load small flower prop
        try {
            xh = ImageIO.read(new File(path + "xiaohua.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load fireballs
        for (int i = 1; i <= 2; i++) {
            try {
                huoQiu_L.add(ImageIO.read(new File(path + "huoqiu" + i + ".png")));
                huoQiu_R.add(ImageIO.read(new File(path + "huoqiu" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load coin props
        for (int i = 1; i <= 4; i++) {
            try {
                jinBi.add(ImageIO.read(new File(path + "jinbi" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load images for Mario running
        for (int i = 1; i <= 2; i++) {
            try {
                run_L.add(ImageIO.read(new File(path + "s_mario_run" + i + "_L.png")));
                run_R.add(ImageIO.read(new File(path + "s_mario_run" + i + "_R.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load obstacles
        try {
            obstacle.add(ImageIO.read(new File(path + "brick.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
            obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load pipes
        for (int i = 1; i <= 4; i++) {
            try {
                obstacle.add(ImageIO.read(new File(path + "pipe" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load indestructible bricks and flags
        try {
            obstacle.add(ImageIO.read(new File(path + "brick2.png")));
            obstacle.add(ImageIO.read(new File(path + "flag.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load turtle enemies moving left and right
        for (int i = 1; i <= 2; i++) {
            try {
                toise_L.add(ImageIO.read(new File(path + "Ltortoise" + i + ".png")));
                toise_R.add(ImageIO.read(new File(path + "Rtortoise" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load turtle shells
        for (int i = 1; i <= 4; i++) {
            try {
                shell.add(ImageIO.read(new File(path + "shell" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load mushroom enemies
        for (int i = 1; i <= 3; i++) {
            try {
                mogu.add(ImageIO.read(new File(path + "fungus" + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load man-eating flower enemies
        for (int i = 1; i <= 2; i++) {
            try {
                flower.add(ImageIO.read(new File(path + "flower1." + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load prop boxes
        for (int i = 1; i <= 4; i++) {
            try {
                obstacle.add(ImageIO.read(new File(path + "box1." + i + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
