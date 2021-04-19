package cs5004.mvc.model;

import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.Oval;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Rectangle;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.AbstractTransformations;
import cs5004.mvc.model.transformations.ChangeColor;
import cs5004.mvc.model.transformations.ITransformations;
import cs5004.mvc.model.transformations.Move;
import cs5004.mvc.model.transformations.Scale;
import cs5004.mvc.model.transformations.Time;
import cs5004.mvc.model.transformations.TypeOfTransformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/** Class that implements the methods from the IModel protocol methods. */
public class ModelImpl implements IModel {
  // class attributes
  private Map<String, IShape> shapes;
  private Map<String, List<ITransformations>> transformations;
  private Canvas canvas;

  /**
   * Constructs a new ModelImpl object that instantiates a HashMap to keep track of all shapes. As
   * well as instantiates a Map of Strings and List of ITransformations that will store the list of
   * transformations that is going to take place.
   */
  public ModelImpl() {
    shapes = new HashMap<>();
    transformations = new HashMap<>();
    canvas = new Canvas(0, 0, 0, 0);
  }

  @Override
  public Map<String, IShape> getShapes() {
    return this.shapes;
  }

  @Override
  public Map<String, List<ITransformations>> getTransformations() {
    return this.transformations;
  }

  @Override
  public Canvas getCanvas() {
    return this.canvas;
  }

  /**
   * Private method to verify that the newly proposed time is legal for a given shape based on its
   * identifier.
   *
   * @param identifier String to find identifier in list of transformations.
   * @param time Time object to check validity against.
   * @param type TypeOfTransformation to check type are same or not.
   * @return true if there is no overlap, false if there is overlap (illegal).
   */

  // MODIFICATION: THIS is also our modification to our code ModelImpl which we check the conditions
  // for overlapping and the check if at most one type of animation is happening and could not have
  // two change shape or tow scale or moving happening at the same time.
  private boolean checkTransformationOverlap(
      String identifier, Time time, TypeOfTransformation type) {
    // loop through all transformations and find all of the same identifiers and check their
    // validity
    if (this.transformations.containsKey(identifier)) {
      // loop through list
      // check type, if type is same then check time
      for (ITransformations t : getTransformations().get(identifier)) {
        if (t.getType().equals(type)) {
          // check time overlap
          if (t.getTime().getStartTime() < time.getStartTime()
                  && t.getTime().getEndTime() > time.getStartTime()
                  && t.getTime().getEndTime() < time.getEndTime()
              || t.getTime().getEndTime() > time.getEndTime()
                  && t.getTime().getStartTime() > time.getStartTime()
                  && t.getTime().getStartTime() < time.getEndTime()) {
            return false;
          }
        }
      }
    }

    return true;
  }

  // Modification: This is a new method that is added to our modelImpl to better help with the
  // declaring shape in the AnimationImpl class
  @Override
  public void declareShape(String name, String type) {
    Color color = new Color(0, 0, 0);
    Position position = new Position(0, 0);
    Size size = new Size(0, 0);

    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape already exists");
    }

    if (type.equalsIgnoreCase("rectangle")) {
      shapes.put(name, new Rectangle(color, position, size));
      transformations.put(name, new ArrayList<ITransformations>());
    }
    if (type.equalsIgnoreCase("ellipse")) {
      shapes.put(name, new Oval(color, position, size));
      transformations.put(name, new ArrayList<ITransformations>());
    }
  }

  @Override
  public void createShape(
      String identifier, Color color, Position position, Size size, TypeOfShape type)
      throws IllegalArgumentException {
    if (identifier == null || identifier.equals("")) {
      throw new IllegalArgumentException("Identifier cannot be null or an empty string");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    if (size == null) {
      throw new IllegalArgumentException("Size cannot be null");
    }
    // if the key is already being used, then set the values.
    if (shapes.containsKey(identifier)) {
      shapes.get(identifier).getColor().setColor(color);
      shapes.get(identifier).getPosition().setPosition(position);
      shapes.get(identifier).getSize().setSize(size);
    }
    // successful ==> add shape to HashMap
    // need a way to determine which shape to create
    if (transformations.containsKey(identifier)) {
      return;
    } else {
      List<ITransformations> list = new ArrayList<>();
      transformations.put(identifier, list);
    }
  }

  @Override
  public void removeShape(String identifier) {
    if (identifier == null) {
      throw new IllegalArgumentException("Identifier cannot be located because it's null");
    }
    if (shapes.containsKey(identifier)) {
      shapes.remove(identifier);
      transformations.remove(identifier);
    }
  }

  @Override
  public void showShape(String identifier, Time time)
      throws IllegalArgumentException, NoSuchElementException {
    if (identifier == null || identifier.equals("")) {
      throw new IllegalArgumentException("Identifier cannot be null or empty");
    }
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }

    if (shapes.containsKey(identifier)) {
      transformations
          .get(identifier)
          .add(new AbstractTransformations(identifier, TypeOfTransformation.SHOW, time));
    } else {
      return;
    }
  }

  @Override
  public void moveShape(String identifier, Position position, Time time)
      throws IllegalArgumentException, NoSuchElementException {
    if (identifier == null || identifier.equals("")) {
      throw new IllegalArgumentException("Identifier cannot be null or empty");
    }
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }
    // if successful
    if (shapes.containsKey(identifier)) {
      if (checkTransformationOverlap(identifier, time, TypeOfTransformation.MOVE)) {
        transformations
            .get(identifier)
            .add(new Move(identifier, TypeOfTransformation.MOVE, position, time));
      } else {
        return;
      }
    } else {
      throw new NoSuchElementException("Identifier doesn't exist in shapes");
    }
  }

  @Override
  public void scaleShape(String identifier, Size size, Time time)
      throws IllegalArgumentException, NoSuchElementException {
    if (identifier == null || identifier.equals("")) {
      throw new IllegalArgumentException("Identifier cannot be null or empty");
    }
    if (size == null) {
      throw new IllegalArgumentException("Size cannot be null");
    }
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }

    // if successful
    if (shapes.containsKey(identifier)) {
      if (shapes.get(identifier).getSize().equals(size)) {
        throw new IllegalArgumentException("The size did not change, it requires changes on size");
      }
      if (checkTransformationOverlap(identifier, time, TypeOfTransformation.SCALE)) {
        transformations
            .get(identifier)
            .add(new Scale(identifier, TypeOfTransformation.SCALE, size, time));
      } else {
        throw new IllegalArgumentException(
            "Time transformations overlaps with preexisting transformations");
      }
    } else {
      throw new NoSuchElementException("Identifier doesn't exist in shapes");
    }
  }

  /**
   * Method to allow user to change the color of a shape.
   *
   * @param identifier String to get the Shape from the HashMap.
   * @param color Color from java.awt.Color to be changed to.
   * @param time Time object to be the duration of the animation.
   * @throws IllegalArgumentException if the identifier doesn't exist or any params are null.
   */
  @Override
  public void changeShapeColor(String identifier, Color color, Time time)
      throws IllegalArgumentException, NoSuchElementException {
    if (identifier == null || identifier.equals("")) {
      throw new IllegalArgumentException("Identifier cannot be null or empty");
    }
    if (color == null) {
      throw new IllegalArgumentException("Colors object cannot be null");
    }
    if (time == null) {
      throw new IllegalArgumentException("Time object cannot be null");
    }
    if (shapes.containsKey(identifier)) {
      if (shapes.get(identifier).getColor().equals(color)) {
        throw new IllegalArgumentException(
            "The color did not change, it requires changes in color");
      }
      if (checkTransformationOverlap(identifier, time, TypeOfTransformation.CHANGECOLOR)) {
        transformations
            .get(identifier)
            .add(new ChangeColor(identifier, TypeOfTransformation.CHANGECOLOR, color, time));
      } else {
        throw new IllegalArgumentException(
            "Time transformation overlaps with preexisting transformations");
      }
    } else {
      throw new NoSuchElementException("Identifier doesn't exist");
    }
  }

  @Override
  public List<IShape> getStateAtTick(int tick) {
    // get a list of shapes that are supposed to be on the screen at given TICK
    // loop through each shape's list and find
    List<IShape> shapesAtTick = new ArrayList<>();
    // loop through hashmap
    for (String key : getTransformations().keySet()) {
      List<ITransformations> shapeTransformations = new ArrayList<>();
      shapeTransformations.addAll(this.getTransformations().get(key));
      // go through and then append shapes at tick to our other list
      for (ITransformations t : shapeTransformations) {
        if (t.getTime().isBetween(tick)) {
          // get initial values from shapes, then get final values from transformation
          // then calculate tween, create new shape, add to list
          // get all starting properties.
          TypeOfTransformation transformationType = t.getType();
          switch (transformationType) {
            case SHOW:
              // add shape with new properties
              IShape shape = t.getShapeAtTick(getShapes().get(key), tick);
              shapesAtTick.add(shape);
              break;
            case MOVE:
              TypeOfShape typeMove = getShapes().get(t.getIdentifier()).getType();
              Color colorMove = getShapes().get(key).getColor();
              Size sizeMove = getShapes().get(key).getSize();
              // use tween to calculate new position
              // position from transformation
              Position endPosition =
                  getTransformations().get(key).get(shapeTransformations.indexOf(t)).getPosition();
              Position startPosition = getShapes().get(key).getPosition();
              // get time object
              Time time =
                  getTransformations().get(key).get(shapeTransformations.indexOf(t)).getTime();
              Position newPosition =
                  new Position(
                      tweenCalculator(startPosition.getX(), endPosition.getX(), time, tick),
                      tweenCalculator(startPosition.getY(), endPosition.getY(), time, tick));
              // add shape with new properties
              // create new shape
              createShape(key, colorMove, newPosition, sizeMove, typeMove);
              // set shapes properties
              IShape shapeMove = t.getShapeAtTick(getShapes().get(key), tick);
              // add shape to list
              shapesAtTick.add(shapeMove);
              break;
            case SCALE:
              TypeOfShape typeScale = getShapes().get(t.getIdentifier()).getType();
              Color colorScale = getShapes().get(key).getColor();
              Position positionScale = getShapes().get(key).getPosition();
              // use tween to calculate new Size
              // Size from transformation
              Size endSize =
                  getTransformations().get(key).get(shapeTransformations.indexOf(t)).getSize();
              Size startSize = getShapes().get(key).getSize();
              // get time
              time = getTransformations().get(key).get(shapeTransformations.indexOf(t)).getTime();
              Size newSize =
                  new Size(
                      tweenCalculator(startSize.getWidth(), endSize.getWidth(), time, tick),
                      tweenCalculator(startSize.getHeight(), endSize.getHeight(), time, tick));
              // add shape with new properties
              createShape(key, colorScale, positionScale, newSize, typeScale);
              IShape shapeScale = t.getShapeAtTick(getShapes().get(key), tick);
              shapesAtTick.add(shapeScale);
              break;
            case CHANGECOLOR:
              TypeOfShape typeColor = getShapes().get(t.getIdentifier()).getType();
              Position positionColor = getShapes().get(key).getPosition();
              Size sizeColor = getShapes().get(key).getSize();
              // use tween to calculate new Size
              // Get color from transformations
              Color endColor =
                  getTransformations().get(key).get(shapeTransformations.indexOf(t)).getColor();
              Color startColor = getShapes().get(key).getColor();
              time = getTransformations().get(key).get(shapeTransformations.indexOf(t)).getTime();
              Color newColor =
                  new Color(
                      tweenCalculatorRGB(startColor.getRed(), endColor.getRed(), time, tick),
                      tweenCalculatorRGB(startColor.getGreen(), endColor.getGreen(), time, tick),
                      tweenCalculatorRGB(startColor.getBlue(), endColor.getBlue(), time, tick));
              // add shape with new properties
              createShape(key, newColor, positionColor, sizeColor, typeColor);
              IShape shapeColor = t.getShapeAtTick(getShapes().get(key), tick);
              shapesAtTick.add(shapeColor);
              break;
            default:
              break;
          }
        }
      }
    }
    // set the properties of the shapes based on the TWEEN values
    // TWEEENIng -> the value of the property at given tick, assuming it's changing
    // set the property that's changing to the tween value.
    // pass to function that renders the shapes
    return shapesAtTick;
  }

  /**
   * Method to calculate the tween values of colors.
   *
   * @param start start value
   * @param end end value
   * @param time time difference
   * @return int with current value
   */
  private int tweenCalculatorRGB(int start, int end, Time time, int tick) {
    // handles negatives
    if (start <= 0 && end <= 0) {
      return 0;
    }
    // handles upper bound
    if (start > 255 && end > 255) {
      return 255;
    }
    if (start > 255 && (end < 255 && end >= 0)) {
      start = 255;
    }
    if (end > 255 && (start < 255 && start >= 0)) {
      end = 255;
    }
    // if no change
    if (start == end) {
      return start;
    }
    int timeDiffByTick = time.getEndTime() - tick;
    if (timeDiffByTick == 0) {
      timeDiffByTick = 1;
    }
    int changePerTick;
    changePerTick = Math.abs(end - start) / timeDiffByTick;
    if (start > end) {
      return start - changePerTick;
    }

    return changePerTick + start;
  }

  /**
   * Method to calculate the tween value given two parameters.
   *
   * @param start initial value for calculation.
   * @param end final value for calculation.
   * @return tween value based on the tick.
   */
  private double tweenCalculator(double start, double end, Time time, int tick) {
    // interpolation formula
    // total time / changes -> perTick
    double changePerTick;
    // interval is incorrect after first pass
    double timeDiffByTick = time.getEndTime() - tick;
    if (timeDiffByTick == 0) {
      timeDiffByTick = 1;
    }
    changePerTick = Math.abs(end - start) / timeDiffByTick;

    if (start == end) {
      return start;
    }

    if (start > end) {
      return start - changePerTick;
    }

    return changePerTick + start;
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    // output for hashmap
    for (String key : shapes.keySet()) {
      output
          .append(key)
          .append(" ")
          .append(shapes.get(key).getType())
          .append(" ")
          .append(shapes.get(key).getColor().toString())
          .append(" at ")
          .append(shapes.get(key).getPosition().toString())
          .append(" ")
          .append(shapes.get(key).getSize().toString());
      output.append("\n");
    }
    // add output for transformations
    for (String s : getTransformations().keySet()) {
      for (ITransformations t : this.transformations.get(s)) {
        output.append(t.getIdentifier());
        switch (t.getType()) {
          case SHOW:
            output
                .append(" appears at time t=")
                .append(t.getTime().getStartTime())
                .append(" and disappears at time t=")
                .append(t.getTime().getEndTime());
            break;
          case MOVE:
            output
                .append(" moves from ")
                .append(getShapes().get(t.getIdentifier()).getPosition().toString())
                .append(" to ")
                .append(t.getPosition().toString())
                .append(" from time t=")
                .append(t.getTime().getStartTime())
                .append(" to t=")
                .append(t.getTime().getEndTime());
            break;
          case SCALE:
            output
                .append(" changes size from ")
                .append(getShapes().get(t.getIdentifier()).getSize().toString())
                .append(" to ")
                .append(t.getSize().toString())
                .append(" from time t=")
                .append(t.getTime().getStartTime())
                .append(" to t=")
                .append(t.getTime().getEndTime());
            break;
          case CHANGECOLOR:
            output
                .append(" changes color from ")
                .append(getShapes().get(t.getIdentifier()).getColor().toString())
                .append(" to ")
                .append(t.getColor().toString())
                .append(" from time t=")
                .append(t.getTime().getStartTime())
                .append(" to t=")
                .append(t.getTime().getEndTime());
            break;
          default:
            break;
        }
        output.append("\n");
      }
    }
    return output.toString().stripTrailing();
  }
}
