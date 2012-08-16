/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/kozoset/src/main/java/kozoset/tab/LayoutTab.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package kozoset.tab;

import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.HBox;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TabBox;
import uk.co.iizuka.kozo.ui.VBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: LayoutTab.java 99566 2012-03-15 16:00:09Z mark@IIZUKA.CO.UK $
 */
public class LayoutTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public LayoutTab()
	{
		Box box = new VBox(
			new GroupBox("HBox",
				new Label("A HBox provides a horizontal box layout:"),
				new HBox(
					new Label("A"),
					new Label("B"),
					new Label("C")
				)
			),
			new GroupBox("VBox",
				new Label("Similarly, a VBox provides a vertical box layout:"),
				new VBox(
					new Label("A"),
					new Label("B"),
					new Label("C")
				)
			),
			new GroupBox("Grid",
				new Label("A Grid provides a column based layout:"),
				new Grid(3,
					new Label("A"),
					new Label("B"),
					new Label("C"),
					new Label("D"),
					new Label("E"),
					new Label("F")
				)
			),
			new GroupBox("GroupBox",
				new Label("A GroupBox allows related items to be grouped together under a heading:"),
				new GroupBox("Cheese",
					new VBox(
						new Label("Brie"),
						new Label("Blue"),
						new Label("Cheddar")
					)
				)
			),
			new GroupBox("TabBox",
				new Label("A TabBox allows related items to be grouped together within tabs:"),
				new TabBox(
					new Tab("Brie",
						new Label("A mold-ripened, whole-milk cheese with a whitish rind and a soft, light yellow "
							+ "center.")
					),
					new Tab("Blue",
						new Label("A semisoft cheese made of cow's milk and having a greenish-blue mold and strong "
							+ "flavor.")
					),
					new Tab("Cheddar",
						new Label("Any of several types of smooth hard cheese varying in flavor from mild to extra "
							+ "sharp.")
					)
				)
			)
		);
		
		setText("Layout");
		setComponent(box);
	}
}
