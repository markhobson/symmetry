/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/AbstractBeanTableModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.beans.BeanInfo;

import org.junit.Before;
import org.junit.Test;

import uk.co.iizuka.common.bean.BeanUtils;
import uk.co.iizuka.common.bean.DefaultBeanInfo;
import uk.co.iizuka.kozo.ui.StubBean;

/**
 * Tests {@code AbstractBeanTableModel}.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractBeanTableModelTest.java 95908 2011-12-05 10:20:51Z mark@IIZUKA.CO.UK $
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
