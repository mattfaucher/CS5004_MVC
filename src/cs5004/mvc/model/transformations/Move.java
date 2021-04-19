package cs5004.mvc.model.transformations;

import cs5004.mvc.model.shape.Position;
import java.util.Objects;

/**
 * Class to represent a move transformation.
 */
public class Move extends AbstractTransformations {
  private Position position;

  /**
   * Constructs a new Move ITransformation object.
   *
   * @param identifier String identifier to match a unique shape.
   * @param type       TypeOfTransformation type.
   * @param position   Position object.
   * @param time       Time object for duration of transformation.
   */
  public Move(String identifier, TypeOfTransformation type, Position position, Time time)
          throws IllegalArgumentException {
    super(identifier, type, time);
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    this.position = position;
  }

  /**
   * Method to get the Position of the current transformation.
   *
   * @return Position object of transformation object.
   */
  public Position getPosition() {
    return this.position;
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
    Move move = (Move) o;
    return Objects.equals(position, move.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), position);
  }
}
