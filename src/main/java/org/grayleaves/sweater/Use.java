package org.grayleaves.sweater;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/v1/use")
public class Use {

	@GET
	@Path("{yards}")
	@Produces(MediaType.APPLICATION_JSON)
	public YarnStatusResponse use(@PathParam("yards") int yards) {
		YarnStatusResponse yarnResponse = new YarnStatusResponse(); 
		yarnResponse.use(yards);
		yarnResponse.delay();
		System.out.println("Status elapsed time: "+yarnResponse.getElapsedTime()+"; response: "+yarnResponse.getResponse()+"; using: "+yarnResponse.getYarnResponse());
		return yarnResponse;
	}


}
