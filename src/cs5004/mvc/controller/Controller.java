package cs5004.mvc.controller;

import cs5004.mvc.model.IModel;
import cs5004.mvc.view.GraphicalView;
import cs5004.mvc.view.IView;
import cs5004.mvc.view.PlaybackView;
import cs5004.mvc.view.TypeOfView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;

/**
 * Class to store the controller and to use this controller to handle the communication between
 * model and the view.
 */
public class Controller {
  private IModel model;
  private IView view;

  public Controller(IModel model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or View are null");
    }

    this.model = model;
    this.view = view;
  }

  /**
   * Method that takes all the input and then starts the animation.
   *
   * @param infile file to read from.
   * @param type type of view to process.
   * @param outfile file to save to if specified.
   * @param speed speed of the animation.
   * @throws IOException if an error occurs with finding/writing to a file.
   */
  public void startAnimation(String infile, TypeOfView type, String outfile, int speed)
      throws IOException {
    switch (view.getViewType()) {
      case TEXT:
        System.out.println(view.getModelString(model));
        break;
      case SVG:
        view.saveFile(view.getModelString(model), outfile);
        break;
      case GRAPHICAL:
        drawAnimation((GraphicalView) view, speed);
        break;
      case PLAYBACK:
        drawAnimation((PlaybackView) view, speed);
        break;
      default:
        System.out.println("Error determining the type of view.");
        System.exit(0);
        break;
    }
  }

  public void drawAnimation(PlaybackView view, int tempo) {
    view.setVisible(true);
    int delay = 1000 / tempo;
    ActionListener al =
        new ActionListener() {
          private int tick = 0;

          @Override
          public void actionPerformed(ActionEvent e) {
            view.atTick(tick);
            tick++;
            view.pack();
            view.setResizable(true);
            view.setMinimumSize(new Dimension(800, 900));
            view.update(view.getGraphics());
            view.repaint();
          }
        };
    new Timer(delay, al).start();
  }

  /**
   * Method to draw the actual image onto the canvas.
   *
   * @param tempo int tempo.
   */
  public void drawAnimation(GraphicalView view, int tempo) {
    view.setVisible(true);
    int delay = 1000 / tempo;
    ActionListener al =
        new ActionListener() {
          private int tick = 0;

          @Override
          public void actionPerformed(ActionEvent e) {
            view.atTick(tick);
            tick++;
            view.pack();
            view.setResizable(true);
            view.setMinimumSize(new Dimension(800, 900));
            view.update(view.getGraphics());
            view.repaint();
          }
        };
    new Timer(delay, al).start();
  }
}
