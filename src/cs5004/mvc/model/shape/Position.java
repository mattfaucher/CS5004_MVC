package cs5004.mvc.model.shape;

import java.util.Objects;

/** Class to store the position of the shape that's on the screen. */
public class Position {
  // class attributes
  private double x;
  private double y;

  /**
   * Constructs a new Position object.
   *
   * @param x double.
   * @param y double..
   */
  public Position(double x, double y) {
    // Here also checks the canvas and if it is larger than canvas should also throw.
    this.x = x;
    this.y = y;
  }

  /**
   * Getter for x.
   *
   * @return double x.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Getter for y.
   *
   * @return double y.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Method to update the current position based on a given position object.
   *
   * @param position Position object.
   */
  public void setPosition(Position position) {
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    // Here we should have a condition that checks the canvas if it sets outside of the canvas
    // should throw will know this later on.
    this.x = position.getX();
    this.y = position.getY();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Position)) {
      return false;
    }
    Position p = (Position) o;
    return this.x == p.x && this.y == p.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  /**
   * Returns the Position as a (x, y) value string.
   *
   * @return String.
   */
  @Override
  public String toString() {
    return String.format("(%.2f, %.2f)", this.x, this.y);
  }
}
