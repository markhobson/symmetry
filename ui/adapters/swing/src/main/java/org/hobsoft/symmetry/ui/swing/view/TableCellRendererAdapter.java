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
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.swing.event.AbstractAdapter;
import org.hobsoft.symmetry.ui.view.TableCellRenderer;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TableCellRendererAdapter extends AbstractAdapter implements javax.swing.table.TableCellRenderer
{
	// constants --------------------------------------------------------------
	
	private static final Border BORDER = new EmptyBorder(1, 1, 1, 1);
	
	// fields -----------------------------------------------------------------
	
	private TableCellRenderer renderer;
	
	private JComponent peer;
	
	private JComponent proxy;
	
	private Border border;
	
	private Border focusBorder;

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
				if (color != null)
				{
					int width = ((Integer) klass.getMethod("getWidth", (Class[]) null).invoke(object, (Object[]) null))
						.intValue();
					int height = ((Integer) klass.getMethod("getHeight", (Class[]) null).invoke(object,
						(Object[]) null)).intValue();
					graphics.setColor(color);
					graphics.fillRect(0, 0, width, height);
				}
			}
			boolean isPublic = Modifier.isPublic(method.getModifiers());
			return (peer == null || !isPublic) ? proxy.invokeSuper(object, args) : proxy.invoke(peer, args);
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public TableCellRendererAdapter(Table table, TableCellRenderer renderer)
	{
		super(table);
		this.renderer = renderer;
		proxy = (JComponent) Enhancer.create(JComponent.class, new PaintMethodInterceptor());
		
		border = BORDER;
		focusBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
	}
	
	// TableCellRenderer methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		boolean hasFocus, int row, int column)
	{
		// TODO: fix
//		TableColumnModel columnModel = table.getColumnModel();
//		int modelColumn = columnModel.getColumn(column).getModelIndex();
//		
//		Component component = renderer.getTableCellComponent((Table) getComponent(), row, modelColumn);
//		peer = (JComponent) AbstractPeer.getWidget(component);
//		
//		peer.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
//		peer.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
//		peer.setBorder(hasFocus ? focusBorder : border);
//		peer.setToolTipText(isSelected ? table.getSelectionBackground().toString() :
//			table.getBackground().toString());
		
		return proxy;
	}
}
