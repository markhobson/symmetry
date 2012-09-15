/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/swing/src/main/java/uk/co/iizuka/kozo/ui/swing/view/TreeCellRendererAdapter.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.swing.view;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.swing.event.AbstractAdapter;
import org.hobsoft.symmetry.ui.view.TreeNodeRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TreeCellRendererAdapter extends AbstractAdapter implements javax.swing.tree.TreeCellRenderer
{
	// fields -----------------------------------------------------------------
	
	private TreeNodeRenderer renderer;
	
	private JComponent peer;
	
	private JComponent proxy;
	
	private Color backgroundColor;
	
	private Color selectionBackgroundColor;
	
	private Color selectionBorderColor;
	
	private boolean drawDashedFocusIndicator;
	
	private boolean selected;
	
	private boolean hasFocus;

	// types ------------------------------------------------------------------
	
	private class PaintMethodInterceptor implements MethodInterceptor
	{
		// CHECKSTYLE:OFF
		@Override
		public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable
		// CHECKSTYLE:ON
		{
			if ("paint".equals(method.getName()) && args.length == 1)
			{
				Class<?> klass = object.getClass();
				Graphics graphics = (Graphics) args[0];
				Color color = (Color) klass.getMethod("getBackground", (Class[]) null).invoke(object, (Object[]) null);
				int width = ((Integer) klass.getMethod("getWidth", (Class[]) null).invoke(object, (Object[]) null))
					.intValue();
				int height = ((Integer) klass.getMethod("getHeight", (Class[]) null).invoke(object, (Object[]) null))
					.intValue();
				
				if (color != null)
				{
					graphics.setColor(color);
					graphics.fillRect(0, 0, width, height);
				}
				if (hasFocus)
				{
					if (selectionBorderColor != null && (selected || !drawDashedFocusIndicator))
					{
						graphics.setColor(selectionBorderColor);
						graphics.drawRect(0, 0, width - 1, height - 1);
					}
					
					if (drawDashedFocusIndicator)
					{
						graphics.setColor(new Color(~color.getRGB()));
						BasicGraphicsUtils.drawDashedRect(graphics, 0, 0, width, height);
					}
				}
			}
			boolean isPublic = Modifier.isPublic(method.getModifiers());
			return (peer == null || !isPublic) ? proxy.invokeSuper(object, args) : proxy.invoke(peer, args);
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public TreeCellRendererAdapter(Tree tree, TreeNodeRenderer renderer)
	{
		super(tree);
		this.renderer = renderer;
		proxy = (JComponent) Enhancer.create(JComponent.class, new PaintMethodInterceptor());
		
		backgroundColor = UIManager.getColor("Tree.textBackground");
		selectionBackgroundColor = UIManager.getColor("Tree.selectionBackground");
		selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
		drawDashedFocusIndicator = Boolean.TRUE.equals(UIManager.get("Tree.drawDashedFocusIndicator"));
	}
	
	// TreeCellRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
		boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		// TODO: fix
//		this.selected = selected;
//		this.hasFocus = hasFocus;
//		
//		javax.swing.tree.TreePath swingTreePath = tree.getPathForRow(row);
//		TreePath treePath;
//		if (swingTreePath == null || !swingTreePath.getLastPathComponent().equals(value))
//		{
//			// TODO: Swing calls this method to obtain the preferred size for a node before making it visible, which
//			// means the complete TreePath is unobtainable. Currently we use an incomplete TreePath, but need to obtain
//			// the full one somehow..
//			treePath = new TreePath(value);
//		}
//		else
//			treePath = new TreePathAdapter(swingTreePath);
//		
//		Component component = renderer.getTreeNodeComponent((Tree) getComponent(), treePath);
//		peer = (JComponent) AbstractPeer.getWidget(component);
//		
//		peer.setBackground(selected ? selectionBackgroundColor : backgroundColor);
		
		return proxy;
	}
}
