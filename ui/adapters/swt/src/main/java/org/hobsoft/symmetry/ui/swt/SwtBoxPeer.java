/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swt/src/main/java/uk/co/iizuka/kozo/ui/swt/SwtBoxPeer.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swt;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Widget;
import org.hobsoft.symmetry.AbstractPeerHandler;
import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Orientation;
import org.hobsoft.symmetry.ui.layout.Length;
import org.hobsoft.symmetry.ui.layout.Length.Unit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SwtBoxPeer.java 99869 2012-03-23 17:26:34Z mark@IIZUKA.CO.UK $
 */
public class SwtBoxPeer extends AbstractPeerHandler
{
	// fields -----------------------------------------------------------------
	
	// TODO: no local fields!
	private final PropertyChangeListener flexListener;

	// types ------------------------------------------------------------------
	
	private class FlexListener implements PropertyChangeListener
	{
		// PropertyChangeListener methods -----------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			recalculateLayoutData((Box) event.getSource());
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public SwtBoxPeer(PeerManager peerManager)
	{
		super(peerManager);
		
		flexListener = new FlexListener();
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
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(newLayout());
		return composite;
	}
	
	// PropertyChangeListener methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		Box box = (Box) event.getSource();
		String name = event.getPropertyName();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Composite composite = (Composite) getPeerManager().getPeer(box);
		
		if (Box.COMPONENTS_PROPERTY.equals(name))
		{
			if (event instanceof IndexedPropertyChangeEvent)
			{
				int index = ((IndexedPropertyChangeEvent) event).getIndex();
				
				if (oldValue == null)
				{
					Component child = (Component) newValue;
					addChild(child);
					recalculateLayoutData(box);
				}
				else if (newValue == null)
				{
					Component child = (Component) oldValue;
					removeChild(child);
					recalculateLayoutData(box);
				}
			}
			else
			{
				// TODO: reimplement
//				for (Control child : composite.getChildren())
//				{
//					child.dispose();
//				}
				
				for (Component child : box)
				{
					addChild(child);
				}
				
				recalculateLayoutData(box);
			}
		}
		else if (Box.ORIENTATION_PROPERTY.equals(name))
		{
			// TODO: recalculate layout data - npe problem
//			recalculateLayoutData(box);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected void addChild(Component child)
	{
		// TODO: reimplement flex support now it's moved from component to box
//		child.addPropertyChangeListener(Component.FLEX_PROPERTY, flexListener);
	}
	
	protected void removeChild(Component child)
	{
		((Widget) getPeerManager().getPeer(child)).dispose();
		
		// TODO: reimplement flex support now it's moved from component to box
//		child.removePropertyChangeListener(Component.FLEX_PROPERTY, flexListener);
	}
	
	protected Layout newLayout()
	{
		FormLayout layout = new FormLayout();
		layout.spacing = 4;
		return layout;
	}
	
	protected void recalculateLayoutData(Box box)
	{
		int totalFlex = 0;
		int childCount = box.getComponentCount();
		
		for (int i = 0; i < childCount; i++)
		{
			totalFlex += getFlex(box.getLayoutData(i).getLength());
		}
		
		// TODO: reuse FormData and FormAttachment instances
		
		int offset = 0;
		boolean isHorizontal = (box.getOrientation() == Orientation.HORIZONTAL);
		Control lastControl = null;
		int lastFlex = -1;
		for (int i = 0; i < childCount; i++)
		{
			Component child = box.get(i);
			Widget peer = (Widget) getPeerManager().getPeer(child);
			if (peer instanceof Control)
			{
				Control control = (Control) peer;
				FormAttachment start = null;
				FormAttachment end = null;
				int flex = getFlex(box.getLayoutData(i).getLength());
				
				if (flex == 0)
				{
					start = (lastControl == null) ? new FormAttachment(0) : new FormAttachment(lastControl);
				}
				else
				{
					start = (lastFlex == -1) ? new FormAttachment(offset, totalFlex, 2)
						: new FormAttachment(lastControl);
					offset += flex;
					end = new FormAttachment(offset, totalFlex, -2);
				}
				
				FormAttachment min = new FormAttachment(0, 2);
				FormAttachment max = new FormAttachment(100, -2);
				
				FormData data = new FormData();
				data.left = isHorizontal ? start : min;
				data.right = isHorizontal ? end : max;
				data.top = isHorizontal ? min : start;
				data.bottom = isHorizontal ? max : end;
				control.setLayoutData(data);
				lastControl = control;
				lastFlex = flex;
			}
		}
		
		// TODO: better?
		Composite composite = (Composite) getPeerManager().getPeer(box);
		composite.setLayout(composite.getLayout());
	}
	
	// private methods --------------------------------------------------------
	
	// TODO: share with kozo-ui-html's FlexUtils
	
	private static int getFlex(Length length)
	{
		return isFlex(length) ? length.getValue() : 0;
	}
	
	private static boolean isFlex(Length length)
	{
		return (length != null) && (length.getUnit() == Unit.FLEX);
	}
}
