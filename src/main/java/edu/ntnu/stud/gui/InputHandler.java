package edu.ntnu.stud.gui;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * The {@code InputHandler} class handles input from the user. It includes methods for getting
 * Strings and times. If the input is a time, it will check if it is a valid time.
 *
 * <p>The class {@code InputHandler} includes methods for getting Strings and Time input.
 *
 * @author Joachim Duong
 * @version 1.0.0
 * @since 1.0.0
 */

public class InputHandler {
  /**
   * This variable is the scanner that is used to get input from the user.
   */
  private final Scanner scanner = new Scanner(System.in);

  /**
   * This method gets the String from the user and returns it as a string.
   * It also converts the command to lowercase, so it is not case-sensitive.
   *
   * @return String that the user inputted.
   */
  public String getString() {
    return scanner.nextLine().toLowerCase();
  }

  /**
   * This method gets the time from the user and returns it as a LocalTime.
   * It also checks if the time is valid, i.e not in the right format,
   * and if it is not, it will return null.
   *
   * @return LocalTime that the user inputted.
   */
  public LocalTime getTimeInput() {
    LocalTime returnTime = null;
    String timeInput = scanner.nextLine();
    if (timeInput.matches("([01]\\d|2[0-3]):([0-5]\\d)")) {
      returnTime = LocalTime.parse(timeInput);
    }
    return returnTime;
  }
}

