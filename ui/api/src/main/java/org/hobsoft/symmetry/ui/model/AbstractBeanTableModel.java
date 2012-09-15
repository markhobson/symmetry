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
import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.Properties;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public abstract class AbstractBeanTableModel<T> extends AbstractTableModel implements BeanTableModel<T>
{
	// fields -----------------------------------------------------------------
	
	private BeanInfo beanInfo;
	
	// constructors -----------------------------------------------------------
	
	public AbstractBeanTableModel(Class<?> beanClass)
	{
		this(beanClass, BeanUtils.getBeanInfo(beanClass));
	}
	
	public AbstractBeanTableModel(Class<?> beanClass, BeanInfo beanInfo)
	{
		this.beanInfo = checkBeanInfo(beanInfo, beanClass);
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount()
	{
		return beanInfo.getPropertyDescriptors().length;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int column)
	{
		return getPropertyDescriptor(column).getDisplayName();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getColumnClass(int column)
	{
		return getPropertyDescriptor(column).getPropertyType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		T bean = getBeanAt(row);
		
		if (bean == null)
		{
			return null;
		}
		
		PropertyDescriptor property = getPropertyDescriptor(column);
		
		return Properties.get(bean, property);
	}
	
	// public methods ---------------------------------------------------------
	
	public Class<T> getBeanClass()
	{
		return (Class<T>) getBeanClass(beanInfo);
	}
	
	public BeanInfo getBeanInfo()
	{
		return beanInfo;
	}
	
	public void setBeanInfo(BeanInfo beanInfo)
	{
		this.beanInfo = checkBeanInfo(beanInfo, getBeanClass());
	}
	
	// protected methods ------------------------------------------------------
	
	protected PropertyDescriptor getPropertyDescriptor(int column)
	{
		return beanInfo.getPropertyDescriptors()[column];
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends BeanInfo> T checkBeanInfo(T beanInfo, Class<?> beanClass)
	{
		checkNotNull(beanInfo, "beanInfo cannot be null");
		checkArgument(beanInfo.getBeanDescriptor() != null, "beanInfo must have a bean descriptor: %s", beanInfo);
		
		Class<?> thatBeanClass = getBeanClass(beanInfo);
		
		checkArgument(beanClass.isAssignableFrom(thatBeanClass), "beanInfo must have a bean class assignable to %s: %s",
			beanClass.getName(), thatBeanClass.getName());
		
		return beanInfo;
	}
	
	private static Class<?> getBeanClass(BeanInfo beanInfo)
	{
		return (Class<?>) beanInfo.getBeanDescriptor().getBeanClass();
	}
}
