package edu.ntnu.stud.gui;

import edu.ntnu.stud.TrainDeparture;
import edu.ntnu.stud.TrainStation;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    trainStation.changeClock(inputHandler.getTimeInput(trainStation.getTime()));
    textPrinter.displayTrainDepartures(trainStation.getSortedDepartureList(), trainStation.getTime());
    menuSelect();
  }

  public void menuSelect(){
    while(running){
      String userCommand = inputHandler.getCommand();
      switch(userCommand){
        case CommandVariables.HELP:
          textPrinter.displayCommands();
          break;
        case CommandVariables.DISPLAY:
          displayTrainDepartures(trainStation.getSortedDepartureList(), trainStation.getTime());
          break;
        case CommandVariables.ADD:
          break;
        case CommandVariables.REMOVE:
          break;
        case CommandVariables.EDIT:
          break;
        case CommandVariables.SET_TIME:
          changeClock();
          break;
        case CommandVariables.QUIT:
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
    textPrinter.displayEnterTimer();
    LocalTime time = inputHandler.getTimeInput(trainStation.getTime());
    if(time != null){
      trainStation.changeClock(time);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      System.out.println(time.format(formatter));
    }
    else{
      textPrinter.invalidTimeEntry();
      changeClock();
    }
  }


}
