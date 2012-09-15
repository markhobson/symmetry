/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/SortableTableModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import org.hobsoft.symmetry.ui.SortOrder;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface SortableTableModel extends TableModel
{
	void setSortColumn(int sortColumn);
	
	void setSortOrder(SortOrder sortOrder);
}
