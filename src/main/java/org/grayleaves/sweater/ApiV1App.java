package org.grayleaves.sweater;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


	@ApplicationPath("/api/*")
	public class ApiV1App extends Application {
	    public ApiV1App() {
	    	System.out.println("yarn reached");
	    	configure();
	    }
	    
    public static void configure() {
    	determineInstance();
    }
	public static void determineInstance() {
		HealthResponse.INSTANCE_VALUE = System.getenv(HealthResponse.INSTANCE_KEY);
	}

}
