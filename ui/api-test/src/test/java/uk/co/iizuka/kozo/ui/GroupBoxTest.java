/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/GroupBoxTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static org.junit.Assert.assertEquals;

import com.googlecode.jtype.Generic;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.kozo.ui.test.AbstractComponentTest;

/**
 * Tests {@code GroupBox}.
 * 
 * @author Mark Hobson
 * @version $Id: GroupBoxTest.java 95554 2011-11-25 23:15:51Z mark@IIZUKA.CO.UK $
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
