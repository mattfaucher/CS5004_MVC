package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
import java.awt.event.ActionListener;
import java.io.IOException;

/** This is a interface for view. it contains textual view and . */
public interface IView {

  /**
   * Method to get all of the data from the Model using its toString method.
   *
   * @param model IModel object.
   * @return String of all data in the model.
   */
  String getModelString(IModel model);

  /**
   * Method to save the view as a file.
   *
   * @param fileString String with content.
   * @param filename STring for filename.
   * @throws IOException if file not found.
   */
  void saveFile(String fileString, String filename) throws IOException;

  /**
   * Method to get the type of the current view.
   *
   * @return TypeOfView.
   */
  TypeOfView getViewType();

  void render(int speed);

  void setListeners(ActionListener click);
}
