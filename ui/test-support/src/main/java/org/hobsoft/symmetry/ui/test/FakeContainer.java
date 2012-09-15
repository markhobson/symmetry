/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/test-support/src/main/java/uk/co/iizuka/kozo/ui/test/FakeContainer.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.test;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: FakeContainer.java 99113 2012-03-09 15:21:45Z mark@IIZUKA.CO.UK $
 */
public class FakeContainer extends Container
{
	// constructors -----------------------------------------------------------

	public FakeContainer()
	{
		super();
	}

	public FakeContainer(Component... children)
	{
		super(children);
	}

	// Container methods ------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, FakeContainer.class, this, parameter);
	}
}
