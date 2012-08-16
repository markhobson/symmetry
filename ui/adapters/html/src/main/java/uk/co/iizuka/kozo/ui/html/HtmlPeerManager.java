/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/HtmlPeerManager.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html;

import uk.co.iizuka.kozo.state.StatePeerManager;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.traversal.PostorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlPeerManager.java 95187 2011-11-16 10:59:27Z mark@IIZUKA.CO.UK $
 */
public class HtmlPeerManager extends StatePeerManager
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
					HtmlPeerManager.super.registerComponent(component);
				}
			}
		}, null);
	}
}
