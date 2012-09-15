/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/AbstractHtmlContainerHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedContainerHydrator;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asContainerVisitor;

/**
 * Phased base hydrator for dehydrating and rehydrating a {@code Container} component using HTML.
 * 
 * @author Mark Hobson
 * @see Container
 * @param <T>
 *            the box type this visitor can visit
 */
public class AbstractHtmlContainerHydrator<T extends Container> extends PhasedContainerHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public AbstractHtmlContainerHydrator()
	{
		super(asContainerVisitor(new PhasedBeanHydrator<T>()));
	}
}
