package cs5004.mvc.model.shape;

/** Class to represent a Rectangle object. Implements the IShape protocol. */
public class Rectangle extends AbstractShape implements IShape {
  private TypeOfShape type;

  /**
   * Constructs a new Rectangle object along with assigning it its respective type.
   *
   * @param color color for rectangle.
   * @param position position for the rectangle starts at top left corner.
   * @param size size of rectangle.
   */
  public Rectangle(Color color, Position position, Size size) {
    super(color, position, size);
    this.type = TypeOfShape.RECTANGLE;
  }

  @Override
  public TypeOfShape getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return "Type: "
        + this.type
        + "\n"
        + "Color: "
        + super.getColor().toString()
        + "\n"
        + "Position: "
        + super.getPosition().toString()
        + "\n"
        + "Size: "
        + super.getSize().toString();
  }
}
