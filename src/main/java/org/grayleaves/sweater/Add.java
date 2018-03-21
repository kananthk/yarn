package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/v1/add")
public class Add {

	@GET
	@Path("{yards}")
	@Produces(MediaType.APPLICATION_JSON)
	public YarnStatusResponse add(@PathParam("yards") int yards) {
		YarnStatusResponse yarnResponse = new YarnStatusResponse(); 
		yarnResponse.add(yards);
		yarnResponse.delay();
		System.out.println("Status elapsed time: "+yarnResponse.getElapsedTime()+"; response: "+yarnResponse.getResponse()+"; adding: "+yarnResponse.getYarnResponse());
		return yarnResponse;
	}


}
