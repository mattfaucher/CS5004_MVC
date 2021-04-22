package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

public class PlaybackView extends AbstractView implements IView {
  private PlayBackPanel panel;

  /**
   * Constructs a new AbstractView Object.
   *
   * @param tempo
   * @param model
   * @throws IllegalArgumentException if max inputs are less than min.
   */
  public PlaybackView(int tempo, IModel model) throws IllegalArgumentException {
    super(tempo, model);
    panel = new PlayBackPanel(model);
    setTitle("Easy Animator Playback");
    setPreferredSize(new Dimension(800, 800));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    add(panel);
    setVisible(true);
  }

  /**
   * Method to refresh the state of the canvas at a given tick.
   *
   * @param tick int tick.
   */
  public void atTick(int tick) {
    panel.removeAll();
    panel.setTick(tick);
    panel.revalidate();
    this.repaint();
  }

  @Override
  public String getModelString(IModel model) {
    return "";
  }

  @Override
  public void saveFile(String fileString, String filename) throws IOException {
    // this may be implemented later on
  }

  @Override
  public TypeOfView getViewType() {
    return TypeOfView.PLAYBACK;
  }
}
