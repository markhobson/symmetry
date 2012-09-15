/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.model;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

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
