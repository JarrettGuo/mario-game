import org.junit.Before;
import org.junit.Test;
import util.BackGround;
import util.StaticValue;

import static org.junit.Assert.*;

/**
 * This class contains tests for the BackGround class.
 * It checks the initialization and state of various attributes in the BackGround object,
 * such as obstacle lists, enemy lists, DaoJu lists, background images, sort and flag values.
 */
public class BackgroundTest {

    private BackGround bg;

    /**
     * Sets up necessary objects and state before each test.
     * Initializes StaticValue class and creates instances of BackGround for testing.
     */
    @Before
    public void setUp() {
        StaticValue.init();
        bg = new BackGround();
        // You may need to set up or mock the StaticValue dependencies here
        bg = new BackGround(1, false); // Test with different constructors
    }

    /**
     * Tests the initialization of the obstacle list in the BackGround object.
     * Ensures that the list is not null and not empty.
     */
    @Test
    public void testObstacleListInitialization() {
        assertNotNull("Obstacle list should not be null", bg.getObstacleList());
        assertFalse("Obstacle list should not be empty", bg.getObstacleList().isEmpty());
    }

    /**
     * Tests the initialization of the enemy list in the BackGround object.
     * Ensures that the list is not null.
     */
    @Test
    public void testEnemyListInitialization() {
        assertNotNull("Enemy list should not be null", bg.getEnemyList());
        // Add more specific checks if necessary, like verifying the number of enemies
    }

    /**
     * Tests the initialization of the DaoJu list in the BackGround object.
     * Ensures that the list is not null.
     */
    @Test
    public void testDaoJuListInitialization() {
        assertNotNull("DaoJu list should not be null", bg.getDaoJuList());
    }

    /**
     * Tests whether the background image is properly initialized in the BackGround object.
     * Ensures that the image is not null.
     */
    @Test
    public void testBackgroundImage() {
        assertNotNull("Background image should not be null", bg.getBgImage());
    }

    /**
     * Tests the correct initialization of 'sort' and 'flag' fields in the BackGround object.
     * Ensures that these fields match the values provided in the constructor.
     */
    @Test
    public void testSortAndFlag() {
        assertEquals("Sort should match constructor argument", 1, bg.getSort());
        assertFalse("Flag should match constructor argument", bg.isFlag());
    }
}
