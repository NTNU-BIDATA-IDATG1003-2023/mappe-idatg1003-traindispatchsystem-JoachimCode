package edu.ntnu.stud;

import edu.ntnu.stud.gui.UserInterface;

/**
 * This is the main class for the train dispatch application. It creates instances of train departures and
 * uses its info to display the correct data.
 *
 * @author JoachimDuong
 * @version 0.0.1
 * @since 4/10/2023
 */
public class TrainDispatchApp {
  TrainStation trainStation;
  UserInterface userInterface;
  public static void main(String[] args){
    TrainDispatchApp trainDispatchApp = new TrainDispatchApp();
    trainDispatchApp.start();
  }
  private void start(){
    trainStation = new TrainStation();
    innit();
    userInterface = new UserInterface(trainStation);
    userInterface.initialize();
  }

  private void innit(){
    trainStation.addTrain(new TrainDeparture(12, "A", 1, "Oslo", 1, 0));
    trainStation.addTrain(new TrainDeparture(13, "B", 2, "Trondheim", 2, 20));
    trainStation.addTrain(new TrainDeparture(14, "C", 3, "Bergen", 3, 0));
    trainStation.addTrain(new TrainDeparture(15, "D", 4, "Stavanger", 4, 0));
  }
}
