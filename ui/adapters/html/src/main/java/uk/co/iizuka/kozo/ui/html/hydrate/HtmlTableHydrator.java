/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTableHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asTableVisitor;

import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedBeanHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedTableHydrator;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Table} component using an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTableHydrator.java 99705 2012-03-20 16:49:55Z mark@IIZUKA.CO.UK $
 * @see Table
 * @param <T>
 *            the table type this visitor can visit
 */
public class HtmlTableHydrator<T extends Table> extends PhasedTableHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTableHydrator()
	{
		// TODO: remove unnecessary actual type argument when javac can cope
		super(asTableVisitor(ComponentHydrators.<T>skipChildrenOnRehydrate(new PhasedBeanHydrator<T>())));
		
		setDelegate(DEHYDRATE, new HtmlTableDehydrator<T>());
	}
}
