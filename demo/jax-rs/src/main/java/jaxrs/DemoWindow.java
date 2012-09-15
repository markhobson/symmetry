/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/jax-rs/src/main/java/jaxrs/DemoWindow.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package jaxrs;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Window;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DemoWindow.java 88032 2011-05-13 19:04:22Z mark@IIZUKA.CO.UK $
 */
public class DemoWindow extends Window
{
	// constructors -----------------------------------------------------------
	
	public DemoWindow()
	{
		super("Kozo JAX-RS Demo");
		
		add(new Label("Hello World!"));
		
		setVisible(true);
	}
}
