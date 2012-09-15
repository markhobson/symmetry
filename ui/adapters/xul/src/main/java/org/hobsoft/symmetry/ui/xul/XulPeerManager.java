/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/main/java/uk/co/iizuka/kozo/ui/xul/XulPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul;

import org.hobsoft.symmetry.state.StatePeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.PostorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class XulPeerManager extends StatePeerManager
{
	// TODO: move to kozo-ui-common
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		((Component) component).accept(new PostorderComponentVisitor<Void, RuntimeException>()
		{
			@Override
			protected void visit(Component component, Void parameter)
			{
				if (!component.isTransient())
				{
					XulPeerManager.super.registerComponent(component);
				}
			}
		}, null);
	}
}
