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

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Orientation;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.VBox;
import org.hobsoft.symmetry.ui.functor.Closures;
import org.hobsoft.symmetry.ui.functor.Command;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ButtonTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public ButtonTab()
	{
		final Button clickButton = new Button("0");
		clickButton.onAction(new Command()
		{
			@Override
			public void execute()
			{
				int n = Integer.parseInt(clickButton.getText());
				clickButton.setText(String.valueOf(n + 1));
			}
		});
		
		// TODO: use chainable setters when supported
		Button disabledButton = new Button("Format C:");
		disabledButton.setEnabled(false);
		
		Button toolTipButton = new Button("Cheese");
		toolTipButton.setToolTip("A solid food prepared from the pressed curd of milk, often seasoned and aged.");
		toolTipButton.onAction(Closures.nop());
		
		Button mnemonicButton = new Button("Mnemonic");
		mnemonicButton.setMnemonic('M');
		mnemonicButton.onAction(Closures.nop());
		
		Box box = new VBox(
			new GroupBox("Button",
				new Label("A Button allows a user to perform an action:"),
				new Button("Button").onAction(Closures.nop()),
				new Label("Applications receive notification by registering an ActionListener:"),
				clickButton,
				new Label("Buttons can also be disabled to restrict operations:"),
				disabledButton,
				new Label("Tooltips can be added to provide context-sensitive information:"),
				toolTipButton,
				new Label("Mnemonics can also be used to provide keyboard shortcuts:"),
				mnemonicButton
			),
			new GroupBox("ToggleButton",
				new Label("A ToggleButton is like a Button but with a selected state:"),
				new ToggleButton("ToggleButton"),
				new Label("The selected state can be mutally exclusive by using ToggleButtonGroups:"),
				new ToggleButtonGroup(Orientation.HORIZONTAL,
					new ToggleButton("1"),
					new ToggleButton("2"),
					new ToggleButton("3"),
					new ToggleButton("4"),
					new ToggleButton("5")
				)
			)
		);
		
		setText("Button");
		setComponent(box);
	}
}
