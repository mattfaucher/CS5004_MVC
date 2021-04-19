package cs5004.mvc.util;

import cs5004.mvc.model.IModel;
import cs5004.mvc.model.shape.Color;
import cs5004.mvc.model.shape.Position;
import cs5004.mvc.model.shape.Size;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.Time;

/** Implementing class for the AnimationBuilder protocol. */
public class AnimationBuilderImpl implements AnimationBuilder<IModel> {
  // class attribute
  private IModel model;

  /**
   * Constructs a new object of the AnimationBuilder that uses an IModel model.
   *
   * @param model IModel object.
   */
  public AnimationBuilderImpl(IModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.model = model;
  }

  @Override
  public IModel build() {
    return this.model;
  }

  @Override
  public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
    this.model.getCanvas().setCanvas(x, y, width, height);
    return new AnimationBuilderImpl(this.model);
  }

  @Override
  public AnimationBuilder<IModel> declareShape(String name, String type) {
    this.model.declareShape(name, type);
    return new AnimationBuilderImpl(this.model);
  }

  /* Throws IllegalArgumentException if the motion cannot be specified.*/
  @Override
  public AnimationBuilder<IModel> addMotion(
      String name,
      int t1,
      int x1,
      int y1,
      int w1,
      int h1,
      int r1,
      int g1,
      int b1,
      int t2,
      int x2,
      int y2,
      int w2,
      int h2,
      int r2,
      int g2,
      int b2) {
    // if the name isn't valid just return
    if (!this.model.getShapes().containsKey(name)) {
      return new AnimationBuilderImpl(this.model);
    }
    // If the shape has been declared but not yet initialized
    if (!(this.model.getShapes().get(name).isInitialized())) {
      // setting the shape's initial properties
      // set the shape to be initialized
      Position oldPosition = new Position(x1, y1);
      Size oldSize = new Size(w1, h1);
      Color oldColor = new Color(r1, g1, b1);
      TypeOfShape type = this.model.getShapes().get(name).getType();
      this.model.createShape(name, oldColor, oldPosition, oldSize, type);
      this.model.getShapes().get(name).setInitialized();
    }

    Time time = new Time(t1, t2);

    // check if moving
    if (x1 != x2 || y1 != y2) {
      Position position = new Position(x2, y2);
      this.model.moveShape(name, position, time);
      return new AnimationBuilderImpl(this.model);
    }

    // check for changing size
    if (w1 != w2 || h1 != h2) {
      Size size = new Size(w2, h2);
      this.model.scaleShape(name, size, time);
      return new AnimationBuilderImpl(this.model);
    }

    // check for changing color
    if (r1 != r2 || g1 != g2 || b1 != b2) {
      Color color = new Color(r2, g2, b2);
      this.model.changeShapeColor(name, color, time);
      return new AnimationBuilderImpl(this.model);
    }

    this.model.showShape(name, time);
    return new AnimationBuilderImpl(this.model);
  }
}
