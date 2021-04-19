package cs5004.mvc.model.shape;

/** Class that represents an Oval shape. Implements the IShape protocol. */
public class Oval extends AbstractShape implements IShape {
  private TypeOfShape type;

  /**
   * Constructs a new Oval object along with assigning it its given type.
   *
   * @param color color for the oval.
   * @param position position of the oval is the center of the shape.
   * @param size size of the oval.
   */
  public Oval(Color color, Position position, Size size) {
    super(color, position, size);
    this.type = TypeOfShape.OVAL;
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
