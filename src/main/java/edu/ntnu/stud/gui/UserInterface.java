package edu.ntnu.stud.gui;

import edu.ntnu.stud.TrainDeparture;
import edu.ntnu.stud.TrainStation;

import java.time.LocalTime;
import java.util.Iterator;


/**
 * This class handles all output to the user. It will handle what to print and get information
 * from the TrainStation class. It will also handle the input from the user.
 */
public class UserInterface {
  private TextPrinter textPrinter;
  private TrainStation trainStation;
  private InputHandler inputHandler;
  private boolean running;
  public UserInterface(TrainStation trainStation){
    textPrinter = new TextPrinter();
    this.trainStation = trainStation;
    inputHandler = new InputHandler();
    running = true;

  }

  public void initialize(){
    textPrinter.displayWelcome();
    textPrinter.displayEnterTimer();
    trainStation.changeClock(inputHandler.getTimeInput());
    textPrinter.displayTrainDepartures(trainStation.getSortedDepartureList(), trainStation.getTime());
    menuSelect();
  }

  public void menuSelect(){
    while(running){
      String userCommand = inputHandler.getCommand();
      switch(userCommand){
        case "/h":
          textPrinter.displayCommands();
          break;
        case "/display":
          displayTrainDepartures(trainStation.getSortedDepartureList(), trainStation.getTime());
          break;
        case "/add":
          break;
        case "/remove":

          break;
        case "/edit":

          break;
        case "/quit":
          running = false;
          break;
        default:
          textPrinter.displayInvalidCommand();
          break;
      }
    }
  }

  public void displayTrainDepartures(Iterator<TrainDeparture> trainDepartures, LocalTime currentTime){
    textPrinter.displayTrainDepartures(trainDepartures, currentTime);
  }

  public void changeClock(){

  }


}
