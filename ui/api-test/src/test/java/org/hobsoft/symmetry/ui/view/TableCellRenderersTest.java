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
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.FakeEnum;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code TableCellRenderers}.
 * 
 * @author Mark Hobson
 * @see TableCellRenderers
 */
public class TableCellRenderersTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void forFunction()
	{
		Table table = new Table(new DefaultTableModel(new Object[] {new Object()}));
		Label label = TableCellRenderers.forFunction(new StubFunction<String>("x")).getTableCellComponent(table, 0, 0);
		
		assertEquals("x", label.getText());
	}
	
	@Test
	public void forEnumWithWord()
	{
		Table table = new Table(new DefaultTableModel(new Object[] {FakeEnum.WORD}));
		Label label = TableCellRenderers.forEnum().getTableCellComponent(table, 0, 0);
		
		assertEquals("Word", label.getText());
	}
}
