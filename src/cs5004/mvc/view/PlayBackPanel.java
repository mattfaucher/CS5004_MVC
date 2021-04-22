package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import cs5004.mvc.model.shape.IShape;
import cs5004.mvc.model.shape.TypeOfShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayBackPanel extends JPanel {
  private IModel model;
  private int tick;
  private JButton play;

  public PlayBackPanel(IModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.model = model;
    this.tick = 0;
    this.setVisible(true);
    play = new JButton("Play/Pause");

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    List<IShape> shapes;
    shapes = model.getStateAtTick(this.tick);
    if (shapes.size() > 0) {
      for (IShape shape : shapes) {
        // list of shapes
        if (shape != null) {
          // get the type of shape
          TypeOfShape shapeType = shape.getType();
          // create a color object to pass to painted shape
          Color color =
              new Color(
                  shape.getColor().getRed(),
                  shape.getColor().getGreen(),
                  shape.getColor().getBlue());
          // set the color for the shape
          g2.setColor(color);
          // Choose correct shape type
          switch (shapeType) {
            case RECTANGLE:
              g2.fillRect(
                  (int) shape.getPosition().getX(),
                  (int) shape.getPosition().getY(),
                  (int) shape.getSize().getWidth(),
                  (int) shape.getSize().getHeight());
              break;
            case OVAL:
              g2.fillOval(
                  (int) shape.getPosition().getX(),
                  (int) shape.getPosition().getY(),
                  (int) shape.getSize().getWidth(),
                  (int) shape.getSize().getHeight());
              break;
            default:
              throw new IllegalArgumentException("Shapes not supported");
          }
        }
      }
    }
  }

  /**
   * Method to set the tick.
   *
   * @param tick int.
   */
  public void setTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick must equal 1 or greater");
    }
    this.tick = tick;
  }

  /**
   * Method to get the current tick.
   *
   * @return int tick.
   */
  public int getTick() {
    return this.tick;
  }
}
