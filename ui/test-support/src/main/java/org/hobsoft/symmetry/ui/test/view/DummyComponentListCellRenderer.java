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
package org.hobsoft.symmetry.ui.test.view;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.view.ListCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the item type
 */
public class DummyComponentListCellRenderer<T> extends DummyComponent implements ListCellRenderer<T>
{
	// ListCellRenderer methods -------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getListCellComponent(ComboBox<T> comboBox, int index)
	{
		return this;
	}
}