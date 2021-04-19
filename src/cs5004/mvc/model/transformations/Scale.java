package cs5004.mvc.model.transformations;

import cs5004.mvc.model.shape.Size;
import java.util.Objects;

/** Class to represent the Scale transformation and stores necessary data. */
public class Scale extends AbstractTransformations {
  // class attributes
  private Size size;

  /**
   * Instantiates a new Scale ITransformations object.
   *
   * @param identifier String that matches unique shape.
   * @param type TypeOfTransformation value.
   * @param size Size object.
   * @param time Time object.
   */
  public Scale(String identifier, TypeOfTransformation type, Size size, Time time)
      throws IllegalArgumentException {
    super(identifier, type, time);
    if (size == null) {
      throw new IllegalArgumentException("Size cannot be null");
    }
    this.size = size;
  }

  /**
   * Method to get the Size object from the current transformation object.
   *
   * @return Size object.
   */
  public Size getSize() {
    return this.size;
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
    Scale scale = (Scale) o;
    return Objects.equals(size, scale.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), size);
  }
}
