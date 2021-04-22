package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import java.io.IOException;
import javax.swing.JFrame;

/** Class to abstract out repeated code from all implementing classes of IView protocol. */
public abstract class AbstractView extends JFrame implements IView {
  // class attributes
  private int left;
  private int top;
  private int width;
  private int height;
  private int tempo;

  /**
   * Constructs a new AbstractView Object.
   *
   * @throws IllegalArgumentException if max inputs are less than min.
   */
  public AbstractView(int tempo, IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    if (tempo < 1) {
      throw new IllegalArgumentException("Tempo must be 1 or greater");
    }
    this.tempo = tempo;
    this.left = model.getCanvas().getX();
    this.top = model.getCanvas().getY();
    this.width = model.getCanvas().getWidth();
    this.height = model.getCanvas().getHeight();
  }

  /**
   * Method to get the min left value.
   *
   * @return int left.
   */
  public int getLeft() {
    return left;
  }

  /**
   * Method to get the min top value.
   *
   * @return int top.
   */
  public int getTop() {
    return top;
  }

  /**
   * Method to get the max width.
   *
   * @return int width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Method to get the max height value.
   *
   * @return int height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Method to get the tempo.
   *
   * @return int tempo.
   */
  public int getTempo() {
    return tempo;
  }

  @Override
  public abstract String getModelString(IModel model);

  @Override
  public abstract void saveFile(String file, String filename) throws IOException;

  @Override
  public abstract TypeOfView getViewType();
}
