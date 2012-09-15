/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/CheckBox.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: CheckBox.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
public class CheckBox extends ToggleButton
{
	// constructors -----------------------------------------------------------
	
	public CheckBox()
	{
		this("");
	}
	
	public CheckBox(String text)
	{
		this(text, false);
	}
	
	public CheckBox(String text, boolean selected)
	{
		super(text, selected);
	}

	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, CheckBox.class, this, parameter);
	}
}
