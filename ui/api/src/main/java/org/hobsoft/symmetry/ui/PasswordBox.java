/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/PasswordBox.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PasswordBox.java 96224 2011-12-09 16:24:18Z mark@IIZUKA.CO.UK $
 */
public class PasswordBox extends TextBox
{
	// constructors -----------------------------------------------------------

	public PasswordBox()
	{
		super();
	}

	public PasswordBox(int columns)
	{
		super(columns);
	}

	public PasswordBox(String text)
	{
		super(text);
	}

	public PasswordBox(String text, int columns)
	{
		super(text, columns);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, PasswordBox.class, this, parameter);
	}
}
