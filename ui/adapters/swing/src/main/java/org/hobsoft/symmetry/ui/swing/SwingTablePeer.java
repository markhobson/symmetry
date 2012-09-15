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
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.event.TableHeaderListener;
import org.hobsoft.symmetry.ui.model.TableModel;
import org.hobsoft.symmetry.ui.swing.event.TableHeaderMouseListenerAdapter;
import org.hobsoft.symmetry.ui.swing.model.TableModelAdapter;
import org.hobsoft.symmetry.ui.swing.view.TableCellRendererAdapter;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingTablePeer extends AbstractPeerHandler
{
	// constructors -----------------------------------------------------------
	
	public SwingTablePeer(PeerManager peerManager)
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
		return new JScrollPane(new JTable());
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Table component = (Table) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JTable table = (JTable) ((JScrollPane) getPeerManager().getPeer(component)).getViewport().getView();
		
		if (Table.MODEL_PROPERTY.equals(name))
		{
			table.setModel(new TableModelAdapter((TableModel) newValue));
		}
		else if (Table.CELL_RENDERER_PROPERTY.equals(name))
		{
			// TODO: handle add/remove properly
			// FIXME: need to know whether to set the renderer for class or column - currently hardcoded to date..
			table.setDefaultRenderer(Date.class, new TableCellRendererAdapter(component, (TableCellRenderer) newValue));
		}
		else if (Table.HEADER_LISTENER_PROPERTY.equals(name))
		{
			// TODO: handle add/remove properly
			table.getTableHeader().addMouseListener(new TableHeaderMouseListenerAdapter(component,
				(TableHeaderListener) newValue));
		}
		else if (Table.HEADER_RENDERER_PROPERTY.equals(name))
		{
			// TODO: better header rendering
			// TODO: handle add/remove properly
//			table.getTableHeader().setDefaultRenderer(new TableCellRendererAdapter((Table) getResource(),
//				(TableCellRenderer) newValue));
		}
	}
}
