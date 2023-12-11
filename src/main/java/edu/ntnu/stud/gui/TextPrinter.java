package edu.ntnu.stud.gui;

import edu.ntnu.stud.datastructures.TrainDeparture;
import java.time.LocalTime;
import java.util.Iterator;

/**
 * The {@code TextPrinter} class handles printing all the messages to the user.
 *
 * <p>The class {@code TextPrinter} includes methods for printing instructions and errors.
 *
 * @author Joachim Duong
 * @version 1.0.0
 * @since 1.0.0
 */
public class TextPrinter {

  /**
   * This method prints the welcome message to the user.
   */
  public void displayWelcome() {
    System.out.println("Welcome to the train dispatch system!");
  }

  /**
   * This method prints the help message to the user.
   */
  public void displayHelp() {
    System.out.println("Type /h for help");
  }

  /**
   * This method prints all the commands to the user. It uses formatted print statements
   * with the constant variables from the CommandVariables class.
   */
  public void displayCommands() {
    printCommands();
    System.out.printf("%s - Displays all the commands%n", CommandVariables.HELP);
    System.out.printf("%s - Displays all the trains departures%n", CommandVariables.DISPLAY);
    System.out.printf("%s - Add a new train departure%n", CommandVariables.ADD);
    System.out.printf("%s - assign track/delay to a train departure%n", CommandVariables.EDIT);
    System.out.printf("%s - Search for a train departure%n", CommandVariables.SEARCH);
    System.out.printf("%s - Change the time%n", CommandVariables.SET_TIME);
    System.out.printf("%s - Quit the program%n", CommandVariables.QUIT);
  }

  /**
   * This method prints all the search commands to the user. It uses formatted print statements
   * with the constant variables from the CommandVariables class.
   */
  public void displaySearchCommands() {
    System.out.println("Do you want to search by destination or train number?");
    printCommands();
    System.out.printf("%s - Search by destination%n", CommandVariables.DESTINATION);
    System.out.printf("%s - Search by train number%n", CommandVariables.TRAIN_NUMBER);
  }

  /**
   * This method prints all the edit commands to the user. It uses formatted print statements
   * with the constant variables from the CommandVariables class.
   */
  public void displayEditCommands() {
    System.out.println("Do you want to assign a track or a delay?");
    printCommands();
    System.out.printf("%s - Assign a track%n", CommandVariables.TRACK);
    System.out.printf("%s - Assign a delay%n", CommandVariables.DELAY);
  }

  /**
   * This method prints all the add commands to the user.
   */
  public void enterCommand() {
    System.out.println("Enter next command: ");
  }

  /**
   * Prints all the train departures.
   * It displays the current time first and
   * then goes over every train departure by using the iterator to print
   * the departure information.
   *
   * @param trainDepartures is the iterator of all the train departures.
   * @param currentTime is the current time of the station.
   */
  public void displayTrainDepartures(Iterator<TrainDeparture> trainDepartures,
                                     LocalTime currentTime) {
    System.out.println("Train departure table");
    System.out.println("current time: " + currentTime);
    System.out.println("Train departures:");
    if (!trainDepartures.hasNext()){
      System.out.println("No train departures");
    }
    while (trainDepartures.hasNext()) {
      TrainDeparture currentTrainDeparture = trainDepartures.next();
      System.out.println(getDepartureInformation(currentTrainDeparture));
    }
  }

  /**
   * Prints the train departure.
   * It uses the getDepartureInformation method to get all the information and
   * formats it in a readable way. It then prints the information.
   *
   * @param trainDeparture is the train departure that is being printed.
   */
  public void displayTrainDeparture(TrainDeparture trainDeparture) {
    System.out.println(getDepartureInformation(trainDeparture));
  }

  /**
   * This method gets all the information of the train departure and formats it in a readable way.
   * It uses the StringBuilder class to append all the information to a string.
   * It checks if the train departure has a delay, and if it does,
   * it will use the strikethroughString method
   * to strike through the departure time and display the real departure time.
   * If there is no delay it will just display the departure time.
   * It also checks if the track is unknown (-1), and if it is,
   * it will display a dash instead of the track number.
   * In the end it formats it to a String and returns it.
   *
   * @param trainDeparture is the train departure that is being printed.
   * @return a string with all the information of the train departure.
   */
  private String getDepartureInformation(TrainDeparture trainDeparture) {
    StringBuilder departureInfo = new StringBuilder("DepartureTime: ");
    if (trainDeparture.getDelay() == 0) {
      departureInfo.append(formatClock(trainDeparture.getDepartureTime()));
    } else {
      departureInfo.append(strikethroughString(formatClock(trainDeparture.getDepartureTime())));
      departureInfo.append(" ");
      departureInfo.append(formatClock(trainDeparture.getDepartureTime()
              + trainDeparture.getDelay()));
    }
    String seperator = "  |  ";
    departureInfo.append(seperator);
    departureInfo.append("Line: ");
    departureInfo.append(trainDeparture.getLine());
    departureInfo.append(seperator);
    departureInfo.append("Destination: ");
    departureInfo.append(trainDeparture.getDestination());
    departureInfo.append(seperator);
    departureInfo.append("Track: ");
    if (trainDeparture.getTrack() == -1) {
      departureInfo.append("-");
    } else {
      departureInfo.append(trainDeparture.getTrack());
    }
    departureInfo.append(seperator);
    departureInfo.append("Delay: ");
    departureInfo.append(trainDeparture.getDelay());
    departureInfo.append(seperator);
    departureInfo.append("Train number: ");
    departureInfo.append(trainDeparture.getTrainNumber());
    return departureInfo.toString();
  }

  /**
   * This method formats the time to a readable format.
   * It uses the String.format method to format the time to HH:MM.
   *
   * @param time is the time that is being formatted.
   * @return a string with the formatted time.
   */
  public String formatClock(int time) {
    int hours = time / 100;
    int minutes = time % 100;
    return String.format("%02d:%02d", hours, minutes);
  }

  /**
   * This method prints the error message to the user.
   */
  public void displayInvalidCommand() {
    System.err.println("Invalid command, type /h for help");
  }


  /**
   * Format a string with strikethrough effect.
   * It uses the StringBuilder class to append the strikethrough
   * character to every character in the string.
   *
   * @param text is the string that is being formatted.
   * @return a string with the strikethrough effect.
   */
  public static String strikethroughString(String text) {
    StringBuilder sb = new StringBuilder();
    for (char c : text.toCharArray()) {
      sb.append(c).append('̶'); // Using an ASCII character for strikethrough effect
    }
    return sb.toString();
  }

  /**
   * This method prints a instruction for how to enter the time.
   *
   * @param currentTime is the current time of the station.
   */
  public void displayEnterTime(LocalTime currentTime) {
    System.out.println("What time to you want to set?");
    System.out.println("Current time is: " + currentTime);
    System.out.println("Use the format HH:MM");
    System.out.println("Enter time: ");
  }

  /**
   * This method prints an instruction for how to enter a departure time.
   */
  public void displayDepartureTimeInput() {
    System.out.println("What time is the train departing?");
    System.out.println("Use the format HH:MM");
    System.out.println("Enter time: ");
  }

  /**
   * This method prints an instruction for how to enter a line.
   */
  public void displayLineInput() {
    System.out.println("What line is the train departure?");
    System.out.println("It cannot share a line with a train"
            + " departure with the same departure time");
    System.out.println("Enter line: ");
  }

  /**
   * This method prints an instruction for how to enter a destination.
   */
  public void displayDestinationInput() {
    System.out.println("What is the destination of the train?");
    System.out.println("Enter destination: ");
  }

  /**
   * This method prints an instruction for how to enter a track.
   */
  public void displayTrackInput() {
    System.out.println("What track is the train departing from?");
    System.out.println("Type -1 if the track is unknown");
    System.out.println("The track must strictly be a number");
    System.out.println("Enter track: ");
  }

  /**
   * This method prints an error message for when the line is already in use for the departure time.
   */
  public void displayInvalidLine() {
    System.err.println("Invalid line, this line is already in use for the departure time");
    printTryAgain();
  }

  /**
   * This method prints an instruction for how to enter a delay.
   */
  public void displayDelayInput() {
    System.out.println("Is there a delay to the train?");
    System.out.println("Type 00:00 if there is no delay");
    System.out.println("If there is enter the delay in format HH:MM");
    System.out.println("Enter delay: ");
  }

  /**
   * This method prints an error message for when the track
   * is already in use for the departure time.
   */
  public void displayInvalidTrack() {
    System.err.println("Invalid track, this track is already in use for the departure time");
    System.out.println("Try again!");
  }

  /**
   * This method prints an instruction for how to enter a train number.
   */
  public void displayTrainNumberInput() {
    System.out.println("What is the train number?");
    System.out.println("Enter train number: ");
  }

  /**
   * This method prints an instruction for how to enter a search destination.
   */
  public void displayRequestDestination() {
    System.out.println("What destination do you want to search for?");
    System.out.println("Enter destination: ");
  }

  /**
   * This method prints an error message for when there is an invalid int.
   */
  public void displayInvalidInt() {
    System.err.println("Invalid input, please enter a positive number");
    printTryAgain();
  }

  /**
   * This method prints an error message for when there is an invalid delay.
   */
  public void displayInvalidDelay() {
    System.err.println("Invalid input, this delay with make "
            + " train depart outside this applications scope (The next day)");
    printTryAgain();
  }

  /**
   * This method prints an error message for when there is an invalid train number.
   */
  public void displayInvalidTrainNumber() {
    System.err.println("Invalid train number, this train number is already in use");
    printTryAgain();
  }

  /**
   * This method prints an error message for when there is none of the destination searched
   * in the register.
   */
  public void displayNoMatchingDestination() {
    System.err.println("No matching destination, exiting search");
    System.out.println("Type /h for more commands");
  }

  /**
   * This method prints an error message for when there is none of the train number searched
   * in the register.
   */
  public void displayNoMatchingNumber() {
    System.err.println("No matching train number, exiting search");
    System.out.println("Type /h for more commands");
  }

  /**
   * Prints a feedback that the train departure was successfully added.
   */
  public void displaySuccessfulAdd() {
    System.out.println("Successfully added train departure");
  }

  /**
   * Prints an error message that the time entry was invalid.
   */
  public void invalidTimeEntry() {
    System.err.println("Invalid time entry, make sure you use the format"
            + " HH:MM and that the time is after the current time ");
    printTryAgain();
  }

  /**
   * Prints a error message that the delay entry was invalid.
   */
  public void invalidDelayEntry() {
    System.err.println("Invalid delay entry, make sure you use the format HH:MM");
    printTryAgain();
  }

  /**
   * Prints a feedback that the train departure was successfully edited.
   */
  public void displaySuccessfulEdit() {
    System.out.println("Successfully edited train departure");
  }
  /*
  * This is a private method that prints the commands message, because
  * it is required many times.
  */
  private void printCommands(){
    System.out.println("Commands:");
  }
  /*
  * This is a private method that prints the try again message, because
  * it is required many times.
   */
  private void printTryAgain(){
    System.out.println("Try again!");
  }

  /**
   * Prints a feedback that the train departure was successfully quit .
   */
  public void displayEndMessage() {
    System.out.println("Thank you for using the train dispatch system!");
  }
}
