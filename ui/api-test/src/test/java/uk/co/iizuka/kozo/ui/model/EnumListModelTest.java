/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/EnumListModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code EnumListModel}.
 * 
 * @author Mark Hobson
 * @version $Id: EnumListModelTest.java 87343 2011-04-27 15:55:00Z mark@IIZUKA.CO.UK $
 * @see EnumListModel
 */
public class EnumListModelTest
{
	// types ------------------------------------------------------------------
	
	private enum TestEnum
	{
		A,
		B,
		C;
	}
	
	// fields -----------------------------------------------------------------
	
	private EnumListModel<TestEnum> model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new EnumListModel<TestEnum>(TestEnum.class);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getItem()
	{
		assertEquals("item", TestEnum.A, model.getItem(0));
	}
	
	@Test
	public void getItemCount()
	{
		assertEquals("item count", 3, model.getItemCount());
	}
}
