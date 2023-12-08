package edu.ntnu.stud.gui;

import edu.ntnu.stud.TrainDeparture;

import java.time.LocalTime;
import java.util.Iterator;

/**
 * This class is to print text to the end user.
 * It contains method that will display set text messages.
 */

public class TextPrinter {
  public void displayWelcome(){
    System.out.println("Welcome to the train dispatch system!");
  }
  public void displayHelp(){
    System.out.println("Type /h for help");
  }

  public void displayCommands(){
    System.out.println("Commands:");
    System.out.printf("%s - Displays all the commands%n", CommandVariables.HELP);
    System.out.printf("%s - Displays all the trains departures%n", CommandVariables.DISPLAY);
    System.out.printf("%s - Add a new train departure%n", CommandVariables.ADD);
    System.out.printf("%s - assign track/delay to a train departure%n", CommandVariables.EDIT);
    System.out.printf("%s - Search for a train departure%n", CommandVariables.SEARCH);
    System.out.printf("%s - Change the time%n", CommandVariables.SET_TIME);
  }

  public void displaySearchCommands(){
    System.out.println("Do you want to search by destination or train number?");
    System.out.println("Commands:");
    System.out.printf("%s - Search by destination%n", CommandVariables.DESTINATION);
    System.out.printf("%s - Search by train number%n", CommandVariables.TRAINNUMBER);
  }

  public void displayEditCommands(){
    System.out.println("Do you want to assign a track or a delay?");
    System.out.println("Commands:");
    System.out.printf("%s - Assign a track%n", CommandVariables.TRACK);
    System.out.printf("%s - Assign a delay%n", CommandVariables.DELAY);
  }

  public void enterCommand(){
    System.out.println("Enter next command: ");
  }

  public void displayTrainDepartures(Iterator<TrainDeparture> trainDepartures, LocalTime currentTime){
    System.out.println("Train departure table");
    System.out.println("current time: " + currentTime);
    System.out.println("Train departures:");
    while(trainDepartures.hasNext()){
      TrainDeparture currentTrainDeparture = trainDepartures.next();
      System.out.println(getDepartureInformation(currentTrainDeparture));
    }
  }

  public void displayTraindeparture(TrainDeparture trainDeparture){
    System.out.println(getDepartureInformation(trainDeparture));
  }

  private String getDepartureInformation(TrainDeparture trainDeparture){
    StringBuilder departureInfo = new StringBuilder("DepartureTime: ");
    if(trainDeparture.getDelay() == 0) {
      departureInfo.append(formatClock(trainDeparture.getDepartureTime()));
    }
    else{
      departureInfo.append(strikethroughString(formatClock(trainDeparture.getDepartureTime())));
      departureInfo.append(" ");
      departureInfo.append(formatClock(trainDeparture.getDepartureTime() + trainDeparture.getDelay()));
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
    if(trainDeparture.getTrack() == -1){
      departureInfo.append("TBA");
    }
    else{
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

  public String formatClock(int time){
    int hours = time / 100;
    int minutes = time % 100;
    return String.format("%02d:%02d", hours, minutes);
  }

  public void displayInvalidCommand(){
    System.err.println("Invalid command, type /h for help");
  }


  public static String strikethroughString(String text) {
    StringBuilder sb = new StringBuilder();
    for (char c : text.toCharArray()) {
      sb.append(c).append('\u0336'); // Using an ASCII character for strikethrough effect
    }
    return sb.toString();
  }

  public void displayEnterTime(LocalTime currentTime){
    System.out.println("What time to you want to set?");
    System.out.println("Current time is: " + currentTime);
    System.out.println("Use the format HH:MM");
    System.out.println("Enter time: ");
  }

  public void displayDepartureTimeInput(){
    System.out.println("What time is the train departing?");
    System.out.println("Use the format HH:MM");
    System.out.println("Enter time: ");
  }

  public void displayLineInput(){
    System.out.println("What line is the train departure?");
    System.out.println("It cannot share a line with a train departure with the same departure time");
    System.out.println("Enter line: ");
  }

  public void displayDestinationInput(){
    System.out.println("What is the destination of the train?");
    System.out.println("Enter destination: ");
  }

  public void displayEnterTrainNumber(){
    System.out.println("What is the train number?");
    System.out.println("Enter train number: ");
  }

  public void displayTrackInput(){
    System.out.println("What track is the train departing from?");
    System.out.println("Type -1 if the track is unknown");
    System.out.println("The track must strictly be a number");
    System.out.println("Enter track: ");
  }

  public void displayInvalidLine(){
    System.err.println("Invalid line, this line is already in use for the departure time");
    System.out.println("Try again!");
  }
  public void displayDelayInput(){
    System.out.println("Is there a delay to the train?");
    System.out.println("Type 0 if there is no delay");
    System.out.println("If there is enter the delay in minutes");
    System.out.println("Enter delay: ");
  }
  public void displayInvalidTrack(){
    System.err.println("Invalid track, this track is already in use for the departure time");
    System.out.println("Try again!");
  }

  public void displayTrainNumberInput(){
    System.out.println("What is the train number?");
    System.out.println("Enter train number: ");
  }

  public void displayRequestDestination(){
    System.out.println("What destination do you want to search for?");
    System.out.println("Enter destination: ");
  }
  public void displayInvalidInt(){
    System.err.println("Invalid input, please enter a positive number");
    System.out.println("Try again!");
  }

  public void displayInvalidDelay(){
    System.err.println("Invalid input, this delay with make the train depart outside this applications scope (The next day)");
    System.out.println("Try again!");
  }

  public void displayInvalidTrainNumber(){
    System.err.println("Invalid train number, this train number is already in use");
    System.out.println("Try again!");
  }

  public void displayNoMatchingDestination(){
    System.err.println("No matching destination, exiting search");
    System.err.flush();
    System.out.println("Type /h for more commands");
  }
  public void displayNoMatchingNumber(){
    System.err.println("No matching train number, exiting search");
    System.err.flush();
    System.out.println("Type /h for more commands");
  }

  public void displaySuccessfulAdd(){
    System.out.println("Successfully added train departure");
  }
  public void invalidTimeEntry(){
    System.err.println("Invalid time entry, make sure you use the format HH:MM and that the time is after the current time ");
    System.out.println("Try again!");
  }
  public void invalidDelayEntry(){
    System.err.println("Invalid delay entry, make sure you use the format HH:MM");
    System.out.println("Try again!");
  }

  public void displaySuccessfulEdit(){
    System.out.println("Successfully edited train departure");
  }
}
