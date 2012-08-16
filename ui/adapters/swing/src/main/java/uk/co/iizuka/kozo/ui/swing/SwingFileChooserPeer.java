/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/SwingFileChooserPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwingFileChooserPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
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
