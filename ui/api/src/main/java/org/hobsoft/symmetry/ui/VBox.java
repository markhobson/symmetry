/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/VBox.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

/**
 * A container that lays its children out along the y-axis. An {@code VBox} is simply a {@code Box} with
 * vertical orientation.
 * 
 * @author Mark Hobson
 * @see Box
 */
public class VBox extends Box
{
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an empty box with vertical orientation.
	 */
	public VBox()
	{
		this(EMPTY_COMPONENT_ARRAY);
	}
	
	/**
	 * Creates a box with vertical orientation and the specified children.
	 * 
	 * @param children
	 *            the components to be added to the box
	 */
	public VBox(Component... children)
	{
		super(Orientation.VERTICAL, children);
	}
}
