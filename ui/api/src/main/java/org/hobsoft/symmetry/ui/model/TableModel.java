/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/TableModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import org.hobsoft.symmetry.ui.event.TableModelListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableModel.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface TableModel
{
	void addTableModelListener(TableModelListener listener);
	
	void removeTableModelListener(TableModelListener listener);
	
	int getRowCount();
	
	int getColumnCount();
	
	String getColumnName(int column);
	
	Class<?> getColumnClass(int column);
	
	Object getValueAt(int row, int column);
}
