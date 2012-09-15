/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/main/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulComponentHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;

/**
 * 
 * 
 * @author Mark Hobson
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
