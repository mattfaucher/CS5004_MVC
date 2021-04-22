package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import cs5004.mvc.model.shape.TypeOfShape;
import cs5004.mvc.model.transformations.ITransformations;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/** The type Textual view. */
public class TextView extends AbstractView implements IView {

  /**
   * Constructs a new AbstractView Object.
   *
   * @throws IllegalArgumentException if max inputs are less than min.
   */
  public TextView(int tempo, IModel model) throws IllegalArgumentException {
    super(tempo, model);
  }

  @Override
  public String getModelString(IModel model) {
    StringBuilder output = new StringBuilder();
    // output for hashmap
    for (String key : model.getShapes().keySet()) {
      if (model.getShapes().get(key).getType() == TypeOfShape.RECTANGLE) {
        output
            .append("Create ")
            .append(model.getShapes().get((key)).getColor())
            .append(" ")
            .append(model.getShapes().get(key).getType())
            .append(" ")
            .append(key)
            .append(" ")
            .append("with corner at ")
            .append(model.getShapes().get(key).getPosition().toString() + ", ")
            .append(model.getShapes().get(key).getSize().toString())
            .append("\n");
      }
      if (model.getShapes().get(key).getType() == TypeOfShape.OVAL) {
        output
            .append("Create ")
            .append(model.getShapes().get((key)).getColor())
            .append(" ")
            .append(model.getShapes().get(key).getType())
            .append(" ")
            .append(key)
            .append(" ")
            .append("with center at ")
            .append(model.getShapes().get(key).getPosition().toString() + ", ")
            .append("radius " + model.getShapes().get(key).getSize().getWidth())
            .append(" and " + model.getShapes().get(key).getSize().getHeight())
            .append("\n");
      }
    }
    output.append("\n");
    // add output for transformations
    for (String s : model.getTransformations().keySet()) {
      for (ITransformations t : model.getTransformations().get(s)) {
        output.append(t.getIdentifier());
        switch (t.getType()) {
          case SHOW:
            output
                .append(" appears at time t=")
                .append(t.getTime().getStartTime())
                .append(" and disappears at time t=")
                .append(t.getTime().getEndTime());
            break;
          case MOVE:
            output
                .append(" moves from ")
                .append(model.getShapes().get(t.getIdentifier()).getPosition().toString())
                .append(" to ")
                .append(t.getPosition().toString())
                .append(" from time t=")
                .append(t.getTime().getStartTime())
                .append(" to t=")
                .append(t.getTime().getEndTime());
            break;
          case SCALE:
            output
                .append(" changes size from ")
                .append(model.getShapes().get(t.getIdentifier()).getSize().toString())
                .append(" to ")
                .append(t.getSize().toString())
                .append(" from time t=")
                .append(t.getTime().getStartTime())
                .append(" to t=")
                .append(t.getTime().getEndTime());
            break;
          case CHANGECOLOR:
            output
                .append(" changes color from ")
                .append(model.getShapes().get(t.getIdentifier()).getColor().toString())
                .append(" to ")
                .append(t.getColor().toString())
                .append(" from time t=")
                .append(t.getTime().getStartTime())
                .append(" to t=")
                .append(t.getTime().getEndTime());
            break;
          default:
            break;
        }
        output.append("\n");
      }
    }
    return output.toString().stripTrailing();
  }

  @Override
  public void saveFile(String text, String filename) throws IllegalArgumentException, IOException {
    if (filename == null || filename.equals("")) {
      throw new IllegalArgumentException("Filename is invalid");
    }
    String path = System.getProperty("user.home") + "/Desktop/" + filename + ".txt";
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(path));
      writer.write(text);
      writer.close();
    } catch (IOException e) {
      throw new IOException("Error writing to file");
    }
  }

  @Override
  public TypeOfView getViewType() {
    return TypeOfView.TEXT;
  }
}
