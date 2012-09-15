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
package kozoset.tab;

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.HBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.VBox;

/**
 * 
 * 
 * @author Mark Hobson
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
