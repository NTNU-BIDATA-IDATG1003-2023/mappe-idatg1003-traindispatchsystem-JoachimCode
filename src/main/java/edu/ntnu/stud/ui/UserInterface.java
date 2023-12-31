package edu.ntnu.stud.ui;

import edu.ntnu.stud.datastructures.TrainDeparture;
import edu.ntnu.stud.datastructures.TrainStation;
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
  private void menuSelect() {
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
          searchTrainNumber();
          searching = false;
        }
        case (CommandVariables.DESTINATION) -> {
          Iterator<TrainDeparture> searchedDepartures = searchByDestinationInput();
          displaySearchedDestination(searchedDepartures);
          searching = false;
        }
        default -> textPrinter.displayInvalidCommand();
      }
    }
  }

  /*
  * This is a method to display a train departure
  * from the train number. If the train number is not
  * in the register, it will be null and display an error message.
  * else it will call the displaySearchedNumber method and display the
  * departure.
   */
  private void searchTrainNumber() {
    TrainDeparture searchedDepartureTrack = searchByTrainNumberInput();
    if (searchedDepartureTrack != null) {
      displaySearchedNumber(searchedDepartureTrack);
    } else {
      textPrinter.displayNoMatchingNumber();
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
          editDelay();
          searching = false;
        }
        case (CommandVariables.TRACK) -> {
          editTrack();
          searching = false;
        }
        default -> textPrinter.displayInvalidCommand();
      }
    }
  }

  /**
   * Edits the delay of a train departure.
   * It searches for a train departure and if it is not null
   * it will set the delay of the train-departure as input.
   */
  private void editDelay() {
    TrainDeparture searchedDepartureDelay = searchByTrainNumberInput();
    if (searchedDepartureDelay != null) {
      displaySearchedNumber(searchedDepartureDelay);
      searchedDepartureDelay.setDelay(getValidDepartureDelayInput(
              searchedDepartureDelay.getDepartureTime()));
      textPrinter.displaySuccessfulEdit();
      textPrinter.enterCommand();
    } else {
      textPrinter.displayNoMatchingNumber();
    }
  }

  /**
   * Edits the track of a train departure.
   * It searches for a train departure and if it is not null
   * it will set the track of the train-departure as input.
   */
  private void editTrack() {
    TrainDeparture searchedDepartureTrack = searchByTrainNumberInput();
    if (searchedDepartureTrack != null) {
      displaySearchedNumber(searchedDepartureTrack);
      searchedDepartureTrack.setTrack(getValidTrackInput(
              searchedDepartureTrack.getDepartureTime()));
      textPrinter.displaySuccessfulEdit();
      textPrinter.enterCommand();
    } else {
      textPrinter.displayNoMatchingNumber();
    }

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
    textPrinter.displayTrainDeparture(trainDeparture);
  }

  /**
   * It gets an iterator of train departures with the searched destination.
   * The method gets an input and calls the getTrainFromDestination
   * method in the trainStation class.
   *
   * @return Iterator of TrainDeparture if the destination is in the hashmap, else null.
   */
  public Iterator<TrainDeparture> searchByDestinationInput() {
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
  private TrainDeparture searchByTrainNumberInput() {
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
  private void displayTrainDepartures(Iterator<TrainDeparture> trainDepartures,
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
    int departureTime = getValidDepartureTimeInput();
    String departureLine = getValidDepartureLineInput(departureTime);
    String departureDestination = getDepartureDestinationInput();
    int departureTrack = getValidTrackInput(departureTime);
    int delay = getValidDepartureDelayInput(departureTime);
    boolean successfullyAdded = false;
    while (!successfullyAdded) {
      int trainNumber = getValidTrainNumberInput();
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
  private int getValidDepartureTimeInput() {
    textPrinter.displayDepartureTimeInput();
    boolean validInput = false;
    LocalTime timeInput = null;
    while (!validInput) {
      timeInput = inputHandler.getTimeInput();
      if (timeInput != null && timeInput.isAfter(trainStation.getTime())) {
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
  private String getValidDepartureLineInput(int departureTime) {
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
  private String getDepartureDestinationInput() {
    textPrinter.displayDestinationInput();
    return inputHandler.getString();
  }

  /**
   * Returns the track the user wants to set
   * It runs until the user enters a valid track
   * It checks if the user enters -1, which means that the track is unknown,
   * and makes it a valid input.
   * Then it checks if it is an valid int, if not display an error message.
   * If it is an valid int, it checks if the track is busy on that
   * timeslot, if not, it sends an error message,
   *
   * @param time is the departure time of the train departure.
   * @return the track of the train departure as an int.
   */
  private int getValidTrackInput(int time) {
    textPrinter.displayTrackInput();
    int trackNumber = -1;
    boolean validTrackNumber = false;
    while (!validTrackNumber) {
      String trackInput = inputHandler.getString();
      if (trackInput.equals("-1")) {
        validTrackNumber = true;
      } else if (validator.canConvertToInt(trackInput)) {
        int parsedTrackInput = Integer.parseInt(trackInput);
        if (parsedTrackInput > 0 && isValidTrack(time, parsedTrackInput)) {
          trackNumber = parsedTrackInput;
          validTrackNumber = true;
        }
      } else {
        textPrinter.displayInvalidInt();
      }
    }
    return trackNumber;
  }

  /**
   * This method checks if the track is available at the given time,
   * because there can only be one train at a track at a time.
   * If not it will display an error message.
   *
   * @param time is the time that is being checked.
   * @param trackNumber is the track that is being checked.
   * @return boolean if the track is available at the given time.
   */
  private boolean isValidTrack(int time, int trackNumber) {
    boolean validTrack = false;
    if (validator.checkAvailableTrack(time, trackNumber, trainStation.getSortedDepartureList())) {
      validTrack = true;
    } else {
      textPrinter.displayInvalidTrack();
    }
    return validTrack;
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

  private int getValidDepartureDelayInput(int departureTime) {
    textPrinter.displayDelayInput();
    boolean validInput = false;
    int delayInput = 0;
    LocalTime time;
    while (!validInput) {
      time = inputHandler.getTimeInput();
      if (time == null) {
        textPrinter.invalidDelayEntry();
      } else if (validator.addDelay(departureTime, getTimeAsInt(time)) == -1) {
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
  private int getValidTrainNumberInput() {
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
   *
   * @param trainStation is the trainStation that the userInterface will use.
   * @throws IllegalArgumentException if the trainStation is null.
   */
  private void setTrainStation(TrainStation trainStation) {
    if (trainStation == null) {
      throw new IllegalArgumentException("TrainStation cannot be null");
    } else {
      this.trainStation = trainStation;
    }
  }

  /**
   * This method ends the program by setting running to false and displaying an end message.
   */
  private void endProgram() {
    running = false;
    textPrinter.displayEndMessage();
  }
}
