package cs5004.mvc.view;

import cs5004.mvc.model.IModel;
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
   * Method to save the view as an svg file.
   *
   * @param svg String with content.
   * @param filename STring for filename.
   * @throws IOException if file not found.
   */
  void saveSVG(String svg, String filename) throws IOException;

  /**
   * Method to get the type of the current view.
   *
   * @return TypeOfView.
   */
  TypeOfView getViewType();
}
