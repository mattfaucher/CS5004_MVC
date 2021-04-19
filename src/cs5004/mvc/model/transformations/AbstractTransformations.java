package cs5004.mvc.model.transformations;

import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.Oval;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Rectangle;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import java.util.Objects;

/**
 * Implementing class for the ITransformations Interface, utilizes a List to store all given
 * transformations.
 */
public class AbstractTransformations implements ITransformations {
  // class attributes
  private TypeOfTransformation type;
  private String identifier;
  private Time time;

  /**
   * Constructs a new AbstractTransformation object. Takes in all common parameters for all types of
   * Transformations.
   *
   * @param identifier String to match a unique shape.
   * @param type TypeOfTransformation type.
   * @param time Time object of duration of transformation.
   */
  public AbstractTransformations(String identifier, TypeOfTransformation type, Time time) {
    if (identifier == null) {
      throw new IllegalArgumentException("Identifier cannot be null");
    }
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }
    this.identifier = identifier;
    this.type = type;
    this.time = time;
  }

  @Override
  public TypeOfTransformation getType() {
    return this.type;
  }

  @Override
  public Time getTime() {
    return this.time;
  }

  @Override
  public String getIdentifier() {
    return this.identifier;
  }

  // method is overridden in applicable child
  @Override
  public Position getPosition() {
    return null;
  }

  // method is overridden in applicable child
  @Override
  public Size getSize() {
    return null;
  }

  // method is overridden in applicable child
  @Override
  public Color getColor() {
    return null;
  }

  @Override
  public IShape getShapeAtTick(IShape shape, int tick) {
    if (shape.getType().equals(TypeOfShape.RECTANGLE)) {
      return new Rectangle(shape.getColor(), shape.getPosition(), shape.getSize());
    }
    if (shape.getType().equals(TypeOfShape.OVAL)) {
      return new Oval(shape.getColor(), shape.getPosition(), shape.getSize());
    }

    throw new IllegalArgumentException("Invalid shape type");
  }

  @Override
  public String toString() {
    return String.format(
        "Name: %s\nType: %s\nTime: %s", this.identifier, this.type, this.time.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractTransformations that = (AbstractTransformations) o;
    return type == that.type
        && Objects.equals(identifier, that.identifier)
        && Objects.equals(time, that.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, identifier, time);
  }
}
