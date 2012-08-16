/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/kozoset/src/main/java/kozoset/tab/InputTab.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package kozoset.tab;

import java.io.IOException;
import java.io.InputStream;

import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.FileChooser;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.HBox;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.SelectionMode;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.ToggleButtonGroup;
import uk.co.iizuka.kozo.ui.VBox;
import uk.co.iizuka.kozo.ui.functor.Command;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: InputTab.java 99566 2012-03-15 16:00:09Z mark@IIZUKA.CO.UK $
 */
public class InputTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public InputTab()
	{
		final FileChooser fileChooser = new FileChooser();
		final Label label = new Label();
		
		Button uploadButton = new Button("Upload").onAction(new Command()
		{
			@Override
			public void execute()
			{
				try
				{
					InputStream in = fileChooser.getFile().getInputStream();
					label.setText(getLength(in) + " bytes");
				}
				catch (IOException exception)
				{
					exception.printStackTrace();
					label.setText("Error");
				}
			}
		});
		
		TextBox textBox = new TextBox();
		
		ListBox<String> listBox = new ListBox<String>("Apple", "Banana", "Grapefruit", "Kiwi", "Lemon", "Orange");
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		
		Box box = new VBox(
			new GroupBox("TextBox",
				new Label("A TextBox allows simple textual input:"),
				new TextBox()
			),
			new GroupBox("CheckBox",
				new Label("A CheckBox provides a simple boolean input:"),
				new CheckBox("CheckBox"),
				new HBox(
					// TODO: bind checkBox.selected to textBox.enabled when implemented
					new CheckBox("It can also be linked to other components", true),
					textBox
				)
			),
			new GroupBox("Radio",
				new Label("A Radio provides a multiple choice input:"),
				new ToggleButtonGroup(
					new Radio("Fruit"),
					new Radio("Vegetable"),
					new Radio("Meat")
				)
			),
			new GroupBox("ComboBox",
				new Label("A ComboBox allows the user to choose from a list of options:"),
				new ComboBox<String>("Apple", "Banana", "Grapefruit", "Kiwi", "Lemon", "Orange"),
				new Label("The options are provided using a ListModel.")
			),
			new GroupBox("ListBox",
				new Label("A ListBox also allows the user to choose from a list of options, but in a more verbose "
					+ "manner:"),
				listBox,
				new Label("Similarly, the options are provided using a ListModel.")
			),
			new GroupBox("FileChooser",
				new Label("A FileChooser can be used to upload files to an application:"),
				new HBox(
					fileChooser,
					uploadButton,
					label
				)
			)
		);
		
		setText("Input");
		setComponent(box);
	}
	
	// private methods --------------------------------------------------------
	
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
