/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/HtmlLabel.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlLabel.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
public class HtmlLabel extends Label
{
	// TODO: image doesn't make much sense here
	
	// constructors -----------------------------------------------------------
	
	public HtmlLabel(Style... styles)
	{
		super(styles);
	}
	
	public HtmlLabel(String text, Style... styles)
	{
		super(text, styles);
	}

	public HtmlLabel(Image image, Style... styles)
	{
		super(image, styles);
	}

	public HtmlLabel(String text, Image image, Style... styles)
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
		return accept(visitor, HtmlLabel.class, this, parameter);
	}
}
