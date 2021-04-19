import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs5004.mvc.model.shape.Position;

import static org.junit.Assert.assertEquals;

/** The Position test that test all functionality of Position. */
public class PositionTest {
  private Position defaultPosition1;
  private Position defaultPosition2;

  /** Sets up all the necessary asset to construct position. */
  @Before
  public void setUp() {
    defaultPosition1 = new Position(0, 100);
    defaultPosition2 = new Position(0, 100);
  }

  /** Teardown after. */
  @After
  public void tearDown() {
    defaultPosition1 = null;
  }

  /** Testing the constructor. */
  @Test
  public void TestConstructor() {
    defaultPosition1 = new Position(0, 100);
    defaultPosition2 = new Position(0, 100);
    assertEquals("(0.00, 100.00)", defaultPosition1.toString());
    assertEquals("(0.00, 100.00)", defaultPosition2.toString());
  }

  /** Testing to accurately to get the X position. */
  @Test
  public void getX() {
    assertEquals(0, defaultPosition1.getX(), .00);
    defaultPosition1 = new Position(20, 20);
    assertEquals(20.00, defaultPosition1.getX(), .00);
  }

  /** Testing to accurately to get the Y position. */
  @Test
  public void getY() {
    assertEquals(100, defaultPosition1.getY(), .00);
    defaultPosition1 = new Position(20, 20);
    assertEquals(20, defaultPosition1.getY(), .00);
  }

  /** Testing the functional of setPosition. */
  @Test
  public void setPosition() {
    Position newPos = new Position(30, 35);
    defaultPosition1.setPosition(newPos);
    assertEquals(newPos.toString(), defaultPosition1.toString());
  }

  /** Testing the TestEqual Method for the Position. */
  @Test
  public void testEquals() {
    // Test
    Position newPos = new Position(0, 100);
    assertEquals(newPos.toString(), defaultPosition1.toString());
    // Mutates the X value and test it again.
    Position newPos1 = new Position(203, 203);
    defaultPosition1.setPosition(newPos1);
    assertEquals(newPos1.toString(), defaultPosition1.toString());
  }

  /** Testing the toString Method for the position Class. */
  @Test
  public void testToString() {
    Position newPos = new Position(0, 100);
    Position newPos1 = new Position(203, 203);
    assertEquals("(0.00, 100.00)", newPos.toString());
    assertEquals("(0.00, 100.00)", defaultPosition1.toString());
    assertEquals("(203.00, 203.00)", newPos1.toString());
  }

  /** Testing setting illegal position. */
  @Test(expected = IllegalArgumentException.class)
  public void IllegalSetPosition() {
    // Setting it to null should throw.
    defaultPosition1.setPosition(null);
    // Setting it to same Position will also throw.
    defaultPosition1.setPosition(defaultPosition2);
    // Setting out of Canvas in the nearest future.
  }
}
