/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/common-test-support/src/main/java/uk/co/iizuka/kozo/ui/common/test/hydrate/DummyComponentRehydrator.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.common.test.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.test.DummyComponent;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyComponentRehydrator.java 98880 2012-02-29 17:06:58Z mark@IIZUKA.CO.UK $
 */
public class DummyComponentRehydrator extends NullHierarchicalComponentHydrator<DummyComponent>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(DummyComponent component, HydrationContext context) throws HydrationException
	{
		component.setState(true);
		
		return super.visit(component, context);
	}
}
