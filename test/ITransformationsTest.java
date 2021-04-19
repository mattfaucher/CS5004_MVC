import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.transformations.AbstractTransformations;
import cs5004.mvc.model.transformations.ChangeColor;
import cs5004.mvc.model.transformations.ITransformations;
import cs5004.mvc.model.transformations.Move;
import cs5004.mvc.model.transformations.Scale;
import cs5004.mvc.model.transformations.Time;
import cs5004.mvc.model.transformations.TypeOfTransformation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Class to test the ITransformations protocol. */
public class ITransformationsTest {
  // variables to be tested
  private ITransformations abs;
  private ITransformations move;
  private ITransformations scale;
  private ITransformations changeColor;
  // Time object for testing
  Time time = new Time(5, 10);
  // Position object for testing
  Position position = new Position(100, 100);
  // Size object for testing
  Size size = new Size(50, 50);
  // Color object for testing (Blue)
  Color color = new Color(0, 0, 255);

  @Before
  public void setUp() {
    abs = new AbstractTransformations("abs", TypeOfTransformation.SHOW, time);
    move = new Move("move", TypeOfTransformation.MOVE, position, time);
    scale = new Scale("scale", TypeOfTransformation.SCALE, size, time);
    changeColor = new ChangeColor("changeColor", TypeOfTransformation.CHANGECOLOR, color, time);
  }

  @After
  public void tearDown() {
    abs = null;
    move = null;
    scale = null;
    changeColor = null;
  }

  // test the constructors for each of the types of moves
  @Test
  public void testAbstractConstructorValid() {
    ITransformations t = new AbstractTransformations("t", TypeOfTransformation.SHOW, time);
    assertEquals("t", t.getIdentifier());
    assertEquals(TypeOfTransformation.SHOW, t.getType());
    assertEquals("Starts at: t=5\nEnds at: t=10", t.getTime().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAbstractConstructorIllegal() {
    String s = null;
    ITransformations t = new AbstractTransformations(s, TypeOfTransformation.SHOW, time);
  }

  @Test
  public void testMoveConstructor() {
    ITransformations t = new Move("t", TypeOfTransformation.MOVE, position, time);
    assertEquals("t", t.getIdentifier());
    assertEquals(TypeOfTransformation.MOVE, t.getType());
    assertEquals("(100.00, 100.00)", position.toString());
    assertEquals("Starts at: t=5\nEnds at: t=10", t.getTime().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveConstructorIllegal() {
    // pass in a null object
    Position p = null;
    ITransformations t = new Move("t", TypeOfTransformation.MOVE, p, time);
  }

  @Test
  public void testScaleConstructor() {
    ITransformations t = new Scale("t", TypeOfTransformation.SCALE, size, time);
    assertEquals("t", t.getIdentifier());
    assertEquals(TypeOfTransformation.SCALE, t.getType());
    assertEquals("Width: 50.00, Height: 50.00", size.toString());
    assertEquals("Starts at: t=5\nEnds at: t=10", t.getTime().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScaleConstructorIllegal() {
    // pass in a null object
    Size s = null;
    ITransformations t = new Scale("t", TypeOfTransformation.SCALE, s, time);
  }

  @Test
  public void testChangeColorConstructor() {
    ITransformations t = new ChangeColor("t", TypeOfTransformation.CHANGECOLOR, color, time);
    assertEquals("t", t.getIdentifier());
    assertEquals(TypeOfTransformation.CHANGECOLOR, t.getType());
    assertEquals("(0,0,255)", color.toString());
    assertEquals("Starts at: t=5\nEnds at: t=10", t.getTime().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorConstructorIllegal() {
    // pass in a null object
    Color c = null;
    ITransformations t = new ChangeColor("t", TypeOfTransformation.CHANGECOLOR, c, time);
  }

  @Test
  public void getType() {
    assertEquals(TypeOfTransformation.SHOW, abs.getType());
    assertEquals(TypeOfTransformation.MOVE, move.getType());
    assertEquals(TypeOfTransformation.SCALE, scale.getType());
    assertEquals(TypeOfTransformation.CHANGECOLOR, changeColor.getType());
  }

  @Test
  public void getTime() {
    assertEquals("Starts at: t=5\nEnds at: t=10", abs.getTime().toString());
    assertEquals("Starts at: t=5\nEnds at: t=10", move.getTime().toString());
    assertEquals("Starts at: t=5\nEnds at: t=10", scale.getTime().toString());
    assertEquals("Starts at: t=5\nEnds at: t=10", changeColor.getTime().toString());
  }

  @Test
  public void getIdentifier() {
    assertEquals("abs", abs.getIdentifier());
    assertEquals("move", move.getIdentifier());
    assertEquals("scale", scale.getIdentifier());
    assertEquals("changeColor", changeColor.getIdentifier());
  }
}
