/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/kozoset/src/main/java/kozoset/tab/TableTab.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package kozoset.tab;

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.SortableTable;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.VBox;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableTab.java 99566 2012-03-15 16:00:09Z mark@IIZUKA.CO.UK $
 */
public class TableTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public TableTab()
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
		
		// TODO: use chainable setters when supported
		Table table = new Table(model);
		table.setVisibleRowCount(5);
		
		SortableTable sortableTable = new SortableTable(model);
		sortableTable.setVisibleRowCount(5);
		
		Box box = new VBox(
			new GroupBox("Table",
				new Label("A Table can display data in tabular form:"),
				table,
				new Label("The data is provided by a TableModel and can be paged by any number of rows, in this case "
					+ "five.")
			),
			new GroupBox("SortableTable",
				new Label("A SortableTable also allows the user to sort data by clicking on the headings:"),
				sortableTable
			)
		);
		
		setText("Table");
		setComponent(box);
	}
}
