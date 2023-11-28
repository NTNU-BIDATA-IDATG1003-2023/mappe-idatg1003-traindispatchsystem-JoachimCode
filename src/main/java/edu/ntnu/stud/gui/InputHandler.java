package edu.ntnu.stud.gui;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * This class handles all the input from the user with a scanner, and validates if the input is legal.
 * The UserInterface class will use this class to get input from the user, and the method will return the input if valid
 * or an invalid value to signal that it is an illegal input.
 */
public class InputHandler {
  private Scanner scanner = new Scanner(System.in);

  public String getCommand(){
    return  scanner.nextLine().toLowerCase();
  }
  public LocalTime getTimeInput(LocalTime currentTime){
    LocalTime returnTime = null;
    String timeInput = scanner.nextLine();
      //Copilot, note
    if(timeInput.matches("([01]\\d|2[0-3]):([0-5]\\d)") && LocalTime.parse(timeInput).isAfter(currentTime)){
      returnTime = LocalTime.parse(timeInput);
    }
    return returnTime;
  }
}

