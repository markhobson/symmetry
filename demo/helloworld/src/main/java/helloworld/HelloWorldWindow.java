/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/helloworld/src/main/java/helloworld/HelloWorldWindow.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package helloworld;

import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Window;

/**
 * The Kozo Hello World demo.
 * 
 * @author Mark Hobson
 * @version $Id: HelloWorldWindow.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
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
