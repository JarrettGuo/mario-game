import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.StaticValue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the StaticValue class.
 * This class verifies the proper initialization of static resources in the StaticValue class,
 * ensuring that various image resources are correctly loaded and available.
 */
class StaticValueTest {

    /**
     * Prepares the testing environment before each test.
     * Calls the init method of StaticValue to load and initialize all static resources.
     */
    @BeforeEach
    void setUp() {
        StaticValue.init();
    }

    /**
     * Tests the initialization of static resources in the StaticValue class.
     * Ensures that all images and lists of images are properly loaded and not null or empty.
     */
    @Test
    void testInit() {
        assertAll(
                () -> assertNotNull(StaticValue.bg, "Background image should be loaded"),
                () -> assertNotNull(StaticValue.stand_L, "Stand left image should be loaded"),
                () -> assertNotNull(StaticValue.stand_R, "Stand right image should be loaded"),
                () -> assertNotNull(StaticValue.jump_L, "Jump left image should be loaded"),
                () -> assertNotNull(StaticValue.jump_R, "Jump right image should be loaded"),
                () -> assertNotNull(StaticValue.tower, "Tower image should be loaded"),
                () -> assertNotNull(StaticValue.gan, "Gan image should be loaded"),
                () -> assertFalse(StaticValue.run_L.isEmpty(), "Run left images should be loaded"),
                () -> assertFalse(StaticValue.run_R.isEmpty(), "Run right images should be loaded"),
                () -> assertFalse(StaticValue.obstacle.isEmpty(), "Obstacle images should be loaded"),
                () -> assertFalse(StaticValue.mogu.isEmpty(), "Mogu images should be loaded"),
                () -> assertFalse(StaticValue.flower.isEmpty(), "Flower images should be loaded")
        );
    }

    /**
     * The main method to execute the tests.
     * Creates an instance of StaticValueTest, executes the setup and test methods,
     * and outputs a message to the console upon completion.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        StaticValueTest staticValueTest = new StaticValueTest();
        staticValueTest.setUp();
        staticValueTest.testInit();

        System.out.println("\nStaticValue class init() methods tests completed.");
    }
}