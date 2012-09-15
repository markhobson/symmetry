/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingTablePeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: SwingTablePeer.java 99628 2012-03-16 14:32:20Z mark@IIZUKA.CO.UK $
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
