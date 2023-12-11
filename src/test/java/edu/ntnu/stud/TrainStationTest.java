package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/*
  * This is the test class for the TrainStation class.
  * It tests all the methods in the TrainStation class, both positive and negative.
  */
class TrainStationTest {
    TrainStation trainStation;

    /*
      * This is the setup method for the test class, it is called everytime before a test is run.
      * It adds three train departures to the train station.
     */
    @BeforeEach
    void setUp() {
        trainStation = new TrainStation();
        trainStation.changeClock(LocalTime.parse("01:00"));
        trainStation.addTrain(new TrainDeparture(1200, "L1", 123, "Oslo", 1, 0));
        trainStation.addTrain(new TrainDeparture(1400, "L2", 142, "Bergen", 2, 0));
        trainStation.addTrain(new TrainDeparture(1200, "F1", 162, "Haugesund", 3, 0));
    }

    /*
      * This is a positive test to check if adding a train is working.
      * It checks if the hashmap contains the train number 123.
     */
    @Test
    void addTrainPositiveTest() {
        trainStation.addTrain(new TrainDeparture(1500, "L8", 977, "Kirkenes", 1, 23));
        assertEquals("Kirkenes",trainStation.getTrainFromTrainNumber(977).getDestination(), "Positive test for adding train is working.");
    }

    /*
      * This is a negative test to check adding a train with a train number that already exists.
      * It checks if the method returns false when trying to add a train with a train number that already exists.
     */
    @Test
    void addTrainNegativeTest() {
      assertFalse(trainStation.addTrain(new TrainDeparture(1500, "L8", 123, "Kirkenes", 1, 23)), "Negative test for adding train is working.");
    }

    /*
      * This is a positive test to check if getting a train from a train number is working.
      * It checks if the hashmap contains the train number 123 and compares the line of the train.
     */
    @Test
    void getTrainFromTrainNumberPositiveTest() {
        assertEquals("L1", trainStation.getTrainFromTrainNumber(123).getLine(), "Positive test for getting train from train number is working.");
    }

    /*
      * This is a negative test to check if getting a train from a train number is working.
      * It checks if the return value is null when trying to get a train from a train number that does not exist.
     */
    @Test
    void getTrainFromTrainNumberNegativeTest() {
        assertNull(trainStation.getTrainFromTrainNumber(1234), "Negative test for getting train from train number is working.");
    }

    /*
      * This is a positive test to check if getting a train from a destination is working.
      * It checks if the hashmap contains the destination "Bergen" and compares the destination of the train.
     */
    @Test
    void getTrainFromDestinationPositiveTest() {
        Iterator<TrainDeparture> iterator = trainStation.getTrainFromDestination("Bergen");
        assertEquals("Bergen", iterator.next().getDestination(), "Positive test for getting train from destination is working.");
    }

    /*
      * This is a negative test to check if getting a train from a destination is working.
      * It checks if the iterator is empty when trying to get a train from a destination that does not exist.
     */
    @Test
    void getTrainFromDestinationNegativeTest() {
        Iterator<TrainDeparture> iterator = trainStation.getTrainFromDestination("Trondheim");
        assertFalse(iterator.hasNext(), "Negative test for getting train from destination is working.");
    }

    /*
      * This is a positive test to check if getting a sorted departure list is working.
      * It checks if the first train in the iterator has the line "L8".
     */
    @Test
    void getSortedDepartureList() {
        trainStation.addTrain(new TrainDeparture(300, "L8", 977, "Kirkenes", 1, 23));
        Iterator<TrainDeparture> iterator = trainStation.getSortedDepartureList();
        assertEquals("L8", iterator.next().getLine(), "Test for getting sorted departure list is working.");

    }

    /*
      * This is a test to check if method for changing the station's clock is working
      * It checks if the first train in the iterator has the line "L2", after changing the time.
     */
    @Test
    void changeClock() {
        trainStation.changeClock(LocalTime.parse("14:00"));
        Iterator<TrainDeparture> iterator = trainStation.getSortedDepartureList();
        assertEquals("L2", iterator.next().getLine(), "Test for changing clock is working.");
    }

    /*
      * This is a  test to check if getting the current time is working.
      * It checks if the current time is 01:00.
     */
    @Test
    void getTime() {
        assertEquals(LocalTime.parse("01:00"), trainStation.getTime(), "Test for getting time is working.");
    }

    /*
    * This is a test to check if converting a LocalTime to an int is working.
      * It checks if the time 01:00 is converted to 100.
     */
    @Test
    void timeAsInt() {
        assertEquals(100, trainStation.timeAsInt(LocalTime.parse("01:00")), "Test for timeAsInt is working.");
    }

    /*
      * This is a  test to check if checking a train number is working.
      * It checks if the method returns true when checking the train number 123.
     */
    @Test
    void checkTrainNumber() {
        assertTrue(trainStation.checkTrainNumber(123), "Test for checkTrainNumber is working.");
    }
}