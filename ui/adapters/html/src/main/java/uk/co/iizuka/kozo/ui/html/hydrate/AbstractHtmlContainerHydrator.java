/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/AbstractHtmlContainerHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asContainerVisitor;

import uk.co.iizuka.kozo.ui.Container;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedBeanHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedContainerHydrator;

/**
 * Phased base hydrator for dehydrating and rehydrating a {@code Container} component using HTML.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractHtmlContainerHydrator.java 99169 2012-03-09 17:09:04Z mark@IIZUKA.CO.UK $
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
