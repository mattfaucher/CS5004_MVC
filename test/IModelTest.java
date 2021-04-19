import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import cs5004.mvc.model.IModel;
import cs5004.mvc.model.ModelImpl;
import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.ITransformations;
import cs5004.mvc.model.transformations.Move;
import cs5004.mvc.model.transformations.Time;
import cs5004.mvc.model.transformations.TypeOfTransformation;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Class to test the IModel interface. */
public class IModelTest {
  // test attributes
  private IModel m;

  @Before
  public void setUp() {
    m = new ModelImpl();
  }

  @After
  public void tearDown() {
    m = null;
    assertNull(m);
  }

  @Test
  public void testConstructor() {
    IModel t = new ModelImpl();
    assertNotNull(t);
    // check for correct initialization of data structures
    assertEquals("{}", t.getShapes().toString());
    assertEquals("{}", t.getTransformations().toString());
  }

  @Test
  public void createShape() {
    // create the shape
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // check size was increased
    assertEquals(1, m.getShapes().size());
    Color newColor = new Color(0, 255, 0);
    Size newSize = new Size(200, 200);
    Position newPosition = new Position(20, 20);
    m.declareShape("S2", "ellipse");
    m.createShape("S2", newColor, newPosition, newSize, TypeOfShape.OVAL);
    assertEquals(2, m.getShapes().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShapeIllegalColor() {
    // create the shape
    Color color = null;
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShapeIllegalPosition() {
    // create the shape
    Color color = new Color(255, 0, 0);
    Position position = null;
    Size size = new Size(50, 50);
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShapeIllegalSize() {
    // create the shape
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = null;
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShapeIllegalIdentifier() {
    // create the shape
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // check size was increased
    assertEquals(1, m.getShapes().size());

    // create another shape with empty string
    m.declareShape("", "rectangle");
    m.createShape("", color, position, size, TypeOfShape.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShapeNullIdentifier() {
    // create the shape
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // check size was increased
    assertEquals(1, m.getShapes().size());

    // create another shape with empty string
    m.declareShape(null, "rectangle");
    m.createShape(null, color, position, size, TypeOfShape.RECTANGLE);
  }

  @Test (expected = IllegalArgumentException.class)
  public void createShapeIdentifierAlreadyInUse() {
    // create the shape
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // check size was increased
    assertEquals(1, m.getShapes().size());
    // create another shape with empty string
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
  }

  @Test
  public void showShape() {
    // create the shape
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // no transformations yet
    assertEquals(0, m.getTransformations().get("S").size());
    m.showShape("S", time);
    // size of transformations list increments by 1
    assertEquals(1, m.getTransformations().size());
    // make sure identifier is same for transformation and shape in HashMap
    assertEquals("S", m.getTransformations().get("S").get(0).getIdentifier());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShowShape() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // The empty String and the Null check for the identifier.
    m.showShape("", time);
    m.showShape(null, time);
    // Testing for the Illegal time to be passed in.
    m.showShape("S", null);
  }

  @Test
  public void testNoSuchElementShowShape() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    // Shows there there are no "A" identifier element.
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    m.showShape("A", time);
    assertEquals(m.getTransformations().size(), 1);
  }

  // Test for the method that checks for time overlap
  @Test(expected = IllegalArgumentException.class)
  public void testOverlapUnder() {
    Time show = new Time(10, 20);
    Time time = new Time(10, 20); // regular time
    Time tLow = new Time(5, 15); // under but overlapping
    Time tHigh = new Time(16, 25); // over but overlapping
    Time t = new Time(50, 70); // no overlap
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    // create a shape
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    Position testPosition = new Position(200, 200);
    m.changeShapeColor("S", color, time);
    // one move between 10-20
    m.moveShape("S", testPosition, time);
    m.moveShape("S", testPosition, tLow);
  }

  @Test
  public void testOverlapNoOverlap() {
    Time show = new Time(10, 20);
    Time time = new Time(10, 20);
    Time t = new Time(50, 70); // no overlap
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    // create a shape
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    Position testPosition = new Position(200, 200);
    m.showShape("S", show);
    // one move between 10-20
    m.moveShape("S", testPosition, time);
    m.moveShape("S", testPosition, t);
    ITransformations tr = new Move("S", TypeOfTransformation.MOVE, testPosition, t);
    assertEquals(tr.getTime(), m.getTransformations().get("S").get(2).getTime());
  }

  @Test
  public void moveShape() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // check size was increased
    assertEquals(1, m.getShapes().size());
    // create a time object
    Time time = new Time(0, 10);
    // perform a move
    Position newPos = new Position(25, 25);
    m.moveShape("S", newPos, time);
    // assert the move was saved into list
    assertEquals("S", m.getTransformations().get("S").get(0).getIdentifier());
    assertEquals(time.toString(), m.getTransformations().get("S").get(0).getTime().toString());
    assertEquals(TypeOfTransformation.MOVE, m.getTransformations().get("S").get(0).getType());
    // check position object is saved correctly in transformations
    assertEquals(newPos, m.getTransformations().get("S").get(0).getPosition());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // The empty String and the Null check for the identifier.
    m.moveShape("", position, time);
    m.moveShape(null, position, time);
    // Testing for the Illegal time and position to be passed in.
    m.moveShape("S", null, time);
    m.moveShape("S", position, null);
  }

  @Test(expected = NoSuchElementException.class)
  public void testNoSuchElementMoveShape() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    // Shows there there are no "A" identifier element.
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // Given a "A" string identifier when there are no "A" in the list.
    m.moveShape("A", position, time);
  }

  @Test
  public void scaleShape() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // check size was increased
    assertEquals(1, m.getShapes().size());
    // create a time object
    Time time = new Time(0, 10);
    // perform a move
    Size newSize = new Size(25, 25);
    m.scaleShape("S", newSize, time);
    // assert the scale was saved into list
    assertEquals("S", m.getTransformations().get("S").get(0).getIdentifier());
    assertEquals(time.toString(), m.getTransformations().get("S").get(0).getTime().toString());
    assertEquals(TypeOfTransformation.SCALE, m.getTransformations().get("S").get(0).getType());
    // check that size is same as passed in size
    assertEquals(newSize, m.getTransformations().get("S").get(0).getSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScale() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // The empty String and the Null check for the identifier.
    m.scaleShape("", size, time);
    m.scaleShape(null, size, time);
    // Testing for the Illegal time and position to be passed in.
    m.scaleShape("S", null, time);
    m.scaleShape("S", size, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScalingAtSameSize() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Size newSize = new Size(50, 50);
    Time time = new Time(0, 5);
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // Giving the another newSize value to scale to and time.
    m.scaleShape("S", newSize, time);
  }

  @Test(expected = NoSuchElementException.class)
  public void testNoSuchElementScale() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    // Shows there there are no "A" identifier element.
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // Given a "A" string identifier when there are no "A" in the list.
    m.scaleShape("A", size, time);
  }

  @Test
  public void changeShapeColor() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // check size was increased
    assertEquals(1, m.getShapes().size());
    // create a time object
    Time time = new Time(0, 10);
    // perform a color change
    Color newColor = new Color(122, 122, 0);
    m.changeShapeColor("S", newColor, time);
    // assert color change info saved correctly
    assertEquals("S", m.getTransformations().get("S").get(0).getIdentifier());
    assertEquals(time.toString(), m.getTransformations().get("S").get(0).getTime().toString());
    assertEquals(
        TypeOfTransformation.CHANGECOLOR, m.getTransformations().get("S").get(0).getType());
    // check that new color is saved correctly
    assertEquals(newColor, m.getTransformations().get("S").get(0).getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChange() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // The empty String and the Null check for the identifier.
    m.changeShapeColor("", color, time);
    m.changeShapeColor(null, color, time);
    // Testing for the Illegal time and position to be passed in.
    m.changeShapeColor("S", null, time);
    m.changeShapeColor("S", color, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeToSameColor() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Color newColor = new Color(255, 0, 0);
    Time time = new Time(0, 5);
    m.declareShape("S", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // Giving the another newSize value to scale to and time.
    m.changeShapeColor("S", newColor, time);
  }

  @Test(expected = NoSuchElementException.class)
  public void testNoSuchElementChangeColor() {
    // Creating the shape then testing it.
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    // Shows there there are no "A" identifier element.
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    // Given a "A" string identifier when there are no "A" in the list.
    m.changeShapeColor("A", color, time);
  }

  @Test
  public void testToString() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    Time time = new Time(0, 5);
    m.declareShape("S", "rectangle");
    m.declareShape("A", "rectangle");
    m.declareShape("B", "ellipse");
    m.declareShape("C", "rectangle");
    m.declareShape("X", "ellipse");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    m.createShape("A", color, position, size, TypeOfShape.RECTANGLE);
    m.createShape("B", color, position, size, TypeOfShape.OVAL);
    m.createShape("C", color, position, size, TypeOfShape.RECTANGLE);
    m.createShape("X", color, position, size, TypeOfShape.OVAL);

    Position p = new Position(200, 200);
    m.moveShape("S", p, time);
    m.moveShape("A", p, time);
    assertEquals(
        "A RECTANGLE (255,0,0) at (100.00, 100.00) Width: 50.00, Height: 50.00\n"
            + "B OVAL (255,0,0) at (100.00, 100.00) Width: 50.00, Height: 50.00\n"
            + "S RECTANGLE (255,0,0) at (100.00, 100.00) Width: 50.00, Height: 50.00\n"
            + "C RECTANGLE (255,0,0) at (100.00, 100.00) Width: 50.00, Height: 50.00\n"
            + "X OVAL (255,0,0) at (100.00, 100.00) Width: 50.00, Height: 50.00\n"
            + "A moves from (100.00, 100.00) to (200.00, 200.00) from time t=0 to t=5\n"
            + "S moves from (100.00, 100.00) to (200.00, 200.00) from time t=0 to t=5",
        m.toString());
  }

  @Test
  public void getStateAtTickShow() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);

    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.declareShape("X", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    m.createShape("X", color, position, size, TypeOfShape.RECTANGLE);
    assertEquals(2, m.getShapes().size());

    Time time = new Time(0, 10);
    m.showShape("S", time);
    m.showShape("X", time);
    List<IShape> list = m.getStateAtTick(2);
    assertEquals(
        "[Type: RECTANGLE\n"
            + "Color: (255,0,0)\n"
            + "Position: (100.00, 100.00)\n"
            + "Size: Width: 50.00, Height: 50.00, Type: RECTANGLE\n"
            + "Color: (255,0,0)\n"
            + "Position: (100.00, 100.00)\n"
            + "Size: Width: 50.00, Height: 50.00]",
        list.toString());
  }

  @Test
  public void getStateAtTickMove() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);

    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.declareShape("X", "rectangle");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    m.createShape("X", color, position, size, TypeOfShape.RECTANGLE);
    assertEquals(2, m.getShapes().size());
    Position newPosition = new Position(120, 120);

    Time time = new Time(0, 10);
    m.moveShape("S", newPosition, time);
    m.moveShape("X", newPosition, time);
    List<IShape> list = m.getStateAtTick(2);
    assertEquals(
        "[Type: RECTANGLE\n"
            + "Color: (255,0,0)\n"
            + "Position: (102.50, 102.50)\n"
            + "Size: Width: 50.00, Height: 50.00, Type: RECTANGLE\n"
            + "Color: (255,0,0)\n"
            + "Position: (102.50, 102.50)\n"
            + "Size: Width: 50.00, Height: 50.00]",
        list.toString());
  }

  @Test
  public void getStateAtTickScale() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);

    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.declareShape("X", "ellipse");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    m.createShape("X", color, position, size, TypeOfShape.OVAL);
    assertEquals(2, m.getShapes().size());

    Size newSize = new Size(100, 100);
    Time time = new Time(0, 10);

    m.scaleShape("S", newSize, time);
    m.scaleShape("X", newSize, time);
    List<IShape> list = m.getStateAtTick(2);
    assertEquals(
        "[Type: RECTANGLE\n"
            + "Color: (255,0,0)\n"
            + "Position: (100.00, 100.00)\n"
            + "Size: Width: 56.25, Height: 56.25, Type: OVAL\n"
            + "Color: (255,0,0)\n"
            + "Position: (100.00, 100.00)\n"
            + "Size: Width: 56.25, Height: 56.25]",
        list.toString());
  }

  @Test
  public void getStateAtTickChangeColor() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(100, 100);
    Size size = new Size(50, 50);
    assertEquals(0, m.getShapes().size());
    m.declareShape("S", "rectangle");
    m.declareShape("X", "ellipse");
    m.createShape("S", color, position, size, TypeOfShape.RECTANGLE);
    m.createShape("X", color, position, size, TypeOfShape.OVAL);
    assertEquals(2, m.getShapes().size());

    Color newColor = new Color(0, 255, 0);
    Time time = new Time(0, 10);

    m.changeShapeColor("S", newColor, time);
    m.changeShapeColor("X", newColor, time);
    List<IShape> list = m.getStateAtTick(2);
    assertEquals(
        "[Type: RECTANGLE\n"
            + "Color: (224,31,0)\n"
            + "Position: (100.00, 100.00)\n"
            + "Size: Width: 50.00, Height: 50.00, Type: OVAL\n"
            + "Color: (224,31,0)\n"
            + "Position: (100.00, 100.00)\n"
            + "Size: Width: 50.00, Height: 50.00]",
        list.toString());
  }
}
