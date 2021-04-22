package cs5004.mvc.view;

import cs5004.mvc.model.IModel;

/** Class to create a factory of views, will create the correct view based on given input. */
public class ViewFactory {

  /**
   * Static method to return the correct instance of IView.
   *
   * @param type TypeOfView for view.
   * @return IView object.
   * @throws IllegalArgumentException if the view isn't valid.
   */
  public static IView makeView(TypeOfView type, int tempo, IModel model)
      throws IllegalArgumentException {
    switch (type) {
      case TEXT:
        return new TextView(tempo, model);
      case GRAPHICAL:
        return new GraphicalView(tempo, model);
      case SVG:
        return new SVGView(tempo, model);
      case PLAYBACK:
        return new PlaybackView(tempo, model);
      default:
        throw new IllegalArgumentException("View is unsupported");
    }
  }
}
