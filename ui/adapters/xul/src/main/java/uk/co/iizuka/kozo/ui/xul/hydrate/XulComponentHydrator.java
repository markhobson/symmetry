/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/main/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulComponentHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TabBox;
import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.ToggleButton;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: XulComponentHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 */
public class XulComponentHydrator extends CompositeComponentHydrator
{
	// constructors -----------------------------------------------------------
	
	public XulComponentHydrator()
	{
		setDelegate(Box.class, new XulBoxDehydrator<Box>());
		
		setDelegate(Button.class, new XulButtonDehydrator<Button>());
		
		setDelegate(CheckBox.class, new XulCheckBoxDehydrator<CheckBox>());
		
		setDelegate(GroupBox.class, new XulGroupBoxDehydrator<GroupBox>());
		
		setDelegate(Label.class, new XulLabelDehydrator<Label>());
		
		setDelegate(Radio.class, new XulRadioDehydrator<Radio>());
		
		setDelegate(TabBox.class, new XulTabBoxDehydrator<TabBox>());
		
		setDelegate(Tab.class, new XulTabDehydrator<Tab>());
		
		setDelegate(TextBox.class, new XulTextBoxDehydrator<TextBox>());
		
		setDelegate(ToggleButton.class, new XulButtonDehydrator<ToggleButton>());

		setDelegate(Window.class, new XulWindowDehydrator<Window>());
	}
}
