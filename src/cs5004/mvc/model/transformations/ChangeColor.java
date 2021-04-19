package cs5004.mvc.model.transformations;

import cs5004.mvc.model.shape.Color;
import java.util.Objects;

/** Class to represent a change in color transformation. */
public class ChangeColor extends AbstractTransformations {
  private Color color;

  /**
   * This iss a constructor for changeColor class which is type of transformation which will change
   * the color when it is called.
   *
   * @param identifier the identifier is the identifier which is passed in.
   * @param type the type of transformation.
   * @param color the color of the which will be change to.
   * @param time the spam of time that occur to transform.
   * @throws IllegalArgumentException the illegal argument exception
   */
  public ChangeColor(String identifier, TypeOfTransformation type, Color color, Time time)
      throws IllegalArgumentException {
    super(identifier, type, time);
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.color = color;
  }

  /**
   * Method to get the Color object from the current Transformation object.
   *
   * @return Color object.
   */
  public Color getColor() {
    return this.color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ChangeColor that = (ChangeColor) o;
    return Objects.equals(color, that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), color);
  }
}
