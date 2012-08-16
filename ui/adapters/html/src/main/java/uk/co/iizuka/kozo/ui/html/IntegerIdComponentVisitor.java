/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/IntegerIdComponentVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html;

import java.util.HashMap;
import java.util.Map;

import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.traversal.PreorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: IntegerIdComponentVisitor.java 95168 2011-11-15 17:09:21Z mark@IIZUKA.CO.UK $
 */
class IntegerIdComponentVisitor extends PreorderComponentVisitor<Void, RuntimeException>
{
	// fields -----------------------------------------------------------------
	
	private final Map<Component, Integer> idsByComponent;
	
	private int nextId;
	
	// constructors -----------------------------------------------------------
	
	public IntegerIdComponentVisitor()
	{
		idsByComponent = new HashMap<Component, Integer>();
		nextId = 0;
	}
	
	// PreorderComponentVisitor methods ---------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void visit(Component component, Void parameter)
	{
		int id = nextId++;
		idsByComponent.put(component, id);
	}
	
	// public methods ---------------------------------------------------------
	
	public Map<Component, Integer> getIdsByComponent()
	{
		return idsByComponent;
	}
}
