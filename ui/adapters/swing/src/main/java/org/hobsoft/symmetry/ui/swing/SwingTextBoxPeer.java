/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.swing;

import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.TextBox;

/**
 * 
 * 
 * @author Mark Hobson
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
