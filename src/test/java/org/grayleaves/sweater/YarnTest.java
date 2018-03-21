package org.grayleaves.sweater;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.grayleaves.utility.Clock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//FIXME would like to use glassfish for serverless testing without introducing glassfish in prod
// would need to instantiate a javax.ws.rs.core.Application for testing, 
// not the subclass org.glassfish.jersey.server.ResourceConfig, as here 
public class YarnTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new TestingApiV1App();
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		Clock.setDateForTesting("10/15/2005 12:00:14 PM");
		StatusResponse.forceDelay(0); 
		StatusResponse.hang(false); 
		StatusResponse.throwExceptions(false); 
		YarnStatusResponse.reset(); 
	}
	
	@Test
	public void addMultipleReturnsYarnResponseAlongWithNormalStatusResponse() {
		ControlResponse controlResponse = target("v1/color/gray").request().get(ControlResponse.class);  
		assertEquals("GRAY", controlResponse.getColor()); 

		YarnStatusResponse yarnResponse = target("v1/add/5").request().get(YarnStatusResponse.class);  
		assertEquals("5 yards GRAY yarn added; 5 yards total", yarnResponse.getYarnResponse());
		yarnResponse = target("v1/add/4").request().get(YarnStatusResponse.class);  
		assertEquals("4 yards GRAY yarn added; 9 yards total", yarnResponse.getYarnResponse());
		yarnResponse = target("v1/add/3").request().get(YarnStatusResponse.class);  
		assertEquals("3 yards GRAY yarn added; 12 yards total", yarnResponse.getYarnResponse());


		assertEquals(StatusResponse.NAME, yarnResponse.getName()); 
		assertEquals(0, yarnResponse.getDelay()); 
		assertEquals(0, yarnResponse.getElapsedTime()); 
		assertEquals(StatusResponse.NORMAL, yarnResponse.getResponse()); 
	}
	@Test
	public void addAndUseReturnYarnResponseAlongWithNormalStatusResponse() {
		ControlResponse controlResponse = target("v1/color/gray").request().get(ControlResponse.class);  
		assertEquals("GRAY", controlResponse.getColor()); 
		
		YarnStatusResponse yarnResponse = target("v1/add/5").request().get(YarnStatusResponse.class);  
		assertEquals("5 yards GRAY yarn added; 5 yards total", yarnResponse.getYarnResponse());
		
		YarnStatusResponse useResponse = target("v1/use/3").request().get(YarnStatusResponse.class);  
		assertEquals("3 yards GRAY yarn requested; 3 yards used", useResponse.getYarnResponse());
		
		assertEquals(StatusResponse.NAME, yarnResponse.getName()); 
		assertEquals(0, yarnResponse.getDelay()); 
		assertEquals(0, yarnResponse.getElapsedTime()); 
		assertEquals(StatusResponse.NORMAL, yarnResponse.getResponse()); 
	}
	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		Clock.reset(); 
		StatusResponse.forceDelay(0); 
		StatusResponse.hang(false); 
		StatusResponse.throwExceptions(false); 
	}

	@ApplicationPath("/api/*")
	private class TestingApiV1App extends ResourceConfig {
	    public TestingApiV1App() {
	        packages("org.grayleaves.sweater");
	    }	
    }
}
