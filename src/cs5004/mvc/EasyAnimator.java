package cs5004.mvc;

import cs5004.mvc.controller.Controller;
import cs5004.mvc.model.IModel;
import cs5004.mvc.model.ModelImpl;
import cs5004.mvc.util.AnimationBuilder;
import cs5004.mvc.util.AnimationBuilderImpl;
import cs5004.mvc.util.AnimationReader;
import cs5004.mvc.view.IView;
import cs5004.mvc.view.TypeOfView;
import cs5004.mvc.view.ViewFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;

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

    // need a minimum of 4 arguments to be valid input
    if (args.length < 4) {
      System.out.println("Incorrect arguments passed");
      System.exit(0);
    }

    // Parse command line arguments
    for (String s : args) {
      if (s.equals("-in")) {
        // out file is the next arg
        infile = args[Arrays.asList(args).indexOf(s) + 1];
        if (infile.equals("")) {
          System.out.println("Error finding file to read, must enter valid file path");
          System.exit(0);
        }
      }

      if (s.equals("-view")) {
        // parse the view type
        String viewType = args[Arrays.asList(args).indexOf(s) + 1];
        if (viewType.equals("")) {
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
        if (viewType.equalsIgnoreCase("playback")) {
          type = TypeOfView.PLAYBACK;
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
    Controller controller;
    AnimationBuilder<IModel> builder = new AnimationBuilderImpl(model);
    // read file from CLI
    Readable inFile = new FileReader(infile);
    AnimationReader.parseFile(inFile, builder);
    // call view factory
    view = ViewFactory.makeView(type, speed, model);
    // set up the controller
    controller = new Controller(model, view);
    controller.startAnimation(infile, type, outfile, speed);

    // TODO: Potential bugs...
    /*
    - buildings.txt doesn't work????
    - Writing to svg file working but kind of not working for some of the text inputs
     */
  }
}
