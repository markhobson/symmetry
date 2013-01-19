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
package org.hobsoft.symmetry.ui.swt;

import org.eclipse.swt.widgets.Shell;
import org.hobsoft.symmetry.CompositePeerManager;
import org.hobsoft.symmetry.PeerHandler;
import org.hobsoft.symmetry.swt.SwtPeerHandlerDecorator;
import org.hobsoft.symmetry.swt.SwtPeerHandlerUtils;
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
import org.hobsoft.symmetry.ui.Tab;
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
 */
public class SwtPeerManager extends CompositePeerManager
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
				SwtPeerHandlerUtils.initComponent(component, SwtPeerManager.this);
			}
		}, null);
		
		// TODO: reimplement this in SWTWindowPeer - need to handle the visible PCE last
		((SwtWindowPeer) getPeerHandler(Window.class)).setVisible((Shell) getPeer(component), true);
	}
	
	// CompositePeerManager methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPeerHandler(Class<?> componentClass, PeerHandler handler)
	{
		// decorate handler
		handler = new SwtPeerHandlerDecorator(handler);
		
		super.setPeerHandler(componentClass, handler);
	}
}
