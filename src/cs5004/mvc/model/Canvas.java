package cs5004.mvc.model;

import java.util.Objects;

// MODIFICATION , we added this Canvas class to keep track of the canvas size and its height and
// weight to be added when working with animationBuilderImpl.

/** Class to control the sizing of the canvas. */
public class Canvas {
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructs a new Canvas object.
   *
   * @param x min x.
   * @param y min y.
   * @param width max x.
   * @param height max y.
   * @throws IllegalArgumentException if width/height < x/y.
   */
  public Canvas(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width < x || height < y) {
      throw new IllegalArgumentException("Max cannot be less than min for bounds");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Method to get the x value of canvas.
   *
   * @return x.
   */
  public int getX() {
    return x;
  }

  /**
   * Method to get the y value of canvas.
   *
   * @return y.
   */
  public int getY() {
    return y;
  }

  /**
   * Method to get the width value of canvas.
   *
   * @return width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Method to get the height value of canvas.
   *
   * @return height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Method to return the Canvas object.
   *
   * @return Canvas.
   */
  public Canvas getCanvas() {
    return this;
  }

  /**
   * Method to set the canvas to new values.
   *
   * @param x min x.
   * @param y min y.
   * @param width max x.
   * @param height max y.
   * @throws IllegalArgumentException if width/height < x/y.
   */
  public void setCanvas(int x, int y, int width, int height) {
    if (width < x || height < y) {
      throw new IllegalArgumentException("Max cannot be less than min for bounds");
    }

    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Canvas canvas = (Canvas) o;
    return x == canvas.x && y == canvas.y && width == canvas.width && height == canvas.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, width, height);
  }
}
