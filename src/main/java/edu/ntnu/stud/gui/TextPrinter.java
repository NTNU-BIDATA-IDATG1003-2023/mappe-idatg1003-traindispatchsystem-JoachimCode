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
    System.out.printf("%s - Remove a train departure%n", CommandVariables.REMOVE);
    System.out.printf("%s - Edit a train departure%n", CommandVariables.EDIT);
    System.out.printf("%s - Change the time", CommandVariables.SET_TIME);
  }

  public void displayTrainDepartures(Iterator<TrainDeparture> trainDepartures, LocalTime currentTime){
    System.out.println("current time: " + currentTime);
    System.out.println("Train departures:");
    while(trainDepartures.hasNext()){
      System.out.println(trainDepartures.next());
    }
  }

  public void displayInvalidCommand(){
    System.out.println("Invalid command, type /h for help");
  }

  public void displayEnterTime(){
    System.out.println("What is the current time?");
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
    System.out.println("Enter line: ");
  }

  public void displayDestinationInput(){
    System.out.println("What is the destination of the train?");
    System.out.println("Enter destination: ");
  }

  public void displayTrackInput(){
    System.out.println("What track is the train departing from?");
    System.out.println("Type 0 if the track is unknown");
    System.out.println("The track must strictly be a number");
    System.out.println("Enter track: ");
  }

  public void displayDelayInput(){
    System.out.println("Is there a delay to the train?");
    System.out.println("Type 0 if there is no delay");
    System.out.println("If there is enter the delay in minutes");
    System.out.println("Enter delay: ");
  }

  public void displayInvalidInt(){
    System.out.println("Invalid input, please enter a number");
    System.out.println("Try again!");
  }

  public void displayInvalidDelay(){
    System.out.println("Invalid input, this delay with make the train depart outside this applications scope (The next day)");
    System.out.println("Try again!");
  }

  public void invalidTimeEntry(){
    System.out.println("Invalid time entry, make sure you use the format HH:MM and that the time is after the current time ");
    System.out.println("Try again!");
  }
}
