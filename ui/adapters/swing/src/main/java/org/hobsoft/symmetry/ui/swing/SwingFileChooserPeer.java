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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingFileChooserPeer extends AbstractPeerHandler
{
	// types ------------------------------------------------------------------
	
	private static class FileChooserPanel extends JPanel implements ActionListener
	{
		// constructors -----------------------------------------------------------
		
		private static final long serialVersionUID = 1L;
		
		// fields -----------------------------------------------------------------
		
		private JTextField textField;
		
		private JButton button;
		
		private JFileChooser fileChooser;
		
		// constructors -----------------------------------------------------------
		
		public FileChooserPanel()
		{
			// TODO: make customisable
			textField = new JTextField();
			textField.setColumns(20);
			add(textField);
			
			button = new JButton();
			// TODO: externalise
			button.setText("Browse...");
			button.addActionListener(this);
			add(button);
			
			fileChooser = new JFileChooser();
		}
		
		// ActionListener methods ---------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				textField.setText(fileChooser.getSelectedFile().getPath());
			}
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public SwingFileChooserPeer(PeerManager peerManager)
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
		return new FileChooserPanel();
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
//		String name = event.getPropertyName();
//		Object oldValue = event.getOldValue();
//		Object newValue = event.getNewValue();
//		JTextField field = (JTextField) getPeer();
	}
}