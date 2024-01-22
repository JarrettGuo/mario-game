import obj.Enemy;
import org.junit.Before;
import org.junit.Test;
import util.BackGround;
import util.StaticValue;

import static org.junit.Assert.*;

/**
 * This class contains tests for the Enemy class.
 * It verifies the correctness of the Enemy object's initialization and the behavior
 * of its getter and setter methods, ensuring that properties like position and type are correctly handled.
 */
public class EnemyTest {

    private Enemy enemy;
    private BackGround bg; // Assuming you have a way to mock or create a simple instance

    /**
     * Sets up the necessary objects before each test.
     * Initializes StaticValue, creates a BackGround instance, and initializes an Enemy object with basic parameters.
     */
    @Before
    public void setUp() {
        StaticValue.init();
        // Initialize with basic parameters
        bg = new BackGround();
        enemy = new Enemy(100, 100, true, 1, bg);
    }

    /**
     * Tests the getX method of the Enemy class.
     * Ensures that the X-coordinate is correctly initialized and returned.
     */
    @Test
    public void testGetX() {
        assertEquals(100, enemy.getX());
    }

    /**
     * Tests the getY method of the Enemy class.
     * Ensures that the Y-coordinate is correctly initialized and returned.
     */
    @Test
    public void testGetY() {
        assertEquals(100, enemy.getY());
    }

    /**
     * Tests the setY method of the Enemy class.
     * Checks if the Y-coordinate of the enemy can be correctly updated.
     */
    @Test
    public void testSetY() {
        enemy.setY(200);
        assertEquals(200, enemy.getY());
    }

    /**
     * Tests the getType method of the Enemy class.
     * Ensures that the type of the enemy is correctly initialized and returned.
     */
    @Test
    public void testGetType() {
        assertEquals(1, enemy.getType());
    }
}
