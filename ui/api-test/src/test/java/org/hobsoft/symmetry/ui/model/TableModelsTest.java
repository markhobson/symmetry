/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/TableModelsTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code TableModels}.
 * 
 * @author Mark Hobson
 * @see TableModels
 */
@RunWith(JMock.class)
public class TableModelsTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void cacheRowCountWithTableModel()
	{
		final TableModel delegate = mockery.mock(TableModel.class);
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).getRowCount(); will(returnValue(1));
		} });
		
		TableModel actual = TableModels.cacheRowCount(delegate);
		
		// invoke twice and expect call once
		actual.getRowCount();
		actual.getRowCount();
	}
	
	@Test
	public void cacheRowCountWithBeanTableModel()
	{
		// cannot safely mock generic types
		@SuppressWarnings("unchecked")
		final BeanTableModel<Object> delegate = mockery.mock(BeanTableModel.class);
		
		mockery.checking(new Expectations() { {
			oneOf(delegate).getRowCount(); will(returnValue(1));
		} });
		
		TableModel actual = TableModels.cacheRowCount(delegate);
		
		// invoke twice and expect call once
		actual.getRowCount();
		actual.getRowCount();
	}
	
	@Test
	public void addColumn()
	{
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnNames("a", "b");
		model.setColumnClasses(Integer.class, Integer.class);
		model.addRow(1, 2);
		model.addRow(3, 4);
		
		DefaultTableModel expected = new DefaultTableModel();
		expected.setColumnNames("a", "b", "c");
		expected.setColumnClasses(Integer.class, Integer.class, String.class);
		expected.addRow(1, 2, "x");
		expected.addRow(3, 4, "x");
		
		TableModel actual = TableModels.addColumn(model, "c", String.class, "x");
		
		assertEquals(expected, actual);
	}
}
