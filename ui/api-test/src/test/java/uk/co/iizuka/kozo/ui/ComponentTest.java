/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/ComponentTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.googlecode.jtype.Generic;

import org.junit.Test;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;
import uk.co.iizuka.kozo.ui.test.DummyComponent;

/**
 * Tests {@code Component}.
 * 
 * @author Mark Hobson
 * @version $Id: ComponentTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
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
