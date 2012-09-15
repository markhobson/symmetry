/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/DefaultBeanInfo.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultBeanInfo.java 97399 2011-12-30 11:55:45Z mark@IIZUKA.CO.UK $
 */
public class DefaultBeanInfo implements BeanInfo
{
	// constants --------------------------------------------------------------
	
	private static final EventSetDescriptor[] EMPTY_EVENT_SET_DESCRIPTORS = new EventSetDescriptor[0];
	
	private static final PropertyDescriptor[] EMPTY_PROPERTY_DESCRIPTORS = new PropertyDescriptor[0];
	
	private static final MethodDescriptor[] EMPTY_METHOD_DESCRIPTORS = new MethodDescriptor[0];
	
	private static final BeanInfo[] EMPTY_BEAN_INFOS = new BeanInfo[0];

	// fields -----------------------------------------------------------------
	
	private BeanDescriptor beanDescriptor;
	
	private List<EventSetDescriptor> eventSetDescriptors;
	
	private int defaultEventIndex;
	
	private List<PropertyDescriptor> propertyDescriptors;
	
	private int defaultPropertyIndex;
	
	private List<MethodDescriptor> methodDescriptors;
	
	private List<BeanInfo> additionalBeanInfo;

	// constructors -----------------------------------------------------------
	
	public DefaultBeanInfo(Class<?> beanClass)
	{
		// TODO: do we always want to mandate a bean descriptor?  BeanInfo allows it to be null
		this(new BeanDescriptor(beanClass), null, null);
	}
	
	public DefaultBeanInfo(BeanDescriptor beanDescriptor, EventSetDescriptor[] eventSetDescriptors,
		PropertyDescriptor[] propertyDescriptors)
	{
		this(beanDescriptor, eventSetDescriptors, -1, propertyDescriptors, -1, null, null);
	}
	
	public DefaultBeanInfo(BeanDescriptor beanDescriptor, EventSetDescriptor[] eventSetDescriptors,
		int defaultEventIndex, PropertyDescriptor[] propertyDescriptors, int defaultPropertyIndex,
		MethodDescriptor[] methodDescriptors, BeanInfo[] additionalBeanInfo)
	{
		setBeanDescriptor(beanDescriptor);
		setEventSetDescriptors(eventSetDescriptors);
		setDefaultEventIndex(defaultEventIndex);
		setPropertyDescriptors(propertyDescriptors);
		setDefaultPropertyIndex(defaultPropertyIndex);
		setMethodDescriptors(methodDescriptors);
		setAdditionalBeanInfo(additionalBeanInfo);
	}
	
	// BeanInfo methods -------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeanDescriptor getBeanDescriptor()
	{
		return beanDescriptor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventSetDescriptor[] getEventSetDescriptors()
	{
		return toArray(eventSetDescriptors, EMPTY_EVENT_SET_DESCRIPTORS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDefaultEventIndex()
	{
		return defaultEventIndex;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyDescriptor[] getPropertyDescriptors()
	{
		return toArray(propertyDescriptors, EMPTY_PROPERTY_DESCRIPTORS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDefaultPropertyIndex()
	{
		return defaultPropertyIndex;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MethodDescriptor[] getMethodDescriptors()
	{
		return toArray(methodDescriptors, EMPTY_METHOD_DESCRIPTORS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeanInfo[] getAdditionalBeanInfo()
	{
		return toArray(additionalBeanInfo, EMPTY_BEAN_INFOS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getIcon(int iconKind)
	{
		// TODO: allow customisation
		
		return null;
	}
	
	// public methods ---------------------------------------------------------
	
	public void setBeanDescriptor(BeanDescriptor beanDescriptor)
	{
		this.beanDescriptor = beanDescriptor;
	}
	
	public void setEventSetDescriptors(EventSetDescriptor[] eventSetDescriptors)
	{
		this.eventSetDescriptors = toList(eventSetDescriptors);
	}
	
	public void setDefaultEventIndex(int defaultEventIndex)
	{
		this.defaultEventIndex = defaultEventIndex;
	}
	
	public void addPropertyDescriptor(String propertyName, String displayName)
	{
		PropertyDescriptor descriptor = Properties.getDescriptor(getBeanClass(), propertyName);
		
		PropertyDescriptor copiedDescriptor = Properties.copyDescriptor(descriptor);
		copiedDescriptor.setDisplayName(displayName);
		
		addPropertyDescriptor(copiedDescriptor);
	}
	
	public void addPropertyDescriptor(String propertyName)
	{
		PropertyDescriptor descriptor = Properties.getDescriptor(getBeanClass(), propertyName);
		
		addPropertyDescriptor(descriptor);
	}
	
	public void addPropertyDescriptor(PropertyDescriptor propertyDescriptor)
	{
		if (propertyDescriptors == null)
		{
			propertyDescriptors = new ArrayList<PropertyDescriptor>();
		}
		
		propertyDescriptors.add(propertyDescriptor);
	}
	
	public void setPropertyDescriptors(PropertyDescriptor[] propertyDescriptors)
	{
		this.propertyDescriptors = toList(propertyDescriptors);
	}
	
	public void setDefaultPropertyIndex(int defaultPropertyIndex)
	{
		this.defaultPropertyIndex = defaultPropertyIndex;
	}
	
	public void setMethodDescriptors(MethodDescriptor[] methodDescriptors)
	{
		this.methodDescriptors = toList(methodDescriptors);
	}
	
	public void setAdditionalBeanInfo(BeanInfo[] additionalBeanInfo)
	{
		this.additionalBeanInfo = toList(additionalBeanInfo);
	}
	
	// private methods --------------------------------------------------------
	
	private Class<?> getBeanClass()
	{
		return (beanDescriptor != null) ? beanDescriptor.getBeanClass() : null;
	}
	
	private static <T> List<T> toList(T[] array)
	{
		return (array != null) ? Arrays.asList(array) : null;
	}
	
	private static <T> T[] toArray(List<T> list, T[] emptyArray)
	{
		return (list != null) ? list.toArray(emptyArray) : null;
	}
}
