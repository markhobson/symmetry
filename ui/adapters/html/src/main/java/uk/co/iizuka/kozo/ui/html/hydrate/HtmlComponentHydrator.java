/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlComponentHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.visibleNodes;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.FileChooser;
import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.HtmlLabel;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.MultiLineLabel;
import uk.co.iizuka.kozo.ui.PasswordBox;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.Spacer;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TabBox;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.TextArea;
import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.ToggleButton;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlComponentHydrator.java 100114 2012-03-30 17:01:39Z mark@IIZUKA.CO.UK $
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
