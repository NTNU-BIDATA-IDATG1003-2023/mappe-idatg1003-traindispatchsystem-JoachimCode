package edu.ntnu.stud.gui;

import edu.ntnu.stud.dataStructures.TrainDeparture;
import edu.ntnu.stud.dataStructures.TrainStation;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * The {@code UserInterface} class handles the user interface and commandos from the user.
 * The {@code UserInterface}s have a TextPrinter, TrainStation,
 * InputHandler, Validator and a boolean running.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 *     UserInterface userInterface = new UserInterface(PARAMETERS);
 * </pre></blockquote>
 *
 * <p>The class {@code UserInterface} includes methods for initializing menus,
 * methods for getting the correct input and methods to change fields in the TrainStation.
 *
 * @author Joachim Duong
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserInterface {
  /*
   * This variable represents the textPrinter,
   * which is the class that handles all the output to the user.
   */
  private final TextPrinter textPrinter;
  /*
   * This variable represents the trainStation, and is the object of the program's station.
   */
  private TrainStation trainStation;
  /*
   * This variable represents the inputHandler,
   * which is the class that handles all the input from the user.
   */
  private final InputHandler inputHandler;
  /*
   * This variable represents the validator,
   *  which is the class that handles validation of data.
   */
  private final Validator validator;
  /*
   * This variable represents the boolean running,
   * which is used to determine if the program should keep running.
   */
  private boolean running;

  /**
   * This is the constructor for the UserInterface class.
   * It initializes the textPrinter, trainStation, inputHandler, validator and running.
   *
   * @param trainStation is the trainStation that the userInterface will use.
   */
  public UserInterface(TrainStation trainStation) {
    textPrinter = new TextPrinter();
    setTrainStation(trainStation);
    inputHandler = new InputHandler();
    validator = new Validator();
    running = true;

  }

  /**
   * This method initializes the program. It will display the welcome message, the train departures,
   * the help message and then call the menuSelect method.
   */
  public void initialize() {
    textPrinter.displayWelcome();
    changeClock();
    textPrinter.displayTrainDepartures(trainStation.getSortedDepartureList(),
            trainStation.getTime());
    textPrinter.displayHelp();
    menuSelect();
  }

  /**
   * This method is the main menu of the program. It gets the user input as a String
   * and uses a switch statement to determine what to do. If an invalid command is entered,
   * it will display an error message and ask for a new command. It uses the constant variables
   * in the CommandVariables class to determine what to do.
   */
  public void menuSelect() {
    while (running) {
      String userCommand = inputHandler.getString();
      switch (userCommand) {
        case CommandVariables.HELP -> textPrinter.displayCommands();
        case CommandVariables.DISPLAY ->
                displayTrainDepartures(trainStation.getSortedDepartureList(),
                        trainStation.getTime());
        case CommandVariables.ADD -> addTrain();
        case CommandVariables.EDIT -> editMenu();
        case CommandVariables.SET_TIME -> changeClock();
        case CommandVariables.SEARCH -> searchMenu();
        case CommandVariables.QUIT -> endProgram();
        default -> textPrinter.displayInvalidCommand();
      }
    }
  }

  /**
   * This method is to display the search menu, if the user wishes to
   * search for a train departure/departures. There are two options,
   * search by destination or search by train number.
   * It will keep running until the user enters a valid command.
   * If it searches by train number it will call the searchByNumber method,
   * which ensures it is a valid train number. It will then display the searched train departure.
   * If it searches by destination it will call the searchByDestination method,
   * which ensures it is a valid destination. It will then display the searched train departures.
   * If the user enters an invalid command, it will display an error message.
   */
  private void searchMenu() {
    boolean searching = true;
    while (searching) {
      textPrinter.displaySearchCommands();
      String userCommand = inputHandler.getString();
      switch (userCommand) {
        case (CommandVariables.TRAIN_NUMBER) -> {
          TrainDeparture searchedDeparture = searchByNumber();
          displaySearchedNumber(searchedDeparture);
          searching = false;
        }
        case (CommandVariables.DESTINATION) -> {
          Iterator<TrainDeparture> searchedDepartures = searchByDestination();
          displaySearchedDestination(searchedDepartures);
          searching = false;
        }
        default -> textPrinter.displayInvalidCommand();
      }
    }
  }

  /**
   * This method is to display the edit menu, if the user wishes to
   * edit a train departure. There are two options,
   * edit delay or edit track.
   * It will keep running until the user enters a valid command.
   * If it edits delay it will call the editDelay method,
   * which ensures it is a valid delay. It will then call the editDelay which sets the
   * delay for the trainDeparture and display a successful edit message.
   * If it edits track it will call the editTrack method,
   * which ensures it is a valid track. It will then call the editTrack which sets the
   * track for the trainDeparture and display a successful edit message.
   * If the user enters an invalid command, it will display
   * an error message, and ask for a new command.
   */
  private void editMenu() {
    boolean searching = true;
    while (searching) {
      textPrinter.displayEditCommands();
      String userCommand = inputHandler.getString();
      switch (userCommand) {
        case (CommandVariables.DELAY) -> {
          TrainDeparture searchedDepartureDelay = searchByNumber();
          displaySearchedNumber(searchedDepartureDelay);
          editDelay(searchedDepartureDelay);
          searching = false;
        }
        case (CommandVariables.TRACK) -> {
          TrainDeparture searchedDepartureTrack = searchByNumber();
          displaySearchedNumber(searchedDepartureTrack);
          editTrack(searchedDepartureTrack);
          searching = false;
        }
        default -> textPrinter.displayInvalidCommand();
      }
    }
  }

  /**
   * Edits the delay of a train departure.
   * It uses the getDepartureDelay method to get the new delay,
   * and then sets the delay of the train departure.
   * In the end it displays a successful edit message.
   *
   * @param trainDeparture is the train departure that is being edited.
   */
  public void editDelay(TrainDeparture trainDeparture) {
    trainDeparture.setDelay(getDepartureDelay(trainDeparture.getDepartureTime()));
    textPrinter.displaySuccessfulEdit();
    textPrinter.enterCommand();
  }

  /**
   * Edits the track of a train departure.
   * It uses the getDepartureTrack method to get the track,
   * and then sets the track of the train departure.
   * In the end it displays a successful edit message.
   *
   * @param trainDeparture is the train departure that is being edited.
   */
  public void editTrack(TrainDeparture trainDeparture) {
    trainDeparture.setTrack(getValidDepartureTime(trainDeparture.getDepartureTime()));
    textPrinter.displaySuccessfulEdit();
    textPrinter.enterCommand();
  }

  /**
   * Displays the searched destination.
   * it uses the iterator to display all the train departures with the searched destination.
   * If it receives an empty iterator, it will display a message
   * saying that there are no matching destinations.
   *
   * @param trainDepartures is the iterator of train departures that is being displayed.
   */
  public void displaySearchedDestination(Iterator<TrainDeparture> trainDepartures) {
    if (trainDepartures.hasNext()) {
      while (trainDepartures.hasNext()) {
        TrainDeparture currentTrainDeparture = trainDepartures.next();
        textPrinter.displayTrainDeparture(currentTrainDeparture);
      }
      textPrinter.enterCommand();
    } else {
      textPrinter.displayNoMatchingDestination();
    }
  }

  /**
   * Displays the train departure matching with searched train number.
   * If it receives a null train departure,
   * it will display a message saying that there are no matching numbers,
   * else it will display the train departure.
   *
   * @param trainDeparture is the train departure that is being displayed.
   */
  public void displaySearchedNumber(TrainDeparture trainDeparture) {
    if (trainDeparture == null) {
      textPrinter.displayNoMatchingNumber();
    } else {
      textPrinter.displayTrainDeparture(trainDeparture);
    }
  }

  /**
   * It gets an iterator of train departures with the searched destination.
   * The method gets an input and calls the getTrainFromDestination
   * method in the trainStation class.
   *
   * @return Iterator of TrainDeparture if the destination is in the hashmap, else null.
   */
  public Iterator<TrainDeparture> searchByDestination() {
    textPrinter.displayRequestDestination();
    String userInput = inputHandler.getString();
    return trainStation.getTrainFromDestination(userInput);
  }

  /**
   * It gets a train departure with the searched train number.
   * The method gets an input and calls the getTrainFromTrainNumber
   * method in the trainStation class.
   * It will keep running until the user enters a valid train number.
   * If there is no matching train number, it will return null.
   *
   * @return TrainDeparture if the train number is in the hashmap, else null.
   */
  public TrainDeparture searchByNumber() {
    boolean validInput = false;
    TrainDeparture trainDeparture = null;
    textPrinter.displayTrainNumberInput();
    while (!validInput) {
      String userInput = inputHandler.getString();
      if (validator.canConvertToInt(userInput)) {
        trainDeparture = trainStation.getTrainFromTrainNumber(Integer.parseInt(userInput));
        validInput = true;
      } else {
        textPrinter.displayInvalidInt();
      }
    }
    return trainDeparture;
  }

  /**
   * Displays the train departures.
   * It uses the displayTrainDepartures method in the textPrinter class.
   *
   * @param trainDepartures is the iterator of train departures that is being displayed.
   * @param currentTime is the current time of the program.
   */
  public void displayTrainDepartures(Iterator<TrainDeparture> trainDepartures,
                                     LocalTime currentTime) {
    textPrinter.displayTrainDepartures(trainDepartures, currentTime);
  }

  /**
   * Adds a train departure to the hashmap.
   * It uses the getDepartureTime, getDepartureLine, getDepartureDestination, getDepartureTrack,
   * getDepartureDelay and getTrainNumber methods to get the information of the train departure.
   * It will keep running until the user enters a valid train number.
   * If the train number is already in the hashmap, it will display an error message.
   * If the train number is not in the hashmap, it will add the
   * train departure and display a successful add message.
   */
  private void addTrain() {
    int departureTime = getValidDepartureInput();
    String departureLine = getValidDepartureLine(departureTime);
    String departureDestination = getDepartureDestination();
    int departureTrack = getValidDepartureTime(departureTime);
    int delay = getDepartureDelay(departureTime);
    boolean successfullyAdded = false;
    while (!successfullyAdded) {
      int trainNumber = getValidTrainNumber();
      if (trainStation.addTrain(new TrainDeparture(departureTime, departureLine,
               trainNumber, departureDestination, departureTrack, delay))) {
        successfullyAdded = true;
        textPrinter.displaySuccessfulAdd();
      } else {
        textPrinter.displayInvalidTrainNumber();
      }
    }
  }

  /**
   * Gets the departure time of the train departure from user input.
   * It runs until the user enters a valid time.
   * It checks if the time is in the correct format
   * and that it is after the current time of the train station.
   * If it fails it will display an error message,
   * and if it succeeds it will return the time as an int.
   *
   * @return the departure time of the train departure as an int.
   */
  private int getValidDepartureInput() {
    textPrinter.displayDepartureTimeInput();
    boolean validInput = false;
    LocalTime timeInput = null;
    while (!validInput) {
      timeInput = inputHandler.getTimeInput();
      if (timeInput != null && timeInput.isAfter(trainStation.getTime())){
        validInput = true;
      } else {
        textPrinter.invalidTimeEntry();
      }
    }
    return getTimeAsInt(timeInput);
  }

  /**
   * Gets the line of the train departure from user input as a String.
   * It runs until the user enters a valid line.
   * It checks if the line is available at the departure time, and if it is not,
   * it will display an error message.
   *
   * @param departureTime is the departure time of the train departure.
   * @return the line of the train departure as a string.
   */
  private String getValidDepartureLine(int departureTime) {
    textPrinter.displayLineInput();
    boolean validInput = false;
    String lineInput = null;
    while (!validInput) {
      lineInput = inputHandler.getString();
      if (validator.checkAvailableLine(departureTime, lineInput,
              trainStation.getSortedDepartureList())) {
        validInput = true;
      } else {
        textPrinter.displayInvalidLine();
      }
    }
    return lineInput;
  }

  /**
   * Gets the destination of the train departure from user input.
   *
   * @return the destination of the train departure as a string.
   */
  private String getDepartureDestination() {
    textPrinter.displayDestinationInput();
    return inputHandler.getString();
  }

  /**
   * Gets the track of the train departure from user input as a String.
   * It runs until the user enters a valid track
   * It first checks if the track is available, and if it is not, it will display an error message
   * It then checks if the user enters -1, which means that the track is unknown,
   * and makes it a valid input.
   * In the end it checks if the user enters a positive number,
   * and if it is, it will return the track as an int.
   *
   * @param time is the departure time of the train departure.
   * @return the track of the train departure as an int.
   */
  private int getValidDepartureTime(int time) {
    textPrinter.displayTrackInput();
    boolean validTrack = false;
    boolean validInt = false;
    String trackInput = null;
    while (!validInt || !validTrack) {
      trackInput = inputHandler.getString();
      if (validator.checkAvailableTrack(time, Integer.parseInt(trackInput),
              trainStation.getSortedDepartureList())) {
        validTrack = true;
      } else {
        textPrinter.displayInvalidTrack();
        validTrack = false;
      }
      if (trackInput.equals("-1")) {
        validInt = true;
        validTrack = true;
      } else if (validator.canConvertToInt(trackInput) && Integer.parseInt(trackInput) > 0) {
        validInt = true;
      } else {
        textPrinter.displayInvalidInt();
      }
    }
    return Integer.parseInt(trackInput);
  }

  /**
   * Gets the delay of the train departure from user input.
   * It runs until the user enters a valid delay.
   * It uses the input handler to get a delay in LocalTime that is in the correct format.
   * If it is correct, will not be returned as null and go to the next check.
   * It then uses the addDelay method to add the delay to the departure time and check
   * if it goes to the next day, which is an invalid input.
   * else it will return the delay as an int.
   *
   * @param departureTime is the departure time of the train departure.
   * @return the delay of the train departure as an int.
   */
  private int getDepartureDelay(int departureTime) {
    textPrinter.displayDelayInput();
    boolean validInput = false;
    int delayInput = 0;
    LocalTime time;
    while (!validInput) {
      time = inputHandler.getTimeInput();
      if (time == null) {
        textPrinter.invalidDelayEntry();
      } else if (addDelay(departureTime, getTimeAsInt(time)) == -1) {
        textPrinter.displayInvalidDelay();
      } else {
        delayInput = getTimeAsInt(time);
        validInput = true;
      }
    }
    return delayInput;
  }

  /**
   * Gets the train number of the train departure from user input as a String.
   * It runs until the user enters a valid train number and sets validInput to true.
   * It checks if the String can be converted to an int and if not it will display an error message.
   *
   * @return the train number of the train departure as an int.
   */
  private int getValidTrainNumber() {
    textPrinter.displayTrainNumberInput();
    boolean validInput = false;
    String trackInput = null;
    while (!validInput) {
      trackInput = inputHandler.getString();
      if (validator.canConvertToInt(trackInput)) {
        validInput = true;
      } else {
        textPrinter.displayInvalidInt();
      }
    }
    return Integer.parseInt(trackInput);
  }

  /**
   * It displays a message for the user to enter a new time.
   * It uses the getTimeInput method in the inputHandler class to get the time in a
   * correct format. It will keep running until the user enters a valid time.
   * If the time is valid, it will call the changeClock method in the trainStation class,
   * which will change the clock of the train station.
   * In the end it will format the time and display a message saying that the time has been changed.
   * if the time is invalid, it will display an error message and ask for a new time.
   */
  private void changeClock() {
    textPrinter.displayEnterTime(trainStation.getTime());
    boolean validInput = false;
    while (!validInput) {
      LocalTime time = inputHandler.getTimeInput();
      if (time != null && time.isAfter(trainStation.getTime())) {
        trainStation.changeClock(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Set current time to: " + time.format(formatter));
        validInput = true;
      } else {
        textPrinter.invalidTimeEntry();
      }
    }
  }

  /**
   * This method adds a delay to the departure time. It is used to check
   * what the time is after the delay and if it reaches the next day.
   * It first extracts the hours and minutes from the departure time and delay time.
   * It then adds the delay to the minutes and hours.
   * It then checks if the minutes exceed 60, and if it does, it will add correctly to the hours
   * and get the remaining minutes after rollover.
   * It then checks if the hours exceed 24, and if it does, it will return -1 because it
   * should not be able to go to the next day.
   * In the end it combines the hours and minutes to get the new time in the format HHMM
   * and returns the new time as an int.
   *
   * @param departureTime is the departure time of the train departure.
   * @param delayTime is the delay of the train departure.
   * @return the new time as an int and -1 if it exceeds 24:00.
   */
  private int addDelay(int departureTime, int delayTime) {
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
    if (hours < 24) {
      actualDepartureTime = hours * 100 + minutes;
    } else {
      actualDepartureTime = -1;
    }

    // Combine hours and minutes to get the new time in the format HHMM
    return actualDepartureTime;
  }

  /**
   * This method converts a LocalTime to an int.
   * It gets the hours and minutes from the LocalTime and combines them to get the time as an int.
   *
   * @param time is the LocalTime that is being converted to an int.
   * @return the time as an int.
   */
  private int getTimeAsInt(LocalTime time) {
    return time.getHour() * 100 + time.getMinute();
  }

  /**
   * This method sets the trainStation of the userInterface.
   * @throws IllegalArgumentException if the trainStation is null.
   * @param trainStation is the trainStation that the userInterface will use.
   */
  private void setTrainStation(TrainStation trainStation) {
    if(trainStation == null){
      throw new IllegalArgumentException("TrainStation cannot be null");
    } else {
      this.trainStation = trainStation;
    }
  }

  /**
   * This method ends the program by setting running to false and displaying an end message.
   */
  private void endProgram(){
    running = false;
    textPrinter.displayEndMessage();
  }
}
