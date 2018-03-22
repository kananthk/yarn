package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/v1/zero")
public class Zero {

	@GET
	@Path("{yards}")
	@Produces(MediaType.APPLICATION_JSON)
	public YarnStatusResponse zero() {
		YarnStatusResponse yarnResponse = new YarnStatusResponse(); 
		yarnResponse.zero();
		yarnResponse.delay();
		System.out.println("Status elapsed time: "+yarnResponse.getElapsedTime()+"; response: "+yarnResponse.getResponse()+"; resetting: "+yarnResponse.getYarnResponse());
		return yarnResponse;
	}


}
