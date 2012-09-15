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
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.support.bean.model.Bean;
import org.hobsoft.symmetry.support.bean.model.BoundBean;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code BeanBinder}.
 * 
 * @author Mark Hobson
 * @see BeanBinder
 */
@RunWith(JMock.class)
public class BeanBinderTest
{
	// fields -----------------------------------------------------------------
	
	// TODO: use spies rather than mocking concrete classes
	private Mockery mockery = new JUnit4Mockery() { {
		setImposteriser(ClassImposteriser.INSTANCE);
	} };
	
	// push tests -------------------------------------------------------------
	
	@Test
	public void pushAddsPropertyChangeListenerToSource()
	{
		BoundBean source = new BoundBean();
		
		BeanBinder.push(source, "foo", new Bean(), "bar");
		
		assertEquals(1, source.getPropertyChangeListeners("foo").length);
	}
	
	@Test
	public void pushSetsTargetPropertyInitially()
	{
		BoundBean source = new BoundBean();
		source.setFoo("x");

		final Bean target = mockery.mock(Bean.class);
		
		mockery.checking(new Expectations() { {
			oneOf(target).setBar("x");
		} });
		
		BeanBinder.push(source, "foo", target, "bar");
	}
	
	@Test
	public void pushSetsTargetPropertySubsequently()
	{
		BoundBean source = new BoundBean();
		final Bean target = mockery.mock(Bean.class);
		
		mockery.checking(new Expectations() { {
			ignoring(target).setBar(null);
		} });

		BeanBinder.push(source, "foo", target, "bar");
		
		mockery.checking(new Expectations() { {
			oneOf(target).setBar("x");
		} });
		
		source.setFoo("x");
	}
	
	@Test
	public void pushDoesNotSetTargetPropertyToNullTwice()
	{
		BoundBean source = new BoundBean();
		final Bean target = mockery.mock(Bean.class);
		
		mockery.checking(new Expectations() { {
			oneOf(target).setBar(null);
		} });

		BeanBinder.push(source, "foo", target, "bar");
		
		source.setFoo(null);
	}
	
	@Test
	public void pushWithDifferentProperty()
	{
		BoundBean source = new BoundBean();
		final Bean target = mockery.mock(Bean.class);
		
		mockery.checking(new Expectations() { {
			ignoring(target).setBar(null);
		} });

		BeanBinder.push(source, "foo", target, "bar");
		
		source.setBar("x");
	}
	
	@Test(expected = NullPointerException.class)
	public void pushWithNullSourceBean()
	{
		BeanBinder.push(null, "foo", new Bean(), "bar");
	}
	
	@Test(expected = NoSuchEventSetException.class)
	public void pushWithUnboundSourceBean()
	{
		BeanBinder.push(new Bean(), "foo", new Bean(), "bar");
	}
	
	@Test(expected = NullPointerException.class)
	public void pushWithNullSourcePropertyName()
	{
		BeanBinder.push(new BoundBean(), null, new Bean(), "b");
	}
	
	@Test(expected = NoSuchPropertyException.class)
	public void pushWithInvalidSourcePropertyName()
	{
		BeanBinder.push(new BoundBean(), "xxx", new Bean(), "bar");
	}

	@Test(expected = NullPointerException.class)
	public void pushWithNullTargetBean()
	{
		BeanBinder.push(new BoundBean(), "foo", null, "bar");
	}

	@Test(expected = NullPointerException.class)
	public void pushWithNullTargetPropertyName()
	{
		BeanBinder.push(new BoundBean(), "foo", new Bean(), null);
	}
	
	@Test(expected = NoSuchPropertyException.class)
	public void pushWithInvalidTargetPropertyName()
	{
		BeanBinder.push(new BoundBean(), "foo", new Bean(), "xxx");
	}

	@Test(expected = BeanException.class)
	public void pushMismatchedPropertyTypes()
	{
		BeanBinder.push(new BoundBean(), "foo", new Bean(), "baz");
	}
	
	// unpush tests -----------------------------------------------------------
	
	@Test
	public void unpushRemovesPropertyChangeListenerFromSource()
	{
		BoundBean source = new BoundBean();
		Bean target = new Bean();
		
		BeanBinder.push(source, "foo", target, "bar");
		BeanBinder.unpush(source, "foo", target, "bar");
		
		assertEquals(0, source.getPropertyChangeListeners("foo").length);
	}
	
	// pull tests -------------------------------------------------------------
	
	@Test
	public void pullAddsPropertyChangeListenerToSource()
	{
		BoundBean source = new BoundBean();
		
		BeanBinder.pull(new Bean(), "bar", source, "foo");
		
		assertEquals(1, source.getPropertyChangeListeners("foo").length);
	}
	
	@Test
	public void pullSetsTargetPropertyInitially()
	{
		final Bean target = mockery.mock(Bean.class);
		BoundBean source = new BoundBean();
		source.setFoo("x");

		mockery.checking(new Expectations() { {
			oneOf(target).setBar("x");
		} });

		BeanBinder.pull(target, "bar", source, "foo");
	}
	
	@Test
	public void pullSetsTargetPropertySubsequently()
	{
		final Bean target = mockery.mock(Bean.class);
		BoundBean source = new BoundBean();
		
		mockery.checking(new Expectations() { {
			ignoring(target).setBar(null);
		} });
		
		BeanBinder.pull(target, "bar", source, "foo");
		
		mockery.checking(new Expectations() { {
			oneOf(target).setBar("x");
		} });
		
		source.setFoo("x");
	}
	
	@Test
	public void pullDoesNotSetTargetPropertyToNullTwice()
	{
		final Bean target = mockery.mock(Bean.class);
		BoundBean source = new BoundBean();
		
		mockery.checking(new Expectations() { {
			oneOf(target).setBar(null);
		} });
		
		BeanBinder.pull(target, "bar", source, "foo");
		
		source.setFoo(null);
	}
	
	@Test
	public void pullWithDifferentProperty()
	{
		final Bean target = mockery.mock(Bean.class);
		BoundBean source = new BoundBean();
		
		mockery.checking(new Expectations() { {
			ignoring(target).setBar(null);
		} });
		
		BeanBinder.pull(target, "bar", source, "foo");
		
		source.setBar("x");
	}
	
	@Test(expected = NullPointerException.class)
	public void pullWithNullSourceBean()
	{
		BeanBinder.pull(new Bean(), "bar", null, "foo");
	}

	@Test(expected = NoSuchEventSetException.class)
	public void pullWithUnboundSourceBean()
	{
		BeanBinder.pull(new Bean(), "bar", new Bean(), "foo");
	}
	
	@Test(expected = NullPointerException.class)
	public void pullWithNullSourcePropertyName()
	{
		BeanBinder.pull(new Bean(), "bar", new BoundBean(), null);
	}

	@Test(expected = NoSuchPropertyException.class)
	public void pullWithInvalidSourcePropertyName()
	{
		BeanBinder.pull(new Bean(), "bar", new BoundBean(), "xxx");
	}

	@Test(expected = NullPointerException.class)
	public void pullWithNullTargetBean()
	{
		BeanBinder.pull(null, "bar", new BoundBean(), "foo");
	}

	@Test(expected = NullPointerException.class)
	public void pullWithNullTargetPropertyName()
	{
		BeanBinder.pull(new Bean(), null, new BoundBean(), "foo");
	}
	
	@Test(expected = NoSuchPropertyException.class)
	public void pullWithInvalidTargetPropertyName()
	{
		BeanBinder.pull(new Bean(), "xxx", new BoundBean(), "foo");
	}
	
	@Test(expected = BeanException.class)
	public void pullWithMismatchedPropertyTypes()
	{
		BeanBinder.pull(new Bean(), "baz", new BoundBean(), "foo");
	}
	
	// unpull tests -----------------------------------------------------------
	
	@Test
	public void unpullRemovesPropertyChangeListenerFromSource()
	{
		Bean target = new Bean();
		BoundBean source = new BoundBean();
		
		BeanBinder.pull(target, "bar", source, "foo");
		BeanBinder.unpull(target, "bar", source, "foo");
		
		assertEquals(0, source.getPropertyChangeListeners("foo").length);
	}
	
	// bind tests -------------------------------------------------------------
	
	@Test
	public void bindAddsPropertyChangeListenerToSource()
	{
		BoundBean source = new BoundBean();
		
		BeanBinder.bind(source, "foo", new BoundBean(), "bar");
		
		assertEquals(1, source.getPropertyChangeListeners("foo").length);
	}
	
	@Test
	public void bindAddsPropertyChangeListenerToTarget()
	{
		BoundBean target = new BoundBean();
		
		BeanBinder.bind(new BoundBean(), "foo", target, "bar");
		
		assertEquals(1, target.getPropertyChangeListeners("bar").length);
	}
	
	@Test
	public void bindSetsTargetPropertyInitially()
	{
		BoundBean source = new BoundBean();
		source.setFoo("x");
		
		final BoundBean target = mockery.mock(BoundBean.class);
		
		mockery.checking(new Expectations() { {
			ignoring(target).addPropertyChangeListener(with(any(PropertyChangeListener.class)));
			oneOf(target).setBar("x");
		} });

		BeanBinder.bind(source, "foo", target, "bar");
	}
	
	@Test
	public void bindSetsTargetPropertySubsequently()
	{
		BoundBean source = new BoundBean();
		final BoundBean target = mockery.mock(BoundBean.class);
		
		mockery.checking(new Expectations() { {
			ignoring(target).addPropertyChangeListener(with(any(PropertyChangeListener.class)));
			ignoring(target).setBar(null);
		} });
		
		BeanBinder.bind(source, "foo", target, "bar");
		
		mockery.checking(new Expectations() { {
			oneOf(target).setBar("x");
		} });
		
		source.setFoo("x");
	}
	
	@Test
	public void bindDoesNotSetTargetPropertyToNullTwice()
	{
		BoundBean source = new BoundBean();
		final BoundBean target = mockery.mock(BoundBean.class);
		
		mockery.checking(new Expectations() { {
			ignoring(target).addPropertyChangeListener(with(any(PropertyChangeListener.class)));
			oneOf(target).setBar(null);
		} });
		
		BeanBinder.bind(source, "foo", target, "bar");
		
		source.setFoo(null);
	}
	
	@Test
	public void bindSetsTargetPropertyAfterAddingListeners()
	{
		BoundBean source = new BoundBean();
		source.setFoo("x");
		
		final BoundBean target = mockery.mock(BoundBean.class);
		
		final Sequence sequence = mockery.sequence("S");
		mockery.checking(new Expectations() { {
			oneOf(target).addPropertyChangeListener(with(any(PropertyChangeListener.class))); inSequence(sequence);
			oneOf(target).setBar("x"); inSequence(sequence);
		} });

		BeanBinder.bind(source, "foo", target, "bar");
	}
	
	@Test
	public void bindSetsSourceProperty()
	{
		final BoundBean source = mockery.mock(BoundBean.class, "source");
		BoundBean target = new BoundBean();
		
		mockery.checking(new Expectations() { {
			ignoring(source).addPropertyChangeListener(with(any(PropertyChangeListener.class)));
			ignoring(source).getFoo(); will(returnValue(null));
		} });

		BeanBinder.bind(source, "foo", target, "bar");
		
		mockery.checking(new Expectations() { {
			oneOf(source).setFoo("x");
		} });
		
		target.setBar("x");
	}
	
	@Test(expected = NullPointerException.class)
	public void bindWithNullSourceBean()
	{
		BeanBinder.bind(null, "foo", new BoundBean(), "bar");
	}

	@Test(expected = NoSuchEventSetException.class)
	public void bindWithUnboundSourceBean()
	{
		BeanBinder.bind(new Bean(), "foo", new BoundBean(), "bar");
	}
	
	@Test(expected = NullPointerException.class)
	public void bindWithNullSourcePropertyName()
	{
		BeanBinder.bind(new BoundBean(), null, new BoundBean(), "bar");
	}

	@Test(expected = NoSuchPropertyException.class)
	public void bindWithInvalidSourcePropertyName()
	{
		BeanBinder.bind(new BoundBean(), "xxx", new BoundBean(), "bar");
	}

	@Test(expected = NullPointerException.class)
	public void bindWithNullTargetBean()
	{
		BeanBinder.bind(new BoundBean(), "foo", null, "bar");
	}

	@Test(expected = NullPointerException.class)
	public void bindWithNullTargetPropertyName()
	{
		BeanBinder.bind(new BoundBean(), "foo", new BoundBean(), null);
	}
	
	@Test(expected = NoSuchPropertyException.class)
	public void bindWithInvalidTargetPropertyName()
	{
		BeanBinder.bind(new BoundBean(), "foo", new BoundBean(), "xxx");
	}
	
	@Test(expected = BeanException.class)
	public void bindWithMismatchedPropertyTypes()
	{
		BeanBinder.bind(new BoundBean(), "foo", new BoundBean(), "baz");
	}
	
	// unbind tests -----------------------------------------------------------
	
	@Test
	public void unbindRemovesPropertyChangeListenerFromSource()
	{
		BoundBean source = new BoundBean();
		BoundBean target = new BoundBean();
		
		BeanBinder.bind(source, "foo", target, "bar");
		BeanBinder.unbind(source, "foo", target, "bar");
		
		assertEquals(0, source.getPropertyChangeListeners("foo").length);
	}
	
	@Test
	public void unbindRemovesPropertyChangeListenerFromTarget()
	{
		BoundBean source = new BoundBean();
		BoundBean target = new BoundBean();
		
		BeanBinder.bind(source, "foo", target, "bar");
		BeanBinder.unbind(source, "foo", target, "bar");
		
		assertEquals(0, target.getPropertyChangeListeners("bar").length);
	}
}
