package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The {@code TrainStation} class represents a Train station.
 * All {@code TrainStation}s have a hashmap of {@code TrainDeparture}s,
 * and a current time which represents the current time at the station.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 *     TrainStation trainStation = new TrainStation(PARAMETERS);
 * </pre></blockquote>
 *
 * <p>The class {@code TrainStation} includes methods for accessing and modifying the
 * hashmap of {@code TrainDeparture}s. It also includes methods for accessing and modifying the
 *
 * @author Joachim Duong
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrainStation {
  /*
   * This variable represents the current time at the station.
   * It is of the datatype LocalTime, because that is how
   * the user adds the time, and it is rarely necessary to
   * convert it to an int for calculations.
   */
  private LocalTime currentTime;
  /*
   * This variable represents the register of train departures.
   * It is of the datatype HashMap, because it is the most
   * efficient way to store the train departures and it is not
   * necessary to sort the departures often, so it does not need
   * to be in a sorted order.
   * The key is the train number, and the value is the train departure.
   */
  private HashMap<Integer, TrainDeparture> trainDepartures;

  /**
   * This is the constructor for the TrainStation class.
   * It initializes the hashmap and the current time at a
   * start value.
   */
  public TrainStation() {
    trainDepartures = new HashMap<>();
    currentTime = LocalTime.of(0, 0);
  }

  /**
   * Adds a train departure to the hashmap.
   * It checks if the train number is already in the hashmap,
   * and if it is, it will not add the train departure and return a false boolean.
   * If it can be added, it will return a true boolean.
   *
   * @param trainDeparture is the trainDeparture that is being added to the hashmap.
   * @return boolean if the train departure was successfully added.
   */
  public boolean addTrain(TrainDeparture trainDeparture) {
    boolean successfullyAdded = false;
    if (!checkTrainNumber(trainDeparture.getTrainNumber())) {
      trainDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
      successfullyAdded = true;
    }
    return successfullyAdded;
  }

  /**
   * Gets a train departure from the hashmap with the given train number as key.
   * It checks if the train number is in the hashmap, and if it is not, it will return null.
   *
   * @param trainNumber is the train number of the train departure that is being returned.
   * @return TrainDeparture if the train number is in the hashmap, else null.
   */
  public TrainDeparture getTrainFromTrainNumber(int trainNumber) {
    TrainDeparture trainDeparture = null;
    if (checkTrainNumber(trainNumber)) {
      trainDeparture = trainDepartures.get(trainNumber);
    }
    return trainDeparture;
  }

  /**
   * Gets train from the hashmap with the given destination as value.
   * There might be multiple trains with the same destination, so it returns an iterator.
   * It uses stream to filter the hashmap, and then returns an iterator of the filtered hashmap.
   *
   * @param destination is the destination of the train departure that is being returned.
   * @return Iterator of TrainDeparture if the destination is in the hashmap, else null.
   */
  public Iterator<TrainDeparture> getTrainFromDestination(String destination) {
    return trainDepartures.values().stream()
            .filter(departure -> departure.getDestination().equalsIgnoreCase(destination))
            .iterator();
  }

  /**
   * Removes all trains from the hashmap with an earlier departure time (including delay)
   * than the current time.
   */
  private void removeEarlierDepartures() {
    trainDepartures.values()
            .removeIf(departure -> (departure.getDepartureTime() + departure.getDelay())
                    < timeAsInt(currentTime));
  }

  /**
   * Gets a sorted iterator of the hashmap.
   * It uses stream to sort the hashmap, and then returns an iterator of the sorted hashmap.
   *
   * @return Iterator of TrainDeparture if the destination is in the hashmap,
   else an empty iterator.
   */
  public Iterator<TrainDeparture> getSortedDepartureList()  {
    return trainDepartures.values()
            .stream()
            .sorted((departure1, departure2)
                    -> departure1.getDepartureTime() - departure2.getDepartureTime())
            .iterator();
  }

  /**
   * Changes the current time to the given time.
   *
   * @param newTime is the new time that the current time is being changed to.
   */
  public void changeClock(LocalTime newTime) {
    currentTime = newTime;
    removeEarlierDepartures();
  }

  /**
   * Gets the current time of the station.
   *
   * @return LocalTime of the current time.
   */
  public LocalTime getTime() {
    return currentTime;
  }

  /**
   * This method converts a LocalTime to an int.
   *
   * @param time is the LocalTime that is being converted to an int.
   * @return the time as an int.
   */
  public int timeAsInt(LocalTime time) {
    return time.getHour() * 100 + time.getMinute();
  }

  /**
   * This method checks if the train number is in the hashmap.
   *
   * @param trainNumber is the train number that is being checked.
   * @return boolean if the train number is in the hashmap.
   */
  public boolean checkTrainNumber(int trainNumber) {
    return trainDepartures.containsKey(trainNumber);
  }

}
