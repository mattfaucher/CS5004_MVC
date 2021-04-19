import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.Oval;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Rectangle;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/** The type Shape test that test all functionality of IShape. */
public class IShapeTest {
  private IShape defaultShape1;
  private Color defaultColor1;
  private Position defaultPosition1;
  private Size defaultSize1;
  private IShape defaultShape2;
  private Color defaultColor2;
  private Position defaultPosition2;
  private Size defaultSize2;

  /** Sets up all the necessary asset to construct shapes. */
  @Before
  public void setUp() {
    defaultColor1 = new Color(10, 10, 10);
    defaultPosition1 = new Position(0, 100);
    defaultSize1 = new Size(20, 20);
    defaultShape1 = new Rectangle(defaultColor1, defaultPosition1, defaultSize1);
    defaultColor2 = new Color(100, 100, 100);
    defaultPosition2 = new Position(50, 50);
    defaultSize2 = new Size(25, 25);
    defaultShape2 = new Oval(defaultColor2, defaultPosition2, defaultSize2);
  }

  /**
   * Tear down.
   *
   * @throws Exception the exception
   */
  @After
  public void tearDown() throws Exception {
    defaultShape1 = null;
    defaultShape2 = null;
  }

  /** This is the test for Rectangle Constructor that should give us a rectangle object. */
  @Test
  public void testRectangleConstructor() {
    defaultShape1 = new Rectangle(defaultColor1, defaultPosition1, defaultSize1);
    // If successful the type should be rectangle.
    assertEquals(TypeOfShape.RECTANGLE, defaultShape1.getType());
    assertNotEquals(defaultShape1.getType(), TypeOfShape.OVAL);
  }

  /** This is the test for Oval Constructor that should give us a oval type object. */
  @Test
  public void testOvalConstructor() {
    defaultShape2 = new Oval(defaultColor2, defaultPosition2, defaultSize2);
    // if successful the type should be oval.
    assertEquals(TypeOfShape.OVAL, defaultShape2.getType());
    assertNotEquals(defaultShape2.getType(), TypeOfShape.RECTANGLE);
  }

  /** This is the test for get Position method that gives the location position of the object. */
  @Test
  public void getPosition() {
    // Testing for Shape Rectangle that mutates its position and see the position match.
    assertEquals(defaultPosition1, defaultShape1.getPosition());
    Position newRectanglePosition = new Position(30, 30);
    defaultShape1 = new Rectangle(defaultColor1, newRectanglePosition, defaultSize1);
    assertEquals(newRectanglePosition, defaultShape1.getPosition());
    // Testing for Shape Oval that mutates its position and see the position match for Oval.
    assertEquals(defaultPosition2, defaultShape2.getPosition());
    Position newOvalPosition = new Position(60, 60);
    defaultShape2 = new Oval(defaultColor2, newOvalPosition, defaultSize2);
    assertEquals(newOvalPosition, defaultShape2.getPosition());
    // Both Tpe of Shape
  }

  /** This is the test for get size method that gives the size of the object. */
  @Test
  public void getSize() {
    // Testing for Shape Rectangle that mutates its Size and see the size matches.
    assertEquals(defaultSize1, defaultShape1.getSize());
    Size newRectangleSize = new Size(50, 50);
    defaultShape1 = new Rectangle(defaultColor1, defaultPosition1, newRectangleSize);
    assertEquals(newRectangleSize, defaultShape1.getSize());
    assertNotEquals(defaultSize1, defaultShape1.getSize());
    // Testing for OvalShape that mutates and see it matches.
    assertEquals(defaultSize2, defaultShape2.getSize());
    Size newOvalSize = new Size(15, 15);
    defaultShape2 = new Oval(defaultColor2, defaultPosition2, newOvalSize);
    assertEquals(newOvalSize, defaultShape2.getSize());
    assertNotEquals(defaultSize2, defaultShape2.getSize());
  }

  /** This is the test for get color method that gives the color of the object. */
  @Test
  public void getColor() {
    // Testing for Color Rectangle that mutates its Color and see the Color matches.
    assertEquals(defaultColor1, defaultShape1.getColor());
    Color newRectangleColor = new Color(33, 33, 33);
    defaultShape1 = new Rectangle(newRectangleColor, defaultPosition1, defaultSize1);
    assertEquals(newRectangleColor, defaultShape1.getColor());
    assertNotEquals(defaultColor1, defaultShape1.getColor());
    // Testing for OvalShape that mutates and see it matches.
    assertEquals(defaultColor2, defaultShape2.getColor());
    Color newOvalColor = new Color(15, 15, 15);
    defaultShape2 = new Oval(newOvalColor, defaultPosition2, defaultSize2);
    assertEquals(newOvalColor, defaultShape2.getColor());
    assertNotEquals(defaultColor2, defaultShape2.getColor());
  }

  /** This is the test for get type that returns the correct type Object. */
  @Test
  public void getType() {

    // Defaulted1 is Rec then we mutates.
    assertEquals(TypeOfShape.RECTANGLE, defaultShape1.getType());
    assertNotEquals(TypeOfShape.OVAL, defaultShape1.getType());
    defaultShape1 = new Oval(defaultColor1, defaultPosition1, defaultSize1);
    assertEquals(TypeOfShape.OVAL, defaultShape1.getType());
    assertNotEquals(TypeOfShape.RECTANGLE, defaultShape1.getType());
    // Default2 is oval then we mutates.
    assertEquals(TypeOfShape.OVAL, defaultShape2.getType());
    assertNotEquals(TypeOfShape.RECTANGLE, defaultShape2.getType());
    defaultShape2 = new Rectangle(defaultColor2, defaultPosition2, defaultSize2);
    assertEquals(TypeOfShape.RECTANGLE, defaultShape2.getType());
    assertNotEquals(TypeOfShape.OVAL, defaultShape2.getType());
  }

  @Test
  public void testToString() {
    // Testing toSting on the RecObject.
    assertEquals(
        "Type: RECTANGLE\n"
            + "Color: (10,10,10)\n"
            + "Position: (0.00, 100.00)\n"
            + "Size: Width: 20.00, Height: 20.00",
        defaultShape1.toString());
    // Mutating
    Color defaultSetColor = new Color(0, 0, 0);
    Position defaultPosition = new Position(20, 30);
    Size defaultSize = new Size(50, 50);
    defaultShape1 = new Oval(defaultSetColor, defaultPosition, defaultSize);
    assertEquals(
        "Type: OVAL\n"
            + "Color: (0,0,0)\n"
            + "Position: (20.00, 30.00)\n"
            + "Size: Width: 50.00, Height: 50.00",
        defaultShape1.toString());

    // Testing toString on the OvalObject.
    assertEquals(
        "Type: OVAL\n"
            + "Color: (100,100,100)\n"
            + "Position: (50.00, 50.00)\n"
            + "Size: Width: 25.00, Height: 25.00",
        defaultShape2.toString());
    // Mutating
    Color newDefaultSetColor = new Color(3, 3, 3);
    Position newDefaultPosition = new Position(23, 32);
    Size newDefaultSize = new Size(1, 11);
    defaultShape2 = new Oval(newDefaultSetColor, newDefaultPosition, newDefaultSize);
    assertEquals(
        "Type: OVAL\n"
            + "Color: (3,3,3)\n"
            + "Position: (23.00, 32.00)\n"
            + "Size: Width: 1.00, Height: 11.00",
        defaultShape2.toString());
  }

  @Test
  public void testIllegalPassedInConstructor() {
    try {
      Rectangle shape1 = new Rectangle(null, defaultPosition1, defaultSize1);
      fail("Color cannot be null");
    } catch (IllegalArgumentException e) {
      assertEquals("Color cannot be null", e.getMessage());
    }
    try {
      Rectangle shape1 = new Rectangle(defaultColor1, null, defaultSize1);
      fail("Position cannot be null");
    } catch (IllegalArgumentException e) {
      assertEquals("Position cannot be null", e.getMessage());
    }
    try {
      Rectangle shape1 = new Rectangle(defaultColor1, defaultPosition1, null);
      fail("Size cannot be null");
    } catch (IllegalArgumentException e) {
      assertEquals("Size cannot be null", e.getMessage());
    }
  }
}
