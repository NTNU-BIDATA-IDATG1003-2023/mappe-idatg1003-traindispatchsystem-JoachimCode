package edu.ntnu.stud;

import java.util.HashMap;
import java.util.Iterator;

public class TrainStation {
  private int currentTime;
  private HashMap<Integer, TrainDeparture> trainsDepartures;
  public TrainStation(int currentTime){
    this.currentTime = currentTime;
    trainsDepartures = new HashMap<>();
  }

  public boolean addTrain(TrainDeparture trainDeparture){
    boolean successfullyAdded = false;
    if(checkTrainNumber(trainDeparture)){
      trainsDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
      successfullyAdded = true;
    }
    return successfullyAdded;
  }

  public TrainDeparture getTrainFromTrainNumber(int trainNumber){
    return trainsDepartures.get(trainNumber);
  }

  public Iterator<TrainDeparture> getTrainFromDestination(String destination){
    return trainsDepartures.values().stream().filter(departure -> departure.getDestination().equals(destination)).iterator();
  }

  public void removeEarlierDepartures(){
    trainsDepartures.values().removeIf(departure -> (departure.getDepartureTime() + departure.getDelayMinutes()) < currentTime);
  }

  public Iterator<TrainDeparture> getSortedDepartureList(){
    return trainsDepartures.values().stream().sorted((departure1, departure2) -> departure1.getDepartureTime() - departure2.getDepartureTime()).iterator(
  }

  public void changeClock(int newTime){
    currentTime = newTime;
  }



  /**
   * This method checks if the train number is already in the hashmap. If it is, it returns false.
   * If you add a object with the same key value in the hashmap, it will terminate the existing object,
   * so it is important with a check before appending to the hashmap.
   * @param trainDeparture is the object that is being checked.
   * @return boolean if the train number is already in the hashmap.
   */
  public boolean checkTrainNumber(TrainDeparture trainDeparture){
    return !trainsDepartures.containsKey(trainDeparture.getTrainNumber());
  }

}
