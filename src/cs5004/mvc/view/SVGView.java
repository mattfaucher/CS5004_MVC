package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.ITransformations;
import cs5004.mvc.model.transformations.TypeOfTransformation;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/** Class to create an SVGView object. */
public class SVGView extends AbstractView implements IView {

  /**
   * Constructs a new SVGView Object.
   *
   * @throws IllegalArgumentException if max inputs are less than min.
   */
  public SVGView(int tempo, IModel model) throws IllegalArgumentException {
    super(tempo, model);
  }

  @Override
  public String getModelString(IModel model) {
    model
        .getCanvas()
        .setCanvas(super.getLeft(), super.getTop(), super.getWidth(), super.getHeight());
    // create string builder to create an svg text file
    StringBuilder svg = new StringBuilder();
    // getting the canvas dimensions and adding to string
    // get each shape, for each shape get type, id, x, y, width, height, fill (color), visibility
    // add all animations within shape
    svg.append(
        "<svg width=\""
            + model.getCanvas().getWidth()
            + "\" height=\""
            + model.getCanvas().getHeight()
            + "\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    // loop through our shapes and add them, then add their transformations within the shape tag
    for (String key : model.getTransformations().keySet()) {
      IShape shape = model.getShapes().get(key);
      TypeOfShape type = shape.getType();
      if (type.equals(TypeOfShape.RECTANGLE)) {
        svg.append("<rect ")
            .append("id=\"")
            .append(key)
            .append("\" ")
            .append("x=\"")
            .append(shape.getPosition().getX())
            .append("\" ")
            .append("y=\"")
            .append(shape.getPosition().getY())
            .append("\" ")
            .append("width=\"")
            .append(shape.getSize().getWidth())
            .append("\" ")
            .append("height=\"")
            .append(shape.getSize().getHeight())
            .append("\" ")
            .append("fill=\"rgb")
            .append(shape.getColor().toString())
            .append("\" ")
            .append("visibility=\"visible\" >\n");
        // loop animations here
        for (ITransformations t : model.getTransformations().get(key)) {
          svg = findAttributeChanging(model, key, t, svg, TypeOfShape.RECTANGLE);
        }
        svg.append("</rect>\n");
      } else if (type.equals(TypeOfShape.OVAL)) {
        svg.append("<ellipse ")
            .append("id=\"")
            .append(key)
            .append("\" ")
            .append("cx=\"")
            .append(shape.getPosition().getX())
            .append("\" ")
            .append("cy=\"")
            .append(shape.getPosition().getY())
            .append("\" ")
            .append("rx=\"")
            .append(shape.getSize().getWidth())
            .append("\" ")
            .append("ry=\"")
            .append(shape.getSize().getHeight())
            .append("\" ")
            .append("fill=\"rgb")
            .append(shape.getColor().toString())
            .append("\" ")
            .append("visibility=\"visible\" >\n");
        // loop animations here
        for (ITransformations t : model.getTransformations().get(key)) {
          svg = findAttributeChanging(model, key, t, svg, TypeOfShape.OVAL);
        }
        svg.append("</ellipse>\n");
      }
    }

    svg.append("</svg>");
    return svg.toString();
  }

  /**
   * Method to add animations depending on changing attributes of the transformation.
   *
   * @param model IModel.
   * @param key String key.
   * @param t ITransformation object.
   * @param svg StringBuilder containing svg output.
   * @return StrinBuilder svg with added output.
   * @throws IllegalArgumentException if no case is entered.
   */
  private StringBuilder findAttributeChanging(
      IModel model, String key, ITransformations t, StringBuilder svg, TypeOfShape shape)
      throws IllegalArgumentException {
    String[] attributes = new String[4];
    TypeOfTransformation type = t.getType();
    if (shape.equals(TypeOfShape.RECTANGLE)) {
      attributes = new String[] {"x", "y", "width", "height"};
    } else if (shape.equals(TypeOfShape.OVAL)) {
      attributes = new String[] {"cx", "cy", "rx", "ry"};
    }
    switch (type) {
      case SHOW:
        return svg;
      case MOVE:
        // check the Position and what is changing there
        // check start value and compare to transformation end value
        Position position = model.getShapes().get(key).getPosition();
        Position endPosition = t.getPosition();
        // if both values are changed
        if (position.getX() != endPosition.getX() && position.getY() != endPosition.getY()) {
          // add animation tag
          // one for X
          svg.append(
              addAnimateTag(
                  model, key, attributes[0], (int) position.getX(), (int) endPosition.getX()));
          // one for Y
          svg.append(
              addAnimateTag(
                  model, key, attributes[1], (int) position.getY(), (int) endPosition.getY()));
          return svg;
        }
        // check if only X is changing
        if (position.getX() != endPosition.getX()) {
          svg.append(
              addAnimateTag(
                  model, key, attributes[0], (int) position.getX(), (int) endPosition.getX()));
          return svg;
        }
        // check if only Y is changing
        if (position.getY() != endPosition.getY()) {
          svg.append(
              addAnimateTag(
                  model, key, attributes[1], (int) position.getY(), (int) endPosition.getY()));
          return svg;
        }
        break;
      case SCALE:
        // check Size and what's changing
        Size size = model.getShapes().get(key).getSize();
        Size endSize = t.getSize();
        // if both values are changed
        if (size.getWidth() != endSize.getWidth() && size.getHeight() != endSize.getHeight()) {
          // add animation tag
          // one for width
          svg.append(addAnimateTag(model, key, attributes[2], size.getWidth(), endSize.getWidth()));
          // one for height
          svg.append(
              addAnimateTag(model, key, attributes[3], size.getHeight(), endSize.getHeight()));
          return svg;
        }
        // check if only width is changing
        if (size.getWidth() != endSize.getWidth()) {
          svg.append(addAnimateTag(model, key, attributes[2], size.getWidth(), endSize.getWidth()));
          return svg;
        }
        // check if only height is changing
        if (size.getHeight() != endSize.getHeight()) {
          svg.append(
              addAnimateTag(model, key, attributes[3], size.getHeight(), endSize.getHeight()));
          return svg;
        }
        break;
      case CHANGECOLOR:
        // check colors changing
        Color color = model.getShapes().get(key).getColor();
        Color endColor = t.getColor();
        // if the values aren't the same, set new fill
        if (!color.equals(endColor)) {
          String start = "rgb" + color.toString();
          String end = "rgb" + endColor.toString();
          svg.append(addAnimateTag(model, key, start, end));
          return svg;
        }
        break;
      default:
        return svg;
    }

    throw new IllegalArgumentException("Type not matching expected types");
  }

  /**
   * Method to add an animate tag to the svg output.
   *
   * @param model IModel.
   * @param key String key.
   * @param attribute the attribute being changed.
   * @param start start value.
   * @param end end value.
   * @return StringBuilder.toString() output.
   */
  private String addAnimateTag(
      IModel model, String key, String attribute, double start, double end) {
    StringBuilder builder = new StringBuilder();
    for (ITransformations t : model.getTransformations().get(key)) {
      builder
          .append("\t\t<animate attributeType=\"xml\" begin=\"")
          .append(
              model
                  .getTransformations()
                  .get(key)
                  .get(model.getTransformations().get(key).indexOf(t))
                  .getTime()
                  .getStartTime())
          .append("s\" dur=\"")
          .append(
              (model
                      .getTransformations()
                      .get(key)
                      .get(model.getTransformations().get(key).indexOf(t))
                      .getTime()
                      .getDifference())
                  / super.getTempo())
          .append("s\"")
          .append(" attributeName=\"")
          .append(attribute) // attr name that's changing
          .append("\" from=\"")
          .append(start) // START VAL
          .append("\" to=\"") // end val
          .append(end)
          .append("\" fill=\"freeze\" />\n");
    }
    return builder.toString();
  }

  /**
   * Method to add an animate tag to the svg output.
   *
   * @param model IModel.
   * @param key String key.
   * @param startColor start rgb value.
   * @param endColor end rgb value.
   * @return StringBuilder.toString() output.
   */
  private String addAnimateTag(IModel model, String key, String startColor, String endColor) {
    StringBuilder builder = new StringBuilder();
    for (ITransformations t : model.getTransformations().get(key)) {
      builder
          .append("\t\t<animate attributeType=\"xml\" begin=\"")
          .append(
              model
                  .getTransformations()
                  .get(key)
                  .get(model.getTransformations().get(key).indexOf(t))
                  .getTime()
                  .getStartTime())
          .append("s\" dur=\"")
          .append(
              (model
                      .getTransformations()
                      .get(key)
                      .get(model.getTransformations().get(key).indexOf(t))
                      .getTime()
                      .getDifference())
                  / getTempo())
          .append("s\"")
          .append(" attributeName=\"")
          .append("fill\"") // attr name that's changing
          .append(" from=\"")
          .append(startColor) // START VAL
          .append("\" to=\"") // end val
          .append(endColor)
          .append("\" fill=\"freeze\" />\n");
    }
    return builder.toString();
  }

  /**
   * Method that will allow one to save the output of the SVGView to a .svg file.
   *
   * @param svg the string for svg output.
   * @param filename String to be the filename.
   * @throws IllegalArgumentException if string name is null.
   * @throws IOException if there's a file error.
   */
  @Override
  public void saveFile(String svg, String filename) throws IllegalArgumentException, IOException {
    if (filename == null || filename.equals("")) {
      throw new IllegalArgumentException("Filename is invalid");
    }
    String path = System.getProperty("user.home") + "/Desktop/" + filename + ".svg";
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(path));
      writer.write(svg);
      writer.close();
    } catch (IOException e) {
      throw new IOException("Error writing to file");
    }
  }

  @Override
  public TypeOfView getViewType() {
    return TypeOfView.SVG;
  }

  @Override
  public void render(int speed) {}

  @Override
  public void setListeners(ActionListener click) {}
}
