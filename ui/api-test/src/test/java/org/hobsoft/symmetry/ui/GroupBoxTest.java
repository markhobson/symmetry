/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/GroupBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jtype.Generic;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code GroupBox}.
 * 
 * @author Mark Hobson
 * @see GroupBox
 */
public class GroupBoxTest extends AbstractComponentTest<GroupBox>
{
	// fields -----------------------------------------------------------------
	
	private GroupBox groupBox;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		groupBox = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setTitle()
	{
		groupBox.setTitle("a");
		
		assertEquals("a", groupBox.getTitle());
	}
	
	@Test
	public void setTitleWithNull()
	{
		groupBox.setTitle(null);
		
		assertEquals("", groupBox.getTitle());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GroupBox createComponent()
	{
		return new GroupBox();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<GroupBox> getComponentType()
	{
		return Generic.get(GroupBox.class);
	}
}
