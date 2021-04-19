package cs5004.mvc.model;

import java.util.List;
import java.util.Map;

import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.transformations.ITransformations;
import cs5004.mvc.model.transformations.Time;
import cs5004.mvc.model.shape.TypeOfShape;

/** Interface for the Model to be used by the User. */
public interface IModel {

  /**
   * Method to get the current HashMap of Shapes in the Model.
   *
   * @return Map containing the shapes and their identifiers.
   */
  Map<String, IShape> getShapes();

  /**
   * Method to get the current List of ITransformations in the Model.
   *
   * @return Map of Strings as keys and the values are List of transformations.
   */
  Map<String, List<ITransformations>> getTransformations();

  /**
   * Method to get the canvas of the IModel object.
   *
   * @return Canvas object.
   */
  Canvas getCanvas();

  /**
   * Method to simple create a shape and add it to the Underlying hashmap of shapes. The values of
   * the shape will all be zeroed out.
   *
   * @param name Unique identifier for the shape.
   * @param type Type of shape.
   */
  void declareShape(String name, String type);

  /**
   * Creates a new shape object to be rendered onto the screen.
   *
   * @param identifier String that is unique for this shape.
   * @param color color from java.awt.Color.
   * @param position Position with x, y coordinates.
   * @param size Size object.
   * @param type The type of Shape to be created.
   */
  void createShape(String identifier, Color color, Position position, Size size, TypeOfShape type);

  /**
   * Method to display a shape that has already been created.
   *
   * @param identifier String that identifies the unique shape to be shown.
   * @param time Time object containing start and end time for showing the shape.
   */
  void showShape(String identifier, Time time);

  /**
   * Method to move a specific shape from its current position to a new position over the course of
   * a Time object.
   *
   * @param identifier String to get Shape from HashMap.
   * @param position Position object to be moved to.
   * @param time Time object to be time of animation duration.
   */
  void moveShape(String identifier, Position position, Time time);

  /**
   * Method to scale a shape in size, either up or down, over the allotted time.
   *
   * @param identifier String to get Shape from HashMap.
   * @param size Size object to scale the shape to.
   * @param time Time object to perform animation.
   */
  void scaleShape(String identifier, Size size, Time time);

  /**
   * Method to change the color of the given shape.
   *
   * @param identifier String to get the Shape from the HashMap.
   * @param color Color from java.awt.Color to be changed to.
   * @param time Time object to be the duration of the animation.
   */
  void changeShapeColor(String identifier, Color color, Time time);

  /**
   * Method to remove a shape from the model along with all of its transformations.
   *
   * @param identifier String.
   */
  void removeShape(String identifier);

  /**
   * This is a method that gets the states of the movies at the given tick. It will return a list of
   * shapes with their current data to be rendered onto the screen.
   *
   * @param tick time values (second , minutes, hours)
   * @return List of IShapes to be rendered on the screen.
   */
  List<IShape> getStateAtTick(int tick);
}
