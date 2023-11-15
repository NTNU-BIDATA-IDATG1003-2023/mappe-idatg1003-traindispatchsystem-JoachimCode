package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainStationTest {
    TrainStation trainStation;

    @BeforeEach
    void setUp() {
        trainStation = new TrainStation();
        trainStation.addTrain(new TrainDeparture(1200, "L1", 123, "Oslo", 1, 0));
        trainStation.addTrain(new TrainDeparture(1400, "L2", 142, "Bergen", 2, 0));
        trainStation.addTrain(new TrainDeparture(1200, "F1", 162, "Haugesund", 3, 0));
    }

    @Test
    void addTrainPositiveTest() {
        trainStation.addTrain(new TrainDeparture(1500, "L8", 977, "Kirkenes", 1, 23));
        assertEquals("Kirkenes",trainStation.getTrainFromTrainNumber(977).getDestination(), "Positive test for adding train is working.");
    }

    @Test
    void addTrainNegativeTestTest() {
        assertEquals(false,trainStation.addTrain(new TrainDeparture(1500, "L8", 123, "Kirkenes", 1, 23)), "Negative test for adding train is working.");
    }

    @Test
    void getTrainFromTrainNumber() {
    }

    @Test
    void getTrainFromDestination() {
    }

    @Test
    void removeEarlierDepartures() {
    }

    @Test
    void getSortedDepartureList() {
    }

    @Test
    void changeClock() {
    }

    @Test
    void checkTrainNumber() {
    }
}