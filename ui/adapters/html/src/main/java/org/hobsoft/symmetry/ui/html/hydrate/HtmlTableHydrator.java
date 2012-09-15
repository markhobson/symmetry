/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTableHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedTableHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTableVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Table} component using an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
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
