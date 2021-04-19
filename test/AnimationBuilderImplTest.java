import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import cs5004.mvc.model.Canvas;
import cs5004.mvc.model.IModel;
import cs5004.mvc.model.ModelImpl;
import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.ChangeColor;
import cs5004.mvc.model.transformations.Move;
import cs5004.mvc.model.transformations.Scale;
import cs5004.mvc.model.transformations.Time;
import cs5004.mvc.model.transformations.TypeOfTransformation;
import cs5004.mvc.util.AnimationBuilder;
import cs5004.mvc.util.AnimationBuilderImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Class to test the AnimationBuilderImpl. */
public class AnimationBuilderImplTest {
  // Class attributes
  private AnimationBuilder<IModel> builder;
  private IModel model;

  @Before
  public void setUp() {
    model = new ModelImpl();
    builder = new AnimationBuilderImpl(model);
  }

  @After
  public void tearDown() {
    model = null;
    builder = null;
    assertNull(model);
    assertNull(builder);
  }

  @Test
  public void build() {
    IModel testModel = new ModelImpl();
    testModel = new ModelImpl();
    testModel.declareShape("R", "rectangle");
    testModel.declareShape("O", "ellipse");
    assertEquals(
        "R RECTANGLE (0,0,0) at (0.00, 0.00) Width: 0.00, Height: 0.00\n"
            + "O OVAL (0,0,0) at (0.00, 0.00) Width: 0.00, Height: 0.00",
        testModel.toString());

    Color color = new Color(0, 255, 0);
    Position position = new Position(10, 10);
    Size size = new Size(100, 100);
    testModel.createShape("R", color, position, size, TypeOfShape.RECTANGLE);
    testModel.createShape("O", color, position, size, TypeOfShape.OVAL);

    Time time = new Time(0, 10);
    testModel.showShape("R", time);
    testModel.showShape("O", time);

    model.declareShape("R", "rectangle");
    model.declareShape("O", "ellipse");
    model.createShape("R", color, position, size, TypeOfShape.RECTANGLE);
    model.createShape("O", color, position, size, TypeOfShape.OVAL);

    model.showShape("R", time);
    model.showShape("O", time);

    // resulting model
    IModel m = builder.build();
    assertEquals(m.toString(), testModel.toString());
  }

  @Test
  public void setBounds() {
    builder.setBounds(50, 100, 1000, 1000);
    Canvas canvas = new Canvas(50, 100, 1000, 1000);
    assertTrue(model.getCanvas().equals(canvas));
  }

  @Test
  public void declareShape() {
    builder.declareShape("Rect", "Rectangle");
    builder.declareShape("Oval", "ellipse");
    Position position = new Position(0, 0);
    Size size = new Size(0, 0);
    Color color = new Color(0, 0, 0);
    assertEquals(TypeOfShape.RECTANGLE, model.getShapes().get("Rect").getType());
    assertEquals(color, model.getShapes().get("Rect").getColor());
    assertEquals(position, model.getShapes().get("Rect").getPosition());
    assertEquals(size, model.getShapes().get("Rect").getSize());
    assertEquals(
        "Rect RECTANGLE (0,0,0) at (0.00, 0.00) Width: 0.00, Height: 0.00\n"
            + "Oval OVAL (0,0,0) at (0.00, 0.00) Width: 0.00, Height: 0.00",
        model.toString());
  }

  @Test
  public void addMotionMove() {
    builder.declareShape("Rect", "Rectangle");
    builder.declareShape("Oval", "ellipse");
    Position position = new Position(30, 30);
    Size size = new Size(0, 0);
    Color color = new Color(0, 0, 0);
    Time time = new Time(0, 10);
    // Move
    builder.addMotion("Rect", 0, 20, 20, 10, 10, 0, 0, 225, 10, 30, 30, 10, 10, 0, 0, 225);
    Move move = new Move("Rect", TypeOfTransformation.MOVE, position, time);
    assertTrue(model.getTransformations().get("Rect").get(0).equals(move));
  }

  @Test
  public void addMotionScale() {
    builder.declareShape("Rect", "Rectangle");
    builder.declareShape("Oval", "ellipse");
    Size size = new Size(10, 50);
    Color color = new Color(0, 0, 0);
    Time time = new Time(0, 10);
    // Scale
    builder.addMotion("Rect", 0, 20, 20, 10, 10, 0, 0, 225, 10, 20, 20, 10, 50, 0, 0, 225);
    Scale scale = new Scale("Rect", TypeOfTransformation.SCALE, size, time);
    assertTrue(model.getTransformations().get("Rect").get(0).equals(scale));
  }

  @Test
  public void addMotionColor() {
    builder.declareShape("Rect", "Rectangle");
    builder.declareShape("Oval", "ellipse");
    Color color = new Color(0, 255, 0);
    Time time = new Time(0, 10);
    // change color
    builder.addMotion("Oval", 0, 20, 20, 10, 10, 0, 0, 225, 10, 20, 20, 10, 10, 0, 255, 0);
    ChangeColor changeColor =
        new ChangeColor("Oval", TypeOfTransformation.CHANGECOLOR, color, time);
    assertTrue(model.getTransformations().get("Oval").get(0).equals(changeColor));
  }
}
