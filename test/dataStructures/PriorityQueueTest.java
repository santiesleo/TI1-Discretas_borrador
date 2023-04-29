package test.dataStructures;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import dataStructures.PriorityQueue.PriorityQueue;
import exception.PriorityQueueException;
import model.Passenger;
import model.PassengerClass;

public class PriorityQueueTest {
    private PriorityQueue<Integer, Passenger> priorityQueue;
    // Objects used
    private Passenger p1 = new Passenger("Juan", "jdColonia", "12345", PassengerClass.ECONOMY, "A8", 18, 50, false, false);
    private Passenger p2 = new Passenger("Santiago", "santiesleo", "23456", PassengerClass.FIRST_CLASS, "B1", 22, 1200,
            false, false);
    private Passenger p3 = new Passenger("Esteban", "EstebanGZam", "34567", PassengerClass.FIRST_CLASS, "C2", 40, 150,
            true, false);
    private Passenger p4 = new Passenger("Javier", "Roman", "56789", PassengerClass.ECONOMY, "C6", 75, 3000, true, false);
    private Passenger p5 = new Passenger("Juan", "Perez", "45678", PassengerClass.ECONOMY, "B5", 33, 0, false, false);

    // setups
    public void setup1() {
        priorityQueue = new PriorityQueue<>();
    }

    public void setup2() {
        priorityQueue = new PriorityQueue<>();
        priorityQueue.insert(40, p2);
        priorityQueue.insert(30, p3);
        priorityQueue.insert(50, p1);
    }

    public void setup3() {
        priorityQueue = new PriorityQueue<>();
        priorityQueue.insert(42, p1);
        priorityQueue.insert(23, p2);
        priorityQueue.insert(56, p3);
        priorityQueue.insert(56, p4);
        priorityQueue.insert(40, p5);
    }

    // insert() tests
    // test 1
    @Test
    public void testInsertIntoEmptyQueue() {
        setup1();
        priorityQueue.insert(42, p1);
        assert 1 == priorityQueue.size();
        assertEquals(p1, priorityQueue.extractMax());
    }

    // test 2
    @Test
    public void testInsertMultipleElements() {
        setup1();
        priorityQueue.insert(42, p1);
        priorityQueue.insert(23, p2);
        priorityQueue.insert(56, p3);
        assert 3 == priorityQueue.size();
        assertEquals(p3, priorityQueue.extractMax());
    }

    // test 3
    @Test
    public void testInsertElementsWithSamePriority() {
        setup1();
        priorityQueue.insert(42, p1);
        priorityQueue.insert(56, p2);
        priorityQueue.insert(56, p3);
        priorityQueue.insert(23, p4);
        assert 4 == priorityQueue.size();
        assertEquals(p2, priorityQueue.extractMax());
    }

    // maximum() test
    // test 1
    @Test
    public void testCorrectMaximum() {
        setup2();
        assert 3 == priorityQueue.size();
        assertEquals(p1, priorityQueue.maximum());
    }

    // test 2
    @Test
    public void testMaximumInEmptyQueue() {
        setup1();
        assertNull(priorityQueue.maximum());
    }

    // test 3
    @Test
    public void testMaximumAfterExtract() {
        setup2();
        assertEquals(p1, priorityQueue.maximum());
        priorityQueue.extractMax();
        assertEquals(p2, priorityQueue.maximum());
        priorityQueue.extractMax();
        assertEquals(p3, priorityQueue.maximum());
    }

    // extractMax() test
    // test 1
    @Test
    public void testExtractMaxNonEmptyPriorityQueue() {
        setup2();
        assertEquals(p1, priorityQueue.extractMax());
        assertEquals(p2, priorityQueue.extractMax());
        assertEquals(p3, priorityQueue.extractMax());
    }

    // test 2
    @Test
    public void testExtractMaxInEmptyPriorityQueue() {
        setup1();
        assertThrows(PriorityQueueException.class, () -> priorityQueue.extractMax());
    }

    // test 3
    @Test
    public void testExtractMaxDuplicatePriorityElements() {
        setup3();
        assertEquals(p3, priorityQueue.extractMax());
        assertEquals(p4, priorityQueue.extractMax());
        assertEquals(p1, priorityQueue.extractMax());
        assertEquals(p5, priorityQueue.extractMax());
        assertEquals(p2, priorityQueue.extractMax());
    }

    // increaseKey() test
    // test 1
    @Test
    public void testIncreaseKey() {
        setup2();
        priorityQueue.increaseKey(1, 57);
        assertEquals(p3, priorityQueue.extractMax());
        assertEquals(p1, priorityQueue.extractMax());
        assertEquals(p2, priorityQueue.extractMax());
    }

    // test 2
    @Test(expected = PriorityQueueException.class)
    public void testIncreaseKeyInvalidNewKey() {
        setup1();
        priorityQueue.insert(42, p1);
        priorityQueue.increaseKey(0, 23);
    }

    // test 3
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIncreaseKeyInvalidIndex() {
        setup1();
        priorityQueue.insert(42, p1);
        priorityQueue.increaseKey(1, 56);
    }

}
