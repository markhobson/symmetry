/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/DefaultTreeModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Tests {@code DefaultTreeModel}.
 * 
 * @author Mark Hobson
 * @see DefaultTreeModel
 */
public class DefaultTreeModelTest
{
	// fields -----------------------------------------------------------------
	
	private DefaultTreeModel model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new DefaultTreeModel();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getRootWhenEmpty()
	{
		assertNull(model.getRoot());
	}
}
