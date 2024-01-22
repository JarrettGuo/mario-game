import obj.Mario;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Mario class. This class verifies the correct behavior of Mario's movement and state changes,
 * such as moving left or right, jumping, and death state.
 */
public class MarioTest {
    private Mario mario;

    /**
     * Sets up the Mario object before each test.
     * Initializes Mario with specific position and size parameters.
     */
    @Before
    public void setUp() {
        mario = new Mario(0, 0, 25, 25); // Initializing Mario Objects
    }

    /**
     * Tests the leftMove method of the Mario class.
     * Checks if Mario's status updates to moving left and his facing direction is set correctly.
     */
    @Test
    public void testLeftMove() {
        mario.leftMove();
        assertEquals("The state should move to the left", "move--left", mario.getStatus());
        assertTrue("Mario should be facing left.", mario.isFace_to());
    }

    /**
     * Tests the rightMove method of the Mario class.
     * Checks if Mario's status updates to moving right and his facing direction is set correctly.
     */
    @Test
    public void testRightMove() {
        mario.rightMove();
        assertEquals("The state should move to the right", "move--right", mario.getStatus());
        assertFalse("Mario should be facing right.", mario.isFace_to());
    }

    /**
     * Tests the jump method of the Mario class.
     * Ensures that Mario's status includes jumping after the method is called.
     */
    @Test
    public void testJump() {
        mario.jump();
        assertTrue("The state should contain jumps", mario.getStatus().contains("jump"));
    }

    /**
     * Tests the death method of the Mario class.
     * Verifies that Mario is marked as dead after the method is called.
     */
    @Test
    public void testDeath() {
        mario.death();
        assertTrue("Mario should be dead.", mario.isDeath());
    }
}
