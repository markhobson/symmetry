/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/SortableTableModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import uk.co.iizuka.kozo.ui.SortOrder;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SortableTableModel.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface SortableTableModel extends TableModel
{
	void setSortColumn(int sortColumn);
	
	void setSortOrder(SortOrder sortOrder);
}
