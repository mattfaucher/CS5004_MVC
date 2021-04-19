package cs5004.mvc.model.shape;

import java.util.Objects;

/** Class to extend Java's awt.Color class. Sets the color of the object using r, g, b values. */
public class Color {
  private int red;
  private int green;
  private int blue;

  /**
   * Creates a new Color object.
   *
   * @param r int value for red.
   * @param g int value for green.
   * @param b int value for blue.
   */
  public Color(int r, int g, int b) {
    if (r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0) {
      throw new IllegalArgumentException("Color parameter out of range");
    }
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  /**
   * Method to set the color object to a new color.
   *
   * @param color color object with differing values.
   * @throws IllegalArgumentException if the color is null or same as current.
   */

  // This is modified and added because our animationBuilderImpl needed to set the color to make
  // and add motion to the animation therefore we added this method.
  public void setColor(Color color) throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }

    this.red = color.red;
    this.green = color.green;
    this.blue = color.blue;
  }

  /**
   * Method to get the value for red.
   *
   * @return int for red.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Method to get the value for green..
   *
   * @return int for green..
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Method to get the value for blue.
   *
   * @return int for blue.
   */
  public int getBlue() {
    return this.blue;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Color)) {
      return false;
    }
    Color c = (Color) o;
    return this.red == c.red && this.green == c.green && this.blue == c.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

  @Override
  public String toString() {
    return String.format("(%d,%d,%d)", this.red, this.green, this.blue);
  }
}
