/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/BooleanListModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code BooleanListModel}.
 * 
 * @author Mark Hobson
 * @version $Id: BooleanListModelTest.java 89152 2011-06-21 15:32:40Z mark@IIZUKA.CO.UK $
 * @see BooleanListModel
 */
public class BooleanListModelTest
{
	// fields -----------------------------------------------------------------
	
	private BooleanListModel model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new BooleanListModel();
	}

	// tests ------------------------------------------------------------------
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemWithNegativeIndex()
	{
		model.getItem(-1);
	}
	
	@Test
	public void getItemWithZeroIndex()
	{
		assertEquals(Boolean.TRUE, model.getItem(0));
	}
	
	@Test
	public void getItemWithOneIndex()
	{
		assertEquals(Boolean.FALSE, model.getItem(1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemWithLargeIndex()
	{
		model.getItem(2);
	}
	
	@Test
	public void getItemCount()
	{
		assertEquals(2, model.getItemCount());
	}
}
