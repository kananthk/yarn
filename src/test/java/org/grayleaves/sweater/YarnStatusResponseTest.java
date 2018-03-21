package org.grayleaves.sweater;

import static org.junit.Assert.*;

import org.grayleaves.utility.Clock;
import org.junit.Before;
import org.junit.Test;

public class YarnStatusResponseTest {


	private YarnStatusResponse status;
	@Before
	public void setUp()
    {
        Clock.reset();
        StatusResponse.forceDelay(0); 
        StatusResponse.throwExceptions(false);
        YarnStatusResponse.reset(); 
    }
	@Test
	public void colorDefaultsToGray() {
		status = new YarnStatusResponse(); 
		assertEquals("GRAY", status.getColor());
	}
	@Test
	public void severalColorsSupported() {
		YarnStatusResponse.color(YarnEnum.WHITE);
		status = new YarnStatusResponse(); 
		assertEquals("WHITE", status.getColor());
	}
	@Test
	public void useDefaultsToNoYarn() {
		status = new YarnStatusResponse(); 
		status.use(1);
		assertEquals("1 yards GRAY yarn requested; 0 yards used", status.getYarnResponse());
		assertEquals(0, status.getUsed());
	}
	@Test
	public void addYarnOfCurrentColor() {
		YarnStatusResponse.color(YarnEnum.BLACK);
		status = new YarnStatusResponse(); 
		status.add(2);
		assertEquals("2 yards BLACK yarn added; 2 yards total", status.getYarnResponse());
		status.add(3);
		assertEquals("3 yards BLACK yarn added; 5 yards total", status.getYarnResponse());
		status.add(4);
		assertEquals("4 yards BLACK yarn added; 9 yards total", status.getYarnResponse());
	}
	@Test
	public void usePossibleOnceYarnAdded() {
		status = new YarnStatusResponse();
		YarnStatusResponse.color(YarnEnum.BROWN);
		status.add(3);
		status.use(2);
		assertEquals("2 yards BROWN yarn requested; 2 yards used", status.getYarnResponse());
		assertEquals(2, status.getUsed());
		status.use(4);
		assertEquals("4 yards BROWN yarn requested; 1 yards used", status.getYarnResponse());
		assertEquals(1, status.getUsed());
	}
}
