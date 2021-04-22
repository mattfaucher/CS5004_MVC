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
import javax.swing.Timer;

public class PlaybackView extends AbstractView implements IView, ActionListener {
  private JPanel mainPanel;
  private JPanel controlPanel;
  private JPanel dataPanel;
  private DrawPanel drawPanel;
  private JButton play;
  private JButton restart;
  private JCheckBox setLoop;
  private boolean paused;
  private boolean loop;
  private Timer timer;
  private int tick = 0;

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
    this.paused = false;
    this.loop = false;

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
    restart = new JButton();
    restart.setText("Restart");
    restart.setActionCommand("restart");
    setLoop = new JCheckBox();
    setLoop.setText("Loop");
    setLoop.setSelected(false);
    setLoop.setActionCommand("loop");
    // set listeners
    this.setListeners(this);

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

  @Override
  public void render(int speed) {
    repaint();
    setVisible(true);
    timer = new Timer(1000 / speed, this);
    atTick(tick);
    tick++;
    pack();
    setResizable(true);
    setMinimumSize(new Dimension(800, 900));
    update(getGraphics());
    repaint();
  }

  @Override
  public void setListeners(ActionListener click) {
    this.play.addActionListener(click);
    this.restart.addActionListener(click);
    this.setLoop.addActionListener(click);
  }

  private void playButton(ActionEvent e) {
    tick += 1000 / getTempo();
    if (paused) {
     timer.stop();
    } else {
      timer.start();
    }
  }

  private void restartButton(ActionEvent e) {
    tick = 0;
    timer.restart();
  }

  private void loopButton(ActionEvent e) {}

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(e.getActionCommand());

    Object source = e.getSource();
    if (play.equals(source)) {
      playButton(e);
    } else if (restart.equals(source)) {
      restartButton(e);
    } else if (setLoop.equals(source)) {
      loopButton(e);
    } else {
      atTick(tick);
      pack();
      setResizable(true);
      setMinimumSize(new Dimension(800, 900));
      update(getGraphics());
      repaint();
    }
    new Timer(1000 / getTempo(), this).start();
  }

  public void atTick(int tick) {
    drawPanel.removeAll();
    drawPanel.setTick(tick);
    drawPanel.revalidate();
    repaint();
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
