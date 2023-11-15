package edu.ntnu.stud.gui;

import edu.ntnu.stud.TrainStation;

/**
 * This class handles all output to the user. It will handle what to print and get information
 * from the TrainStation class. It will also handle the input from the user.
 */
public class UserInterface {
  TextPrinter textPrinter;
  TrainStation trainStation;
  public UserInterface(TrainStation trainStation){
    textPrinter = new TextPrinter();
    this.trainStation = trainStation;
  }

}
