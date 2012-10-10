/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultWindowObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import uk.co.iizuka.common.binding.kozo.KozoObservables.WindowObservables;
import uk.co.iizuka.kozo.ui.Window;

/**
 * Default {@code WindowObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultWindowObservables.java 97523 2012-01-04 16:57:21Z mark@IIZUKA.CO.UK $
 * @see WindowObservables
 */
class DefaultWindowObservables extends DefaultBoxObservables implements WindowObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultWindowObservables(Window window)
	{
		super(window);
	}
	
	// WindowObservables methods ----------------------------------------------
	
	// TODO: implement when added
}
