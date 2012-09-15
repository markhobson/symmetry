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
package org.hobsoft.symmetry.ui.swt;

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
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
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
