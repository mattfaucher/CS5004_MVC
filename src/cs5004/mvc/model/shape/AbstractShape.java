package cs5004.mvc.model.shape;

/** Class that allows instantiation of any shape in the IShape protocol. */
public abstract class AbstractShape implements IShape {
  // class attributes
  private Color color;
  private Position position;
  private Size size;
  private boolean isInitialized;

  /**
   * Abstract Constructor to create a new IShape object using dynamic dispatch.
   *
   * @param color java.awt.Color color.
   * @param position position object.
   * @param size Size object.
   */
  public AbstractShape(Color color, Position position, Size size) {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    if (size == null) {
      throw new IllegalArgumentException("Size cannot be null");
    }

    // if success
    this.color = color;
    this.position = position;
    this.size = size;
    this.isInitialized = false;
  }

  /**
   * Private method to do the underlying setting for the changeColor method.
   *
   * @param color Colors object to change the color.
   */
  private void setColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.color = color;
  }

  /**
   * Private method to set a different position for the shape.
   *
   * @param position Position object.
   */
  private void setPosition(Position position) {
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    this.position = position;
  }

  /**
   * Private method to set a different size for the shape.
   *
   * @param size Size object.
   */
  private void setSize(Size size) {
    if (size == null) {
      throw new IllegalArgumentException("Size cannot be null");
    }
    this.size = size;
  }

  @Override
  public boolean isInitialized() {
    return this.isInitialized;
  }

  @Override
  public void setInitialized() {
    if (!this.isInitialized) {
      this.isInitialized = true;
    }
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public Size getSize() {
    return this.size;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * This method will be overridden in the sub types.
   *
   * @return TypeOfShape for current shape object.
   */
  @Override
  public abstract TypeOfShape getType();
}
