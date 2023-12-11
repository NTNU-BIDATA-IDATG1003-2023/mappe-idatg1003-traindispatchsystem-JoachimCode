package edu.ntnu.stud.ui;

import edu.ntnu.stud.datastructures.TrainDeparture;
import java.util.Iterator;

/**
 * The {@code Validator} class handles validation of data.
 *
 * @author JoachimDuong
 * @version 1.0.0
 * @since 1.0.0
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
    if (canConvert) {
      for (int i = 0; i < str.length(); i++) {
        if (!Character.isDigit(str.charAt(i))) {
          canConvert = false;
        }
      }
    }
    return canConvert;
  }

  /**
   * This method checks if the track is available at the given time,
   * because there can only be one train at a track at a time.
   * It goes over every train departure in the hashmap and checks
   * if the track is available at the given time, including the delay,
   * because that is the real departure time.
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
      if (departure.getTrack() == track && addDelay(
              departure.getDepartureTime(), departure.getDelay()) == time) {
        availableTrack = false;
      }
    }
    return availableTrack;
  }

  /**
   * This method checks if the line is available at the given time,
   * because there can only be one train at a line at a time.
   * It goes over every train departure in the hashmap and checks
   * if the line is available at the given time, including the delay,
   * because that is the real departure time.
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
      if (departure.getLine().equalsIgnoreCase(line) && addDelay(
              departure.getDepartureTime(), departure.getDelay()) == time) {
        availableLine = false;
      }
    }
    return availableLine;
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
  public int addDelay(int departureTime, int delayTime) {
    int actualDepartureTime;
    int hours = departureTime / 100;
    int minutes = departureTime % 100;

    int delayMinutes = delayTime % 100;
    int delayHours = delayTime / 100;

    minutes += delayMinutes;
    hours += delayHours;

    if (minutes >= 60) {
      hours += minutes / 60;
      minutes %= 60;
    }

    if (hours < 24) {
      actualDepartureTime = hours * 100 + minutes;
    } else {
      actualDepartureTime = -1;
    }
    return actualDepartureTime;
  }
}
