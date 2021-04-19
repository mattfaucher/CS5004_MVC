import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cs5004.mvc.model.IModel;
import cs5004.mvc.model.ModelImpl;
import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.Time;
import cs5004.mvc.view.SVGView;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Class to test the SVGView class and its functionality. */
public class SVGViewTest {
  private SVGView defaultSVG;
  private IModel model;

  @Before
  public void setUp() {
    model = new ModelImpl();
    model.getCanvas().setCanvas(0, 0, 700, 500);
    defaultSVG = new SVGView(1, model);
  }

  @After
  public void tearDown() throws Exception {
    defaultSVG = null;
    model = null;
  }

  @Test
  public void getModelStringMove() {
    Position postion = new Position(200, 200);
    Size size = new Size(50, 100);
    Color color = new Color(128, 0, 128);
    model.declareShape("P", "rectangle");
    model.createShape("P", color, postion, size, TypeOfShape.RECTANGLE);
    Position newPos = new Position(500, 100);
    Size newSiz = new Size(60, 30);
    Color newColor = new Color(255, 128, 0);
    Time timeE = new Time(2, 7);
    model.declareShape("E", "ellipse");
    model.createShape("E", newColor, newPos, newSiz, TypeOfShape.OVAL);
    Position newPostion = new Position(300, 200);
    Position move = new Position(600, 400);
    Time time = new Time(1, 5);
    model.moveShape("P", newPostion, time);
    model.moveShape("E", move, timeE);
    assertEquals(
        "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\""
            + " fill=\"rgb(128,0,128)\" visibility=\"visible\" >\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"x\" "
            + "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"E\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(255,128,0)\" "
            + "visibility=\"visible\" >\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"2s\" dur=\"5s\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" to=\"600.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"2s\" dur=\"5s\" "
            + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>",
        defaultSVG.getModelString(model));
  }

  @Test
  public void getModelStringScale() {
    Position position = new Position(200, 200);
    Size size = new Size(50, 100);
    Color color = new Color(128, 0, 128);
    model.declareShape("P", "rectangle");
    model.createShape("P", color, position, size, TypeOfShape.RECTANGLE);
    Size newSiz = new Size(60, 30);
    Time time = new Time(1, 20);

    model.scaleShape("P", newSiz, time);

    assertEquals(
        "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(128,0,128)\" visibility=\"visible\" >\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"19s\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"60.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"19s\" "
            + "attributeName=\"height\" from=\"100.0\" to=\"30.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>",
        defaultSVG.getModelString(model));
  }

  @Test
  public void getModelStringColor() {
    Position position = new Position(200, 200);
    Size size = new Size(50, 100);
    Color color = new Color(128, 0, 128);
    Color newColor = new Color(0, 255, 0);
    model.declareShape("P", "rectangle");
    model.createShape("P", color, position, size, TypeOfShape.RECTANGLE);
    Time time = new Time(1, 5);

    model.changeShapeColor("P", newColor, time);

    assertEquals(
        "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"P\" x=\"200.0\" y=\"200.0\" width=\"50.0\" "
            + "height=\"100.0\" fill=\"rgb(128,0,128)\" visibility=\"visible\" >\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"fill\" "
            + "from=\"rgb(128,0,128)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>",
        defaultSVG.getModelString(model));
  }

  @Test
  public void getModelStringTestAllTrans() {
    Position position1 = new Position(200, 200);
    Position position2 = new Position(400, 400);
    Position position3 = new Position(0, 0);
    Position endPosition = new Position(50, 50);
    Size size1 = new Size(50, 100);
    Size size2 = new Size(200, 100);
    Size size3 = new Size(20, 20);
    Size endSize = new Size(1, 1);
    Color color1 = new Color(255, 0, 255);
    Color color2 = new Color(0, 255, 0);
    Color color3 = new Color(0, 0, 255);
    Color endColor = new Color(255, 255, 255);
    model.declareShape("Red", "rectangle");
    model.declareShape("Green", "ellipse");
    model.declareShape("Blue", "ellipse");
    model.createShape("Red", color1, position1, size1, TypeOfShape.RECTANGLE);
    model.createShape("Green", color2, position2, size2, TypeOfShape.OVAL);
    model.createShape("Blue", color3, position3, size3, TypeOfShape.OVAL);
    Time time = new Time(1, 5);

    model.moveShape("Red", endPosition, time);
    model.moveShape("Green", endPosition, time);
    model.moveShape("Blue", endPosition, time);

    model.scaleShape("Red", endSize, time);
    model.scaleShape("Green", endSize, time);
    model.scaleShape("Blue", endSize, time);

    model.changeShapeColor("Red", endColor, time);
    model.changeShapeColor("Green", endColor, time);
    model.changeShapeColor("Blue", endColor, time);

    assertEquals(
        "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"Red\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(255,0,255)\" visibility=\"visible\" >\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"x\" "
            + "from=\"200.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"x\" "
            + "from=\"200.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"x\" "
            + "from=\"200.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"y\" "
            + "from=\"200.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"y\" "
            + "from=\"200.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" attributeName=\"y\" "
            + "from=\"200.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"height\" from=\"100.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"height\" from=\"100.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"height\" from=\"100.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,255)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,255)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"fill\" from=\"rgb(255,0,255)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"Blue\" cx=\"0.0\" cy=\"0.0\" rx=\"20.0\" ry=\"20.0\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"cx\" from=\"0.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"cx\" from=\"0.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"cx\" from=\"0.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"cy\" from=\"0.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"cy\" from=\"0.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"cy\" from=\"0.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"rx\" from=\"20.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"rx\" from=\"20.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"rx\" from=\"20.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"ry\" from=\"20.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"ry\" from=\"20.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"ry\" from=\"20.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" "
            + "dur=\"4s\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"Green\" cx=\"400.0\" cy=\"400.0\" rx=\"200.0\" ry=\"100.0\" "
            + "fill=\"rgb(0,255,0)\" visibility=\"visible\" >\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"cx\" from=\"400.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"cx\" from=\"400.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"cx\" from=\"400.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"cy\" from=\"400.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"cy\" from=\"400.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"cy\" from=\"400.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"rx\" from=\"200.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"rx\" from=\"200.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"rx\" from=\"200.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"ry\" from=\"100.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"ry\" from=\"100.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"ry\" from=\"100.0\" to=\"1.0\" fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "\t\t<animate attributeType=\"xml\" begin=\"1s\" dur=\"4s\" "
            + "attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(255,255,255)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>",
        defaultSVG.getModelString(model));
  }

  @Test
  public void testWriteSVG() throws IOException {
    Position position1 = new Position(200, 200);
    Position position2 = new Position(400, 400);
    Position position3 = new Position(0, 0);
    Position endPosition = new Position(50, 50);
    Size size1 = new Size(50, 100);
    Size size2 = new Size(200, 100);
    Size size3 = new Size(20, 20);
    Size endSize = new Size(1, 1);
    Color color1 = new Color(255, 0, 255);
    Color color2 = new Color(0, 255, 0);
    Color color3 = new Color(0, 0, 255);
    Color endColor = new Color(255, 255, 255);
    model.declareShape("Red", "rectangle");
    model.declareShape("Green", "ellipse");
    model.declareShape("Blue", "ellipse");
    model.createShape("Red", color1, position1, size1, TypeOfShape.RECTANGLE);
    model.createShape("Green", color2, position2, size2, TypeOfShape.OVAL);
    model.createShape("Blue", color3, position3, size3, TypeOfShape.OVAL);
    Time time = new Time(1, 5);

    model.moveShape("Red", endPosition, time);
    model.moveShape("Green", endPosition, time);
    model.moveShape("Blue", endPosition, time);

    model.scaleShape("Red", endSize, time);
    model.scaleShape("Green", endSize, time);
    model.scaleShape("Blue", endSize, time);

    model.changeShapeColor("Red", endColor, time);
    model.changeShapeColor("Green", endColor, time);
    model.changeShapeColor("Blue", endColor, time);

    defaultSVG.saveSVG(defaultSVG.getModelString(model), "test");
    assertEquals("(0,255,0)", color2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWriteSVGError() throws IOException {
    Position position1 = new Position(200, 200);
    Position position2 = new Position(400, 400);
    Position position3 = new Position(0, 0);
    Position endPosition = new Position(50, 50);
    Size size1 = new Size(50, 100);
    Size size2 = new Size(200, 100);
    Size size3 = new Size(20, 20);
    Size endSize = new Size(1, 1);
    Color color1 = new Color(255, 0, 255);
    Color color2 = new Color(0, 255, 0);
    Color color3 = new Color(0, 0, 255);
    Color endColor = new Color(255, 255, 255);
    model.declareShape("Red", "rectangle");
    model.declareShape("Green", "ellipse");
    model.declareShape("Blue", "ellipse");
    model.createShape("Red", color1, position1, size1, TypeOfShape.RECTANGLE);
    model.createShape("Green", color2, position2, size2, TypeOfShape.OVAL);
    model.createShape("Blue", color3, position3, size3, TypeOfShape.OVAL);
    Time time = new Time(1, 5);

    model.moveShape("Red", endPosition, time);
    model.moveShape("Green", endPosition, time);
    model.moveShape("Blue", endPosition, time);

    model.scaleShape("Red", endSize, time);
    model.scaleShape("Green", endSize, time);
    model.scaleShape("Blue", endSize, time);

    model.changeShapeColor("Red", endColor, time);
    model.changeShapeColor("Green", endColor, time);
    model.changeShapeColor("Blue", endColor, time);

    defaultSVG.saveSVG(defaultSVG.getModelString(model), null);
  }

  @Test(expected = NoSuchElementException.class)
  public void testWriteSVGErrorAlreadyExists() throws IOException {
    Position position1 = new Position(200, 200);
    Position position2 = new Position(400, 400);
    Position position3 = new Position(0, 0);
    Position endPosition = new Position(50, 50);
    Size size1 = new Size(50, 100);
    Size size2 = new Size(200, 100);
    Size size3 = new Size(20, 20);
    Size endSize = new Size(1, 1);
    Color color1 = new Color(255, 0, 255);
    Color color2 = new Color(0, 255, 0);
    Color color3 = new Color(0, 0, 255);
    Color endColor = new Color(255, 255, 255);
    model.declareShape("Red", "rectangle");
    model.declareShape("Green", "oval");
    model.declareShape("Blue", "oval");
    model.createShape("Red", color1, position1, size1, TypeOfShape.RECTANGLE);
    model.createShape("Green", color2, position2, size2, TypeOfShape.OVAL);
    model.createShape("Blue", color3, position3, size3, TypeOfShape.OVAL);
    Time time = new Time(1, 5);

    model.moveShape("Red", endPosition, time);
    model.moveShape("Green", endPosition, time);
    model.moveShape("Blue", endPosition, time);

    model.scaleShape("Red", endSize, time);
    model.scaleShape("Green", endSize, time);
    model.scaleShape("Blue", endSize, time);

    model.changeShapeColor("Red", endColor, time);
    model.changeShapeColor("Green", endColor, time);
    model.changeShapeColor("Blue", endColor, time);

    defaultSVG.saveSVG(defaultSVG.getModelString(model), "test");
    assertNotNull(model);
  }
}
