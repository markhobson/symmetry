/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/TableModelListener.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableModelListener.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface TableModelListener extends EventListener
{
	// TODO: add all table mutation methods
	
	void tableChanged(TableModelEvent event);
}
