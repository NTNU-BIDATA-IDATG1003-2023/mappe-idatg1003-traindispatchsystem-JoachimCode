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
    textPrinter.displayEnterTime();
    trainStation.changeClock(inputHandler.getTimeInput(trainStation.getTime()));
    textPrinter.displayTrainDepartures(trainStation.getSortedDepartureList(), trainStation.getTime());
    textPrinter.displayHelp();
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

  public void addTrain(){
    int departureTime = getDepartureTime();
    String departureLine = getDepartureLine();
    String departureDestination = getDepartureDestination();
    int departureTrack = getDepartureTrack();

  }

  public int getDepartureTime(){
    textPrinter.displayDepartureTimeInput();
    LocalTime time = inputHandler.getTimeInput(trainStation.getTime());
    if(time == null){
      textPrinter.invalidTimeEntry();
      getDepartureTime();
    }
    return getTimeAsInt(time);
  }

  public String getDepartureLine(){
    textPrinter.displayLineInput();
    return inputHandler.getString();
  }
  public String getDepartureDestination(){
    textPrinter.displayDestinationInput();
    return inputHandler.getString();
  }

  public int getDepartureTrack(){
    textPrinter.displayTrackInput();
    String trackInput = inputHandler.getString();
    if(!canConvertToInt(trackInput)) {
      textPrinter.displayInvalidInt();
      getDepartureTrack();
    }
    return Integer.parseInt(trackInput);
  }

  public int getDepartureDelay(int departureTime){
    textPrinter.displayDelayInput();
    String delayInput = inputHandler.getString();
    if(!canConvertToInt(delayInput)){
      textPrinter.displayInvalidInt();
      getDepartureDelay(departureTime);
    }
    if(addDelay(departureTime, Integer.parseInt(delayInput)) == -1){
      textPrinter.displayInvalidDelay();
      getDepartureDelay(departureTime);
    }
    return Integer.parseInt(delayInput);
  }

  public void changeClock(){
    textPrinter.displayEnterTime();
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

  public int addDelay(int departureTime, int delayTime){
    int actualDepartureTime;
    int hours = departureTime / 100; // Extract hours (e.g., 13 from 1324)
    int minutes = departureTime % 100; // Extract minutes (e.g., 24 from 1324)

    minutes += delayTime; // Add delay to current minutes

    // Handle rollover if minutes exceed 60
    if (minutes >= 60) {
      hours += minutes / 60; // Add excess minutes to hours
      minutes %= 60; // Get remaining minutes after rollover
    }

    // Handle rollover if hours exceed 24
    if(hours < 24){
      actualDepartureTime = hours * 100 + minutes;
    }
    else{
      actualDepartureTime = -1;
    }

    // Combine hours and minutes to get the new time in the format HHMM
    return actualDepartureTime;
  }
  public int getTimeAsInt(LocalTime time){
    return time.getHour() * 100 + time.getMinute();
  }
  public boolean canConvertToInt(String str) {
    boolean canConvert = str != null && !str.isEmpty();
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        canConvert = false;
      }
    }
    return canConvert;
  }
}
