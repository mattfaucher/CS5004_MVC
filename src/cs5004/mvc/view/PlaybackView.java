package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlaybackView extends AbstractView implements IView, ActionListener {
  private JPanel mainPanel;
  private JPanel controlPanel;
  private JPanel dataPanel;
  private DrawPanel drawPanel;
  private JTextArea dataOutput;
  private JButton play;
  private JButton restart;
  private JCheckBox setLoop;

  /**
   * Constructs a new AbstractView Object.
   *
   * @param tempo
   * @param model
   * @throws IllegalArgumentException if max inputs are less than min.
   */
  public PlaybackView(int tempo, IModel model) throws IllegalArgumentException {
    super(tempo, model);
    setTitle("Easy Animator Playback");
    setPreferredSize(new Dimension(1600, 800));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // set up main panel to house all other panels
    mainPanel = new JPanel();
    mainPanel.setSize(1920, 300);
    mainPanel.setBackground(Color.white);
    mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainPanel.setDoubleBuffered(true);

    // set up drawPanel
    drawPanel = new DrawPanel(model);
    drawPanel.setBorder(BorderFactory.createTitledBorder("Animation Visualization"));
    drawPanel.setVisible(true);
    drawPanel.setDoubleBuffered(true);

    // set up data panel
    dataPanel = new JPanel();
    dataPanel.setBorder(BorderFactory.createTitledBorder("Data Output"));
    dataPanel.setBackground(Color.PINK);
    dataPanel.setVisible(true);
    dataPanel.setLayout(new GridLayout());

    // set up the controlPanel
    controlPanel = new JPanel();
    controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
    controlPanel.setLocation(50, 725);
    controlPanel.setSize(700, 130);
    controlPanel.setBackground(Color.gray);
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
    controlPanel.setVisible(true);
    controlPanel.setDoubleBuffered(true);

    // set up buttons
    play = new JButton();
    play.setText("Play/Pause");
    play.setActionCommand("play/pause");
    play.addActionListener(this);
    restart = new JButton();
    restart.setText("Restart");
    restart.setActionCommand("restart");
    restart.addActionListener(this);
    setLoop = new JCheckBox();
    setLoop.setText("Loop");
    setLoop.setSelected(false);
    setLoop.setActionCommand("loop");
    setLoop.addActionListener(this);

    controlPanel.add(play);
    controlPanel.add(restart);
    controlPanel.add(setLoop);

    // add all to mainpanel
    mainPanel.add(drawPanel);
    mainPanel.add(dataPanel);
    this.add(controlPanel);
    this.add(mainPanel);
    pack();
    setVisible(true);
  }

  /**
   * Method to refresh the state of the canvas at a given tick.
   *
   * @param tick int tick.
   */
  public void atTick(int tick) {
    drawPanel.removeAll();
    drawPanel.setTick(tick);
    drawPanel.revalidate();
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

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "play/pause":
        System.out.println("play/pause");
        break;
      case "restart":
        System.out.println("restart");
        break;
      case "loop":
        System.out.println("loop");
        break;
      default:
        break;
    }
  }
}
