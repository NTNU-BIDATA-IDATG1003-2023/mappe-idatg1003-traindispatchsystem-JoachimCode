package edu.ntnu.stud.datastructures;

/**
 * The {@code TrainDeparture} class represents a TrainDeparture.
 * All {@code TrainDeparture}s have departure time,
 * line, train number, destination, track and
 * delay.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 *     TrainDeparture trainDeparture = new trainDeparture(PARAMETERS);
 * </pre></blockquote>
 *
 * <p>The class {@code TrainDeparture} includes methods for accessors and mutators.
 * @author Joachim Duong
 * @version 1.0.0
 * @since 1.0
 */

public class TrainDeparture {
  /*
   * This variable is the time that is to be displayed and is
   * of the TimeDisplay class. It is an int because it is
   * easier and faster to calculate with. It is formatted in
   * an 4 digit int, where the 2 first digits is the hours and
   * the last is the minutes.
   */
  private int departureTime;
  /*
   * This variable represent the line, which is the route. It is of the datatype String
   * because the name of the lines consists of a letter and a number.
   */
  private String line;
  /*
   * This variable represents the id of the train that is responsible for the departure.
   * It is an int, because it only consists of whole numbers.
   */
  private int trainNumber;
  /*
   * This variable represents the destination of the train departure. It is the name of the
   * city/station and therefore uses the class String as datatype.
   */
  private String destination;
  /*
   * This variable represent the track that the departure will
   * take place at. It is the physical track that the train will
   * arrive at. The tracks are identified by numbers and the
   * variable will therefore be of the datatype int.
   */
  private int track;
  /*
   * This variable represents the delay of the departure in an int.
   * It is formatted in an 4 digit int, where the
   * 2 first digits is the hours and the last is the minutes.'
   * It is an int because it is eaiser to calculate with,
   * without parsing localtime.
   * This will be how far off the departureTime
   * the actual departure is. The delay is represented in hour:minutes
   * format and its therefore sensible to use the class
   */
  private int delay;
  /**
   * This is the constructor which creates and sets the fields of the train departure.
   * The constructor is not overloaded, but since track and delay
   * can be unknown, they are set to an invalid value if unknown.
   *
   * @param departureTime the departure time of the train departure.
   * @param line          the line of the train departure.
   * @param trainNumber   the train number of the train departure.
   * @param destination   the destination of the train departure.
   * @param track         the track of the train departure.
   * @param delay  the delay minutes of the train departure.
   */

  public TrainDeparture(int departureTime, String line,  int trainNumber,
      String destination, int track, int delay) {
    setDepartureTime(departureTime);
    setLine(line);
    setTrainNumber(trainNumber);
    setDestination(destination);
    setTrack(track);
    setDelay(delay);
  }

  /** Gets the train departure's departure time.
   *
   * @return an int representing the departure time of the train departure.
   */
  public int getDepartureTime() {
    return departureTime;
  }

  /** Sets the departure time.
   *
   * @param departureTime is the departure time of the train departure.
   */
  private void setDepartureTime(int departureTime) {
    if (departureTime < 0) {
      this.departureTime = -1;
    } else {
      this.departureTime = departureTime;
    }
  }

  /** Gets the train departure's line .
   *
   * @return A string representing the line of the train departure.
   */
  public String getLine() {
    return line;
  }

  /** Sets the line of the train departure.
   * It has a check to see if the line is empty or null,
   * and sets the value to "invalid" if it does not pass the test.
   *
   * @param line is the line of the train departure.
   */
  private void setLine(String line) {
    if (line == null || line.isEmpty()) {
      this.line = "invalid";
    } else {
      this.line = line;
    }
  }

  /** Gets the train number of the train departure.
   *
   * @return trainNumber the train number of the train departure.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Sets the train number of the train departure.
   * It has a check to see if the train number is negative,
   * and sets the value to -1 if it does not pass the test,
   * so it can be handled later.
   *
   * @param trainNumber the train number of the train departure.
   */
  private void setTrainNumber(int trainNumber) {
    if (trainNumber < 0) {
      this.trainNumber = -1;
    } else {
      this.trainNumber = trainNumber;
    }
  }

  /** Gets the destination of the train departure.
   *
   * @return a string representing the destination of the train departure.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * This method sets the destination of the train departure.
   * It has a check to see if the destination is empty or null,
   * and sets the value to "invalid" if it does not pass the test.
   *
   * @param destination the destination of the train departure.
   */
  private void setDestination(String destination) {
    if (destination == null || destination.isEmpty()) {
      this.destination = "invalid";
    } else {
      this.destination = destination;
    }
  }

  /** Gets the track of the train departure.
   *
   * @return the track that the train is coming on.
   */
  public int getTrack() {
    return track;
  }

  /**
   * Sets the track of the train departure.
   * It has a check to see if the track is negative,
   *
   * @param track the track of the train departure.
   */
  public void setTrack(int track) {
    if (track < 0) {
      this.track = -1;
    } else {
      this.track = track;
    }
  }

  /** Gets the delay minutes of the train departure.
   *
   * @return the delay of the train departure.
   */
  public int getDelay() {
    return delay;
  }

  /**
   * This method sets the delay minutes of the train departure.
   * It has a check to see if the delay minutes is negative,
   * and sets the value to -1 if it does not pass the test,
   * so it can be handled later.
   *
   * @param delay the delay minutes of the train departure.
   */
  public void setDelay(int delay) {
    if (delay < 0) {
      this.delay = -1;
    } else {
      this.delay = delay;
    }
  }
}
