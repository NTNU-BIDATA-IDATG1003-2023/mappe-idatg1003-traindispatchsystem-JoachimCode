package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
  TrainDeparture trainDeparture = new TrainDeparture(1200, "L1", 123, "Oslo", 1, 0);

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
  * This is a positive test to check if the setter for departure time is working.
  */
  @Test
  void setDepartureTimePositiveTest() {
    trainDeparture.setDepartureTime(1300);
    assertEquals(1300, trainDeparture.getDepartureTime(), "Positive test for setting departure time is working.");
  }

  /*
  * This is a negative test to check if the setter for departure time is working.
   */
  @Test
  void setDepartureTimesNegativeTest() {
    trainDeparture.setDepartureTime(-200);
    assertEquals(-1, trainDeparture.getDepartureTime(), "Negative test for setting departure time is working.");
  }


  /*
  * This is a positive test to check if the getter for line is working.
   */
  @Test
  void getLinePositiveTest() {
    assertEquals("L1", trainDeparture.getLine(), "Positive test for getting line is working.");
  }

  /*
  * This is a positive test to check if the setter for line is working.
   */
  @Test
  void setLinePositiveTest() {
    trainDeparture.setLine("L2");
    assertEquals("L2", trainDeparture.getLine());
  }
  /*
  * This is a positive test for the getter for train number.
   */
  @Test
  void getTrainNumberPositiveTest() {
    assertEquals(123, trainDeparture.getTrainNumber(), "Positive test for getting train number is working.");
  }

  /*
  * This is a positive test for the setter for train number.
  * It uses the setTrainNumber method to set the train number to 124.
  * and then checks if the train number is 124 with assertEquals.
   */
  @Test
  void setTrainNumberPositiveTest() {
    trainDeparture.setTrainNumber(124);
    assertEquals(124, trainDeparture.getTrainNumber(), "Positive test for setting train number is working.");
  }

  /*
  * This is a negative test for the setter for train number.
  * It uses the setTrainNumber method to set the train number to -24.
  * and then checks if the train number is -1 with assertEquals.
   */
  @Test
  void setTrainNumberNegativeTest() {
    trainDeparture.setTrainNumber(-24);
    assertEquals(-1, trainDeparture.getTrainNumber(), "Negative test for setting train number is working.");
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
  * This is a positive test for the setter for destination.
  * It uses the setDestination method to set the destination to Bergen.
  * It then checks if the destination is Bergen with assertEquals.
   */
  @Test
  void setDestinationPositiveTest() {
    trainDeparture.setDestination("Bergen");
    assertEquals("Bergen", trainDeparture.getDestination(), "Positive test for setting destination is working.");
  }

  /*
  * This is a negative test for the setter for destination.
  * It uses the setDestination method to set the destination to "".
  * It then checks if the destination is invalid with assertEquals.
   */
  @Test
  void setDestivationNegativeTest() {
    trainDeparture.setDestination("");
    assertEquals("invalid", trainDeparture.getDestination(), "Negative test for setting destination is working.");
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
    assertEquals(0, trainDeparture.getDelayMinutes(), "Positive test for getting delay minutes is working.");
  }

  /*
  * This is a positive test for the setter for delay minutes.
  * It uses the setDelayMinutes method to set the delay minutes to 10.
  * It then checks if the delay minutes is 10 with assertEquals.
   */
  @Test
  void setDelayMinutesPositiveTest() {
    trainDeparture.setDelayMinutes(10);
    assertEquals(10, trainDeparture.getDelayMinutes(), "Positive test for setting delay minutes is working.");
  }

  /*
  * This is a negative test for the setter for delay minutes.
  * It uses the setDelayMinutes method to set the delay minutes to -10.
  * It then checks if the delay minutes is -1 with assertEquals.
   */
  @Test
  void setDelayMinutesNegativeTest() {
    trainDeparture.setDelayMinutes(-10);
    assertEquals(-1, trainDeparture.getDelayMinutes(), "Negative test for setting delay minutes is working.");
  }
}