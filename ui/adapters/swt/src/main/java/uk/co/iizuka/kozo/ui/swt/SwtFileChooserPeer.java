/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtFileChooserPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.swt;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import uk.co.iizuka.kozo.AbstractPeerHandler;
import uk.co.iizuka.kozo.PeerManager;
import uk.co.iizuka.kozo.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtFileChooserPeer.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public class SwtFileChooserPeer extends AbstractPeerHandler implements SelectionListener
{
	// fields -----------------------------------------------------------------
	
	// TODO: no local fields!
	private Text text;
	
	private FileDialog fileDialog;
	
	// constructors -----------------------------------------------------------
	
	public SwtFileChooserPeer(PeerManager peerManager)
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
		
		fileDialog = new FileDialog(parent.getShell());
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		text = new Text(composite, SWT.SINGLE | SWT.BORDER);
		Button button = new Button(composite, SWT.PUSH);
		// TODO: externalise
		button.setText("Browse...");
		button.addSelectionListener(this);
		return composite;
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
//		Text text = (Text) getPeer();
	}
	
	// SelectionListener methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void widgetDefaultSelected(SelectionEvent event)
	{
		// no-op
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void widgetSelected(SelectionEvent event)
	{
		String path = fileDialog.open();
		
		if (path != null)
		{
			text.setText(path);
		}
	}
}
