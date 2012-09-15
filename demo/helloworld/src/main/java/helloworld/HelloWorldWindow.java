/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/helloworld/src/main/java/helloworld/HelloWorldWindow.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package helloworld;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Window;

/**
 * The Kozo Hello World demo.
 * 
 * @author Mark Hobson
 */
public class HelloWorldWindow extends Window
{
	public HelloWorldWindow()
	{
		super("Hello World Demo");
		
		add(new Label("Hello World!"));
		
		setVisible(true);
	}
}
