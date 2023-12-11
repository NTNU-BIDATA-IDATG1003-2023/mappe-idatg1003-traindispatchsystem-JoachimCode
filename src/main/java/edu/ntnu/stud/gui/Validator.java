package edu.ntnu.stud.gui;

import edu.ntnu.stud.datastructures.TrainDeparture;
import java.util.Iterator;

/**
 * The {@code Validator} class handles validation of data.
 *
 * @author JoachimDuong
 * @version 0.0.1
 * @since 4/10/2023
 */
public class Validator {
  /**
   * This method checks if the input is a valid integer and return a boolean.
   * It first checks if the input is null or empty, and if it is, it will set the boolean to false.
   * It then goes over every character in the string and checks if it is a digit.
   * If any character is not a digit, it will set the boolean to false.
   *
   * @param str is the string that is being checked.
   * @return boolean if the string can be converted to an integer.
   */
  public boolean canConvertToInt(String str) {
    boolean canConvert = str != null && !str.isEmpty();
    if(canConvert) {
      for (int i = 0; i < str.length(); i++) {
        if (!Character.isDigit(str.charAt(i))) {
          canConvert = false;
        }
      }
    }
    return canConvert;
  }

  /**
   * This method chekcs if the track is available at the given time,
   * because there can only be one train at a track at a time.
   * It goes over every train departure in the hashmap and checks
   * if the track is available at the given time.
   * If there is a train at the track at the given time, it will return false.
   * else it will return true.
   *
   * @param time is the time that is being checked.
   * @param track is the track that is being checked.
   * @param iterator is the iterator of all the departures.
   * @return boolean if the track is available at the given time.
   */
  public boolean checkAvailableTrack(int time, int track, Iterator<TrainDeparture> iterator) {
    boolean availableTrack = true;
    while (iterator.hasNext()) {
      TrainDeparture departure = iterator.next();
      if (departure.getTrack() == track && departure.getDepartureTime() == time) {
        availableTrack = false;
      }
    }
    return availableTrack;
  }

  /**
   * This method checks if the line is available at the given time,
   * because there can only be one train at a line at a time.
   * It goes over every train departure in the hashmap and checks
   * if the line is available at the given time.
   * If there is a train at the line at the given time, it will return false.
   * else it will return true.
   *
   * @param time is the time that is being checked.
   * @param line is the line that is being checked.
   * @param iterator is the iterator of all the departures.
   * @return boolean if the line is available at the given time.
   */
  public boolean checkAvailableLine(int time, String line, Iterator<TrainDeparture> iterator) {
    boolean availableLine = true;
    while (iterator.hasNext()) {
      TrainDeparture departure = iterator.next();
      if (departure.getLine().equalsIgnoreCase(line) && departure.getDepartureTime() == time) {
        availableLine = false;
      }
    }
    return availableLine;
  }
}
