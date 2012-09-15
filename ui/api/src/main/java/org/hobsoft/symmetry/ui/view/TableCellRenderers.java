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

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.functor.Function;
import org.hobsoft.symmetry.ui.functor.Functions;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class TableCellRenderers
{
	// constructors -----------------------------------------------------------
	
	private TableCellRenderers()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static LabelTableCellRenderer forFunction(final Function<Object, String> textFunction)
	{
		return new LabelTableCellRenderer()
		{
			@Override
			public Label getTableCellComponent(Table table, int row, int column)
			{
				Object value = table.getModel().getValueAt(row, column);
				String text = textFunction.apply(value);
				
				setText(text);
				
				return this;
			}
		};
	}
	
	public static LabelTableCellRenderer forEnum()
	{
		// TODO: cache
		// TODO: resolve how to avoid using raw Enum type
		// TODO: compose functions when cast function implemented
		return new LabelTableCellRenderer()
		{
			@Override
			public Label getTableCellComponent(Table table, int row, int column)
			{
				Enum value = (Enum) table.getModel().getValueAt(row, column);
				// TODO: string cast for javac
				String text = (String) Functions.<Enum>enumTitleCase().apply(value);
				
				setText(text);
				
				return this;
			}
		};
	}
}
