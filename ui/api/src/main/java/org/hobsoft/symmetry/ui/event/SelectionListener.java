/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/SelectionListener.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SelectionListener.java 88173 2011-05-18 09:24:49Z mark@IIZUKA.CO.UK $
 */
public interface SelectionListener extends EventListener
{
	// TODO: itemUnselected?
	
	void itemSelected(SelectionEvent event);
}
