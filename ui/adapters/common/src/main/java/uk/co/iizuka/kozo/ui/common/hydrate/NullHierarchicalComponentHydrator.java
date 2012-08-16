/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common/src/main/java/uk/co/iizuka/kozo/ui/common/hydrate/NullHierarchicalComponentHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.common.hydrate;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.traversal.NullHierarchicalComponentVisitor;

/**
 * Convenience class that adapts a {@code NullHierarchicalComponentVisitor} to a {@code HierarchicalComponentHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: NullHierarchicalComponentHydrator.java 98863 2012-02-29 10:48:53Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
public class NullHierarchicalComponentHydrator<T extends Component>
	extends NullHierarchicalComponentVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// simple subtype
}
