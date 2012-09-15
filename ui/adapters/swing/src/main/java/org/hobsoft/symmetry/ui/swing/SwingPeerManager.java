/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing;

import javax.swing.UIManager;

import org.hobsoft.symmetry.swing.AbstractSwingPeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.FileChooser;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.Spacer;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToolBar;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.traversal.PreorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingPeerManager.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
public class SwingPeerManager extends AbstractSwingPeerManager
{
	// constructors -----------------------------------------------------------
	
	public SwingPeerManager()
	{
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		UIManager.put("Tree.showsRootHandles", Boolean.TRUE);
		
		setPeerHandler(Box.class, new SwingBoxPeer(this));
		setPeerHandler(Button.class, new SwingButtonPeer(this));
		setPeerHandler(CheckBox.class, new SwingCheckBoxPeer(this));
		setPeerHandler(ComboBox.class, new SwingComboBoxPeer(this));
		setPeerHandler(Deck.class, new SwingDeckPeer(this));
		setPeerHandler(FileChooser.class, new SwingFileChooserPeer(this));
		setPeerHandler(Grid.class, new SwingGridPeer(this));
		setPeerHandler(GroupBox.class, new SwingGroupBoxPeer(this));
		setPeerHandler(Label.class, new SwingLabelPeer(this));
		setPeerHandler(ListBox.class, new SwingListBoxPeer(this));
		setPeerHandler(Radio.class, new SwingRadioPeer(this));
		setPeerHandler(Spacer.class, new SwingSpacerPeer(this));
//		setPeerHandler(Tab.class, new NoOpPeer());
		setPeerHandler(TabBox.class, new SwingTabBoxPeer(this));
		setPeerHandler(Table.class, new SwingTablePeer(this));
		setPeerHandler(TextBox.class, new SwingTextBoxPeer(this));
		setPeerHandler(ToggleButton.class, new SwingToggleButtonPeer(this));
		setPeerHandler(ToolBar.class, new SwingToolBarPeer(this));
		setPeerHandler(Tree.class, new SwingTreePeer(this));
		setPeerHandler(Window.class, new SwingWindowPeer(this));
	}
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		((Component) component).accept(new PreorderComponentVisitor<Void, RuntimeException>()
		{
			@Override
			protected void visit(Component component, Void parameter)
			{
				SwingPeerManager.super.registerComponent(component);
			}
		}, null);
		
		((Component) component).accept(new PreorderComponentVisitor<Void, RuntimeException>()
		{
			@Override
			protected void visit(Component component, Void parameter)
			{
				initComponent(component);
			}
		}, null);
	}
}
