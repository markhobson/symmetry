/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultComponentObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import uk.co.iizuka.common.binding.kozo.KozoObservables.ComponentObservables;
import uk.co.iizuka.kozo.ui.Component;

/**
 * Default {@code ComponentObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultComponentObservables.java 97523 2012-01-04 16:57:21Z mark@IIZUKA.CO.UK $
 * @see ComponentObservables
 */
class DefaultComponentObservables implements ComponentObservables
{
	// fields -----------------------------------------------------------------
	
	private final Component component;
	
	// constructors -----------------------------------------------------------
	
	public DefaultComponentObservables(Component component)
	{
		this.component = component;
	}
	
	// ComponentObservables methods -------------------------------------------
	
	// TODO: implement when added
	
	// public methods ---------------------------------------------------------
	
	public Component getComponent()
	{
		return component;
	}
}
