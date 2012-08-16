/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Radio.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Radio.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
public class Radio extends ToggleButton
{
	// constructors -----------------------------------------------------------
	
	public Radio()
	{
		this("");
	}
	
	public Radio(String text)
	{
		this(text, false);
	}
	
	public Radio(String text, boolean selected)
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
		return accept(visitor, Radio.class, this, parameter);
	}
}
