package edu.ntnu.stud;

/**
 * This is the class that contains the train departures with the necessary information that is to be displayed
 *  @author JoachimDuong
 *  @version 0.0.1
 *  @since 4/10/2023
 */

public class TrainDeparture {
  /**
   *This variable is the time that is to be displayed and is of the TimeDisplay class. It is not an int, because
   * it needs the abilities to be displayed and incremented like a clock, therefore a
   * separate class is a more efficient solution for that purpose.
   */
  private TimeDisplay departureTime;
  /**
   * This variable represent the line, which is the route. It is of the datatype String
   * because the name of the lines consists of a letter and a number.
   */
  private String line;
  /**
   * This variable represents the id of the train that is responsible for the departure.
   * It is an int, because it only consists of whole numbers.
   */
  private int trainNumber;
  /**
   * This variable represents the destination of the train departure. It is the name of the
   * city/station and therefore uses the class String as datatype.
   */
  private String destination;
  /**
   * This variable represent the track that the departure will take place at. It is the physical track that the train will
   * arrive at. The tracks are identified by numbers and the variable will therefore be of the datatype int.
   */
  private int track;
  /**
   * This variable represents the delay of the departure in minutes. This will be how far off the departureTime
   * the actual departure is. The delay is represented in hour:minutes format and its therefore sensible to use the class
   * Timedisplay class that we used for departureTime, as the datatype.
   */
  private TimeDisplay delayMinutes;

  /**
   * This is the constructor which sets the fields of the train departure.
   */
  public TrainDeparture(){

  }
}
