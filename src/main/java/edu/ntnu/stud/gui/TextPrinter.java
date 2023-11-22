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
    System.out.println("Type /h for help");
  }

  public void displayCommands(){
    System.out.println("Commands:");
    System.out.println("/h - Displays all the commands");
    System.out.println("/display - Displays all the trains departures");
    System.out.println("/add - Add a new train departure");
    System.out.println("/remove - Remove a train departure");
    System.out.println("/edit - Edit a train departure");
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
    System.out.println("Use the format HH:MM");
    System.out.println("Enter time: ");
  }
}
