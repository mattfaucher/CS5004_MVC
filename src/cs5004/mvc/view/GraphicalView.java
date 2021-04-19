package cs5004.mvc.view;

import cs5004.mvc.model.Canvas;
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
    super.setPreferredSize(new Dimension(model.getCanvas().getWidth(), model.getCanvas().getHeight()));
    JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 0,100,-500,500);
    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL,0,100,-500,500);
    // add scroll bars

    // setting up the canvas
    // Create the canvas and set it's values
    this.draw = new Draw(model);
    this.add(draw);
    JFrame frame = this;
    this.pack();
    this.setVisible(true);
    setResizable(true);
    class ScrollListener implements AdjustmentListener {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        frame.setSize(e.getValue(), e.getValue());
      }
    }
    this.add(draw);
    hbar.addAdjustmentListener(new ScrollListener());
    vbar.addAdjustmentListener(new ScrollListener());
    this.getContentPane().add(hbar, BorderLayout.PAGE_END);
    this.getContentPane().add(vbar,BorderLayout.LINE_END);
    this.getContentPane().add(this.draw, BorderLayout.CENTER);
    // pack everything that's been added
    this.pack();
    this.setVisible(true);
    setResizable(true);
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
    this.repaint();
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
