package cs5004.mvc.model.shape;

/** Class to define the protocol for all shapes to be animated in the application. */
public interface IShape {
  /**
   * Method to return the position of a Shape.
   *
   * @return Position object of shape.
   */
  Position getPosition();

  /**
   * Method to return the size of a Shape.
   *
   * @return Size.
   */
  Size getSize();

  /**
   * Method to get the size of a Shape.
   *
   * @return Color.
   */
  Color getColor();

  /**
   * Method to get the type of the current shape.
   *
   * @return TypeOfShape value.
   */
  TypeOfShape getType();

  /**
   * Method to return true if the shape has been set to initial values or not.
   *
   * @return boolean true if set.
   */
  boolean isInitialized();

  /** Method that sets the state of the IShape to being initialized. */
  void setInitialized();
}
