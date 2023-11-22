package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;

public class TrainStation {
  private LocalTime currentTime;
  private HashMap<Integer, TrainDeparture> trainsDepartures;
  public TrainStation(){
    trainsDepartures = new HashMap<>();
    currentTime = LocalTime.of(0, 0);
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
    trainsDepartures.values().removeIf(departure -> (departure.getDepartureTime() + departure.getDelayMinutes()) < timeAsInt(currentTime));
  }

  public Iterator<TrainDeparture> getSortedDepartureList(){
    return trainsDepartures.values().stream().sorted((departure1, departure2) -> departure1.getDepartureTime() - departure2.getDepartureTime()).iterator();
  }

  public void changeClock(LocalTime newTime){
    currentTime = newTime;
  }

  public void testMethodPrintTable(){
    trainsDepartures.values().forEach(System.out::println);
  }

  public LocalTime getTime(){
    return currentTime;
  }

  public int timeAsInt(LocalTime time){
    return time.getHour() * 100 + time.getMinute();
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
