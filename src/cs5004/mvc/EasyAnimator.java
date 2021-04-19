package cs5004.mvc;

import cs5004.mvc.model.IModel;
import cs5004.mvc.model.ModelImpl;
import cs5004.mvc.util.AnimationBuilder;
import cs5004.mvc.util.AnimationBuilderImpl;
import cs5004.mvc.util.AnimationReader;
import cs5004.mvc.view.GraphicalView;
import cs5004.mvc.view.IView;
import cs5004.mvc.view.TypeOfView;
import cs5004.mvc.view.ViewFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Main method for the EasyAnimator, allows command line arguments to dictate the running of the
 * program.
 */
public class EasyAnimator {

  /**
   * Main Method for the program.
   *
   * @param args command line arguments.
   * @throws IOException if invalid file.
   */
  public static void main(String[] args) throws IOException {
    // parse command line args
    String infile = "";
    String outfile = "";
    TypeOfView type = TypeOfView.TEXT;
    int speed = 1;
    JOptionPane error = new JOptionPane();

    if (args.length == 0 || args.length < 4) {
      System.out.println("Incorrect arguments passed");
      System.exit(0);
    }

    for (String s : args) {
      if (s.equals("-in")) {
        // out file is the next arg
        infile = args[Arrays.asList(args).indexOf(s) + 1];
        if (infile.equals("") || infile == null) {
          error.createDialog("Error finding file to read, must input valid file");
          error.setVisible(true);
          System.out.println("Error finding file to read, must enter valid file path");
          System.exit(0);
        }
      }
      if (s.equals("-view")) {
        // parse the view type
        String viewType = args[Arrays.asList(args).indexOf(s) + 1];
        if (viewType.equals("")) {
          error.createDialog("A view type is required for the program to run");
          error.setVisible(true);
          System.out.println("A view type is required for the program to run");
          System.exit(0);
        }
        if (viewType.equalsIgnoreCase("text")) {
          type = TypeOfView.TEXT;
        }
        if (viewType.equalsIgnoreCase("visual")) {
          type = TypeOfView.GRAPHICAL;
        }
        if (viewType.equalsIgnoreCase("svg")) {
          type = TypeOfView.SVG;
        }
      }
      if (s.equals("-out")) {
        // parse the outfile arg
        outfile = args[Arrays.asList(args).indexOf(s) + 1];
      }
      if (s.equals("-speed")) {
        // parse the speed arg
        speed = Integer.parseInt(args[Arrays.asList(args).indexOf(s) + 1]);
      }
    }
    // Make a model, make a view
    IModel model = new ModelImpl();
    IView view;
    AnimationBuilder builder = new AnimationBuilderImpl(model);
    // read file from CLI
    Readable inFile = new FileReader(infile);
    AnimationReader.parseFile(inFile, builder);
    // call view factory
    view = ViewFactory.makeView(type, speed, model);
    switch (view.getViewType()) {
      case TEXT:
        System.out.println(view.getModelString(model));
        break;
      case SVG:
        view.saveSVG(view.getModelString(model), outfile);
        break;
      case GRAPHICAL:
        drawAnimation((GraphicalView) view, speed);
        break;
      default:
        System.out.println("Error determining the type of view.");
        System.exit(0);
        break;
    }
    // TODO: Potential bugs...
    /*
    - buildings.txt doesn't work????
    - Writing to svg file working but kind of not working for some of the text inputs
    - make JAR file
    - Lastly, fix any broken tests
     */

  }

  /**
   * Method to draw the actual image onto the canvas.
   *
   * @param tempo int tempo.
   */
  public static void drawAnimation(GraphicalView view, int tempo) {
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
            view.setMinimumSize(new Dimension(800,900));
            view.update(view.getGraphics());
            view.repaint();
          }
        };
    new Timer(delay, al).start();
  }
}
