import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import cs5004.mvc.model.shape.Size;

/** The type Size test. */
public class SizeTest {
  private Size defaultSize1;
  private Size defaultSize2;

  /** Sets up the Size object so to test on . */
  @Before
  public void setUp() {
    defaultSize1 = new Size(30, 30);
    defaultSize2 = new Size(30, 30);
  }

  /** Tear down after. */
  @After
  public void tearDown() {
    defaultSize1 = null;
    defaultSize2 = null;
  }

  /** Test constructor. */
  @Test
  public void TestConstructor() {
    defaultSize1 = new Size(100, 100);
    defaultSize2 = new Size(200, 200);
    assertEquals("Width: 100.00, Height: 100.00", defaultSize1.toString());
    assertEquals("Width: 200.00, Height: 200.00", defaultSize2.toString());
  }

  /** Gets width test. */
  @Test
  public void getWidth() {
    assertEquals(30, defaultSize1.getWidth(), .0001);
    Size newSize = new Size(100, 100);
    defaultSize1.setSize(newSize);
    assertEquals(100, defaultSize1.getWidth(), 0.001);
  }

  /** Gets height. */
  @Test
  public void getHeight() {
    assertEquals(30, defaultSize1.getHeight(), 0.001);
    Size newSize = new Size(100, 100);
    defaultSize1.setSize(newSize);
    assertEquals(100, defaultSize1.getHeight(), 0.001);
  }

  /** Sets width test to get the new value and compare to the old one. */
  @Test
  public void setWidth() {
    defaultSize1.setWidth(100);
    assertEquals("Width: 100.00, Height: 30.00", defaultSize1.toString());
    defaultSize1.setWidth(12);
    assertEquals("Width: 12.00, Height: 30.00", defaultSize1.toString());
  }

  /** Sets height test to get the new High value. */
  @Test
  public void setHeight() {
    defaultSize1.setHeight(100);
    assertEquals("Width: 30.00, Height: 100.00", defaultSize1.toString());
    defaultSize1.setHeight(12);
    assertEquals("Width: 30.00, Height: 12.00", defaultSize1.toString());
  }

  /** Setting a new size and toString to compare it. */
  @Test
  public void setSize() {
    Size newSize = new Size(100, 100);
    defaultSize1.setSize(newSize);
    assertEquals(newSize.toString(), defaultSize1.toString());
    Size newSize2 = new Size(30, 50);
    defaultSize2.setSize(newSize2);
    assertEquals(newSize2.toString(), defaultSize2.toString());
  }

  /** Test equals method for same object. */
  @Test
  public void testEquals() {
    assertEquals(defaultSize1, defaultSize2);
    Size newSize = new Size(30, 30);
    assertEquals(newSize.toString(), defaultSize1.toString());
  }

  /** Test to string. */
  @Test
  public void testToString() {
    Size newSize = new Size(100, 100);
    assertEquals("Width: 30.00, Height: 30.00", defaultSize1.toString());
    assertEquals("Width: 100.00, Height: 100.00", newSize.toString());
  }

  /** This should catch the null and size that didn't change. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSetSize() {
    // the Size didn't change test.
    defaultSize1.setSize(defaultSize2);
    // The null check
    defaultSize2.setSize(null);
  }

  /** Illegal size when constructed. */
  @Test
  public void illegalSize() {
    // Test for invalid width
    defaultSize1 = new Size(-10, 5);
    assertEquals(0, defaultSize1.getWidth(), 0.001);
    assertEquals(0, defaultSize1.getHeight(), 0.001);
    // Test for Invalid height
    defaultSize1 = new Size(10, -10);
    assertEquals(0, defaultSize1.getWidth(), 0.001);
    assertEquals(0, defaultSize1.getHeight(), 0.001);
    // Test for both invalid
    defaultSize1 = new Size(-19, -19);
    assertEquals(0, defaultSize1.getWidth(), 0.001);
    assertEquals(0, defaultSize1.getHeight(), 0.001);
  }
}
