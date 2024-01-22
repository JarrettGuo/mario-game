import obj.Obstacle;
import org.junit.Before;
import org.junit.Test;
import util.BackGround;
import util.StaticValue;

import static org.junit.Assert.*;

/**
 * Tests for the Obstacle class.
 * This class verifies the correct creation and initialization of Obstacle objects,
 * ensuring that the properties like position, type, and the associated image are properly set.
 */
public class ObstacleTest {
    private Obstacle obstacle;
    private BackGround bg;

    /**
     * Sets up the testing environment before each test.
     * Initializes necessary objects including StaticValue for global settings,
     * BackGround, and an Obstacle instance.
     */
    @Before
    public void setUp() {
        // Initialize the StaticValue class to populate the static list.
        StaticValue.init();

        bg = new BackGround();
        obstacle = new Obstacle(100, 100, 0, bg);
    }

    /**
     * Tests the Obstacle constructor.
     * Verifies that an Obstacle object is correctly initialized with the given parameters.
     */
    @Test
    public void testObstacleConstructor() {
        assertEquals("Incorrect X-coordinate of obstacle", 100, obstacle.getX());
        assertEquals("Incorrect Y-coordinate of obstacle", 100, obstacle.getY());
        assertEquals("Incorrect type of obstacle", 0, obstacle.getType());
        assertNotNull("Obstacle image not set correctly", obstacle.getShow());
    }
}