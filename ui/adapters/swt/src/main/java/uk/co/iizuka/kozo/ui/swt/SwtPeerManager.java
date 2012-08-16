/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import org.eclipse.swt.widgets.Shell;

import uk.co.iizuka.kozo.swt.AbstractSwtPeerManager;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.FileChooser;
import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.Spacer;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TabBox;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.ToggleButton;
import uk.co.iizuka.kozo.ui.ToolBar;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.traversal.PreorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtPeerManager.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
public class SwtPeerManager extends AbstractSwtPeerManager
{
	// constructors -----------------------------------------------------------
	
	public SwtPeerManager()
	{
		setPeerHandler(Box.class, new SwtBoxPeer(this));
		setPeerHandler(Button.class, new SwtButtonPeer(this));
		setPeerHandler(CheckBox.class, new SwtCheckBoxPeer(this));
		setPeerHandler(ComboBox.class, new SwtComboBoxPeer(this));
		setPeerHandler(Deck.class, new SwtDeckPeer(this));
		setPeerHandler(FileChooser.class, new SwtFileChooserPeer(this));
		setPeerHandler(Grid.class, new SwtGridPeer(this));
		setPeerHandler(GroupBox.class, new SwtGroupBoxPeer(this));
		setPeerHandler(Label.class, new SwtLabelPeer(this));
		setPeerHandler(ListBox.class, new SwtListBoxPeer(this));
		setPeerHandler(Radio.class, new SwtRadioPeer(this));
		setPeerHandler(Spacer.class, new SwtSpacerPeer(this));
		setPeerHandler(Tab.class, new SwtTabPeer(this));
		setPeerHandler(TabBox.class, new SwtTabBoxPeer(this));
		setPeerHandler(Table.class, new SwtTablePeer(this));
		setPeerHandler(TextBox.class, new SwtTextBoxPeer(this));
		setPeerHandler(ToggleButton.class, new SwtToggleButtonPeer(this));
		setPeerHandler(ToolBar.class, new SwtToolBarPeer(this));
		setPeerHandler(Tree.class, new SwtTreePeer(this));
		setPeerHandler(Window.class, new SwtWindowPeer(this));
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
				SwtPeerManager.super.registerComponent(component);
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
		
		// TODO: reimplement this in SWTWindowPeer - need to handle the visible PCE last
		((SwtWindowPeer) getPeerHandler(Window.class)).setVisible((Shell) getPeer(component), true);
	}
}
