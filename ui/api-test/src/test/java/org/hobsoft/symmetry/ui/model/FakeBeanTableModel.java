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

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public class FakeBeanTableModel<T> extends AbstractBeanTableModel<T>
{
	// constructors -----------------------------------------------------------
	
	public FakeBeanTableModel(Class<T> beanClass)
	{
		super(beanClass);
	}
	
	public FakeBeanTableModel(Class<T> beanClass, BeanInfo beanInfo)
	{
		super(beanClass, beanInfo);
	}
	
	// TableModel methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount()
	{
		return 0;
	}
	
	// BeanTableModel methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getBeanAt(int row)
	{
		return null;
	}
}
