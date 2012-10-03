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
package org.hobsoft.symmetry.ui.swing.view;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.swing.event.AbstractAdapter;
import org.hobsoft.symmetry.ui.view.TreeNodeRenderer;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
				Color color = (Color) klass.getMethod("getBackground").invoke(object);
				int width = (Integer) klass.getMethod("getWidth").invoke(object);
				int height = (Integer) klass.getMethod("getHeight").invoke(object);
				
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
