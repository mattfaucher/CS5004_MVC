package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollBar;

/** Class for GraphicalView that builds a java swing window and draws the animations. */
public class GraphicalView extends AbstractView implements IView {
  // class attributes
  private Draw draw;

  /**
   * Constructs a new AbstractView Object.
   *
   * @throws IllegalArgumentException if max inputs are less than min.
   */
  public GraphicalView(int tempo, IModel model) throws IllegalArgumentException {
    super(tempo, model);
    // set up the JFrame
    this.setTitle("Easy Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationByPlatform(true);
    super.setPreferredSize(new Dimension(super.getWidth(), super.getHeight()));
    // add scroll bars
    JScrollBar horiz =
        new JScrollBar(
            JScrollBar.HORIZONTAL,
            super.getWidth(),
            super.getWidth(),
            super.getWidth(),
            super.getWidth() * 3);

    // setting up the canvas
    // Create the canvas and set it's values

    this.draw = new Draw(model);
    JFrame frame = this;
    class ScrollListener implements AdjustmentListener {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        frame.setSize(e.getValue(), e.getValue());
      }
    }

    this.add(draw);
    horiz.addAdjustmentListener(new ScrollListener());
    this.getContentPane().add(horiz, BorderLayout.SOUTH);
    // pack everything that's been added
    this.pack();
    draw.setVisible(true);
    this.setVisible(true);
    super.setVisible(true);
  } // end constructor

  /**
   * Method to refresh the state of the canvas at a given tick.
   *
   * @param tick int tick.
   */
  public void atTick(int tick) {
    draw.removeAll();
    draw.setTick(tick);
    draw.revalidate();
    draw.repaint();
  }

  @Override
  public String getModelString(IModel model) {
    return model.toString();
  }

  @Override
  public void saveSVG(String svg, String filename) throws IOException {
    // implementation doesn't exist here.
  }

  @Override
  public TypeOfView getViewType() {
    return TypeOfView.GRAPHICAL;
  }
}
