package cs5004.mvc.model.transformations;

import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;

/** Interface to handle the different transformations that can occur for the shapes. */
public interface ITransformations {

  /**
   * Method to get the type of transformation the shape is performing.
   *
   * @return ITransformations object.
   */
  TypeOfTransformation getType();

  /**
   * Method to return the Time object of the given transformation.
   *
   * @return Time object.
   */
  Time getTime();

  /**
   * Method to get the unique identifier of the shape that the transformation refers to.
   *
   * @return String identifier.
   */
  String getIdentifier();

  /**
   * Method to get the Position of the transformation for Move.
   *
   * @return Positions object.
   */
  Position getPosition();

  /**
   * Method to get the Size of the transformation for Scale.
   *
   * @return Size object.
   */
  Size getSize();

  /**
   * Method to get the Color of the transformation for ChangeColor.
   *
   * @return Color object.
   */
  Color getColor();

  /**
   * Method to return the shape with given properties for that tick.
   *
   * @param shape IShape shape object..
   * @param tick int tick.
   * @return IShape shape.
   */
  IShape getShapeAtTick(IShape shape, int tick);
}
