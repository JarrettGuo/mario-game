import obj.*;
import util.BackGround;
import util.StaticValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the main frame of the game, extending JFrame and implementing KeyListener and Runnable.
 */
public class MyFrame extends JFrame implements KeyListener, Runnable {
    // Stores all backgrounds
    private List<BackGround> allBg = new ArrayList<>();
    // Stores the current background
    private BackGround nowBg = new BackGround();
    // Used for double buffering
    private Image offScreenImage = null;
    // Mario object
    private Mario mario = new Mario();
    // Thread object for Mario's movement
    private Thread thread = new Thread(this);

    /**
     * Constructor of MyFrame class. Initializes the game window.
     */
    public MyFrame() {
        // Sets window size to 800x600
        this.setSize(800,600);
        // Centers the window
        this.setLocationRelativeTo(null);
        // Makes the window visible
        this.setVisible(true);
        // Exits the program when window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prevents resizing of the window
        this.setResizable(false);
        // Adds a key listener to the window
        this.addKeyListener(this);
        // Sets the window title
        this.setTitle("Super Mario");
        // Initializes images
        StaticValue.init();
        // Initializes Mario
        mario = new Mario(10,355,this.getWidth(),this.getHeight());
        // Creates all scenes
        for (int i = 1; i <= 5; i++) {
            allBg.add(new BackGround(i, i == 5));
        }
        // Sets the first scene as the current scene
        nowBg = allBg.get(0);
        mario.setBackGround(nowBg);
        // Repaints the graphics
        repaint();
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(800,600);
        }

        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,800,600);

        // Draw background
        graphics.drawImage(nowBg.getBgImage(),0,0,this);

        // Draw enemies
        for (Enemy e : nowBg.getEnemyList()) {
            graphics.drawImage(e.getShow(),e.getX(),e.getY(),this);
        }

        // Draw mushroom props
        for (DaoJu d : nowBg.getDaoJuList()) {
            graphics.drawImage(d.getShow(),d.getX(),d.getY(),this);
        }

        // Draw obstacles
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(),ob.getX(),ob.getY(),this);
        }

        // Draw castle
        graphics.drawImage(nowBg.getTower(),620,270,this);

        // Draw flagpole
        graphics.drawImage(nowBg.getGan(),500,220,this);


        // Draw Mario
        if (!mario.isBig()) {
            graphics.drawImage(mario.getShow(),mario.getX(),mario.getY(),25,25,this);
        } else {
            graphics.drawImage(mario.getShow(),mario.getX(),mario.getY() - 15,28,40,this);
        }

        // Display score
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Helvetica", Font.BOLD, 25));
        graphics.drawString("Current score: " + mario.getScore(),300,100);
        graphics.setColor(c);

        // Draws the image onto the window
        g.drawImage(offScreenImage,0,0,this);
    }

    public static void main(String[] args) {
        new MyFrame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    /**
     * Handles key press events for controlling Mario.
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Move right
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mario.rightMove();
        }
        // Move left
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            mario.leftMove();
        }
        // Jump
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            mario.jump();
        }
    }

    /**
     * Handles key release events for stopping Mario's movement.
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Stop moving left
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            mario.leftStop();
        }
        // Stop moving right
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mario.rightStop();
        }
    }

    /**
     * The run method for the game's main loop.
     */
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);

                // Check if Mario reaches the end of the screen
                if (mario.getX() >= 775) {
                    nowBg = allBg.get(nowBg.getSort());
                    mario.setBackGround(nowBg);
                    mario.setX(10);
                    mario.setY(355);
                }

                // Check if Mario dies
                if (mario.isDeath()) {
                    JOptionPane.showMessageDialog(this, "GAME OVER!!!");
                    System.exit(0);
                }

                // Check if the game is completed
                if (mario.isOK()) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You have completed the game.");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Obstacle getMario() {
        return new Obstacle(this.getX(),this.getY(),1,this.nowBg);
    }
}
