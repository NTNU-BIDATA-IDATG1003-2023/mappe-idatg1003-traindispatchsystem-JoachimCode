package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
  TrainDeparture trainDeparture = new TrainDeparture(1200, "L1", 123, "Oslo", 1, 0);
  @Test
  void getDepartureTimePositiveTest() {
    assertEquals(1200, trainDeparture.getDepartureTime(), "Positive test for getting departure time is working.");
  }

  @Test
  void setDepartureTimePositiveTest() {
    trainDeparture.setDepartureTime(1300);
    assertEquals(1300, trainDeparture.getDepartureTime(), "Positive test for setting departure time is working.");
  }

  @Test
  void setDepartureTimesNegativeTest() {
    trainDeparture.setDepartureTime(-200);
    assertEquals(-1, trainDeparture.getDepartureTime(), "Negative test for setting departure time is working.");
  }


  @Test
  void getLinePositiveTest() {
    assertEquals("L1", trainDeparture.getLine(), "Positive test for getting line is working.");
  }

  @Test
  void setLinePositiveTest() {
    trainDeparture.setLine("L2");
    assertEquals("L2", trainDeparture.getLine());
  }

  @Test
  void getTrainNumberPositiveTest() {
    assertEquals(123, trainDeparture.getTrainNumber(), "Positive test for getting train number is working.");
  }

  @Test
  void setTrainNumberPositiveTest() {
    trainDeparture.setTrainNumber(124);
    assertEquals(124, trainDeparture.getTrainNumber(), "Positive test for setting train number is working.");
  }

  @Test
  void setTrainNumberNegativeTest() {
    trainDeparture.setTrainNumber(-24);
    assertEquals(.1, trainDeparture.getTrainNumber(), "Negative test for setting train number is working.");
  }

  @Test
  void getDestinationPositiveTest() {
    assertEquals("Oslo", trainDeparture.getDestination(), "Positive test for getting destination is working.");
  }

  @Test
  void setDestinationPositiveTest() {
    assertEquals("Oslo", trainDeparture.getDestination(), "Positive test for setting destination is working.");
  }

  @Test
  void setDestivationNegativeTest() {
    trainDeparture.setDestination("");
    assertEquals("invalid", trainDeparture.getDestination(), "Negative test for setting destination is working.");
  }

  @Test
  void getTrackPositiveTest() {
    assertEquals(1, trainDeparture.getTrack(), "Positive test for getting track is working.");
  }

  @Test
  void setTrackPositiveTest() {
    trainDeparture.setTrack(2);
    assertEquals(2, trainDeparture.getTrack(), "Positive test for setting track is working.");
  }

  @Test
  void setTrackNegativeTest() {
    trainDeparture.setTrack(-2);
    assertEquals(-1, trainDeparture.getTrack(), "Negative test for setting track is working.");
  }

  @Test
  void getDelayMinutesPositiveTest() {
    assertEquals(0, trainDeparture.getDelayMinutes(), "Positive test for getting delay minutes is working.");
  }

  @Test
  void setDelayMinutesPositiveTest() {
    trainDeparture.setDelayMinutes(10);
    assertEquals(10, trainDeparture.getDelayMinutes(), "Positive test for setting delay minutes is working.");
  }

  @Test
  void setDelayMinutesNegativeTest() {
    trainDeparture.setDelayMinutes(-10);
    assertEquals(-1, trainDeparture.getDelayMinutes(), "Negative test for setting delay minutes is working.");
  }
}