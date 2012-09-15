/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComponentHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.FileChooser;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.HtmlLabel;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.MultiLineLabel;
import org.hobsoft.symmetry.ui.PasswordBox;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.Spacer;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.TextArea;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;

import com.googlecode.jtype.Generic;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.visibleNodes;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class HtmlComponentHydrator extends CompositeComponentHydrator
{
	// constructors -----------------------------------------------------------
	
	public HtmlComponentHydrator()
	{
		setDelegate(Box.class, new ContainerHtmlBoxHydrator<Box>());
		
		setDelegate(Button.class, new AnchorHtmlButtonHydrator<Button>());
		
		setDelegate(CheckBox.class, new HtmlCheckBoxHydrator<CheckBox>());
		
		setDelegate(new Generic<ComboBox<?>>() { /**/ }, new HtmlComboBoxHydrator<ComboBox<?>>());
		
		setDelegate(Deck.class, new HtmlDeckHydrator<Deck>());
		
		setDelegate(FileChooser.class, new HtmlFileChooserHydrator<FileChooser>());
		
		setDelegate(Grid.class, new CellWidthHtmlGridHydrator<Grid>());
		
		setDelegate(GroupBox.class, new HtmlGroupBoxHydrator<GroupBox>(new ContainerHtmlBoxDehydrator<Box>()));

		setDelegate(HtmlLabel.class, new HtmlHtmlLabelHydrator<HtmlLabel>());

		setDelegate(Label.class, new HtmlLabelHydrator<Label>());

		setDelegate(new Generic<ListBox<?>>() { /**/ }, new HtmlListBoxHydrator<ListBox<?>>());

		setDelegate(MultiLineLabel.class, new HtmlMultiLineLabelHydrator<MultiLineLabel>());
		
		setDelegate(PasswordBox.class, new HtmlPasswordBoxHydrator<PasswordBox>());

		setDelegate(Radio.class, new HtmlRadioHydrator<Radio>());

		setDelegate(Spacer.class, new HtmlSpacerHydrator<Spacer>());

		setDelegate(TabBox.class, new HtmlTabBoxHydrator<TabBox>());

		setDelegate(Tab.class, new HtmlTabHydrator<Tab>());

		setDelegate(Table.class, HtmlPagedTableDehydrator.decorate(new HtmlTableHydrator<Table>()));
		
		setDelegate(TextArea.class, new HtmlTextAreaHydrator<TextArea>());

		setDelegate(TextBox.class, new HtmlTextBoxHydrator<TextBox>());
		
		setDelegate(ToggleButton.class, new AnchorHtmlButtonHydrator<ToggleButton>());
		
		setDelegate(Tree.class, visibleNodes(new HtmlTreeHydrator<Tree>()));

		setDelegate(Window.class, new FormHtmlWindowHydrator<Window>(new ContainerHtmlBoxDehydrator<Box>()));
	}
}
