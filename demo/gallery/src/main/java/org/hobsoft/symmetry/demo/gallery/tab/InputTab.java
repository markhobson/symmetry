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

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataSource;

import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.FileChooser;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.HBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.SelectionMode;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.VBox;
import org.hobsoft.symmetry.ui.functor.Command;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class InputTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public InputTab()
	{
		setText("Input");
		setComponent(new VBox(createTextBoxBox(), createCheckBoxBox(), createRadioBox(), createComboBoxBox(),
			createListBoxBox(), createFileChooserBox()));
	}

	// private methods --------------------------------------------------------
	
	private static GroupBox createTextBoxBox()
	{
		GroupBox box = new GroupBox("TextBox");
		box.add(new Label("A TextBox allows simple textual input:"));
		box.add(new TextBox());
		return box;
	}
	
	private static GroupBox createCheckBoxBox()
	{
		GroupBox box = new GroupBox("CheckBox");
		
		box.add(new Label("A CheckBox provides a simple boolean input:"));
		box.add(new CheckBox("CheckBox"));
		
		// TODO: bind checkBox.selected to textBox.enabled when implemented
		CheckBox checkBox = new CheckBox("It can also be linked to other components", true);
		TextBox textBox = new TextBox();
		box.add(new HBox(checkBox, textBox));
		
		return box;
	}

	private static GroupBox createRadioBox()
	{
		GroupBox box = new GroupBox("Radio");
		box.add(new Label("A Radio provides a multiple choice input:"));
		box.add(new ToggleButtonGroup(new Radio("Fruit"), new Radio("Vegetable"), new Radio("Meat")));
		return box;
	}

	private static GroupBox createComboBoxBox()
	{
		GroupBox box = new GroupBox("ComboBox");
		box.add(new Label("A ComboBox allows the user to choose from a list of options:"));
		box.add(new ComboBox<String>("Apple", "Banana", "Grapefruit", "Kiwi", "Lemon", "Orange"));
		box.add(new Label("The options are provided using a ListModel."));
		return box;
	}

	private static GroupBox createListBoxBox()
	{
		GroupBox box = new GroupBox("ListBox");
		
		box.add(new Label("A ListBox also allows the user to choose from a list of options, but in a more verbose "
			+ "manner:"));
		
		ListBox<String> listBox = new ListBox<String>("Apple", "Banana", "Grapefruit", "Kiwi", "Lemon", "Orange");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		box.add(listBox);
		
		box.add(new Label("Similarly, the options are provided using a ListModel."));
		
		return box;
	}

	private static GroupBox createFileChooserBox()
	{
		final FileChooser fileChooser = new FileChooser();
		final Label label = new Label();
		
		Button uploadButton = new Button("Upload");
		
		uploadButton.onAction(new Command()
		{
			@Override
			public void execute()
			{
				label.setText(getLength(fileChooser.getFile()) + " bytes");
			}
		});
		
		GroupBox box = new GroupBox("FileChooser");
		box.add(new Label("A FileChooser can be used to upload files to an application:"));
		box.add(new HBox(fileChooser, uploadButton, label));
		return box;
	}

	private static int getLength(DataSource dataSource)
	{
		try
		{
			return getLength(dataSource.getInputStream());
		}
		catch (IOException exception)
		{
			return -1;
		}
	}
	
	private static int getLength(InputStream in) throws IOException
	{
		byte[] buffer = new byte[1024];
		int length = 0;
		int n;
		
		while ((n = in.read(buffer)) != -1)
		{
			length += n;
		}
		
		return length;
	}
}
