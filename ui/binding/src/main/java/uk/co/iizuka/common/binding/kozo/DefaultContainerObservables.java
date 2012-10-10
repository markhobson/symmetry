/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultContainerObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.symmetry.ui.Container;

import uk.co.iizuka.common.binding.kozo.KozoObservables.ContainerObservables;

/**
 * Default {@code ContainerObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultContainerObservables.java 99159 2012-03-09 16:58:42Z mark@IIZUKA.CO.UK $
 * @see ContainerObservables
 */
class DefaultContainerObservables extends DefaultComponentObservables implements ContainerObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultContainerObservables(Container container)
	{
		super(container);
	}
	
	// ContainerObservables methods -------------------------------------------
	
	// TODO: implement when added
}
