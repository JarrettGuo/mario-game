package obj;

import util.BackGround;
import util.StaticValue;

import java.awt.image.BufferedImage;

/**
 * This class represents the Mario character in the game.
 */
public class Mario implements Runnable {
    // Coordinates
    private int x;
    private int y;
    // Mario's width and height
    private int width = 25;      // Width
    private int height = 25;     // Height
    // Current status of Mario
    private String status = null;
    // Flag to check if Mario has eaten a mushroom
    private boolean big = false;
    // Flag to check if Mario has eaten a flower
    private boolean eatHua = false;
    // Current image to display for Mario
    private BufferedImage show = null;
    // Background object for obstacle information
    private BackGround backGround = new BackGround();
    // Thread for Mario's actions
    private Thread thread = null;
    // Mario's movement speed
    private int xSpeed;
    // Mario's jumping speed
    private int ySpeed;
    // Index for animations
    private int index;
    // Time Mario spends going up during a jump
    private int upTime = 0;
    // Flag to check if Mario has reached the castle door
    private boolean isOK;
    // Flag to check if Mario is dead
    private boolean isDeath = false;
    // Score
    private int score = 0;
    // Direction Mario is facing
    private boolean face_to = true;

    public Mario() {
    }

    public Mario(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        show = StaticValue.stand_R;
        this.status = "stand--right";
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Method to handle Mario's death.
     */
    public void death() {
        isDeath = true;
    }

    /**
     * Moves Mario to the left.
     */
    public void leftMove() {
        xSpeed = -5;
        if (backGround.isReach()) {
            xSpeed = 0;
        }
        status = status.contains("jump") ? "jump--left" : "move--left";
        this.face_to = true;
    }

    /**
     * Moves Mario to the right.
     */
    public void rightMove() {
        xSpeed = 5;
        if (backGround.isReach()) {
            xSpeed = 0;
        }
        status = status.contains("jump") ? "jump--right" : "move--right";
        this.face_to = false;
    }

    /**
     * Stops Mario's movement to the left.
     */
    public void leftStop() {
        xSpeed = 0;
        status = status.contains("jump") ? "jump--left" : "stop--left";
        this.face_to = true;
    }

    /**
     * Stops Mario's movement to the right.
     */
    public void rightStop() {
        xSpeed = 0;
        status = status.contains("jump") ? "jump--right" : "stop--right";
        this.face_to = false;
    }

    /**
     * Handles Mario's jump action.
     */
    public void jump() {
        if (!status.contains("jump")) {
            status = status.contains("left") ? "jump--left" : "jump--right";
            this.face_to = !status.contains("left");
            ySpeed = this.isEatHua() ? -20 : -10;
            upTime = this.isEatHua() ? 5 : 7;
        }
        if (backGround.isReach()) {
            ySpeed = 0;
        }
    }

    /**
     * Handles Mario's fall action.
     */
    public void fall() {
        status = status.contains("left") ? "jump--left" : "jump--right";
        this.face_to = !status.contains("left");
        ySpeed = 10;
    }

    @Override
    public void run() {
        while (true) {
            //Determine if you are on an obstacle
            boolean onObstacle = false;
            // Determine if you can go right
            boolean canRight = true;
            // Determine if you can go left
            boolean canLeft = true;
            //Determine whether Mario has reached the flagpole position or not
            if (backGround.isFlag() && this.x >= 500) {
                this.backGround.setReach(true);

                //Determine if the flag has finished falling
                if (this.backGround.isBase()) {
                    status = "move--right";
                    if (x < 690) {
                        x += 5;
                    } else {
                        isOK = true;
                    }
                } else {
                    if (y < 395) {
                        xSpeed = 0;
                        this.y += 5;
                        status = "jump--right";
                    }

                    if (y > 395) {
                        this.y = 395;
                        status = "stop--right";
                    }
                }

            } else {
                // Iterate over all obstacles in the current scene.
                for (int i = 0; i < backGround.getObstacleList().size(); i++) {
                    Obstacle ob = backGround.getObstacleList().get(i);
                    //Determine if Mario is on an obstacle course
                    if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                        onObstacle = true;
                    }
                    // Determine if you are jumping up to the top of the bricks.
                    if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20) && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                        if (ob.getType() == 0) {
                            backGround.getObstacleList().remove(ob);
                            score += 1;
                        }
                        //If you hit a prop cube, draw a prop mushroom and change the prop cube to a normal obstacle cube.
                        if (ob.getType() == 9 ) {
                            if (this.big) {
                                backGround.getDaoJuList().add(new DaoJu(ob.getX(), this.y - 55, 1, backGround));
                            } else {
                                backGround.getDaoJuList().add(new DaoJu(ob.getX(), this.y - 55, 0, backGround));
                            }
                            ob.setType(11);
                        }
                        // Judge Prop Cube 3, topping Prop Cube 3 increases points by 5 and changes Prop Cube 3 to a normal obstacle cube
                        if (ob.getType() == 11) {
                            score += 1;
                            backGround.getDaoJuList().add(new DaoJu(ob.getX() + 5,this.y - 53,2,backGround));
                            ob.setType(12);
                        }

                        if (ob.getType() == 12) {
                            score += 5;
                            ob.setType(7);
                        }
                        upTime = 0;
                    }

                    // Determine if you can go right
                    if (ob.getX() == this.x + 25 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canRight = false;
                    }

                    // Determine if you can go left
                    if (ob.getX() == this.x - 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canLeft = false;
                    }

                }

                // Determine if Mario ate the props
                for (int i = 0; i < backGround.getDaoJuList().size(); i++) {
                    DaoJu d = backGround.getDaoJuList().get(i);

                    if ((d.getX() + 35 > this.x && d.getX() - 25 < this.x) && (d.getY() + 35 > this.y && d.getY() - 20 < this.y)) {
                        if (d.getType() == 0) {
                            d.eat();
                            this.setBig(true);
                        }
                        if (d.getType() == 1) {
                            d.eat();
                            this.setEatHua(true);
                        }
                    }
                    if ((d.getY() + 35 > this.y && d.getY() - 25 < this.y) && (d.getX() + 35 > this.x && d.getX() - 20 < this.y)) {
                        if (d.getType() == 2) {
                            this.score += 5;
                            d.eat();
                        }
                    }
                }

                // Determine if Mario has run into an enemy and died or stomped on a mushroom enemy.
                for (int i = 0; i < backGround.getEnemyList().size(); i++) {
                    Enemy e = backGround.getEnemyList().get(i);

                    if (e.getY() == this.y + 20 && (e.getX() - 25 <= this.x && e.getX() + 35 >= this.x)) {
                        if (e.getType() == 1) {
                            e.death();
                            score += 2;
                            upTime = 3;
                            ySpeed = -10;
                        } else if (e.getType() == 2) {
                            // Mario Death
                            death();
                        } else if (e.getType() == 3) {
                            score += 2;
                            upTime = 3;
                            ySpeed = -10;
                            e.death();
                            System.out.println(this.x);
                            e.setY(e.getY() + 10);
                        } else if (e.getType() == 4) {
                            score += 2;
                            upTime = 3;
                            ySpeed = -10;
                            e.death();
                        }
                    }

                    if ((e.getX() + 35 > this.x && e.getX() - 25 < this.x) && (e.getY() + 35 > this.y && e.getY() - 20 < this.y)) {
                        // Mario Death
                        death();
                    }
                }

                // Perform a Mario Jump
                if (onObstacle && upTime == 0) {
                    if (status.indexOf("left") != -1) {
                        if (xSpeed != 0) {
                            status = "move--left";
                        } else {
                            status = "stop--left";
                        }
                    } else {
                        if (xSpeed != 0) {
                            status = "move--right";
                        } else {
                            status = "stop--right";
                        }
                    }
                } else {
                    if (upTime != 0) {
                        upTime--;
                    } else {
                        fall();
                    }
                    y += ySpeed;
                }
            }

            if ((canLeft && xSpeed < 0) || (canRight && xSpeed > 0)) {
                x += xSpeed;
                // Determine if Mario has reached the far left.
                if (x < 0) {
                    x = 0;
                }
            }
            // Determine whether the current state is mobile or not
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }
            //Judge whether to move to the left or not
            if ("move--left".equals(status)) {
                show = StaticValue.run_L.get(index);
            }
            //Judge whether to move to the right or not
            if ("move--right".equals(status)) {
                show = StaticValue.run_R.get(index);
            }
            //Judge whether to stop to the left or not
            if ("stop--left".equals(status)) {
                show = StaticValue.stand_L;
            }
            //Judge whether to stop to the right
            if ("stop--right".equals(status)) {
                show = StaticValue.stand_R;
            }
            //Judge whether to jump to the left or not
            if ("jump--left".equals(status)) {
                show = StaticValue.jump_L;
            }
            //Judge whether to jump to the right or not
            if ("jump--right".equals(status)) {
                show = StaticValue.jump_R;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public boolean isOK() {
        return isOK;
    }

    public boolean isDeath() {
        return isDeath;
    }

    public int getScore() {
        return score;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isBig() {
        return big;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    public boolean isEatHua() {
        return eatHua;
    }

    public void setEatHua(boolean eatHua) {
        this.eatHua = eatHua;
    }

    public boolean isFace_to() {
        return face_to;
    }

    public void setFace_to(boolean face_to) {
        this.face_to = face_to;
    }

    public String getStatus() {
        return status;
    }
}
