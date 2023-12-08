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
  private Validator validator;
  private boolean running;
  public UserInterface(TrainStation trainStation){
    textPrinter = new TextPrinter();
    this.trainStation = trainStation;
    inputHandler = new InputHandler();
    validator = new Validator();
    running = true;

  }

  public void initialize(){
    textPrinter.displayWelcome();
    changeClock();
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
          addTrain();
          break;
        case CommandVariables.EDIT:
          editMenu();
          break;
        case CommandVariables.SET_TIME:
          changeClock();
          break;
        case CommandVariables.SEARCH:
          searchMenu();
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

  public void searchMenu(){
    boolean searching = true;
    while(searching){
      textPrinter.displayEditCommands();
      String userCommand = inputHandler.getCommand();
      switch(userCommand){
        case(CommandVariables.DELAY):
          TrainDeparture searchedDeparture = searchByNumber();
          displaySearchedNumber(searchedDeparture);
          searching = false;
          break;
        case(CommandVariables.TRACK):
          Iterator<TrainDeparture> searchedDepartures = searchByDestination();
          displaySearchedDestination(searchedDepartures);
          searching = false;
          break;
        default:
          textPrinter.displayInvalidCommand();
          break;
      }
    }
  }

  public void editMenu(){
    boolean searching = true;
    while(searching){
      textPrinter.displayEditCommands();
      String userCommand = inputHandler.getCommand();
      switch(userCommand){
        case(CommandVariables.DELAY):
          TrainDeparture searchedDepartureDelay = searchByNumber();
          displaySearchedNumber(searchedDepartureDelay);
          editDelay(searchedDepartureDelay);
          searching = false;
          break;
        case(CommandVariables.TRACK):
          TrainDeparture searchedDepartureTrack = searchByNumber();
          displaySearchedNumber(searchedDepartureTrack);
          editTrack(searchedDepartureTrack);
          searching = false;
          break;
        default:
          textPrinter.displayInvalidCommand();
          break;
      }
    }
  }

  public void editDelay(TrainDeparture trainDeparture) {
    trainDeparture.setDelay(getDepartureDelay(trainDeparture.getDepartureTime()));
    textPrinter.displaySuccessfulEdit();
    textPrinter.enterCommand();
  }

  public void editTrack(TrainDeparture trainDeparture) {
    trainDeparture.setTrack(getDepartureTrack(trainDeparture.getDepartureTime()));
    textPrinter.displaySuccessfulEdit();
    textPrinter.enterCommand();
  }
  public void displaySearchedDestination(Iterator<TrainDeparture> trainDepartures){
    if(trainDepartures.hasNext()){
      while(trainDepartures.hasNext()){
        TrainDeparture currentTrainDeparture = trainDepartures.next();
        textPrinter.displayTraindeparture(currentTrainDeparture);
      }
      textPrinter.enterCommand();
    }
    else{
      textPrinter.displayNoMatchingDestination();
    }
  }
  public void displaySearchedNumber(TrainDeparture trainDeparture){
    if(trainDeparture == null){
      textPrinter.displayNoMatchingNumber();
    }
    else {
      textPrinter.displayTraindeparture(trainDeparture);
    }
  }

  public Iterator<TrainDeparture> searchByDestination() {
    textPrinter.displayRequestDestination();
    String userInput = inputHandler.getString();
    return trainStation.getTrainFromDestination(userInput);
  }
  public TrainDeparture searchByNumber() {
    boolean validInput = false;
    TrainDeparture trainDeparture = null;
    textPrinter.displayTrainNumberInput();
    while(!validInput) {
      String userInput = inputHandler.getString();
      if (validator.canConvertToInt(userInput)) {
        trainDeparture = trainStation.getTrainFromTrainNumber(Integer.parseInt(userInput));
        validInput = true;
      }
      else{
        textPrinter.displayInvalidInt();
      }
    }
    return trainDeparture;
  }

  public void displayTrainDepartures(Iterator<TrainDeparture> trainDepartures, LocalTime currentTime){
    textPrinter.displayTrainDepartures(trainDepartures, currentTime);
  }

  public void addTrain(){
    int departureTime = getDepartureTime();
    String departureLine = getDepartureLine(departureTime);
    String departureDestination = getDepartureDestination();
    int departureTrack = getDepartureTrack(departureTime);
    int delay = getDepartureDelay(departureTime);
    boolean successfullyAdded = false;
    while (!successfullyAdded){
      int trainNumber = getTrainNumber();
      if(trainStation.addTrain(new TrainDeparture(departureTime, departureLine, trainNumber, departureDestination, departureTrack, delay))){
        successfullyAdded = true;
        textPrinter.displaySuccessfulAdd();
      }
      else{
        textPrinter.displayInvalidTrainNumber();
      }
    }
  }

  public int getDepartureTime(){
    textPrinter.displayDepartureTimeInput();
    boolean validInput = false;
    LocalTime timeInput = null;
    while(!validInput){
      timeInput = inputHandler.getTimeInput(trainStation.getTime());
      if(timeInput != null){
        validInput = true;
      }
      else{
        textPrinter.invalidTimeEntry();
      }
    }
    return getTimeAsInt(timeInput);
  }

  public String getDepartureLine(int departureTime){
    textPrinter.displayLineInput();
    boolean validInput = false;
    String lineInput = null;
    while(!validInput){
      lineInput = inputHandler.getString();
      if(validator.checkAvailableLine(departureTime, lineInput, trainStation.getSortedDepartureList())){
        validInput = true;
      }
      else{
        textPrinter.displayInvalidLine();
      }
    }
    return lineInput;
  }
  public String getDepartureDestination(){
    textPrinter.displayDestinationInput();
    return inputHandler.getString();
  }

  public int getDepartureTrack(int time){
    textPrinter.displayTrackInput();
    boolean validTrack = false;
    boolean validInt = false;
    String trackInput = null;
    while(!validInt || !validTrack){
      trackInput = inputHandler.getString();
      if(validator.checkAvailableTrack(time, Integer.parseInt(trackInput), trainStation.getSortedDepartureList())){
        validTrack = true;
      }
      else{
        textPrinter.displayInvalidTrack();
        validTrack = false;
      }
      if(trackInput.equals("-1")){
        validInt = true;
        validTrack = true;
      }
      else if(validator.canConvertToInt(trackInput) && Integer.parseInt(trackInput) > 0){
        validInt = true;
      }
      else{
        textPrinter.displayInvalidInt();
      }
    }
    return Integer.parseInt(trackInput);
  }

  public int getDepartureDelay(int departureTime) {
    textPrinter.displayDelayInput();
    boolean validInput = false;
    int delayInput = 0;
    LocalTime time;
    while (!validInput) {
      time = inputHandler.getTimeInputDelay(trainStation.getTime());
      if (time == null) {
        textPrinter.invalidDelayEntry();
      }
      else if(addDelay(departureTime, getTimeAsInt(time)) == -1){
          textPrinter.displayInvalidDelay();
        }
      else
        {
        delayInput = getTimeAsInt(time);
        validInput = true;
      }
    }
    return delayInput;
  }

  public int getTrainNumber(){
    textPrinter.displayTrainNumberInput();
    boolean validInput = false;
    String trackInput = null;
    while(!validInput){
      trackInput = inputHandler.getString();
      if(validator.canConvertToInt(trackInput)){
        validInput = true;
      }
      else{
        textPrinter.displayInvalidInt();
      }
    }
    return Integer.parseInt(trackInput);
  }


  public void changeClock(){
    textPrinter.displayEnterTime(trainStation.getTime());
    boolean validInput = false;
    while(!validInput){
      LocalTime time = inputHandler.getTimeInput(trainStation.getTime());
      if(time != null){
        trainStation.changeClock(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Set current time to: " + time.format(formatter));
        validInput = true;
      }
      else{
        textPrinter.invalidTimeEntry();
      }
    }
  }

  public int addDelay(int departureTime, int delayTime){
    int actualDepartureTime;
    int hours = departureTime / 100; // Extract hours (e.g., 13 from 1324)
    int minutes = departureTime % 100; // Extract minutes (e.g., 24 from 1324)

    int delayMinutes = delayTime % 100; // Extract minutes from delayTime
    int delayHours = delayTime / 100; // Extract hours from delayTime

    minutes += delayMinutes; // Add delay to current minutes
    hours += delayHours; // Add delay to current hours

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

}
