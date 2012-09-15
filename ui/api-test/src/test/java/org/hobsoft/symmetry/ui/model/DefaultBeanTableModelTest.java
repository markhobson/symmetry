/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/model/DefaultBeanTableModelTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

import java.beans.BeanInfo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hobsoft.symmetry.support.bean.BeanUtils;
import org.hobsoft.symmetry.ui.StubBean;
import org.hobsoft.symmetry.ui.event.TableModelEvent;
import org.hobsoft.symmetry.ui.event.TableModelListener;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code DefaultBeanTableModel}.
 * 
 * @author Mark Hobson
 * @see DefaultBeanTableModel
 */
@RunWith(JMock.class)
public class DefaultBeanTableModelTest
{
	// fields -----------------------------------------------------------------
	
	private Mockery mockery = new JUnit4Mockery();
	
	private DefaultBeanTableModel<StubBean> model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new DefaultBeanTableModel<StubBean>(StubBean.class);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newDefaultBeanTableModel()
	{
		assertEquals("beanClass", StubBean.class, model.getBeanInfo().getBeanDescriptor().getBeanClass());
		assertEquals("rows", Collections.emptyList(), model.getRows());
	}
	
	@Test(expected = NullPointerException.class)
	public void newDefaultBeanTableModelWithNullBeanClass()
	{
		new DefaultBeanTableModel<StubBean>(null);
	}
	
	@Test
	public void newDefaultBeanTableModelWithBeanArray()
	{
		StubBean[] beans = createBeans();
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, beans);
		
		assertEquals("beanClass", StubBean.class, model.getBeanInfo().getBeanDescriptor().getBeanClass());
		assertEquals("rows", Arrays.asList(beans), model.getRows());
	}
	
	@Test
	public void newDefaultBeanTableModelWithNullBeanArray()
	{
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, (StubBean[]) null);
		
		assertEquals("beanClass", StubBean.class, model.getBeanInfo().getBeanDescriptor().getBeanClass());
		assertEquals("rows", Collections.emptyList(), model.getRows());
	}
	
	@Test
	public void newDefaultBeanTableModelWithBeanInfo()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(StubBean.class);
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, beanInfo);
		
		assertSame("beanInfo", beanInfo, model.getBeanInfo());
		assertEquals("rows", Collections.emptyList(), model.getRows());
	}
	
	@Test(expected = NullPointerException.class)
	public void newDefaultBeanTableModelWithNullBeanInfo()
	{
		new DefaultBeanTableModel<StubBean>(StubBean.class, (BeanInfo) null);
	}
	
	@Test
	public void newDefaultBeanTableModelWithBeanInfoAndBeanArray()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(StubBean.class);
		StubBean[] beans = createBeans();
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, beanInfo, beans);
		
		assertSame("beanInfo", beanInfo, model.getBeanInfo());
		assertEquals("rows", Arrays.asList(beans), model.getRows());
	}
	
	@Test
	public void newDefaultBeanTableModelWithBeanInfoAndNullBeanArray()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(StubBean.class);
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, beanInfo, (StubBean[]) null);
		
		assertSame("beanInfo", beanInfo, model.getBeanInfo());
		assertEquals("rows", Collections.emptyList(), model.getRows());
	}
	
	@Test
	public void newDefaultBeanTableModelWithBeanCollection()
	{
		List<StubBean> beans = createBeansAsList();
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, beans);
		
		assertEquals("beanClass", StubBean.class, model.getBeanInfo().getBeanDescriptor().getBeanClass());
		assertEquals("rows", beans, model.getRows());
	}
	
	@Test
	public void newDefaultBeanTableModelWithNullBeanCollection()
	{
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, (Collection<StubBean>) null);
		
		assertEquals("beanClass", StubBean.class, model.getBeanInfo().getBeanDescriptor().getBeanClass());
		assertEquals("rows", Collections.emptyList(), model.getRows());
	}
	
	@Test
	public void newDefaultBeanTableModelWithBeanInfoAndBeanCollection()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(StubBean.class);
		List<StubBean> beans = createBeansAsList();
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, beanInfo, beans);
		
		assertSame("beanInfo", beanInfo, model.getBeanInfo());
		assertEquals("rows", beans, model.getRows());
	}
	
	@Test
	public void newDefaultBeanTableModelWithBeanInfoAndNullBeanCollection()
	{
		BeanInfo beanInfo = BeanUtils.getBeanInfo(StubBean.class);
		model = new DefaultBeanTableModel<StubBean>(StubBean.class, beanInfo, (Collection<StubBean>) null);
		
		assertSame("beanInfo", beanInfo, model.getBeanInfo());
		assertEquals("rows", Collections.emptyList(), model.getRows());
	}
	
	@Test
	public void getRowCount()
	{
		model.addRow(createBean("a"));
		
		assertEquals("rowCount", 1, model.getRowCount());
	}
	
	@Test
	public void getBeanAt()
	{
		StubBean bean = createBean("a");
		model.addRow(bean);
		
		assertEquals("bean", bean, model.getBeanAt(0));
	}
	
	@Test
	public void setRowCountGrowsRows()
	{
		StubBean bean = createBean("a");
		model.addRow(bean);
		
		model.setRowCount(2);
		
		assertEquals("rows", Arrays.asList(bean, null), model.getRows());
	}
	
	@Test
	public void setRowCountShrinksRows()
	{
		StubBean bean1 = createBean("a");
		StubBean bean2 = createBean("b");
		model.addRows(bean1, bean2);
		
		model.setRowCount(1);
		
		assertEquals("rows", Collections.singletonList(bean1), model.getRows());
	}
	
	@Test
	public void setRowCountFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setRowCount(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setRowCountWithNegative()
	{
		model.setRowCount(-1);
	}
	
	@Test
	public void clear()
	{
		model.setRows(createBeans());
		
		model.clear();
		
		assertEquals("rows", Collections.emptyList(), model.getRows());
	}
	
	@Test
	public void clearFiresTableChangedEvent()
	{
		model.setRows(createBeans());
		
		model.addTableModelListener(mockTableModelListener(model));
		model.clear();
	}
	
	@Test
	public void addRow()
	{
		StubBean bean = createBean("a");
		model.addRow(bean);
		
		assertEquals("rows", Collections.singletonList(bean), model.getRows());
	}
	
	@Test
	public void addRowFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.addRow(createBean("a"));
	}
	
	@Test
	public void addRowsArray()
	{
		StubBean[] beans = createBeans();
		model.addRows(beans);
		
		assertEquals("rows", Arrays.asList(beans), model.getRows());
	}
	
	@Test
	public void addRowsArrayFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.addRows(createBeans());
	}
	
	@Test
	public void addRowsCollection()
	{
		List<StubBean> beans = createBeansAsList();
		model.addRows(beans);
		
		assertEquals("rows", beans, model.getRows());
	}
	
	@Test
	public void addRowsCollectionFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.addRows(createBeansAsList());
	}
	
	@Test
	public void insertRowAtStart()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		model.insertRow(0, bean2);
		
		assertEquals("rows", Arrays.asList(bean2, bean1), model.getRows());
	}
	
	@Test
	public void insertRowAtMiddle()
	{
		StubBean bean1 = createBean("a");
		StubBean bean2 = createBean("b");
		model.setRows(bean1, bean2);
		
		StubBean bean3 = createBean("x");
		model.insertRow(1, bean3);
		
		assertEquals("rows", Arrays.asList(bean1, bean3, bean2), model.getRows());
	}
	
	@Test
	public void insertRowAtEnd()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		model.insertRow(1, bean2);
		
		assertEquals("rows", Arrays.asList(bean1, bean2), model.getRows());
	}
	
	@Test
	public void insertRowAfterEnd()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		model.insertRow(2, bean2);
		
		assertEquals("rows", Arrays.asList(bean1, null, bean2), model.getRows());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertRowBeforeStart()
	{
		model.insertRow(-1, createBean("a"));
	}
	
	@Test
	public void insertRowFiresTableChangeEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.insertRow(0, createBean("a"));
	}
	
	@Test
	public void insertRowsArrayAtStart()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		StubBean bean3 = createBean("y");
		model.insertRows(0, bean2, bean3);
		
		assertEquals("rows", Arrays.asList(bean2, bean3, bean1), model.getRows());
	}
	
	@Test
	public void insertRowsArrayAtMiddle()
	{
		StubBean bean1 = createBean("a");
		StubBean bean2 = createBean("b");
		model.setRows(bean1, bean2);
		
		StubBean bean3 = createBean("x");
		StubBean bean4 = createBean("y");
		model.insertRows(1, bean3, bean4);
		
		assertEquals("rows", Arrays.asList(bean1, bean3, bean4, bean2), model.getRows());
	}
	
	@Test
	public void insertRowsArrayAtEnd()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		StubBean bean3 = createBean("y");
		model.insertRows(1, bean2, bean3);
		
		assertEquals("rows", Arrays.asList(bean1, bean2, bean3), model.getRows());
	}
	
	@Test
	public void insertRowsArrayAfterEnd()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		StubBean bean3 = createBean("y");
		model.insertRows(2, bean2, bean3);
		
		assertEquals("rows", Arrays.asList(bean1, null, bean2, bean3), model.getRows());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertRowsArrayBeforeStart()
	{
		model.insertRows(-1, createBeans());
	}
	
	@Test
	public void insertRowsArrayFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.insertRows(0, createBeans());
	}
	
	@Test
	public void insertRowsCollectionAtStart()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		StubBean bean3 = createBean("y");
		model.insertRows(0, Arrays.asList(bean2, bean3));
		
		assertEquals("rows", Arrays.asList(bean2, bean3, bean1), model.getRows());
	}
	
	@Test
	public void insertRowsCollectionAtMiddle()
	{
		StubBean bean1 = createBean("a");
		StubBean bean2 = createBean("b");
		model.setRows(bean1, bean2);
		
		StubBean bean3 = createBean("x");
		StubBean bean4 = createBean("y");
		model.insertRows(1, Arrays.asList(bean3, bean4));
		
		assertEquals("rows", Arrays.asList(bean1, bean3, bean4, bean2), model.getRows());
	}
	
	@Test
	public void insertRowsCollectionAtEnd()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		StubBean bean3 = createBean("y");
		model.insertRows(1, Arrays.asList(bean2, bean3));
		
		assertEquals("rows", Arrays.asList(bean1, bean2, bean3), model.getRows());
	}
	
	@Test
	public void insertRowsCollectionAfterEnd()
	{
		StubBean bean1 = createBean("a");
		model.setRows(bean1);
		
		StubBean bean2 = createBean("x");
		StubBean bean3 = createBean("y");
		model.insertRows(2, Arrays.asList(bean2, bean3));
		
		assertEquals("rows", Arrays.asList(bean1, null, bean2, bean3), model.getRows());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertRowsCollectionBeforeStart()
	{
		model.insertRows(-1, createBeansAsList());
	}
	
	@Test
	public void insertRowsCollectionFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.insertRows(0, createBeansAsList());
	}
	
	@Test
	public void setRowsArray()
	{
		StubBean[] beans = createBeans();
		model.setRows(beans);
		
		assertEquals("rows", Arrays.asList(beans), model.getRows());
	}
	
	@Test
	public void setRowsArrayFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setRows(createBeans());
	}
	
	@Test
	public void setRowsCollection()
	{
		List<StubBean> beans = createBeansAsList();
		model.setRows(beans);
		
		assertEquals("rows", beans, model.getRows());
	}
	
	@Test
	public void setRowsCollectionFiresTableChangedEvent()
	{
		model.addTableModelListener(mockTableModelListener(model));
		model.setRows(createBeansAsList());
	}
	
	@Test
	public void setRowsClears()
	{
		model.addRow(createBean("x"));
		
		StubBean bean1 = createBean("a");
		StubBean bean2 = createBean("b");
		StubBean bean3 = createBean("c");
		model.setRows(bean1, bean2, bean3);
		
		assertEquals("rows", Arrays.asList(bean1, bean2, bean3), model.getRows());
	}
	
	// private methods --------------------------------------------------------
	
	private static StubBean createBean(String name)
	{
		StubBean bean = new StubBean();
		bean.setName(name);
		return bean;
	}
	
	private static StubBean[] createBeans()
	{
		StubBean bean1 = createBean("a");
		StubBean bean2 = createBean("b");
		StubBean bean3 = createBean("c");
		
		return new StubBean[] {bean1, bean2, bean3};
	}
	
	private static List<StubBean> createBeansAsList()
	{
		return Arrays.asList(createBeans());
	}
	
	private TableModelListener mockTableModelListener(TableModel model)
	{
		final TableModelListener listener = mockery.mock(TableModelListener.class);
		
		mockery.checking(new Expectations() { {
			// TODO: examine event when equals or matcher implemented
			oneOf(listener).tableChanged(with(any(TableModelEvent.class)));
		} });
		
		return listener;
	}
}
