/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/kozoset/src/main/java/kozoset/KozoSetWindow.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package kozoset;

import kozoset.tab.ButtonTab;
import kozoset.tab.InputTab;
import kozoset.tab.LabelTab;
import kozoset.tab.LayoutTab;
import kozoset.tab.TableTab;
import kozoset.tab.TreeTab;
import kozoset.tab.WizardTab;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.Window;


/**
 * The KozoSet demo.
 * 
 * @author Mark Hobson
 */
public class KozoSetWindow extends Window
{
	// constructors -----------------------------------------------------------
	
	public KozoSetWindow()
	{
		setTitle("KozoSet");
		
		add(
			new Label("KozoSet 0.6.0-SNAPSHOT"),
			new TabBox(
				new LabelTab(),
				new ButtonTab(),
				new InputTab(),
				new LayoutTab(),
				new TableTab(),
				new TreeTab(),
				new WizardTab()
			)
		);
		
		setVisible(true);
	}
}
