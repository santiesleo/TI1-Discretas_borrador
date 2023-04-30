package test.dataStructures;

import dataStructures.Hash.Hash;
import exception.HashException;
import model.Passenger;
import model.PassengerClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

public class HashTest {

	private Hash<String, Passenger> passengerHash;

	// Objects used
	private final int NUMBER_OF_PASSENGERS = 5;
	private final Passenger p1 = new Passenger("Miguel", "Torres", "12345", PassengerClass.ECONOMY, "A8", 18, 50, false, false);
	private final Passenger p2 = new Passenger("Daniel", "Gonzales", "23456", PassengerClass.FIRST_CLASS, "B1", 22, 1200, false, false);
	private final Passenger p3 = new Passenger("Maria", "Gomez", "34567", PassengerClass.FIRST_CLASS, "C2", 40, 150, true, false);
	private final Passenger p4 = new Passenger("Juan", "Perez", "45678", PassengerClass.ECONOMY, "B5", 33, 0, false, false);
	private final Passenger p5 = new Passenger("Javier", "Roman", "56789", PassengerClass.ECONOMY, "C6", 75, 3000, true, false);

	// Setups
	public void setup1() {
		passengerHash = new Hash<>(NUMBER_OF_PASSENGERS);
	}

	public void setup2() {
		passengerHash = new Hash<>(NUMBER_OF_PASSENGERS);
		try {
			passengerHash.insert(p1.getIdentification(), p1);
			passengerHash.insert(p2.getIdentification(), p2);
		} catch (HashException he) {
			fail(he.getMessage());
		}
	}

	public void setup3() {
		passengerHash = new Hash<>(NUMBER_OF_PASSENGERS);
		try {
			passengerHash.insert(p1.getIdentification(), p1);
			passengerHash.insert(p2.getIdentification(), p2);
			passengerHash.insert(p3.getIdentification(), p3);
			passengerHash.insert(p4.getIdentification(), p4);
			passengerHash.insert(p5.getIdentification(), p5);
		} catch (HashException he) {
			fail(he.getMessage());
		}
	}

	// Insert(): test 1
	@Test
	public void testInsertTwoObjects() {
		setup1();
		try {
			passengerHash.insert(p1.getIdentification(), p1);
			passengerHash.insert(p2.getIdentification(), p2);
			assert 2 == passengerHash.size();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	// Insert(): test 2
	@Test
	public void testInsertHashOverflow() {
		setup3();
		Passenger p6 = new Passenger("Esteban", "GZam", "54321", PassengerClass.FIRST_CLASS, "A1", 18, 2000, false, false);
		assertThrows(HashException.class, () -> passengerHash.insert(p6.getIdentification(), p6));
	}

	// Insert(): test 3
	@Test
	public void testInsertElementWithTheSameKey() {
		setup2();

		Passenger p6 = new Passenger("Juan", "jdColonia", "12345", PassengerClass.FIRST_CLASS, "A3", 18, 3500, false, false);
		assertThrows(HashException.class, () -> passengerHash.insert(p6.getIdentification(), p6));
	}

	// Search(): test 1
	@Test
	public void testSearchElement() {
		setup2();
		try {
			Passenger p6 = new Passenger("Santiago", "santiesleo", "98765", PassengerClass.FIRST_CLASS, "B2", 19, 1500, false, false);
			passengerHash.insert(p6.getIdentification(), p6);
			assertEquals(p6, passengerHash.search("98765"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	// Search(): test 2
	@Test
	public void testSearchElementInEmptyHash() {
		setup1();
		assertNull(passengerHash.search(p1.getIdentification()));
	}

	// Search(): test 3
	@Test
	public void testSearchWithNoEnteredKey() {
		setup3();
		assertNull(passengerHash.search("54321"));
	}

	// Delete(): test 1
	@Test
	public void testDeleteTheUniqueObjectInserted() {
		setup1();
		try {
			passengerHash.insert(p1.getIdentification(), p1);
			passengerHash.delete(p1.getIdentification());
			assert 0 == passengerHash.size();
		} catch (HashException he) {
			fail(he.getMessage());
		}
	}

	// Delete(): test 2
	@Test
	public void testDeleteWithNotEnteredObjects() {
		setup3();
		passengerHash.delete("46789");
		passengerHash.delete("98765");
		assert 5 == passengerHash.size();
	}

	// Delete(): test 3
	@Test
	public void testDeleteMultipleElements() {
		setup3();
		passengerHash.delete("12345");
		passengerHash.delete("23456");
		passengerHash.delete("45678");
		assertNull(passengerHash.search("12345"));
		assertNull(passengerHash.search("23456"));
		assertNull(passengerHash.search("45678"));
		assert 2 == passengerHash.size();
	}

}