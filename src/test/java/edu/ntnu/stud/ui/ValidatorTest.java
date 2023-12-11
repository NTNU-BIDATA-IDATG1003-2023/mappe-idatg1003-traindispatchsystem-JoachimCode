package edu.ntnu.stud.ui;

import edu.ntnu.stud.datastructures.TrainDeparture;
import edu.ntnu.stud.datastructures.TrainStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
  * This is the test class for the Validator class.
  * It tests all the methods in the Validator class, both positive and negative.
 */
class ValidatorTest {
  Validator validator;
  TrainStation trainStation;
  /*
    * This is the setup method for the test class, it is called everytime before a test is run.
    * It adds two train departures to the train station.
   */
  @BeforeEach
  void setUp() {
    validator = new Validator();
    trainStation = new TrainStation();
    trainStation.addTrain(new TrainDeparture(1224, "A", 2, "Oslo", -1, 0));
    trainStation.addTrain(new TrainDeparture(1337, "B", 1, "Trondheim", 2, 20));
  }

  @Test
  void canConvertToIntPositive() {
    assertTrue(validator.canConvertToInt("123"), "Positive test for canConvertToInt is working.");
  }

  @Test
  void canConvertToIntNegative() {
    assertFalse(validator.canConvertToInt("1aa"), "Negative test for canConvertToInt is working.");
  }

  @Test
  void canConvertToIntNull() {
    assertFalse(validator.canConvertToInt(null), "Negative test for canConvertToInt is working.");
  }

  @Test
  void checkAvailableTrackPositive() {
    assertTrue(validator.checkAvailableTrack(1200, 1, trainStation.getSortedDepartureList()), "Positive test for checkAvailableTrack is working.");
  }

  @Test
  void checkAvailableTrackNegative() {
    assertFalse(validator.checkAvailableTrack(1337, 2, trainStation.getSortedDepartureList()), "Negative test for checkAvailableTrack is working.");
  }

  @Test
  void checkAvailableLine() {
    assertTrue(validator.checkAvailableLine(1200, "A", trainStation.getSortedDepartureList()), "Positive test for checkAvailableLine is working.");
  }

  @Test
  void checkAvailableLineNegative() {
    assertFalse(validator.checkAvailableLine(1337, "B", trainStation.getSortedDepartureList()), "Negative test for checkAvailableLine is working.");
  }
}