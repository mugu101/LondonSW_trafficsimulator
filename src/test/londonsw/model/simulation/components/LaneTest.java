package londonsw.model.simulation.components;

import londonsw.model.simulation.components.vehicles.Car;
import londonsw.model.simulation.components.vehicles.Vehicle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test class for Lane
 */
public class LaneTest {

    Lane x;
    Coordinate a;
    Coordinate b;

    @Before
    public void setUpForTests() throws NotALaneException {
        a = new Coordinate(0, 1);
        b = new Coordinate(2, 1);
        x = new Lane(a, b);
    }

    @Test
    public void testGetLaneLengthValid() {
        int length = x.getLength();
        assertEquals(length,3);
    }

    @Test(expected = NotALaneException.class)
    public void testGetLaneLengthInvalid() throws NotALaneException {
        Lane l = new Lane(new Coordinate(2,3),new Coordinate(3,4));
    }

    @Test
    public void testGetEntryCoordinate()  {
        Coordinate entry = x.getEntry();
        assertNotNull(entry);
        assertEquals(entry, new Coordinate(0,1));
    }

    @Test
    public void testGetExitCoordinate() {
        Coordinate exit = x.getExit();
        assertNotNull(exit);
        assertEquals(exit, new Coordinate(2,1));
    }

    @Test
    public void testGetLength() {
        int length = x.getLength();
        assertEquals(length,3);

    }

    @Test
    public void testIsCellEmpty() {
        boolean empty = x.isCellEmpty(2);
        assertTrue(empty);
    }

    @Test
    public void testIsCellNotEmpty() {
        Vehicle v = new Car();
        x.setCell(v,2);
        boolean empty = x.isCellEmpty(2);
        assertFalse(empty);
    }
}