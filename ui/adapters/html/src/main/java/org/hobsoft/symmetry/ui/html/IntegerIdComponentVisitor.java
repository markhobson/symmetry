/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/IntegerIdComponentVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html;

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.PreorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
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
