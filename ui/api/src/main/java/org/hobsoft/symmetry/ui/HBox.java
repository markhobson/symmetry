/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/HBox.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

/**
 * A container that lays its children out along the x-axis. An {@code HBox} is simply a {@code Box} with
 * horizontal orientation.
 * 
 * @author Mark Hobson
 * @see Box
 */
public class HBox extends Box
{
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an empty box with horizontal orientation.
	 */
	public HBox()
	{
		this(EMPTY_COMPONENT_ARRAY);
	}
	
	/**
	 * Creates a box with horizontal orientation and the specified children.
	 * 
	 * @param children
	 *            the components to be added to the box
	 */
	public HBox(Component... children)
	{
		super(Orientation.HORIZONTAL, children);
	}
}
