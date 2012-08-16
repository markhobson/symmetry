/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingTextBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.TextBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingTextBoxPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwingTextBoxPeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
	// TODO: no locals!
	private class TextFieldDocumentListener implements DocumentListener
	{
		// fields -----------------------------------------------------------------
		
		private final TextBox component;
		
		// DocumentListener methods -----------------------------------------------
		
		public TextFieldDocumentListener(TextBox component)
		{
			this.component = component;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void changedUpdate(DocumentEvent event)
		{
			// never fired with JTextField
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void insertUpdate(DocumentEvent event)
		{
			component.setText(((JTextField) getPeerManager().getPeer(component)).getText());
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void removeUpdate(DocumentEvent event)
		{
			insertUpdate(event);
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public SwingTextBoxPeer(PeerManager peerManager)
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
		JTextField textField = new JTextField();
		textField.getDocument().addDocumentListener(new TextFieldDocumentListener((TextBox) component));
		return textField;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		TextBox textBox = (TextBox) event.getSource();
		String name = event.getPropertyName();
		// Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		JTextField textField = (JTextField) getPeerManager().getPeer(textBox);
		
		if (TextBox.COLUMNS_PROPERTY.equals(name))
		{
			textField.setColumns(((Integer) newValue).intValue());
		}
		else if (TextBox.TEXT_PROPERTY.equals(name))
		{
			if (!newValue.equals(textField.getText()))
			{
				textField.setText((String) newValue);
			}
		}
		else if (TextBox.ENABLED_PROPERTY.equals(name))
		{
			textField.setEnabled((Boolean) newValue);
		}
	}
}
