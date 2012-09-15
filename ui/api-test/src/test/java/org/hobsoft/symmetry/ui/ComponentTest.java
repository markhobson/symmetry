/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ComponentTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.googlecode.jtype.Generic;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.junit.Test;

/**
 * Tests {@code Component}.
 * 
 * @author Mark Hobson
 * @see Component
 */
public class ComponentTest extends AbstractComponentTest<DummyComponent>
{
	// tests ------------------------------------------------------------------

	@Test
	public void newComponent()
	{
		Component component = getComponent();
		
		assertFalse("Transient", component.isTransient());
		assertTrue("Visible", component.isVisible());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DummyComponent createComponent()
	{
		return new DummyComponent();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<DummyComponent> getComponentType()
	{
		return Generic.get(DummyComponent.class);
	}
}
