package edu.ntnu.stud;

//import java.time.LocalTime;
/**
 * The {@code TrainDeparture} class represents a TrainDeparture.
 * All {@code TrainDeparture}s have departure time,
 * line, train number, destination, track and
 * delay in minutes.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 *     TrainDeparture trainDeparture = new trainDeparture(PARAMETERE);
 * </pre></blockquote>
 *
 * <p>The class {@code TrainDeparture} includes methods for accessors and mutators.
 *
 * @author Joachim Duong
 * @version 1.0
 * @since 1.0
 */

public class TrainDeparture {
  /*
   * This variable is the time that is to be displayed and is
   * of the TimeDisplay class. It is not an int, because
   * it needs the abilities to be displayed and incremented
   * like a clock, therefore a
   * separate class is a more efficient solution
   * for that purpose.
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
   * This variable represents the delay of the departure in minutes.
   * This will be how far off the departureTime
   * the actual departure is. The delay is represented in hour:minutes
   * format and its therefore sensible to use the class
   * Timedisplay class that we used for departureTime, as the datatype.
   */
  private int delayMinutes;
  /**
   * This is the constructor which sets the fields of the train departure.
   * The constructor is overloaded, with two additional constructors, that
   * makes track and delayMinutes optional. This is because the user is not
   * always aware of the track or the is no delay, and therefore it is not required.
   *
   * @param departureTime the departure time of the train departure.
   * @param line          the line of the train departure.
   * @param trainNumber   the train number of the train departure.
   * @param destination   the destination of the train departure.
   * @param track         the track of the train departure.
   * @param delayMinutes  the delay minutes of the train departure.
   */

  public TrainDeparture(int departureTime, String line, int trainNumber,
      String destination, int track, int delayMinutes) {
    setDepartureTime(departureTime);
    setLine(line);
    setTrainNumber(trainNumber);
    setDestination(destination);
    setTrack(track);
    setDelayMinutes(delayMinutes);
  }

  public TrainDeparture(int departureTime, String line, int trainNumber,
                        int track, String destination) {
    setDepartureTime(departureTime);
    setLine(line);
    setTrainNumber(trainNumber);
    setDestination(destination);
    setTrack(track);
    setDelayMinutes(0);
  }

  public TrainDeparture(int departureTime, String line, int trainNumber
                        , String destination, int delayMinutes) {
    setDepartureTime(departureTime);
    setLine(line);
    setTrainNumber(trainNumber);
    setDestination(destination);
    setTrack(0);
    setDelayMinutes(delayMinutes);
  }

  /**
   *
   * This method returns the departure time of the train departure.
   * @return departureTime is the departure time of the train departure.
   */
  public int getDepartureTime() {
    return departureTime;
  }

  /**
   *
   * <p>This method sets the departure time of the train departure. </p>
   * @param departureTime is the departure time of the train departure.
   */
  public void setDepartureTime(int departureTime) {
    if (departureTime < 0) {
      this.departureTime = -1;
    } else {
      this.departureTime = departureTime;
    }
  }

  /**
   * This method returns the line of the train departure.
   */
  public String getLine() {
    return line;
  }

  /**
   * This method sets the line of the train departure.
   * It has a check to see if the line is empty or null,
   * and sets the value to "invalid" if it does not pass the test.
   * @param line is the line of the traindeparture.
   */
  public void setLine(String line) {
    if (line == null || line.isEmpty()) {
      this.line = "invalid";
    } else {
      this.line = line;
    }
  }

  /**
   * This method returns the train number of the train departure.
   * @return trainNumber the train number of the train departure.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * This method sets the train number of the train departure. It has a check to see if the train number is negative,
   * and sets the value to -1 if it does not pass the test, so it can be handled later.
   * @param trainNumber
   */
  public void setTrainNumber(int trainNumber) {
    if(trainNumber < 0){
      this.trainNumber = -1;
    }
    else {
      this.trainNumber = trainNumber;
    }
  }

  /**
   * This method returns the destination of the train departure.
   * @return
   */
  public String getDestination() {
    return destination;
  }

  /**
   * This method sets the destination of the train departure. It has a check to see if the destination is empty or null,
   * and sets the value to "invalid" if it does not pass the test.
   * @param destination
   */
  public void setDestination(String destination) {
    if(destination == null || destination.isEmpty()){
      this.destination = "invalid";
    }
    else {
      this.destination = destination;
    }
  }

  /**
   * This method returns the track of the train departure.
   * @return the track that the train is coming on.
   */
  public int getTrack() {
    return track;
  }

  /**
   * This method sets the track of the train departure. It has a check to see if the track is negative,
   * @param track
   */
  public void setTrack(int track) {
    if(track < 0){
      this.track = -1;
    }
    else {
      this.track = track;
    }
  }

  /**
   * This method returns the delay minutes of the train departure.
   * @return
   */
  public int getDelayMinutes() {
    return delayMinutes;
  }

  /**
   * This method sets the delay minutes of the train departure. It has a check to see if the delay minutes is negative,
   * and sets the value to -1 if it does not pass the test, so it can be handled later.
   * @param delayMinutes the delay minutes of the train departure.
   */
  public void setDelayMinutes(int delayMinutes) {
    if (delayMinutes < 0) {
      this.delayMinutes = -1;
    } else {
      this.delayMinutes = delayMinutes;
    }
  }
}
