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
package org.hobsoft.symmetry.ui.model;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hobsoft.symmetry.support.bean.BeanUtils;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkNonNegative;
import static org.hobsoft.symmetry.ui.model.Utilities.grow;
import static org.hobsoft.symmetry.ui.model.Utilities.setSize;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public class DefaultBeanTableModel<T> extends AbstractBeanTableModel<T>
{
	// TODO: deleteRow(s)
	// TODO: addColumn(s), insertColumn(s), deleteColumn(s), getColumns, setColumns (all as PropertyDescriptors)
	
	// fields -----------------------------------------------------------------
	
	private final List<T> beans;
	
	// constructors -----------------------------------------------------------
	
	public DefaultBeanTableModel(Class<T> beanClass, T... beans)
	{
		this(beanClass, BeanUtils.getBeanInfo(beanClass), beans);
	}
	
	public DefaultBeanTableModel(Class<T> beanClass, BeanInfo beanInfo, T... beans)
	{
		this(beanClass, beanInfo, nullAsList(beans));
	}
	
	public DefaultBeanTableModel(Class<T> beanClass, Collection<? extends T> beans)
	{
		this(beanClass, BeanUtils.getBeanInfo(beanClass), beans);
	}
	
	public DefaultBeanTableModel(Class<T> beanClass, BeanInfo beanInfo, Collection<? extends T> beans)
	{
		super(beanClass, beanInfo);
		
		if (beans == null)
		{
			beans = Collections.emptySet();
		}
		
		this.beans = new ArrayList<T>();
		
		setRows(beans);
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return beans.size();
	}
	
	// BeanTableModel methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getBeanAt(int row)
	{
		return beans.get(row);
	}
	
	// public methods ---------------------------------------------------------
	
	public void setRowCount(int rows)
	{
		checkNonNegative(rows, "rows");
		
		setSize(beans, rows);
		
		fireTableChangedEvent();
	}
	
	public void clear()
	{
		beans.clear();
		
		fireTableChangedEvent();
	}
	
	public void addRow(T bean)
	{
		beans.add(bean);
		
		fireTableChangedEvent();
	}
	
	public void addRows(T... beans)
	{
		addRows(Arrays.asList(beans));
	}
	
	public void addRows(Collection<? extends T> beans)
	{
		this.beans.addAll(beans);
		
		fireTableChangedEvent();
	}
	
	public void insertRow(int row, T bean)
	{
		checkNonNegative(row, "row");
		
		growRows(row);
		beans.add(row, bean);
		
		fireTableChangedEvent();
	}
	
	public void insertRows(int row, T... beans)
	{
		insertRows(row, Arrays.asList(beans));
	}
	
	public void insertRows(int row, Collection<? extends T> beans)
	{
		checkNonNegative(row, "row");

		growRows(row);
		this.beans.addAll(row, beans);
		
		fireTableChangedEvent();
	}
	
	public List<T> getRows()
	{
		return Collections.unmodifiableList(beans);
	}
	
	public void setRows(T... beans)
	{
		setRows(Arrays.asList(beans));
	}
	
	public void setRows(Collection<? extends T> beans)
	{
		this.beans.clear();
		addRows(beans);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T> List<T> nullAsList(T... elements)
	{
		return (elements != null) ? Arrays.asList(elements) : null;
	}
	
	private void growRows(int rows)
	{
		grow(beans, rows);
	}
}