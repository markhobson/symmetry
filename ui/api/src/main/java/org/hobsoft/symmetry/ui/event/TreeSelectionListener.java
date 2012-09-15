/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/TreeSelectionListener.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import java.util.EventListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreeSelectionListener.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface TreeSelectionListener extends EventListener
{
	void treeSelected(TreeEvent event);
}
