package cs5004.mvc.model.shape;

import java.util.Objects;

/** Class to store the width and height for any given Shape. */
public class Size {
  // class attributes
  private double width;
  private double height;

  /**
   * Constructs a new size object given a width and height.
   *
   * @param width double for width.
   * @param height double for height.
   * @throws IllegalArgumentException if either param is negative.
   */
  public Size(double width, double height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      this.width = 0;
      this.height = 0;
    } else {
      this.width = width;
      this.height = height;
    }
  }

  /**
   * Method to get width.
   *
   * @return this width.
   */
  public double getWidth() {
    return width;
  }

  /**
   * Method to set the width.
   *
   * @param width double for width.
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Method to get the height.
   *
   * @return this height.
   */
  public double getHeight() {
    return height;
  }

  /**
   * Method to set the height.
   *
   * @param height double for height.
   */
  public void setHeight(double height) {
    this.height = height;
  }

  /**
   * Method to set the size of the shape.
   *
   * @param size Size object to be set to this size.
   */
  public void setSize(Size size) {
    if (size == null) {
      throw new IllegalArgumentException("Size cannot be null");
    }
    this.width = size.getWidth();
    this.height = size.getHeight();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Size)) {
      return false;
    }
    Size s = (Size) o;
    return this.width == s.width && this.height == s.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }

  @Override
  public String toString() {
    return String.format("Width: %.2f, Height: %.2f", this.width, this.height);
  }
}
