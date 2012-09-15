/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/jax-rs/src/main/java/jaxrs/DemoResource.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * 
 * @author Mark Hobson
 */
@Path("/")
public class DemoResource
{
	// public methods ---------------------------------------------------------

	@GET
	@Produces(MediaType.TEXT_HTML)
	public DemoWindow getWindow()
	{
		DemoWindow window = new DemoWindow();
		
		// TODO: rehydrate window
		
		return window;
	}
}
