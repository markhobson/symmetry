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

import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.support.bean.DefaultBeanInfo;
import org.hobsoft.symmetry.ui.StubBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code AbstractBeanTableModel}.
 * 
 * @author Mark Hobson
 * @see AbstractBeanTableModel
 */
public class AbstractBeanTableModelTest
{
	// fields -----------------------------------------------------------------
	
	private AbstractBeanTableModel<StubBean> model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new FakeBeanTableModel<StubBean>(StubBean.class);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newAbstractBeanTableModelWithBeanClass()
	{
		assertEquals("beanClass", StubBean.class, model.getBeanInfo().getBeanDescriptor().getBeanClass());
	}
	
	@Test(expected = NullPointerException.class)
	public void newAbstractBeanTableModelWithNullBeanClass()
	{
		new FakeBeanTableModel<StubBean>(null);
	}
	
	@Test
	public void newAbstractBeanTableModelWithBeanInfo()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(StubBean.class);
		model = new FakeBeanTableModel<StubBean>(StubBean.class, beanInfo);
		
		assertSame("beanInfo", beanInfo, model.getBeanInfo());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newAbstractBeanTableModelWithBeanInfoAndNullBeanDescriptor()
	{
		BeanInfo beanInfo = new DefaultBeanInfo(null, null, null);
		new FakeBeanTableModel<StubBean>(StubBean.class, beanInfo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newAbstractBeanTableModelWithBeanInfoAndUnassignableBeanClass()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(Object.class);
		new FakeBeanTableModel<StubBean>(StubBean.class, beanInfo);
	}
	
	@Test(expected = NullPointerException.class)
	public void newAbstractBeanTableModelWithNullBeanInfo()
	{
		new FakeBeanTableModel<StubBean>(StubBean.class, null);
	}
	
	@Test
	public void getValueAtWhenBeanNull()
	{
		assertNull(model.getValueAt(0, 0));
	}
	
	@Test
	public void setBeanInfo()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(StubBean.class);
		
		model.setBeanInfo(beanInfo);
		
		assertEquals("beanClass", StubBean.class, model.getBeanInfo().getBeanDescriptor().getBeanClass());
	}
	
	@Test(expected = NullPointerException.class)
	public void setBeanInfoWithNull()
	{
		model.setBeanInfo(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setBeanInfoWithNullBeanDescriptor()
	{
		BeanInfo beanInfo = new DefaultBeanInfo(null, null, null);
		
		model.setBeanInfo(beanInfo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setBeanInfoWithUnassignableBeanClass()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(Object.class);
		
		model.setBeanInfo(beanInfo);
	}
}
