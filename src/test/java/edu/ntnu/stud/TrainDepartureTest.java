package edu.ntnu.stud;

import edu.ntnu.stud.dataStructures.TrainDeparture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter .api.Assertions.*;
/*
* This is the test class for the TrainDeparture class.
* It tests all the methods in the TrainDeparture class, both positive and negative.
 */
class TrainDepartureTest {
  TrainDeparture trainDeparture;

  /*
  * This is the setup method for the test class, it is called everytime before a test is run.
   */
  @BeforeEach
  void setUp() {
    trainDeparture = new TrainDeparture(1200, "L1", 123, "Oslo", 1, 0);
  }

  /*
  * This is a test to check if the getter for departure time is working.
  * It checks if the departure time is 1200 with assertEquals.
  * It then uses the setDepartureTime method to set the departure time to 1200.
  */
  @Test
  void getDepartureTimeTest() {
    assertEquals(1200, trainDeparture.getDepartureTime(), "Positive test for getting departure time is working.");
  }

  /*
  * This is a positive test to check if the getter for line is working.
   */
  @Test
  void getLinePositiveTest() {
    assertEquals("L1", trainDeparture.getLine(), "Positive test for getting line is working.");
  }

  /*
  * This is a positive test for the getter for train number.
   */
  @Test
  void getTrainNumberPositiveTest() {
    assertEquals(123, trainDeparture.getTrainNumber(), "Positive test for getting train number is working.");
  }

  /*
  * This is a test for the getter for destination.
  * It checks if the destination is Oslo with assertEquals.
   */
  @Test
  void getDestinationTest() {
    assertEquals("Oslo", trainDeparture.getDestination(), "Test for getting destination is working.");
  }

  /*
  * This is a positive test for the getter for track.
  * It checks if the track is 1 with assertEquals.
   */
  @Test
  void getTrackPositiveTest() {
    assertEquals(1, trainDeparture.getTrack(), "Positive test for getting track is working.");
  }

  /*
  * This is a positive test for the setter for track.
  * It uses the setTrack method to set the track to 2.
  * It then checks if the track is 2 with assertEquals.
   */
  @Test
  void setTrackPositiveTest() {
    trainDeparture.setTrack(2);
    assertEquals(2, trainDeparture.getTrack(), "Positive test for setting track is working.");
  }
  /*
  * This is a negative test for the setter for track.
  * It uses the setTrack method to set the track to -2.
  * It then checks if the track is -1 with assertEquals.
   */
  @Test
  void setTrackNegativeTest() {
    trainDeparture.setTrack(-2);
    assertEquals(-1, trainDeparture.getTrack(), "Negative test for setting track is working.");
  }

  /*
  * This is a positive test for the getter for delay minutes.
  * It checks if the delay minutes is 0 with assertEquals.
  * It then uses the setDelayMinutes method to set the delay minutes to 10.
   */
  @Test
  void getDelayMinutesPositiveTest() {
    assertEquals(0, trainDeparture.getDelay(), "Positive test for getting delay minutes is working.");
  }

  /*
  * This is a positive test for the setter for delay minutes.
  * It uses the setDelayMinutes method to set the delay minutes to 10.
  * It then checks if the delay minutes is 10 with assertEquals.
   */
  @Test
  void setDelayMinutesPositiveTest() {
    trainDeparture.setDelay(10);
    assertEquals(10, trainDeparture.getDelay(), "Positive test for setting delay minutes is working.");
  }

  /*
  * This is a negative test for the setter for delay minutes.
  * It uses the setDelayMinutes method to set the delay minutes to -10.
  * It then checks if the delay minutes is -1 with assertEquals.
   */
  @Test
  void setDelayMinutesNegativeTest() {
    trainDeparture.setDelay(-10);
    assertEquals(-1, trainDeparture.getDelay(), "Negative test for setting delay minutes is working.");
  }
}