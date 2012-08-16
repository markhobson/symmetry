/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtTextBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.TextBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtTextBoxPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwtTextBoxPeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
	private static class TextModifyListener implements ModifyListener
	{
		// fields -----------------------------------------------------------------
		
		private final TextBox textBox;
		
		// constructors -----------------------------------------------------------
		
		public TextModifyListener(TextBox textBox)
		{
			this.textBox = textBox;
		}
		
		// ModifyListener methods -------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void modifyText(ModifyEvent event)
		{
			Text text = (Text) event.widget;
			textBox.setText(text.getText());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public SwtTextBoxPeer(PeerManager peerManager)
	{
		super(peerManager);
	}
	
	// PeerHandler methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object createPeer(Object component)
	{
		Composite parent = SwtUtils.getComposite((Widget) getPeerManager().getPeer(((Component) component)
			.getParent()));
		
		Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.addModifyListener(new TextModifyListener((TextBox) component));
		return text;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		TextBox component = (TextBox) event.getSource();
		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Text text = (Text) getPeerManager().getPeer(component);
		
		if (TextBox.COLUMNS_PROPERTY.equals(name))
		{
			text.setTextLimit(((Integer) newValue).intValue());
		}
		else if (TextBox.TEXT_PROPERTY.equals(name))
		{
			if (!newValue.equals(text.getText()))
			{
				text.setText((String) newValue);
			}
		}
	}
}
