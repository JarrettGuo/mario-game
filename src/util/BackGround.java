package util;

import obj.DaoJu;
import obj.Enemy;
import obj.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a background scene in the game.
 */
public class BackGround {
    // Image to be displayed for the current scene
    private BufferedImage bgImage = null;
    // Identifies which scene this is
    private int sort;
    // Flag to determine if this is the last scene
    private boolean flag;
    // List to store all obstacles in the scene
    private List<Obstacle> obstacleList = new ArrayList<>();
    // List to store all enemies in the scene
    private List<Enemy> enemyList = new ArrayList<>();
    // List to store all props (DaoJu) in the scene
    private List<DaoJu> daoJuList = new ArrayList<>();
    // Image for the flagpole
    private BufferedImage gan = null;
    // Image for the castle
    private BufferedImage tower = null;
    // Flag to check if Mario has reached the flagpole
    private boolean isReach = false;
    // Flag to check if the flag has landed
    private boolean isBase = false;

    public BackGround() {
    }

    /**
     * Constructs a new BackGround with specific characteristics.
     *
     * @param sort Indicates the level or order of the background.
     * @param flag Indicates if this is the last scene in the sequence.
     */
    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;
        bgImage = flag ? StaticValue.bg2 : StaticValue.bg;

        // Determine if it's the first level
        if (sort == 1) {
            //Mapping the ground of the first level
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }

            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }
            //draw the bricks
            obstacleList.add(new Obstacle(70,360,7,this));
            for (int i = 120; i <= 150; i += 30) {
                obstacleList.add(new Obstacle(i, 300, 7, this));
            }
            obstacleList.add(new Obstacle(180, 300, 9, this));
            obstacleList.add(new Obstacle(210, 300, 7, this));
            for (int i = 300; i <= 570; i += 30) {
                if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
                    obstacleList.add(new Obstacle(i, 300, 7, this));
                } else {
                    obstacleList.add(new Obstacle(i, 300, 0, this));
                }
            }
            for (int i = 420; i <= 450; i += 30) {
                obstacleList.add(new Obstacle(i, 240, 7, this));
            }

            //Drawing of water pipes
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }

            //Drawing the enemy
            enemyList.add(new Enemy(670, 385, true, 1, this));
            enemyList.add(new Enemy(635, 420, true, 2, 328, 428, this));
            enemyList.add(new Enemy(580, 385, true, 3, this));
        }

        //Determining if it's the second level
        if (sort == 2) {
            //Mapping the ground
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }
            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }

            //Drawing Prop Cube 2
            obstacleList.add(new Obstacle(210, 300, 9, this));

            //Drawing of water pipes
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(60, i, 3, this));
                    obstacleList.add(new Obstacle(85, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(60, i, 5, this));
                    obstacleList.add(new Obstacle(85, i, 6, this));
                }
            }
            for (int i = 330; i <= 600; i += 25) {
                if (i == 330) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }

            obstacleList.add(new Obstacle(300, 330, 0, this));

            for (int i = 270; i <= 330; i += 30) {
                if (i == 270 || i == 330) {
                    obstacleList.add(new Obstacle(i, 360, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 360, 7, this));
                }
            }

            for (int i = 240; i <= 360; i += 30) {
                if (i == 240 || i == 360) {
                    obstacleList.add(new Obstacle(i, 390, 0, this));
                } else {
                    obstacleList.add(new Obstacle(i, 390, 7, this));
                }
            }

            obstacleList.add(new Obstacle(240, 300, 0, this));

            for (int i = 360; i <= 540; i += 60) {
                obstacleList.add(new Obstacle(i, 270, 7, this));
            }

            //Drawing the enemy
            enemyList.add(new Enemy(75, 420, true, 2, 328, 418, this));
            enemyList.add(new Enemy(635, 420, true, 2, 298, 388, this));
            enemyList.add(new Enemy(200, 385, true, 1, this));
            enemyList.add(new Enemy(500, 385, true, 1, this));
            enemyList.add(new Enemy(220, 385, true, 3, this));

        }

        // Determining if it's the third level
        if (sort == 3) {
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }

            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }

            //Drawing steps
            for (int i = 70; i <= 130; i += 30){
                obstacleList.add(new Obstacle(i,360,7,this));
            }
            for (int i = 160; i <= 220; i += 30) {
                obstacleList.add(new Obstacle(i, 300, 7, this));
            }
            for (int i = 250; i <= 310; i += 30) {
                obstacleList.add(new Obstacle(i, 240, 7, this));
            }
            for (int i = 340; i <= 400; i += 30) {
                obstacleList.add(new Obstacle(i, 300, 7, this));
            }

            //Drawing of water pipes
            for (int i = 300; i <= 600; i += 25) {
                if (i == 300) {
                    obstacleList.add(new Obstacle(490, i, 3, this));
                    obstacleList.add(new Obstacle(515, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(490, i, 5, this));
                    obstacleList.add(new Obstacle(515, i, 6, this));
                }
            }
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }

            //Drawing the enemy
            enemyList.add(new Enemy(670, 385, true, 1, this));
            enemyList.add(new Enemy(300, 385, true, 1, this));
            enemyList.add(new Enemy(200, 385, true, 1, this));
            enemyList.add(new Enemy(635, 420, true, 2, 328, 428, this));
            enemyList.add(new Enemy(505, 360, true, 2, 268, 368, this));
            enemyList.add(new Enemy(580, 385, true, 3, this));
        }

        // Determining if it's the fourth level
        if (sort == 4) {
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }

            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }

            //Drawing of water pipes
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(130, i, 3, this));
                    obstacleList.add(new Obstacle(155, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(130, i, 5, this));
                    obstacleList.add(new Obstacle(155, i, 6, this));
                }
            }
            for (int i = 300; i <= 600; i += 25) {
                if (i == 300) {
                    obstacleList.add(new Obstacle(250, i, 3, this));
                    obstacleList.add(new Obstacle(275, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(250, i, 5, this));
                    obstacleList.add(new Obstacle(275, i, 6, this));
                }
            }
            for (int i = 240; i <= 600; i += 25) {
                if (i == 240) {
                    obstacleList.add(new Obstacle(370, i, 3, this));
                    obstacleList.add(new Obstacle(395, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(370, i, 5, this));
                    obstacleList.add(new Obstacle(395, i, 6, this));
                }
            }
            for (int i = 300; i <= 600; i += 25) {
                if (i == 300) {
                    obstacleList.add(new Obstacle(490, i, 3, this));
                    obstacleList.add(new Obstacle(515, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(490, i, 5, this));
                    obstacleList.add(new Obstacle(515, i, 6, this));
                }
            }
            for (int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620, i, 3, this));
                    obstacleList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstacleList.add(new Obstacle(620, i, 5, this));
                    obstacleList.add(new Obstacle(645, i, 6, this));
                }
            }
            //Drawing steps
            obstacleList.add(new Obstacle(200,300,7,this));
            obstacleList.add(new Obstacle(320,240,7,this));

            //Drawing the enemy
            enemyList.add(new Enemy(670, 385, true, 1, this));
            enemyList.add(new Enemy(550, 385, true, 1, this));
            enemyList.add(new Enemy(420, 385, true, 1, this));
            enemyList.add(new Enemy(290, 385, true, 1, this));
            enemyList.add(new Enemy(190, 385, true, 1, this));
            enemyList.add(new Enemy(635, 420, true, 2, 328, 428, this));
            enemyList.add(new Enemy(505, 360, true, 2, 268, 368, this));
            enemyList.add(new Enemy(385, 300, true, 2, 208, 308, this));
            enemyList.add(new Enemy(265, 360, true, 2, 268, 368, this));
            enemyList.add(new Enemy(145, 420, true, 2, 328, 428, this));
        }


        //Determining if it's the fifth level
        if (sort == 5) {
            for (int i = 0; i < 27; i++) {
                obstacleList.add(new Obstacle(i * 30, 420, 1, this));
            }

            for (int j = 0; j <= 120; j += 30) {
                for (int i = 0; i < 27; i++) {
                    obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }
            int temp = 290;
            for (int i = 390; i >= 270; i -= 30) {
                for (int j = temp; j <= 410; j += 30) {
                    obstacleList.add(new Obstacle(j, i, 7, this));
                }
                temp += 30;
            }
            temp = 60;
            for (int i = 390; i >= 360; i -= 30) {
                for (int j = temp; j <= 90; j += 30) {
                    obstacleList.add(new Obstacle(j, i, 7, this));
                }
                temp += 30;
            }

            //Drawing the flagpole
            gan = StaticValue.gan;

            //Drawing the Castle
            tower = StaticValue.tower;

            //Adding a Flag to a Flagpole
            obstacleList.add(new Obstacle(515, 220, 8, this));

            //Drawing the enemy
            enemyList.add(new Enemy(150, 385, true, 1, this));
            enemyList.add(new Enemy(220, 385, true, 3, this));
        }
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }


    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }

    public boolean isReach() {
        return isReach;
    }

    public void setReach(boolean reach) {
        isReach = reach;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public List<DaoJu> getDaoJuList() {
        return daoJuList;
    }
}
