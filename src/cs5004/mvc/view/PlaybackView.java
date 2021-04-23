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
import javax.swing.JTextField;
import javax.swing.Timer;

public class PlaybackView extends AbstractView implements IView, ActionListener {
  private JPanel mainPanel;
  private JPanel controlPanel;
  private JPanel dataPanel;
  private DrawPanel drawPanel;
  private JButton play;
  private JButton restart;
  private JCheckBox setLoop;
  private JTextField setSpeed;
  private JButton changeSpeed;
  private boolean paused;
  private boolean loop;
  private Timer timer;
  int tick = 0;
  int speed;
  int delay;

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
    this.speed = tempo;
    this.delay = 1000 / speed;

    // set up main panel to house all other panels
    mainPanel = new JPanel();
    mainPanel.setSize(1920, 300);
    mainPanel.setBackground(Color.white);
    mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    // set up drawPanel
    drawPanel = new DrawPanel(model);
    drawPanel.setBorder(BorderFactory.createTitledBorder("Animation Visualization"));

    // set up data panel
    dataPanel = new JPanel();
    dataPanel.setBorder(BorderFactory.createTitledBorder("Data Output"));
    dataPanel.setBackground(Color.PINK);
    dataPanel.setLayout(new GridLayout());

    // set up the controlPanel
    controlPanel = new JPanel();
    controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
    controlPanel.setLocation(50, 725);
    controlPanel.setSize(700, 130);
    controlPanel.setBackground(Color.gray);
    controlPanel.setLayout(new GridLayout());
    //controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

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
    setSpeed = new JTextField(this.speed);
    setSpeed.setBorder(BorderFactory.createTitledBorder("Speed Input"));
    changeSpeed = new JButton("Set Speed");
    changeSpeed.setActionCommand("speed");

    // set listeners
    this.setListeners(this);

    controlPanel.add(play);
    controlPanel.add(restart);
    controlPanel.add(setLoop);
    controlPanel.add(changeSpeed);
    controlPanel.add(setSpeed);

    // add all to mainpanel
    mainPanel.add(drawPanel);
    mainPanel.add(dataPanel);
    this.add(controlPanel);
    this.add(mainPanel);
    pack();
    setVisible(true);
  }

  /**
   * Method for the play button which will toggle play or pause based on current state.
   *
   * @param e ActionEvent.
   */
  private void playButton(ActionEvent e) {
    if (paused) {
      timer.start();
      paused = false;
    } else {
      paused = true;
      timer.stop();
    }
  }

  /**
   * Method for the restart button which will cause the animation to restart.
   *
   * @param e ActionEvent.
   */
  private void restartButton(ActionEvent e) {
    timer.stop();
    super.repaint();
    this.tick = 0;
    render(this.speed);
  }

  /**
   * Method to handle loop button action.
   *
   * @param e ActionEvent.
   */
  private void loopButton(ActionEvent e) {
    if (loop) {
      if (!timer.isRunning()) {
        this.tick = 0;
        atTick(0);
        timer.restart();
      }
    }
  }

  /**
   * Method to handle the changing of the animation speed, dynamically.
   *
   * @param e ActionEvent.
   */
  private void speedToggle(ActionEvent e) {
    String text = setSpeed.getText();
    int val = Integer.parseInt(text);
    if (val > 0) {
      this.speed = Integer.parseInt(text);
      this.delay = 1000 / this.speed;
      System.out.println(this.speed);
      render(this.speed);
    }
  }

  @Override
  public void render(int speed) {
    ActionListener al =
        e -> {
          if (timer.isRunning()) {
            atTick(tick);
            tick++;
            setPreferredSize(new Dimension(1920, 1000));
            pack();
            update(getGraphics());
            repaint();
          }
        };
    this.timer = new Timer(this.delay, al);
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "play/pause":
        playButton(e);
        break;
      case "restart":
        restartButton(e);
        break;
      case "loop":
        loop = !loop;
        loopButton(e);
        break;
      case "speed":
        speedToggle(e);
      default:
        break;
    }
  }

  public void atTick(int tick) {
    drawPanel.setTick(tick);
    drawPanel.paintComponent(getGraphics());
    drawPanel.revalidate();
  }

  @Override
  public void setListeners(ActionListener click) {
    this.play.addActionListener(click);
    this.restart.addActionListener(click);
    this.setLoop.addActionListener(click);
    this.changeSpeed.addActionListener(click);
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
