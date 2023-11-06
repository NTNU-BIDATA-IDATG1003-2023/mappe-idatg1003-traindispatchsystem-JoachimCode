package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainManager {
  private HashMap<Integer, TrainDeparture> trainsDepartures;
  public TrainManager(){
    trainsDepartures = new HashMap<>();
  }

  public void addTrain(TrainDeparture train){
    trainsDepartures.put(train.getTrainNumber(), train);
  }

  public boolean validAppend(TrainDeparture trainDeparture){
    boolean valid = true;
    if(trainsDepartures.containsKey(trainDeparture.getTrainNumber())){
      valid = false;
    }
    return valid;
  }
}
