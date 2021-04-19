import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs5004.mvc.model.shape.Color;

import static org.junit.Assert.assertEquals;

/** The Color Object test that test all functionality of Color class. */
public class ColorTest {
  private Color defaultColor1;

  /** setting up the Color and for testing. */
  @Before
  public void setUp() {
    // Normal Color with normal values
    defaultColor1 = new Color(10, 10, 10);
  }

  @After
  public void tearDown() {
    defaultColor1 = null;
  }

  @Test
  public void testConstructor() {
    defaultColor1 = new Color(20, 20, 20);
    assertEquals("(20,20,20)", defaultColor1.toString());
  }

  /** Testing the .Equal method to see if it is equal. */
  @Test
  public void testEquals() {
    Color newColor = new Color(10, 10, 10);
    assertEquals(newColor.toString(), defaultColor1.toString());
  }

  /** Testing the toString method here. */
  @Test
  public void testToString() {
    assertEquals("(10,10,10)", defaultColor1.toString());
  }



  /** This is the test for Illegal Color these color shouldn't be constructed. */
  @Test(expected = IllegalArgumentException.class)
  public void illegalColor() {
    // All negative color.
    defaultColor1 = new Color(-5, -5, -5);
    // All out of bound color
    defaultColor1 = new Color(290, 300, 300);
    // False Red and Green but okay Blue.
    defaultColor1 = new Color(-5, 300, 100);
    // False Red okay Green okay Blue
    defaultColor1 = new Color(300, 100, -5);
    // okay red false green and false blue;
    defaultColor1 = new Color(200, -5, 300);
    // Mix with good out of bound and negative.
    defaultColor1 = new Color(-5, 5, 265);
  }
}
