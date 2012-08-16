/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/DefaultTreeModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@code DefaultTreeModel}.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultTreeModelTest.java 95332 2011-11-18 12:22:38Z mark@IIZUKA.CO.UK $
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
