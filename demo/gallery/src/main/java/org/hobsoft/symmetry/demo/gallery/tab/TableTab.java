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
package org.hobsoft.symmetry.demo.gallery.tab;

import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.SortableTable;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.VBox;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.hobsoft.symmetry.ui.model.TableModel;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TableTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public TableTab()
	{
		setText("Table");
		setComponent(new VBox(createTableBox(), createSortableTableBox()));
	}

	// private methods --------------------------------------------------------
	
	private static GroupBox createTableBox()
	{
		GroupBox box = new GroupBox("Table");
		
		box.add(new Label("A Table can display data in tabular form:"));
		
		Table table = new Table(createTableModel());
		table.setVisibleRowCount(5);
		box.add(table);
		
		box.add(new Label("The data is provided by a TableModel and can be paged by any number of rows, in this case "
			+ "five."));
		
		return box;
	}
	
	private static GroupBox createSortableTableBox()
	{
		GroupBox box = new GroupBox("SortableTable");
		
		box.add(new Label("A SortableTable also allows the user to sort data by clicking on the headings:"));
		
		SortableTable sortableTable = new SortableTable(createTableModel());
		sortableTable.setVisibleRowCount(5);
		box.add(sortableTable);
		
		return box;
	}

	private static TableModel createTableModel()
	{
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnNames("Animal", "Colour", "Food");
		
		model.addRow("Monkey", "Brown", "Bananas");
		model.addRow("Pig", "Pink", "Swill");
		model.addRow("Giraffe", "Yellow", "Leaves");
		model.addRow("Tiger", "Yellow", "Meat");
		model.addRow("Mouse", "Grey", "Cheese");
		model.addRow("Conch", "Light Brown", "Algae");
		model.addRow("Hippopotomous", "Grey", "Water Lettuce");
		model.addRow("Doormouse", "Brown", "Seeds");
		model.addRow("Stick Insect", "Green", "Other Insects");
		model.addRow("Bat", "Dark Brown", "Insects");
		
		return model;
	}
}
