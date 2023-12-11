package edu.ntnu.stud;

import edu.ntnu.stud.datastructures.TrainDeparture;
import edu.ntnu.stud.datastructures.TrainStation;
import edu.ntnu.stud.ui.UserInterface;

/**
 * This is the main class for the train dispatch application.
 * It creates instances of train departures and
 * uses its info to display the correct data.
 *
 * @author Joachim Duong
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrainDispatchApp {
  TrainStation trainStation;
  UserInterface userInterface;

  public static void main(String[] args) {
    TrainDispatchApp trainDispatchApp = new TrainDispatchApp();
    trainDispatchApp.start();
  }

  private void start() {
    trainStation = new TrainStation();
    innit();
    userInterface = new UserInterface(trainStation);
    userInterface.initialize();
  }

  private void innit() {
    trainStation.addTrain(new TrainDeparture(1224, "A", 2, "Oslo", -1, 0));
    trainStation.addTrain(new TrainDeparture(1337, "B", 1, "Trondheim", 2, 20));
    trainStation.addTrain(new TrainDeparture(1400, "C", 3, "Bergen", 3, 0));
    trainStation.addTrain(new TrainDeparture(1515, "D", 4, "Stavanger", 4, 0));
  }
}
