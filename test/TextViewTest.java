import cs5004.mvc.model.IModel;
import cs5004.mvc.model.ModelImpl;
import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.Time;
import cs5004.mvc.view.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** Class to test the functionality of the TextView View. */
public class TextViewTest {
  private IModel model;
  private TextView tv;

  @Before
  public void setUp() {
    model = new ModelImpl();
    tv = new TextView(1, model);
  }

  @After
  public void tearDown() {
    model = null;
    tv = null;
  }

  @Test
  public void getModelString() {
    Color color = new Color(255, 0, 0);
    Position position = new Position(200, 200);
    Size size = new Size(50, 100);

    Color color2 = new Color(0, 0, 255);
    Position position2 = new Position(500, 100);
    Size size2 = new Size(60, 30);

    model.declareShape("R", "rectangle");
    model.declareShape("C", "ellipse");

    model.createShape("R", color, position, size, TypeOfShape.RECTANGLE);
    model.createShape("C", color2, position2, size2, TypeOfShape.OVAL);

    Time timer = new Time(1, 100);
    Time timec = new Time(6, 100);

    model.showShape("R", timer);
    model.showShape("C", timec);

    Position pos2 = new Position(300, 300);
    Time timer2 = new Time(10, 50);
    model.moveShape("R", pos2, timer2);

    Position pos3 = new Position(500, 400);
    Time timec2 = new Time(20, 70);

    model.moveShape("C", pos3, timec2);

    assertEquals(
        "Create (255,0,0) RECTANGLE R with corner at (200.00, 200.00), "
            + "Width: 50.00, Height: 100.00\n"
            + "Create (0,0,255) OVAL C with center at (500.00, 100.00), radius 60.0 and 30.0\n\n"
            + "R appears at time t=1 and disappears at time t=100\n"
            + "R moves from (200.00, 200.00) to (300.00, 300.00) from time t=10 to t=50\n"
            + "C appears at time t=6 and disappears at time t=100\n"
            + "C moves from (500.00, 100.00) to (500.00, 400.00) from time t=20 to t=70",
        tv.getModelString(model));
  }
}
