/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTreeHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedTreeHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTreeVisitor;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Tree} component using an HTML {@code <ul/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTreeHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Tree
 * @param <T>
 *            the tree type this visitor can visit
 */
public class HtmlTreeHydrator<T extends Tree> extends PhasedTreeHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTreeHydrator()
	{
		super(asTreeVisitor(new PhasedBeanHydrator<T>()));
		
		setDelegate(DEHYDRATE, new HtmlTreeDehydrator<T>());
	}
}
