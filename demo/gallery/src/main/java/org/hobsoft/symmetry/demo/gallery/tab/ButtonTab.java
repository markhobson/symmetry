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
		setText("Button");
		setComponent(new VBox(createButtonBox(), createToggleButtonBox()));
	}

	// private methods --------------------------------------------------------
	
	private static GroupBox createButtonBox()
	{
		GroupBox box = new GroupBox("Button");
		
		box.add(new Label("A Button allows a user to perform an action:"));
		box.add(new Button("Button").onAction(Closures.nop()));
		box.add(new Label("Applications receive notification by registering an ActionListener:"));
		box.add(createCountButton());
		
		box.add(new Label("Buttons can also be disabled to restrict operations:"));

		Button disabledButton = new Button("Format C:");
		disabledButton.setEnabled(false);
		box.add(disabledButton);
		
		box.add(new Label("Tooltips can be added to provide context-sensitive information:"));

		Button toolTipButton = new Button("Cheese");
		toolTipButton.setToolTip("A solid food prepared from the pressed curd of milk, often seasoned and aged.");
		toolTipButton.onAction(Closures.nop());
		box.add(toolTipButton);

		box.add(new Label("Mnemonics can also be used to provide keyboard shortcuts:"));
		
		Button mnemonicButton = new Button("Mnemonic");
		mnemonicButton.setMnemonic('M');
		mnemonicButton.onAction(Closures.nop());
		box.add(mnemonicButton);
		
		return box;
	}
	
	private static Button createCountButton()
	{
		final Button button = new Button("0");
		
		button.onAction(new Command()
		{
			@Override
			public void execute()
			{
				int n = Integer.parseInt(button.getText());
				button.setText(String.valueOf(n + 1));
			}
		});
		
		return button;
	}
	
	private static GroupBox createToggleButtonBox()
	{
		GroupBox box = new GroupBox("ToggleButton");
		
		box.add(new Label("A ToggleButton is like a Button but with a selected state:"));
		box.add(new ToggleButton("ToggleButton"));
		
		box.add(new Label("The selected state can be mutally exclusive by using ToggleButtonGroups:"));
		box.add(new ToggleButtonGroup(Orientation.HORIZONTAL, new ToggleButton("1"), new ToggleButton("2"),
			new ToggleButton("3"), new ToggleButton("4"), new ToggleButton("5")));
		
		return box;
	}
}
