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
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.swing.model.TreeModelAdapter;
import org.hobsoft.symmetry.ui.swing.view.TreeCellRendererAdapter;
import org.hobsoft.symmetry.ui.view.TreeNodeRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingTreePeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
//	private class TreeSelectionListenerAdapter extends ListenerAdapter
//		implements javax.swing.event.TreeSelectionListener
//	{
//		public TreeSelectionListenerAdapter(TreeSelectionListener listener)
//		{
//			super(listener);
//		}
//		
//		public void valueChanged(TreeSelectionEvent event)
//		{
//			TreePath path = new TreePathAdapter(event.getPath());
//			TreeEvent treeEvent = new TreeEvent((Tree) getComponent(), path);
//			((TreeSelectionListener) getListener()).treeSelected(treeEvent);
//		}
//	}
	
	// constructors -----------------------------------------------------------
	
	public SwingTreePeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		JTree tree = new JTree();
		ToolTipManager.sharedInstance().registerComponent(tree);
		return new JScrollPane(tree);
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Tree component = (Tree) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JTree tree = (JTree) ((JScrollPane) getPeerManager().getPeer(component)).getViewport().getView();
		
		if (Tree.MODEL_PROPERTY.equals(name))
		{
			tree.setModel(new TreeModelAdapter((TreeModel) newValue));
			
			// Swing always expands the root node
			tree.collapseRow(0);
		}
		else if (Tree.TREE_NODE_RENDERER_PROPERTY.equals(name))
		{
			tree.setCellRenderer(new TreeCellRendererAdapter(component, (TreeNodeRenderer) newValue));
		}
		else if (Tree.TREE_EXPANSION_LISTENER_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.TREE_SELECTION_LISTENER_PROPERTY.equals(name))
		{
			if (oldValue == null)
			{
				// TODO: reimplement
//				tree.addTreeSelectionListener(new TreeSelectionListenerAdapter((TreeSelectionListener) newValue));
			}
			else if (newValue == null)
			{
				// TODO: reimplement
//				tree.removeTreeSelectionListener((javax.swing.event.TreeSelectionListener)
//					getListener((TreeSelectionListener) oldValue));
			}
		}
		else if (Tree.EXPANDED_PATHS_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.SELECTED_PATHS_PROPERTY.equals(name))
		{
			// TODO: implement
		}
		else if (Tree.SELECTION_MODE_PROPERTY.equals(name))
		{
			// TODO: implement
		}
	}
}
