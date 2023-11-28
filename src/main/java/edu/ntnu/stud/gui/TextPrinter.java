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
    System.out.printf("Type %s for help%n", CommandVariables.HELP);
  }

  public void displayCommands(){
    System.out.println("Commands:");
    System.out.printf("%s - Displays all the commands", CommandVariables.HELP);
    System.out.printf("%s - Displays all the trains departures", CommandVariables.DISPLAY);
    System.out.printf("%s - Add a new train departure", CommandVariables.ADD);
    System.out.printf("%s - Remove a train departure", CommandVariables.REMOVE);
    System.out.printf("%s - Edit a train departure", CommandVariables.EDIT);
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

  public void displayEnterTimer(){
    System.out.println("What is the current time?");
    System.out.println("Use the format HH:MM");
    System.out.println("Enter time: ");
  }

  public void invalidTimeEntry(){
    System.out.println("Invalid time entry, make sure you use the format HH:MM and that the time is after the current time ");
    System.out.println("Try again!");
    }
}
