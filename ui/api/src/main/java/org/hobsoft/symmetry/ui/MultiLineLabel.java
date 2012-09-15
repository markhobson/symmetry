/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/MultiLineLabel.java $
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
 * @version $Id: MultiLineLabel.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
public class MultiLineLabel extends Label
{
	// TODO: does image make much sense here?
	
	// constructors -----------------------------------------------------------
	
	public MultiLineLabel(Style... styles)
	{
		super(styles);
	}

	public MultiLineLabel(String text, Style... styles)
	{
		super(text, styles);
	}

	public MultiLineLabel(Image image, Style... styles)
	{
		super(image, styles);
	}

	public MultiLineLabel(String text, Image image, Style... styles)
	{
		super(text, image, styles);
	}

	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, MultiLineLabel.class, this, parameter);
	}
}
